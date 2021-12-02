package com.ruixin.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ruixin.bean.Log;
import com.ruixin.bean.Menu;
import com.ruixin.bean.User;
import com.ruixin.common.utils.CacheNames;
import com.ruixin.common.utils.CacheUtils;
import com.ruixin.common.utils.SpringContextHolder;
import com.ruixin.common.utils.StringUtils;
import com.ruixin.dao.LogDao;
import com.ruixin.dao.MenuDao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.method.HandlerMethod;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * 日志工具类
 */
public class LogUtils {

	private static LogDao logDao = SpringContextHolder.getBean(LogDao.class);
	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);

	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, String title) {
		saveLog(request, null, null, title);
	}

	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, Object handler, Exception ex, String title) {
		User Userinfo = UserUtils.getUser();
		if (Userinfo.getId() != 0) {
			Log log = new Log();
			log.setTitle(title);
			log.setType(ex == null ? Log.TYPE_ACCESS : Log.TYPE_EXCEPTION);
			log.setRemoteAddr(StringUtils.getRemoteAddr(request));
			log.setUserAgent(request.getHeader("user-agent"));
			log.setRequestUrl(request.getRequestURI());
			log.setParams(request.getParameterMap());
			log.setMethod(request.getMethod());
			// 异步保存日志
			new SaveLogThread(log, handler, ex).start();
		}
	}

	/**
	 * 保存日志线程
	 */
	public static class SaveLogThread extends Thread {

		private Log log;
		private Object handler;
		private Exception ex;

		public SaveLogThread(Log log, Object handler, Exception ex) {
			super(SaveLogThread.class.getSimpleName());
			this.log = log;
			this.handler = handler;
			this.ex = ex;
		}

		@Override
		public void run() {
			// 获取日志标题
			if (StringUtils.isBlank(log.getTitle())) {
				String permission = "";
				if (handler instanceof HandlerMethod) {
					Method m = ((HandlerMethod) handler).getMethod();
					RequiresPermissions rp = m.getAnnotation(RequiresPermissions.class);
					permission = (rp != null ? StringUtils.join(rp.value(), ",") : "");
				}
				log.setTitle(getMenuinfoNamePath(log.getRequestUrl(), permission));
			}
			// 如果有异常，设置异常信息
			if (ex == null) {
				log.setException("");
			} else {
				StringWriter stringWriter = new StringWriter();
				ex.printStackTrace(new PrintWriter(stringWriter));
				log.setException(stringWriter.toString());
			}
			// 如果无标题并无异常日志，则不保存信息
			if (StringUtils.isBlank(log.getTitle()) && StringUtils.isBlank(log.getException())) {
				return;
			}
			// 保存日志信息
			log.preInsert();
			logDao.insert(log);
		}
	}

	/**
	 * 获取菜单名称路径（如：系统设置-机构用户-用户管理-编辑）
	 */
	public static String getMenuinfoNamePath(String requestUrl, String permission) {
		String title=(String) CacheUtils.get(CacheNames.MENU_CACHE,requestUrl+permission);
		if (title==null){
			Menu menu=new Menu();
			menu.setPermission(permission);
			menu.setUrl(requestUrl);
			title=menuDao.getMenuinfoNamePath(menu);
			CacheUtils.put(CacheNames.MENU_CACHE,requestUrl+permission,title);
		}
		return title;
	}
}

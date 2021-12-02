package com.ruixin.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruixin.common.utils.StringUtils;
import com.ruixin.util.LogUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;


/**
 * 日志拦截器
 */
public class LogInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
		// 保存日志
		String permission=null;
		if (handler instanceof HandlerMethod) {
			Method m = ((HandlerMethod) handler).getMethod();
			RequiresPermissions rp = m.getAnnotation(RequiresPermissions.class);
			permission = (rp != null ? StringUtils.join(rp.value(), ",") : null);
		}
		if(permission!=null&&permission.startsWith("base")) {
			LogUtils.saveLog(request, handler, ex, null);
		}
	}

}

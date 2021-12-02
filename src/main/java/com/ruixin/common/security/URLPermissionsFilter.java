package com.ruixin.common.security;
import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ruixin.bean.Role;
import com.ruixin.bean.User;
import com.ruixin.util.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.stereotype.Component;

/**
 * 连接权限管理类
 */
@Component("urlPermissionsFilter")
public class URLPermissionsFilter extends PermissionsAuthorizationFilter{

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        String curUrl = getRequestUrl(request);
        if(StringUtils.endsWithAny(curUrl, ".js",".css",".html",".jsp")
                || StringUtils.endsWithAny(curUrl, ".jpg",".png",".gif", ".jpeg")) {
            return true;
        }

       User user=UserUtils.getUser();
       if(user==null||(user!=null&&user.getRoles()==null)){
           return false;
       }
       for (Role role:user.getRoles()){
            if(curUrl.contains(role.getName())){
               return true;
           }
        }
        return false;
    }

    /**
     * 获取当前URL+Parameter
     * @author lance
     * @since  2014年12月18日 下午3:09:26
     * @param request	拦截请求request
     * @return			返回完整URL
     */
    private String getRequestUrl(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest)request;
        String queryString = req.getQueryString();
        queryString = StringUtils.isBlank(queryString)?"": "?"+queryString;
        return req.getRequestURI()+queryString;
    }
}
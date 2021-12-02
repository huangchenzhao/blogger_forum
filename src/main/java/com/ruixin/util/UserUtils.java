package com.ruixin.util;

import com.ruixin.bean.Menu;
import com.ruixin.bean.Role;
import com.ruixin.bean.User;
import com.ruixin.common.utils.CacheNames;
import com.ruixin.common.utils.CacheUtils;
import com.ruixin.common.utils.SpringContextHolder;
import com.ruixin.service.MenuService;
import com.ruixin.service.RoleService;
import com.ruixin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;


public class UserUtils {

    private static UserService userService= SpringContextHolder.getBean(UserService.class);
    private static RoleService roleService=SpringContextHolder.getBean(RoleService.class);
    private static MenuService menuService=SpringContextHolder.getBean(MenuService.class);

    /**
     * 根据ID获取用户
     * @param id
     * @return 取不到返回null
     */
    public static User get(int id){
        User user = (User) CacheUtils.get(CacheNames.USER_CACHE, CacheNames.USER_CACHE_KEY + id);
        if (user ==  null){
            user = userService.get(id);
            if (user == null){
                return null;
            }
            user.setRoles(roleService.findList(new Role(user)));
            CacheUtils.put(CacheNames.USER_CACHE,CacheNames.USER_CACHE_KEY+ user.getId(), user);
        }
        return user;
        /*User user = userService.get(id);
        if (user == null){
            return null;
        }
        return user;*/
    }

    /**
     * 获取当前用户
     * @return 取不到返回 new User()
     */
    public static User getUser(){
        User user = getPrincipal();
        if (user!=null){
            user = get(user.getId());
            if (user != null){
                return user;
            }
            return new User();
        }
        // 如果没有登录，则返回实例化空的User对象。
        return new User();
    }


    /**
     * 获取当前登录者对象
     */
    public static User getPrincipal(){
        try{
            Subject subject = SecurityUtils.getSubject();
            User principal = (User) subject.getPrincipal();
            if (principal != null){
                return principal;
            }
        }catch (UnavailableSecurityManagerException e) {

        }catch (InvalidSessionException e){

        }
        return null;
    }

    /**
     * 获取当前用户的session
     * @return
     */
    public static Session getSession(){
        try{
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null){
                session = subject.getSession();
            }
            if (session != null){
                return session;
            }
        }catch (InvalidSessionException e){

        }
        return null;
    }

    /**
     * 获取当前用户授权菜单
     * @return
     */
    public static List<Menu> getMenuinfoList(){
        User userinfo = getUser();
        List<Menu> menuList=(List<Menu>)CacheUtils.get(CacheNames.MENU_CACHE,CacheNames.MENU_LIST+userinfo.getId());
        if (menuList == null){
            menuList=menuService.findList(null);
            menuList=getRoleMenuList(userinfo.getRoles(),menuList);
            CacheUtils.put(CacheNames.MENU_CACHE,CacheNames.MENU_LIST+userinfo.getId(),menuList);
        }
        return menuList;
    }


    public static List<Menu> getRoleMenuList(List<Role> roles,List<Menu> menus){
        List<Menu> list=new ArrayList<Menu>();
        for (Role role:roles){
            for (Menu menu:menus) {
                String permission=menu.getPermission();
                if(permission!=null){
                    if("sys".equals(role.getName())){
                        if(permission.contains("type")||permission.contains("data")){
                            list.add(menu);
                        }
                    }else{
                        if(permission.contains(role.getName())){
                            list.add(menu);
                        }
                    }
                }
            }
        }
        return list;
    }
}

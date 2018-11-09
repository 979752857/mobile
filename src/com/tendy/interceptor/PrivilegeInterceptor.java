package com.tendy.interceptor;

import com.tendy.dao.DataMapperUtil;
import com.tendy.dao.bean.SysCmsMenu;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PrivilegeInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		// 可以访问的菜单
		List<SysCmsMenu> menuList = (List<SysCmsMenu>) session.getAttribute("menuList");
		if (menuList.isEmpty()) {
			return false;
		}
		// 所有需要权限访问的菜单
		List<SysCmsMenu> allMenuList = DataMapperUtil.getSysCmsMenuMenuListBySelective(null, null);

		String path = request.getServletPath();
		if (_containsPath(menuList, path)) {
			return true;
		} else {
			if (_containsPath(allMenuList, path)) {
				return false;
			} else {
				return true;
			}
		}
	}

	private boolean _containsPath(List<SysCmsMenu> menuList, String path) {
		for (SysCmsMenu menu : menuList) {
			if (path.equals(menu.getMenuUrl())) {
				return true;
			}
		}
		return false;
	}
}

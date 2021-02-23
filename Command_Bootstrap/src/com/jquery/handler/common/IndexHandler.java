package com.jquery.handler.common;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.dto.MenuVO;
import com.jquery.handler.CommandHandler;
import com.jquery.service.MenuService;
import com.jquery.service.MenuServiceImpl;

public class IndexHandler implements CommandHandler {

	private MenuService menuService;
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="common/indexPage";
		
		String mCode = request.getParameter("mCode");
		
		if(mCode ==null) mCode="M000000";
		
		try {
			List<MenuVO> menuList = menuService.getMainMenuList();			
			MenuVO menu = menuService.getMenuByMcode(mCode);
			
			request.setAttribute("menuList",menuList);
			request.setAttribute("menu",menu);
		} catch (SQLException e) {			
			e.printStackTrace();
			url=null;
		}
		
		return url;
	}

}

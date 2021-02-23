package com.jquery.handler.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.dto.MenuVO;
import com.jquery.handler.CommandHandler;
import com.jquery.service.MenuService;
import com.jquery.service.MenuServiceImpl;
import com.jquery.utils.JsonResolver;

public class SubMenuHandler implements CommandHandler {

	private MenuService menuService;
	public void setMenuService(MenuService menuService) {
		this.menuService=menuService;
	}
	
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	String mCode = request.getParameter("mCode");
		
		List<MenuVO> subMenu =null;
		
		try {
			 subMenu=menuService.getSubMenuList(mCode);		
			
			 JsonResolver.view(response, subMenu);
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();			
		}
		return null;
	}

}

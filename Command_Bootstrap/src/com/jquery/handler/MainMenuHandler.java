package com.jquery.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.dto.MenuVO;
import com.jquery.service.MenuService;
import com.jquery.service.MenuServiceImpl;
import com.jquery.utils.JsonResolver;

public class MainMenuHandler implements CommandHandler {

	private MenuService menuService = new MenuServiceImpl();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mCode = request.getParameter("mCode");
		
		if(mCode ==null) mCode="M000000";
		
		try {
			List<MenuVO> menuList = menuService.getMainMenuList();			
			MenuVO menu = menuService.getMenuByMcode(mCode);
				
			Map<String,Object> jsonDataMap = new HashMap<String,Object>();
			jsonDataMap.put("menuList", menuList);
			jsonDataMap.put("menu", menu);
			
			JsonResolver.view(response, jsonDataMap);
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return null;
	}

}

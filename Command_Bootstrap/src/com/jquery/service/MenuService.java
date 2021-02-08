package com.jquery.service;

import java.sql.SQLException;
import java.util.List;

import com.jquery.dto.MenuVO;

public interface MenuService {
	
	List<MenuVO> getMainMenuList()throws SQLException;
	
	List<MenuVO> getSubMenuList(String mCode)throws SQLException;
	
	MenuVO getMenuByMcode(String mCode)throws SQLException;

}








package com.jquery.test;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jquery.dao.MenuDAO;
import com.jquery.dto.MenuVO;

public class MockMenuDAO implements MenuDAO {

	@Override
	public List<MenuVO> selectMainMenu(SqlSession session) throws SQLException {
		List<MenuVO> menuList = new ArrayList<MenuVO>();
		
		for(int i=0;i<10;i++) {
			MenuVO menu = new MenuVO();
			menu.setMcode("M"+new DecimalFormat("000000").format(i));
			menuList.add(menu);
		}
		return menuList;
	}

	@Override
	public List<MenuVO> selectSubMenu(SqlSession session, String mCode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuVO selectMenuByMcode(SqlSession session, String mCode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.jquery.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jquery.dao.MenuDAO;
import com.jquery.dao.MenuDAOImpl;
import com.jquery.dto.MenuVO;
import com.jquery.mybatis.OracleIBatisSqlSessionFactory;

public class MenuServiceImpl implements MenuService {

	private SqlSessionFactory sqlSessionFactory
		= OracleIBatisSqlSessionFactory.getSqlSessionFactory();	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	private MenuDAO menuDAO = new MenuDAOImpl();	
	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	@Override
	public List<MenuVO> getMainMenuList() throws SQLException {
		List<MenuVO> menuList= null;
		
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			menuList =menuDAO.selectMainMenu(session);		
			session.commit();
		}catch(SQLException e) {
			session.rollback();
			e.printStackTrace();
			throw e;
			
		}finally {
			session.close();
		}
		
		return menuList;
	}

	@Override
	public List<MenuVO> getSubMenuList(String mCode) throws SQLException {
		List<MenuVO> menuList= null;
		
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			menuList =menuDAO.selectSubMenu(session, mCode);		
			session.commit();
		}catch(SQLException e) {
			session.rollback();
			e.printStackTrace();
			throw e;
			
		}finally {
			session.close();
		}
		
		return menuList;
	}
	

	@Override
	public MenuVO getMenuByMcode(String mCode) throws SQLException {
		MenuVO menu= null;
		
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			menu =menuDAO.selectMenuByMcode(session, mCode);		
			session.commit();
		}catch(SQLException e) {
			session.rollback();
			e.printStackTrace();
			throw e;
			
		}finally {
			session.close();
		}
		
		return menu;
	
	}
	
}

package com.jquery.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jquery.dto.MenuVO;
import com.jquery.mybatis.OracleIBatisSqlSessionFactory;



public class TestOracleIbatisSqlSessionFactory {
	
	private SqlSessionFactory factory;
	
	@Before
	public void init() {
		factory = OracleIBatisSqlSessionFactory.getSqlSessionFactory();
	}
	
	@Test
	public void testSqlSession() throws Exception{
		SqlSession session = factory.openSession();
		Assert.assertNotNull(session);
		session.close();
		
	}
	
	@Test
	public void testSQL() {
		SqlSession session = factory.openSession();
		MenuVO menu = session.selectOne("Menu-Mapper.selectMenuByMcode","M010100");
		Assert.assertEquals("회원목록", menu.getMname());
	}
		
	
}






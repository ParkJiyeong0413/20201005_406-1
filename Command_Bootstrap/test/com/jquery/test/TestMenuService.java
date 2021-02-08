package com.jquery.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jquery.dto.MenuVO;
import com.jquery.service.MenuServiceImpl;


public class TestMenuService {
	
	private MenuServiceImpl service;
	
	@Before
	public void init() {
		service = new MenuServiceImpl();
		service.setMenuDAO(new MockMenuDAO());
	}
	
	@Test
	public void test()throws Exception{
		List<MenuVO> menuList = service.getMainMenuList();
		
		Assert.assertEquals(10, menuList.size());
		Assert.assertEquals("M000009", menuList.get(9).getMcode());
	}
}








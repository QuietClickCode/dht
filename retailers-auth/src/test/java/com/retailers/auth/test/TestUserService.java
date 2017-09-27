package com.retailers.auth.test;

import com.retailers.auth.dao.MenuMapper;
import com.retailers.auth.entity.Menu;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.base.TestBaseJunit;

import java.util.List;

/**
 * 创建时间：2015-1-27 下午10:45:38
 *
 * @author andy
 * @version 2.2
 */
public class TestUserService extends TestBaseJunit {

	 static final Logger LOGGER = Logger
			.getLogger(TestUserService.class);

	@Autowired
	MenuMapper menuMapper;

	/*
	 * @Test public void testQueryById() { ApplicationContext ac = new
	 * ClassPathXmlApplicationContext( new String[] { "spring.xml",
	 * "spring-mybatis.xml" }); UserService userService = (UserService)
	 * ac.getBean("userService"); UserInfo userInfo =
	 * userService.getUserById(1); System.out.println(userInfo.getUname()); }
	 */

	@Test
	public void test() throws InterruptedException {
//		for (int i = 0; i < 1; i++) {
//			new Thread(new Runnable() {
//				public void run() {
//					testInsert();
////					testQueryById1();
//				}
//			}).start();
//			;
//		}
//		Menu menu = new Menu();
//		menu.setDescription("菜单数据测试");
//		menu.setIsValid(0);
//		menu.setResourse("com.zpaman.cra.menu.index");
//		menu.setUrl("/url/zpaman");
//		menuMapper.saveMenu(menu);
		List<Menu> menu=menuMapper.getAllMenus(32,"");
		System.out.println(JSON.toJSONString(menu));
//		menuMapper.queryMenuById(-1);
		Thread.sleep(100);
//		testQueryById1();

	}
}

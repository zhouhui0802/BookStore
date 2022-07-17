package com.zh.test;

import java.util.UUID;

import org.junit.Test;

import com.zh.bean.User;
import com.zh.service.UserService;
import com.zh.service.impl.UserServiceImpl;

public class UserServiceTest {

	@Test
	public static void testRegist()
	{
		UserService service=new UserServiceImpl();
		boolean success=service.regist(new User(UUID.randomUUID().toString(),"xfzhang5","1234","tt@qq.com"));
		System.out.println(success);
	}
	
	@Test
	public static void testLogin()
	{
		UserService service=new UserServiceImpl();
//		boolean success=service.login("xfzhang5", "1234");
//		System.out.println(success);
	}
	
	public static void main(String args[])
	{
		testLogin();
	}
}

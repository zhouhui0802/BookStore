package com.zh.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

import com.zh.bean.Transacation;

/*
 * 生成代理对象的工具类(用于事务处理)
 */

public class TransacationProxyUtils implements InvocationHandler{

	private Object src;

	private TransacationProxyUtils(Object src) {
		super();
		this.src = src;
	}

	public static <T> T getProxy(Object src) {
		Object proxyObject = Proxy.newProxyInstance(src.getClass().getClassLoader(), src.getClass()
				.getInterfaces(), new TransacationProxyUtils(src));
		
		//System.out.println("动态代理中的执行"+src.getClass().getClassLoader().toString());
		//System.out.println("=================");
		//System.out.println("查看初始化之后的第一个接口"+src.getClass()
				//.getInterfaces()[0]+"数组大小为"+src.getClass()
				//.getInterfaces().length);
		
		//System.out.println("proxy " + proxyObject.getClass().getInterfaces()[0]);
		
		return (T) proxyObject;
	}
	
	@Override
	public Object invoke(Object arg0, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		Connection connection = null;
		Object result = null;
		boolean hasTrans = method.isAnnotationPresent(Transacation.class);
		try {
			//System.out.println("开始执行这个函数TransacationProxyUtils中的invoke");
			connection = JDBCUtils.getConnection();
			
			//System.out.println("hasTrans="+hasTrans);
			
			if(hasTrans) {
				connection.setAutoCommit(false);  // 当false的时候不会自动提交
			}
			
			//System.out.println("method.toString()="+method.toString());
			result = method.invoke(src, args);
			
			if(hasTrans) {
				connection.commit();  //跟前面的connection.setAutoCommit(false);配合使用，防止出现脏数据
			}
			
		} catch (Exception e) {
			if(connection!=null && hasTrans) {
				connection.rollback();  //事务回滚
			}
			throw e;
		} finally {
			JDBCUtils.releaseConn(connection);  //关闭链接
		}
		return result;
	}

}

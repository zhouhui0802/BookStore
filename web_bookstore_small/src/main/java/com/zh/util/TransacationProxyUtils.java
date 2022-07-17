package com.zh.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

import com.zh.bean.Transacation;

/*
 * ���ɴ������Ĺ�����(����������)
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
		
		//System.out.println("��̬�����е�ִ��"+src.getClass().getClassLoader().toString());
		//System.out.println("=================");
		//System.out.println("�鿴��ʼ��֮��ĵ�һ���ӿ�"+src.getClass()
				//.getInterfaces()[0]+"�����СΪ"+src.getClass()
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
			//System.out.println("��ʼִ���������TransacationProxyUtils�е�invoke");
			connection = JDBCUtils.getConnection();
			
			//System.out.println("hasTrans="+hasTrans);
			
			if(hasTrans) {
				connection.setAutoCommit(false);  // ��false��ʱ�򲻻��Զ��ύ
			}
			
			//System.out.println("method.toString()="+method.toString());
			result = method.invoke(src, args);
			
			if(hasTrans) {
				connection.commit();  //��ǰ���connection.setAutoCommit(false);���ʹ�ã���ֹ����������
			}
			
		} catch (Exception e) {
			if(connection!=null && hasTrans) {
				connection.rollback();  //����ع�
			}
			throw e;
		} finally {
			JDBCUtils.releaseConn(connection);  //�ر�����
		}
		return result;
	}

}

package com.zh.factory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/*
 * Service��Dao����Ĺ���
 */
//ͨ����ȡ�����ļ���ͨ�������ʼ������
public final class BeanFactory {

	private BeanFactory(){}
	
	private static Map<String, Object> map = new HashMap<String, Object>();
	
	//����static�鱻���� 
	public static void init() {
		//System.out.println("BeanFactory init()");
	}
	
	//��һ������һ��Servletʱ����--->����Ӧ��
	static {
		try {
			
			//���е�ʱ���ʼ��
			String[] configs = {"dao.properties","service.properties"};
			for(String config : configs) {
				//��ȡdao.properties,service.properties //classpath(�������)
				InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream(config);
				Properties properties = new Properties();
				properties.load(is);
				
				//ȡ����������
				Set<Object> keySet = properties.keySet();
				
				//System.out.println(keySet.size());
				for(Object o : keySet) {
					String interfaceName = (String) o;
					String className = properties.getProperty(interfaceName);
					//System.out.println("ͨ����������з������");
					//System.out.println(interfaceName);
					map.put(interfaceName, Class.forName(className).newInstance());
					//map<UserDao, UserDaoImpl�ĳ�ʼ��>
				}
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static <T> T get(Class<T> type) {
		//System.out.println("type "+type);
		String name = type.getSimpleName();
		//System.out.println("type.getSimpleName()="+name);
		return (T) map.get(name);
	}
	
}

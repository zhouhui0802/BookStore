package com.zh.factory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/*
 * Service和Dao对象的工厂
 */
//通过读取配置文件，通过反射初始化对象
public final class BeanFactory {

	private BeanFactory(){}
	
	private static Map<String, Object> map = new HashMap<String, Object>();
	
	//触发static块被调用 
	public static void init() {
		//System.out.println("BeanFactory init()");
	}
	
	//第一次请求一个Servlet时调用--->部署应用
	static {
		try {
			
			//运行的时候初始化
			String[] configs = {"dao.properties","service.properties"};
			for(String config : configs) {
				//读取dao.properties,service.properties //classpath(类加载器)
				InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream(config);
				Properties properties = new Properties();
				properties.load(is);
				
				//取出所有数据
				Set<Object> keySet = properties.keySet();
				
				//System.out.println(keySet.size());
				for(Object o : keySet) {
					String interfaceName = (String) o;
					String className = properties.getProperty(interfaceName);
					//System.out.println("通过工厂类进行反射调用");
					//System.out.println(interfaceName);
					map.put(interfaceName, Class.forName(className).newInstance());
					//map<UserDao, UserDaoImpl的初始化>
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

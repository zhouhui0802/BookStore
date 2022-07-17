package com.zh.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

/**
 * 在线用户列表的信息类
 * @author xfzhang
 * 
 * 什么时候创建 OnLineUser对象保存到application中?
 *    解决: 监听application对象的创建
 * 什么时候向Map中放数据?
 * 		登陆时:session.setAttribute("USER", user);
 * 	  解决: 监听session添加了属性
 * 什么时候从Map中移除数据?
 *    登出时: session.rmoveAttribute("USER);
 *  解决: 监听session删除了属性  	
 *    session失效
 *  解决: 监听session的死亡
 */

/*
 * 在线用户列表信息实体类
 */
public class OnLineBean {

	// 所有登陆用户的集合(key为ip, value为用户名)
	private Map<String, String> userMap = new HashMap<String, String>();
	
	// 所有未登陆用户ip的集合
	private Set<String> visitorSet = new HashSet<String>();

	public Map<String, String> getUserMap() {
		return userMap;
	}

	public Set<String> getVisitorSet() {
		return visitorSet;
	}

	public void addUser(String ip, String username) {
		userMap.put(ip, username);
		visitorSet.remove(ip);
	}

	public void removeUser(String ip, boolean online) {
		userMap.remove(ip);
		if (online) {
			visitorSet.add(ip);
		} else {
			visitorSet.remove(ip);
		}
	}

	public void addVisitor(String ip) {
		visitorSet.add(ip);
	}

	public int getUserCount() {
		return userMap.size();
	}

	public int gettotalCount() {
		return visitorSet.size()+userMap.size();
	}
}

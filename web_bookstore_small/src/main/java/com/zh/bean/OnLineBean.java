package com.zh.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

/**
 * �����û��б����Ϣ��
 * @author xfzhang
 * 
 * ʲôʱ�򴴽� OnLineUser���󱣴浽application��?
 *    ���: ����application����Ĵ���
 * ʲôʱ����Map�з�����?
 * 		��½ʱ:session.setAttribute("USER", user);
 * 	  ���: ����session���������
 * ʲôʱ���Map���Ƴ�����?
 *    �ǳ�ʱ: session.rmoveAttribute("USER);
 *  ���: ����sessionɾ��������  	
 *    sessionʧЧ
 *  ���: ����session������
 */

/*
 * �����û��б���Ϣʵ����
 */
public class OnLineBean {

	// ���е�½�û��ļ���(keyΪip, valueΪ�û���)
	private Map<String, String> userMap = new HashMap<String, String>();
	
	// ����δ��½�û�ip�ļ���
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

package com.zh.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.zh.bean.*;
/*
 * Web�㴦��Ĺ�����
 */
public class WebUtils {

	/**
	 * �������е�����������ݷ�װ��ָ�����͵Ķ�����, ������
	 * 
	 * @param request
	 * @param beanClass
	 * @return
	 */
	public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {

		T t = null;
		try {
			t = beanClass.newInstance();
			// ��request��ȡ�������������(����ֵ),���õ�t����Ķ�Ӧ������
			Map<String, String[]> map = request.getParameterMap();
			for (String name : map.keySet()) {
				Object value = map.get(name);
				BeanUtils.copyProperty(t, name, value);
			}
			if (request.getParameter("id") == null) {// ����������û��Я��id
				// ����������һ��UUID�����������һ��36λ���ֵ
				BeanUtils.copyProperty(t, "id", UUID.randomUUID().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(t.toString());
		return t;
	}

	/**
	 * ת������/result.jspҳ��, ��ʾָ������ʾ��Ϣ
	 * 
	 * @param request
	 * @param response
	 * @param message
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void forwordMessageUI(HttpServletRequest request, HttpServletResponse response,
			String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

	/**
	 * ת������ָ��·��, ������ָ��������������
	 * 
	 * @param request
	 * @param response
	 * @param path
	 * @param name
	 * @param value
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void forword(HttpServletRequest request, HttpServletResponse response, String path,
			String name, Object value) throws ServletException, IOException {
		request.setAttribute(name, value);
		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * ����û��Ƿ��½
	 * @param request
	 * @param response
	 * @return
	 */
	public static User checkUser(HttpServletRequest request, HttpServletResponse response) {
		User user = null;
		user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie c : cookies) {
					String name = c.getName();
					System.out.println("checkUser�е�name="+name);
					if ("user".equals(name)) {
						//�û���δ��¼
						String value = c.getValue();
						String[] namePassword = value.split(",");
						for(int i=0;i<namePassword.length;i++)
						{
							System.out.println("checkUser�е�namePassword"+i+"��ֵ��"+namePassword[i]);
						}
						user = new User(namePassword[0], namePassword[1], namePassword[2], null);
						//����һ��
						request.getSession().setAttribute("user", user);
						break;
					}
				}
			}
		}
		return user;
	}

	/**
	 * ��½
	 * @param request
	 * @param response
	 * @param user
	 * @param auto
	 */
	public static void login(HttpServletRequest request, HttpServletResponse response, User user, boolean auto) {
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		if (auto) {
			Cookie c = new Cookie("user", user.getId() + "," + user.getUsername() + "," + user.getPassword());
			c.setMaxAge(60 * 60 * 24 * 30);
			c.setPath("/");
			response.addCookie(c);
		}
	}

	/**
	 * �ǳ�
	 * @param request
	 * @param response
	 */
	public static void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		Cookie c = new Cookie("user", "");
		c.setMaxAge(0);
		c.setPath("/");
		response.addCookie(c);
	}

	/**
	 * ��ӵ������ʷ
	 * @param request
	 * @param response
	 * @param book
	 */
	public static void addToHistory(HttpServletRequest request, HttpServletResponse response, Book book) {
		// cookie��key����bookName_currentTime
		// book.jsp?book=JavaWeb
		// ��ȡcookie
		Cookie[] cs = request.getCookies();
		int count =  0;
		Cookie deleteCookie = null;
		
		// ɾ�����е�һ��cookie
		if (cs != null && cs.length > 0) {
			
			for (int i = 0; i < cs.length; i++) {
				
				String name = cs[i].getName();
				
					System.out.println("history�е�name="+name);
					
					if(name.startsWith("book_")) {
						
						if (name.startsWith("book_" + book.getId())) {
							cs[i].setMaxAge(0);
							response.addCookie(cs[i]);  //�Ѿ����ˣ���֮ǰ��cookieɾ��
							break;
						} else {
							count++;
							
							if(deleteCookie==null) {
								deleteCookie = cs[i];
							}
						}
					}
			}
			
			if (count == 4) {
				// ��cookie�����еĵ�һ��ɾ��
				deleteCookie.setMaxAge(0);
				response.addCookie(deleteCookie);
			}
		}
		
		try {
			//����ǵ�һ��  key value  
			Cookie c = new Cookie("book_" + book.getId(), URLEncoder.encode(book.getName(), "utf-8"));
			c.setMaxAge(60 * 60 * 24 * 30);
			response.addCookie(c);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �õ������������ͼ��
	 * @param request
	 * @return
	 */
	public static List<Map<String, String>> getHistoryBooks(HttpServletRequest request) {
		//��������ı���
		List<Map<String, String>> list=null;
		try {
			request.setCharacterEncoding("utf-8");
			
			list = new ArrayList<Map<String, String>>();
			Cookie[] cs = request.getCookies();
			if(cs!=null && cs.length>0) {
				for(int i=cs.length-1;i>=0;i--) {
					Cookie c = cs[i];
					String cName = c.getName();
					System.out.println("cName="+cName);
					if(cName.startsWith("book_")) {
						String id = cName.substring(cName.indexOf("_")+1);
						System.out.println("Decodename="+c.getValue());
						String name = URLDecoder.decode(c.getValue(),"utf-8");//��Ҫ������ָ������
						Map<String, String> map = new HashMap<>();
						map.put("id", id);
						map.put("name", name);
						System.out.println("name="+name);
						list.add(map);
					}
				}
			}
	
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;

	}

	/**
	 * ��������ʷ
	 * @param request
	 * @param resp
	 */
	public static void clearHistory(HttpServletRequest request, HttpServletResponse resp) {
		Cookie[] cs = request.getCookies();
		if(cs!=null && cs.length>0) {
			for(int i=cs.length-1;i>=0;i--) {
				Cookie c = cs[i];
				String cName = c.getName();
				if(cName.startsWith("book_")) {
					c.setMaxAge(0);
					resp.addCookie(c);
				}
			}
		}
	}

	public static Cart getCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("CART");
		if(cart==null) {
			cart = new Cart();
			session.setAttribute("CART", cart);
		}
		return cart;
	}
	
	/*
	 * ���ɶ�����,�����ŵ���� ��λ��+��λ��+��λ��+��λСʱ+����Ķ������� ��:11071012100
	 * 
	 * @param count
	 * 
	 * @return
	 */
	public static String buildOrderid(long count) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHH");// 2011-07-10
		StringBuilder out = new StringBuilder(dateFormat.format(new Date()));
		int length = 15;
		int countLength = String.valueOf(out).length();
		int zeroLength = length - countLength;
		for (int i = 0; i < zeroLength; i++) {
			out.append(0);
		}
		out.append(count);
		return out.toString();
	}
	/**
	 * �ϴ�ͼ��ͼƬ
	 * @param request
	 * @return
	 */
	public static Book uploadBook(HttpServletRequest request) {
		Book book = new Book();

		String id = UUID.randomUUID().toString();
		book.setId(id);

		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> fileItems = upload.parseRequest(request);
			for (FileItem item : fileItems) {
				// �ж��Ƿ�Ϊ��ͨ���ֶ�
				if (item.isFormField()) {
					// ��װbean
					BeanUtils.setProperty(book, item.getFieldName(), item.getString("utf-8"));
				} else {
					// �ļ��ϴ��ֶ�
					// ����ļ��� �ļ����п��������� ���鷳�� �������ǲ�ȡ�Զ����ļ����� ��֤���ظ�
					String filename = item.getName();
					String extName = filename.substring(filename.lastIndexOf("."));
					filename = id + extName;

					// �����Ե�ǰwebӦ�õ�·��
					String webPath = "/images/" + filename;
					// ��þ���·��
					ServletContext servletContext = request.getSession().getServletContext();
					String path = servletContext.getRealPath(webPath);
					// �����ļ�
					File file = new File(path);
					file.getParentFile().mkdirs();
					file.createNewFile();

					// �������Կ�
					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(file);
					Streams.copy(in, out, true);

					// ɾ����ʱ�ļ�
					item.delete();

					// �ļ�·�� ��װ��bean �� imagepath
					BeanUtils.setProperty(book, "imagepath", webPath);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
}
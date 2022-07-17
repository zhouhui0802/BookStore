package com.zh.dao;

import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.*; 

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zh.exception.DBException;
import com.zh.util.JDBCUtils;
import java.util.*;
/*
	����daoʵ����Ļ���
		DML(insert/update/delete)
		DQL(select)
			��ѯ��һ����¼(T)
			��ѯ�õ�������¼(List<T>)
		
		QueryRunner
			ִ��DML: update(Connection conn, String sql, Object... params)
			ִ��DQL: <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params)
				ResultSetHandler: ����һ���ӿ�(��resultSet�е����ݷ�װ�������͵Ķ���)
				����һ������: ��BeanHandler
				����һ������ļ���: ��BeanListHandler
 */

public abstract class BaseDao<T> {

	private Class<T> beanClass;
	private QueryRunner queryRunner = new QueryRunner();

	public BaseDao() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass()
				.getGenericSuperclass();
		beanClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];

	}

	/*
	 * ִ��DML,������ɾ�ĵĲ���
	 */
	public void update(String sql, Object... params) {
		Connection conn = null;

		try {
			conn = JDBCUtils.getConnection();
			queryRunner.update(conn, sql, params);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/*
	 * ִ��DQL,�õ�����
	 */
	public T query(String sql, Object... params) {
		Connection conn = null;
		T t = null;

		try {
			conn = JDBCUtils.getConnection();
			t = queryRunner.query(conn, sql, new BeanHandler<T>(beanClass),
					params);
		} catch (SQLException e) {
			throw new DBException(e);
		}

		return t;
	}

	/*
	 * ִ��DQL,�õ�����ļ���
	 */
	public List<T> queryForList(String sql, Object... params) {

		Connection conn = null;
		List<T> list = new ArrayList<>();

		try {
			conn = JDBCUtils.getConnection();
			list = queryRunner.query(conn, sql, new BeanListHandler<T>(
					beanClass), params);
		} catch (SQLException e) {
			throw new DBException(e);
		}

		return list;
	}
	
	/**
	 *  ִ��DQL, �õ���һ�������еĵ�һ���ֶε�ֵ
	 * @param sql
	 * @param params
	 * @return
	 */
	public <K> K QueryForSingleValue(String sql, Object... params) {
		K k = null;
		Connection conn = null;

		try {
			conn = JDBCUtils.getConnection();
			k = (K) queryRunner.query(conn, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			throw new DBException(e);
		}
		return k;
	}
	
	/**
	 * ��������
	 * @param sql
	 * @param params
	 */
	public void batch(String sql, Object[]... params) {
		Connection conn = null;

		try {
			conn = JDBCUtils.getConnection();
			queryRunner.batch(conn, sql, params);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

}

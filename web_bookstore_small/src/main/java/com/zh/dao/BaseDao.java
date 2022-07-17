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
	所有dao实现类的基类
		DML(insert/update/delete)
		DQL(select)
			查询得一条记录(T)
			查询得到多条记录(List<T>)
		
		QueryRunner
			执行DML: update(Connection conn, String sql, Object... params)
			执行DQL: <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params)
				ResultSetHandler: 它是一个接口(将resultSet中的数据封装不现类型的对象)
				返回一个对象: 用BeanHandler
				返回一个对象的集合: 用BeanListHandler
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
	 * 执行DML,进行增删改的操作
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
	 * 执行DQL,得到对象
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
	 * 执行DQL,得到对象的集合
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
	 *  执行DQL, 得到第一条数据中的第一个字段的值
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
	 * 批量操作
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

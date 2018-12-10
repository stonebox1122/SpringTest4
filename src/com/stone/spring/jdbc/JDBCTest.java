package com.stone.spring.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class JDBCTest {
	
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
	UserDao userDao = ctx.getBean(UserDao.class);
	UserDao1 userDao1 = ctx.getBean(UserDao1.class);
	NamedParameterJdbcTemplate namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);

	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
	
	/**
	 * 执行insert,update,delete
	 */
	@Test
	public void testUpdate() {
		String sql = "update t_user set user_name=? where id=?";
		jdbcTemplate.update(sql,"ccc",2);
	}
	
	/**
	 * 执行批量更新：批量的insert,update,delete
	 * 最后一个参数是Object[]的List类型：因为修改一条记录需要一个Object数组，多条记录就需要多个Object数组
	 */
	@Test
	public void testBatchUpdate() {
		String sql = "insert into t_user(id,user_name,password) values(?,?,?)";
		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[] {3,"ccc","ccc"});
		batchArgs.add(new Object[] {4,"ddd","ddd"});
		batchArgs.add(new Object[] {5,"eee","eee"});
		batchArgs.add(new Object[] {6,"fff","fff"});
		batchArgs.add(new Object[] {7,"ggg","ggg"});
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	/**
	 * 从数据库中获取一条记录，实际得到对应的一个对象
	 * 注意不是调用queryForObject(String sql, Class<User> requiredType, Object... args)方法！ 
	 * 而需要调用queryForObject(String sql, RowMapper<User> rowMapper, Object... args)方法
	 * 1、其中的RowMapper指定如何去映射结果集的行，常用的实现类为BeanPropertyRowMapper
	 * 2、使用SQL列的别名完成列名和类的属性名的映射，例如last_name lastName
	 * 3、不支持级联属性，JdbcTemplate到底是一个JDBC的小工具，而不是ORM框架
	 */
	@Test
	public void testQueryObject() {
		String sql = "select id,user_name userName,password from t_user where id=?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		System.out.println(user);
	}
	
	/**
	 * 查到实体类的集合
	 * 注意调用的不是queryForList方法
	 */
	@Test
	public void testQueryForList() {
		String sql = "select id,user_name userName,password from t_user where id>?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		List<User> users = jdbcTemplate.query(sql, rowMapper, 3);
		System.out.println(users);
	}
	
	/**
	 * 获取单个列的值，或做统计查询
	 * 使用queryForObject(String sql, Class<Long> requiredType)方法
	 */
	@Test
	public void testQueryForObject2() {
		String sql = "select count(id) from t_user";
		long count = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}
	
	@Test
	public void testUserDao() {
		System.out.println(userDao.get(1));
	}
	
	@Test
	public void testUserDao1() {
		System.out.println(userDao1.get(1));
	}
	
	/**
	 * 可以为参数起名字。
	 * 1、好处：若有多个参数，则不用再去对应位置，直接对应参数名，便于维护
	 * 2、缺点：较为麻烦。
	 */
	@Test
	public void testNamedParameterJdbcTemplate() {
		String sql = "insert into t_user(id,user_name,password) values(:id,:username,:password)";
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", 8);
		paramMap.put("username", "hh");
		paramMap.put("password", "hh");
		
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	@Test
	public void testNamedParameterJdbcTemplate2() {
		String sql = "insert into t_user(id,user_name,password) values(:id,:userName,:password)";
		User user = new User(9, "iii", "iii");
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}
}
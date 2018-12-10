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
	Aaa aaa = ctx.getBean(Aaa.class);
	Aaa2 aaa2 = ctx.getBean(Aaa2.class);
	
	@Test
	public void testAaa() {
		String sql = "select id from aaa";
		String id = jdbcTemplate.queryForObject(sql, String.class);
		System.out.println(id);
	}
	
	@Test
	public void testAaa2() {
		String sql = "select id from aaa";
		double id = jdbcTemplate.queryForObject(sql, double.class);
		System.out.println(id);
	}

	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

}
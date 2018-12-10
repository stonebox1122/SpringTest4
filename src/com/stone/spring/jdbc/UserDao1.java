package com.stone.spring.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 不推荐使用JdbcDaoSupport，而推荐直接使用JDBCTemplate作为Dao类的成员变量
 * @author lei.shi445
 *
 */
@Repository
public class UserDao1 extends JdbcDaoSupport {
	
	@Autowired
	public void setDataSource2(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	public User get(Integer id) {
		String sql = "select id,user_name userName,password from t_user where id=?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		User user = getJdbcTemplate().queryForObject(sql, rowMapper, id);
		return user;
	}
}

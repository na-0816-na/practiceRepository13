package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

import lombok.RequiredArgsConstructor;
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	
	private final JdbcTemplate jdbcTemplate;

	@Override
	public void add(User user) {

		String sql =
				" INSERT INTO m_user " +
				" (e_mail, pass_word, user_name) " +
				" VALUES (?, ?, ?) ";

			jdbcTemplate.update(sql, user.getEMail(),
									 user.getPassWord(),
									 user.getUserName());
									 
	}

	@Override
	public User findByEmail(String email) {
		
		String sql = "SELECT * FROM m_user WHERE e_mail = ?";
		
		List<Map<String, Object>> list
		= jdbcTemplate.queryForList(sql, email);
	
			List<User> result = new ArrayList<User>();
			for (Map<String, Object> one : list) {
				User user = new User();
				user.setUserId((Integer) one.get("user_id"));
				user.setEMail((String) one.get("e_mail"));
				user.setPassWord((String) one.get("pass_word"));
				user.setUserName((String) one.get("user_name"));
				result.add(user);
}
			if (result.isEmpty()) {
		        return null;
	}
			return result.get(0);
}
}











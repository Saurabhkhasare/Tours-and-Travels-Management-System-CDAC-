package com.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojo.User;

public interface UserDao extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}

package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DAO.UserDao;
import com.app.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

	@Override
	public User findUser(User user) {
		
		String email= user.getEmail();
		User newUser= userDao.findByEmail(email);
		if(newUser!=null) {
			if (newUser.getPassword().equals(user.getPassword())) {
				return newUser;
			}
		}
		return null;
	}

	
	
}

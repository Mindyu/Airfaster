package com.air.dao;

import com.air.domain.User;

public interface IUserDao {
	public void add(User user);
	public void delete(User user);
	public boolean check(User user);
	public boolean checkUsername(String username);
}

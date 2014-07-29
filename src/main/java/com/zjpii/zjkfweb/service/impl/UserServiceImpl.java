package com.zjpii.zjkfweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zjpii.zjkfweb.dao.IBaseDao;
import com.zjpii.zjkfweb.entity.TUser;
import com.zjpii.zjkfweb.service.UserService;




/**
 * UserServiceImpl
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<TUser> implements UserService {

	/**
	 * 重写该方法,覆盖注解
	 */
	@Autowired
	@Qualifier("userDao")
	public void setDao(IBaseDao<TUser> dao) {
		super.setDao(dao);
	}

	public void getUser() {
		
		System.out.println(this.getEntity(1)+"-----");
	}
	
	
	

	
}

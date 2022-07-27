package com.spring.boardweb.service.user;

import com.spring.boardweb.entity.User;

public interface UserService {
	
	void join(User user); //user 앤티티객체 임포트
	
	User idCheck(String userId);
}

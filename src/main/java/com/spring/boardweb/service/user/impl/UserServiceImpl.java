package com.spring.boardweb.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boardweb.entity.User;
import com.spring.boardweb.repository.UserRepository;
import com.spring.boardweb.service.user.UserService;

@Service 
public class UserServiceImpl implements UserService{
								//UserService 상속받기
	@Autowired //객체 자동생성
	UserRepository userRepository;
	//UserRepository userRepository = new UserRepository(); 를 오토와이어드 한것.
	
	@Override //@Override 는 생략가능
	public void join(User user) { //User는 entity.User.java를 의미 user는 내가 만든 매개변수명
		userRepository.save(user); //user를 저장하면 자동으로 insert구문이 생성.
	} //MySql에 저장시켜주는 메소드

	@Override
	public User idCheck(String userId) { //User.java에 있는 변수 중 userId의 내용을 문자열로 받음
		if(userRepository.findById(userId).isPresent()) { //만약 save했던 아이디를 찾아서 존재(참 또는 거짓)가 참이라면
			return userRepository.findById(userId).get(); //그 아이디를 객체(T)로 반환한다. 
		} else {
			return null;
		}
	}
}

package com.spring.boardweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boardweb.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	//JpaRepository: 부모객체
	User findByUserId(String UserId);
}

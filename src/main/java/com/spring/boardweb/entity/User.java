package com.spring.boardweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.Data;

//이 객체가 엔티티 객체임을 선언(필수설정)
@Entity
//테이블 어노테이션은 필수값은 아님. 객체명과 테이블명이 다를 때 테이블명을 지정하기 위해서 사용
@Table(name="T_USER")
@Data
@DynamicInsert
public class User {
	//키값으로 지정
	//객체안에 있는 모든 속성 값들이 column으로 생성됨
	@Id
	private String userId;
	
	//column 어노테이션은 필수값은 아니지만 column에 옵션을 주기위해 사용
	//nullable: NOT NULL 설정
	//name: column명 설정
	//length: column에 저장되는 데이터의 최대 길이 설정
	//unique: 해당 column을 유니크로 설정
	@Column(nullable=false)
	private String userPw;
	
	@Column(nullable=false)
	private String userNm;
	
	@Column(nullable=false)
	private String userMail;
	
	private String userTel;
	
	@Column
	@ColumnDefault("'ROLE_USER'")
	private String role;
	
	//@Transient: 해당 속성값은 column으로 생성하지 않음
	@Transient
	private String userAddress;
}

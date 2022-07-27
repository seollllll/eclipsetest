package com.spring.boardweb.dto;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@Data   -> 모든 어노테이션을 다 만들 수 있음
public class TestDTO {
	private int idx;
	private String testStr;
	private List<String> testList;
	private Map<String, String> testMap;
	
}

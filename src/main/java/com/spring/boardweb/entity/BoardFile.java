package com.spring.boardweb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="T_Board_File")
@Data
@IdClass(BoardFileId.class)  //다중 pk 설정, 다중 pk가 모여있는 객체를 만들어서 연결해줌
public class BoardFile {
	@Id  //foreign key 설정. <매핑관계가 1:1매핑일때 @OneToOne><다:1 매핑일때 @ManyToOne><1:다 매핑일때 @OneToMany>
		 //					<BoardFile 입장에서 board_seq는 다:1>
	@ManyToOne
	@JoinColumn(name="BOARD_SEQ") //어떤 컬럼으로 조인할지
	private Board board;
	
	@Id
	private int fileSeq;
	
	private String originalFileName;
	
	private String fileName;
	
	private String filePath;
	
}

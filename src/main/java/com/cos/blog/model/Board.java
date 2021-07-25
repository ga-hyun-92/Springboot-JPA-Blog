package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false,length = 100)
	private String title;
	
	@Lob	//대용량 데이터
	private String content;	//섬머노트 라이브러리 사용=> <html>태그가 섞여 디자인 됨

	@ColumnDefault("0")
	private int count;	
	//DB는 오브젝트를 저장할 수 없어서 FK키 사용, 그러나 자바는 오브젝트 가능
	//그러나 ORM 사용하면? 오브젝트 저장 가능
	//연관관계 Many=Board, One=User;한명의 user는 여러개의 게시글 쓸 수 있다
	@ManyToOne	(fetch=FetchType.EAGER)								
	@JoinColumn(name = "userId")	//필드값 userId로 표시될거다!
	private User user;
	//mappedBy가 적혀있으면? 연관관계의 주인이아니다(FK키가 아니다)
	//FK는 Reply테이블에 적힌 Bord가 FK키다
	//즉,나는 그냥 Board select할때, JOIN문을 통해서 값을 얻기위한 거에요!
	@OneToMany(mappedBy = "board",fetch=FetchType.EAGER)	
	private List<Reply> reply;
	@CreationTimestamp
	private Timestamp createDate;
}

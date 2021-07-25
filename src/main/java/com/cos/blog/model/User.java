package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder					//빌더 패턴!!
//ORM-> Java(다른언어) object -> 테이블로 매핑해주는 것
@Entity					//User클래스가 MySQL에 테이블이 생성됨
//@DynamicInsert	//insert할때, null인 필드 제외
public class User {
	@Id		//Primary key
	//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다
	//즉, DB를 오라클로 사용하면? 시퀀스로, MySQL을 사용하면? auto increment로
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;							//시퀀스, auto_increment
	
	@Column(nullable = false,length = 30,unique = true)
	private String username;		//아이디
	
	//길이를 넉넉히 100으로 잡음?? 나중에 비번을 Hash로 변경해서 암호화할것임
	@Column(nullable = false,length = 100)
	private String password;
	@Column(nullable = false,length = 50)
	private String email;
	
	//String보다, Enum으로 쓰는게 좋다=>domain을 정할 수 있음
	//회원가입했을때, 이 사람의 roll은? admin,user,manager
	//각각의 roll에 따라 권한설정 가능
	//@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)		//DB는 RoleType이 없기 때문
	private RoleType role;	//RoleType : USER,ADMIN
	
	//시간이 자동 입력됨
	@CreationTimestamp		
	private Timestamp createDate;
}

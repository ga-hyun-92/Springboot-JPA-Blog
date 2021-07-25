package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//사용자가 요청->응답(html파일) 
//@Controller

//사용자가 요청->응답(data)
@RestController
public class HttpControllerTest {

	private static final String TAG="HttpControllerTest: ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//Member m=new Member(1, "ssar", "1234", "ssar@naver.com");
		//Member클래스에서 생성자 위에 @Builder 어노테이션 적으면 됨
		//해당 변수를 순서대로 넣지 않아도 된다. 
		Member m=Member.builder().username("ssar").password("1234").email("ssar@naver.com").build();
		System.out.println(TAG+"getter : "+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter : "+m.getUsername());
		return "lombokTest 완료";
		
	}
	
	//인터넷 브라우저 요청은 무조건 get요청만 가능!
	// http://localhost:8080/http/get=>select
	@GetMapping("/http/get")
	public String getTest(Member m) {   // 또는 @RequestParams int id
		
		return "get 요청: "+m.getPassword()+","+m.getId()+","+m.getUsername();
	}
	
	// http://localhost:8080/http/post=>insert
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post 요청: "+m.getUsername();
	
	}
	
	// http://localhost:8080/http/put=>update
	@PutMapping("/http/put")  //text/plain, application/json
	public String putTest() {
		return "put 요청";
	}
	
	// http://localhost:8080/http/delete=>delete
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}

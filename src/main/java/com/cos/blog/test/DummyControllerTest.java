package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//html파일이 아니라 data를 리턴해주는 controller
@RestController
public class DummyControllerTest {

	private final UserRepository userRepository;
	
	//final로 선언하고 아래 생성자 만들어서 DI해준다 
	//=>아래 생성자를 생성하거나! 아니면! @Autowired를 해준다!
	public DummyControllerTest(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping("/dummy/join")
	public String join(User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 완료";
	}
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	//{id}=>주소로 파라미터를 전달받을 수 있음
	//http://localhost:8080/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//user/4를 찾으면 내가 DB에서 못찾아 오게되면 user가 null이 될 거 아냐?
		//그럼 return null이 리턴되잖아.. 그럼 프로그램에 문제가 있지 않겠니
		//Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해!!
		//.get()은?? null이 될일 없으니, 바로 User객체를 꺼내서 리턴해
		//.orElseThrow()는?? 있으면 해당 객체 리턴, 없으면? exception날려!
		User user =userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id :"+id);
			}
		});
		//요청 : 웹브라우저
		//user 객체 = 자바 오브젝트
		//변환(웹브라우저가 이해할 수 있는 데이터) -> json(Gson라이브러리)
		//스프링부트에선? MessageConverter라는 애가 응답시에 자동 작동
		//만약 자바 오브젝트를 리턴하게 되면? MessageConverter가 Jackson 라이브러리를 호출해서
		//user오브젝트를 json으로 변환해서 브라우저에게 던져줌
		return user;
	}
	
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser=userRepository.findAll(pageable);
		List<User> users=pagingUser.getContent();
		return users;
	}
	
	//save함수는 id를 전달하지 않으면? insert해주고
	//save함수는 id를 전달하면? 해당 id에 대한 데이터가 있으면, update해주고
	//save함수는 id를 전달하면? 해당 id에 대한 데이터가 없으면, insert를 해요
	//email, password만 수정!
	@Transactional		//save하지 않아도, update됨, 함수 종료시 자동 commit이 된다
	@PutMapping("/dummy/user/{id}")
	public String updateUser(@PathVariable int id,@RequestBody User requestUser) {//json데이터 요청=>Java Object(MessageConverter의 Jackson라이브러리가 변환해서 java object로 받아줘요)
		User userEntity=userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다");
		});
		
		userEntity.setEmail(requestUser.getEmail());
		userEntity.setPassword(requestUser.getPassword());
		//userRepository.save(userEntity);
		
		//더티 체킹 : 변경을 감지해서 DB에 update해준다 @Transactional	기능
		return "update 완료";
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다";
		}
		return "삭제 완료";
	}
}

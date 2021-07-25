package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;

//JSP로 보면, DAO와 같은 역할(Data Access Object)
//@Repository없어도 자동으로 bean등록이 된다.
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	//JPA Naming전략
	//SELECT *FROM user WHERE username= ?1 AND password= ?2
	User findByUsernameAndPassword(String name,String password);

//	@Query(value="SELECT *FROM user WHERE username= :username AND password= :password",nativeQuery = true)
//	User mLogin(String username,String password);

	
}

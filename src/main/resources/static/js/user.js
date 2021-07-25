let index={
	init: function(){
		$("#btn-save").on("click",()=>{	//function(){}대신, ()=>{}사용한 이유? this를 바인딩하기 위해!!
			this.save();
		});
		$("#btn-login").on("click",()=>{
			this.login();
		})
	},
	
	save: function(){
		//alert('user의 save함수 호출됨');
		let data={
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		//console.log(data);
		
		//ajax통신을 이용해서 3개 데이터를 json으로 변경하여 insert요청!
		//ajax호출시 default가 비동기 호출
		$.ajax({
			//회원가입 수행요청
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), //http body데이터(자바스크립트 타입)를 json으로 변경
			contentType: "application/json; charset=utf-8",	//body데이터가 어떤 타입인지(MIME)
			dataType: "json"		//요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열임. 생긴게 json이라면? 자바스크립트 오브젝트로 변경
		}).done(function(resp){
			alert("회원가입 완료");
			location.href="/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});	
	},
	login: function(){
		//alert('user의 save함수 호출됨');
		let data={
			username: $("#username").val(),
			password: $("#password").val()
		};
		//ajax통신을 이용해서 3개 데이터를 json으로 변경하여 insert요청!
		//ajax호출시 default가 비동기 호출
		$.ajax({
			//회원가입 수행요청
			type: "POST",
			url: "/blog/api/user/login",
			data: JSON.stringify(data), //http body데이터(자바스크립트 타입)를 json으로 변경
			contentType: "application/json; charset=utf-8",	//body데이터가 어떤 타입인지(MIME)
			dataType: "json"		//요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열임. 생긴게 json이라면? 자바스크립트 오브젝트로 변경
		}).done(function(resp){
			alert("로그인 완료");
			location.href="/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});	
	}
}

index.init();
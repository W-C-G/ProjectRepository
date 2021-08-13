<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	
	<title>Recommend Platform</title>
	<style>
	body {
		margin: 0;
	}
	#yummy{
		padding: 20px;
	}

	#adapt_center{
		text-align: center;
	}
	
	td{
		overflow: auto;
	}
	h2{
		padding: 10px;
	}
	h3{
		text-align: center;
	}
	#loginForm, #joinForm{
		width: 600px;
		border: 2px dotted black;
		padding: 20px;
		margin: 0 auto;
		margin-top: 20px;
	}
	
	#btn_set{
		margin: 0 auto;	
	}
	#title{
		text-decoration: none;
	}
	
	#pagination{
		width: fit-content;
		padding: 10px;
		margin: 10px auto;
	}
	</style>
	<script
	  src="https://code.jquery.com/jquery-3.6.0.min.js"
	  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	  crossorigin="anonymous">
	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
	</script>
	<script>
	$(function(){
		$('div#joinForm').css('display', 'none');
		$('div#loginForm').css('display', 'none');
	});
	function showJoinForm(){
		$('div#joinForm').css('display', 'block');
		$('div#loginForm').css('display', 'none');
		$('div#restListForm').css('display', 'none');
	}
	function showLoginForm(){
		$('div#loginForm').css('display', 'block');
		$('div#joinForm').css('display', 'none');
		$('div#restListForm').css('display', 'none');
	}
	function backJoinForm(){
		$('div#joinForm').css('display', 'none');
		$('div#restListForm').css('display', 'block');
	}
	function backLoginForm(){
		$('div#joinForm').css('display', 'none')
		$('div#restListForm').css('display', 'block');;
	}
	function insertUser(){
		if(!confirm('가입하시겠어요?')) return false;
		
		var obj = {};
		obj.id = $('#id').val();
		obj.pwd = $('#pwd').val();
		obj.name = $('#name').val();
		obj.phone = $('#phone').val();
		obj.email = $('#email').val();
		
		$.ajax({
			url: "/home/join",
			method: "post",
			data: obj,
			dataType: 'text',
			
			success: function(res){
				alert(eval(res) ? "회원 가입에 성공했습니다!" : "회원 가입에 실패했습니다!");
				location.href = "/home";
			},
			
			error: function(xhr, status, error){
				alert(status+":"+error);
			}
		})
		return false;
	}
	
	function loginUser(){
		var obj = {};
		obj.id = $('#loginId').val();
		obj.pwd = $('#loginPwd').val();
		
		$.ajax({
			url: "/home/login",
			method: "post",
			data: obj,
			dataType: 'text',
			
			success: function(res){
				if(eval(res)){
					alert("로그인에 성공했습니다!");
					location.href = "/home";
				}
				else{
					alert("로그인에 실패했습니다.");
				}
				
			},
			error: function(xhr, status, error){
				alert(status+":"+error);
			}
		
		})
	}
	
	function logOut(){
		var obj = {};
		$.ajax({
			url: "/home/logout",
			method: "post",
			data: obj,
			dataType: 'text',
			
			success: function(res){
				if(eval(res)){
					alert("로그아웃에 성공했습니다!");
					location.href = "/home";
				}
				else{
					alert("로그아웃에 실패했습니다.");
				}
				
			},
			error: function(xhr, status, error){
				alert(status+":"+error);
			}
		
		})
	}
	
	function showMyList(){
		var obj = {};
		
		$.ajax({
			url: "/home/mylistcheck",
			method: "post",
			data: obj,
			dataType: 'text',
			
			success: function(res){
				if(eval(res)){
					alert("mylist 이동에 성공했습니다!");
					location.href = "/home/mylist/1";
				}
				else{
					alert("로그인을 해주세요");
				}
				
			},
			error: function(xhr, status, error){
				alert(status+":"+error);
			}
		
		})
	}
	</script>

</head>
<body>
	<header>
	<nav class = "navbar navbar-expand-lg navbar-light bg-info">
		<div class = "container-fluid">
			<h2><a id="title" class = "text-white" href = "/home"><b>Recommendation Platform</b></a></h2>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
     			<span class="navbar-toggler-icon"></span>
    		</button>
    		<div class="collapse navbar-collapse" id="navbarSupportedContent">
    			<ul class = "navbar-nav me-auto">
					<li class = "nav-item">
						<a class = "nav-link" href = "/home/list/1">All List</a>
					</li>
					<li class = "nav-item">
						<a class = "nav-link" onclick ="showMyList()">My List</a>
					</li>
					<li class = "nav-item">
						<a class = "nav-link" href = "/home/qa/1">Q&A</a>
					</li>
				</ul>
				<form class="d-flex" action="/home/searchlist/1" method = "post">
					<input class="form-control me-2" type = "search" placeholder = "Search..." name="searchtext"></input>
					<button class="btn btn-outline-success" type = "button">Search</button>
				</form>
				<form class="d-flex">
					<c:if test="${empty uid}">
						<button class="btn btn-outline-success" type = "button" onclick = "showLoginForm()">Log_In</button>
					</c:if>
					<c:if test="${not empty uid}">
						<button class="btn btn-outline-success" type = "button" onclick = "location.href = '/home/mypage'">My_Page</button>
					</c:if>
					<c:if test="${empty uid}">
						<button class="btn btn-outline-success" type = "button" onclick = "showJoinForm()">Sign_Up</button>
					</c:if>
					<c:if test="${not empty uid}">
						<input type = "hidden" value="${uid}" id="logOutID"></input>
						<button class="btn btn-outline-success" type = "button" onclick = "logOut()">Log_Out</button>
					</c:if>
				</form>
					
    		</div>
		</div>
	</nav>
	</header>
	
	<!-- Restaurant List -->
	<div id = "restListForm">
		<h2 align = center><b>Restaurant List</b></h2>
		<table class = "table">
			<thead>
				<tr id = "adapt_center">
					<th scope = "col">Num</th>
					<th scope = "col">Name</th>
					<th scope = "col">Field</th>
					<th scope = "col">Score</th>
					<th scope = "col">Address</th>
				</tr>
			<thead>
			<c:forEach var = "row" items="${pageInfo.list}">
				<tr>
					<th scope = "row" id = "adapt_center">${row.res_num}</th>
					<td id = "adapt_center">${row.res_name}</td>
					<td id = "adapt_center">${row.res_subclass}</td>
					<td id = "adapt_center">${row.res_score}</td>
					<td>${row.res_address}</td>
				</tr>
			</c:forEach>
		</table>
		<div id = "pagination">
		<c:forEach var="i" items = "${pageInfo.navigatepageNums}">
			<c:choose>
				<c:when test="${i==pageInfo.pageNum}">
					[${i}]
				</c:when>
				<c:otherwise>
					[<a href="/home/list/${i}">${i}</a>]
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
	</div>
	
	<!-- Join Form -->
	<div id = "joinForm">
		<form action = "/home/join" method = "post">
			<h3><b>[Join Us!]</b></h3>
	  		<div class = "mb-3">
	  			<label for="id" class = "form-label">ID</label>
	  			<input type = "email" class="form-control" id = "id" required></input>
	  		</div>
	  		<div class = "mb-3">
	  			<label class = "form-label" for ="pwd">PW</label>
	  			<input type = "password" class="form-control" id = "pwd" required></input>
	  		</div>
	  		<div class = "mb-3">
	  			<label class = "form-label" for ="name">Name</label>
	  			<input class="form-control" type = "text" id = "name" required></input>
	  		</div>
	  		<div class = "mb-3">
	  			<label class = "form-label" for = "phone">Phone </label>
	  			<input type = "text" class="form-control" id = "phone" required></input>
	  		</div>
	  		<div class = "mb-3">
	  			<label class = "form-label" for ="email">Email</label>
	  			<input type = "text" class="form-control" id = "email" required></input>
	  		</div>  		
	  		<div id = "btn_set">
	  			<button type ="button" class = "btn btn-primary" onclick = "insertUser()">완료</button>
	  			<button type = "button" class="btn btn-light" onclick = "backJoinForm()">뒤로</button>
	  		</div>
		</form>
	</div>
	
	<!-- Login Form -->
	<div id = "loginForm">
		<form action = "/home/login" method = "post">
			<h3><b>[Login Form]</b></h3>
	  		<div class = "mb-3">
	  			<label for="loginId" class="form-label">ID </label>
	  			<input type = "email" class="form-control" id = "loginId" required></input>
	  		</div>
	  		<div class ="mb-3">
	  			<label for ="loginPwd" class="form-label">Password </label>
	  			<input type = "password" class="form-control" id = "loginPwd" required></input>
	  		</div>		
	  		<div id = "btn_set">
	  			<button type ="button" class="btn btn-primary" onclick = "loginUser()">로그인</button>
	  			<button type = "button" class="btn btn-light" onclick = "backJoinForm()">뒤로</button>
	  		</div>
		</form>
	</div>
	
	<div id = "yummy" align = center><img src="/yummy_guys.jpg" width="200px" class="rounded mx-auto d-block"></div>	
	
</body>
</html>
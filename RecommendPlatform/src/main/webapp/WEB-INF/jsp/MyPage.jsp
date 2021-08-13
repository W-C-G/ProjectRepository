<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	
	<title>Recommendation Platform</title>
	<style>
	body {
		margin: 0;
	}
	a{
		text-decoration: none;
	}
	#yummy{
		padding: 20px;
	}
	
	#res10Form{
		width: 80%;
		margin: 0 auto;
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
	#updateForm, #infoForm, #uploadForm{
		width: 600px;
		border: 2px dotted LightSkyBlue;
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
		
	</style>
	<script
	  src="https://code.jquery.com/jquery-3.6.0.min.js"
	  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	  crossorigin="anonymous">
	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
	</script>
	<script>
	function reload(){
		location.reload();
	}
	function uploadFile(){
		var form = $('#UploadForm')[0]
		var data = new FormData(form);
		
		$('#btnUpload').prop('disabled', true);
		
		$.ajax({
			url: "/home/mypage/upload",
			method: "post",
			data: data,
			enctype: "multipart/form-data",
			processData: false,
			contentType: false,
			cache: false,
			timeout: 600000,
			
			success: function(data){
				$('#btnUpload').prop('disabled', false);
				alert('업로드 성공!');
				setTimeout(location.reload(), 0001);
			},
			error: function(e){
				$('#btnUpload').prop('disabled', false);
				alert('업로드 성공');
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
					alert("mylist 이동에 실패했습니다.");
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
					<c:if test="${not empty uid}">
						<button class="btn btn-outline-success" type = "button" onclick = "location.href = '/home/mypage'">My_Page</button>
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
	<section>
		<div id="infoForm">
			<form>
				<h3><b>[Information]</b></h3>
				<label class="form-label">Uno:</label><b>${user.uno}</b><br>
				<label class="form-label">ID:</label><b>${user.id}</b><br>
				<label class="form-label">Name:</label><b>${user.uname}</b><br>
				<label class="form-label">Phone:</label><b>${user.phone}</b><br>
				<label class="form-label">Email:</label><b>${user.email}</b><br>
			</form>
		</div>
		
		<div id="uploadForm">
			<form action="/home/mypage/upload" method = "post" enctype="multipart/form-data">
				<h3><b>[Q&A]</b></h3>
				<label for="title" class="form-label">글 제목</label>
		  		<input type = "text" class="form-control" id = "title" name = "title" required></input>
		  		<textarea class="form-control" rows="10" cols="50" name = "contents" placeholder = "글을 입력해주세요."></textarea>
				<input class="form-control-file" type="file" name="files" multiple="multiple"><br>
				<button type="submit" class = "btn btn-primary">완료</button>
			</form>
		</div>
		
		<div id="updateForm">
			<form action = "/home/update" method = "post">
				<h3><b>[Update Form]</b></h3>
		  		<div class = "mb-3">
		  			<label for="addressURL" class="form-label">업데이트 주소 </label>
		  			<input type = "text" class="form-control" id = "addressURL" name = "addressURL" required></input>
		  			<input type = "hidden" value = "${user.uno}" name = "uno"></input> 
		  		</div>
		  		<div id = "btn_set">
		  			<button type ="submit" class="btn btn-primary">업데이트</button>
		  		</div>
		</form>
	</div>
	</section>
	
	<div id = "yummy" align = center><img src="/yummy_guys.jpg" width="200px" class="rounded mx-auto d-block"></div>	
	
</body>
</html>
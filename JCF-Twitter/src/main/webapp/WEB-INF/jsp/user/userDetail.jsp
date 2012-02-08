<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>User Registration</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
	<style type="text/css">
	body {
		padding-top: 60px;
		padding-bottom: 40px;
	}
	
	.userImage {
		display: inline-block;
		width: 80px;
		height: 80px;
		vertical-align: top;
		padding-left: 70px;
		margin-right: 6px;
		margin-bottom: 10px;
		border: none;
		cursor: pointer;
	}
	p.submit{
		padding-left: 170px;
	}
	p.submit2{
		padding-left: 110px;
	}
	</style>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</head>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> 
			<a class="brand" href="<%=request.getContextPath()%>/login">JCF-Twitter</a>
			<div class="nav-collapse">
				<ul class="nav">
					<li><a class="active" href="<%=request.getContextPath()%>/tweet">트위터</a></li>
					<li><a href="<%=request.getContextPath()%>/user/findUsers">사용자관리</a></li>
					<li><a href="http://about.me/jeado">Contact</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>
<body>
<div class="container">
	<h2>${user.userName} 상세 조회</h2>
	<br>
	<div class="row">
	<div class="span3 offset1 well">
		<h3>사용자 저장 및 수정</h3>
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/user/updateUser">
			<label>아이디</label><input type="text" class="disabled" name="userId" value="${user.userId}" >
			<label>이름	</label><input type="text" class="idle" name="userName" value="${user.userName}" >
			<label>email</label><input type="text" class="idle" name="userEmail" value="${user.userEmail}" >
			<label>현재 사용자 사진</label><img class="userImage" src='<%=request.getContextPath()%>/file/fileView/${user.userId}'>
			<label>바꿀 사진</label><input class="input-file" id="fileInput" name="userFile" type="file">
			<p class="submit2">
				<button type="submit" class="btn btn-primary">저장 또는 수정</button>
			</p>
		</form>
	</div>
	<div class="span3 well">
		<h3>사용자 삭제</h3>
		<form method="post" action="<%=request.getContextPath()%>/user/deleteUser">
			<label>아이디</label><input type="text" class="disabled" name="userId" value="${user.userId}" >
			<label>이름	</label><input type="text" class="idle" name="userName" value="${user.userName}" >
			<label>email</label><input type="text" class="idle" name="userEmail" value="${user.userEmail}" >
			<label>현재 사용자 사진</label><img class="userImage" src='<%=request.getContextPath()%>/file/fileView/${user.userId}'>
			<p class="submit">
				<button type="submit" class="btn btn-danger">삭제</button>
			</p> 
		</form>
	</div>
</div>
</body>
</html>


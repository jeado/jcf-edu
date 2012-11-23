<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>사용자 관리</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
.userImage {
	display: inline-block;
	width: 48px;
	height: 48px;
	vertical-align: top;
	margin-right: 6px;
	border: none;
	cursor: pointer;
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
					<li><a href="<%=request.getContextPath()%>/tweet">트위터</a></li>
					<li><a class="active" href="<%=request.getContextPath()%>/user/findUsers">사용자관리</a></li>
					<li><a href="http://about.me/jeado">Contact</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>
<body>
<div class="container">
	<div class="row">
		<div class="offset3 well form-search>
			<form method="post" action="<%=request.getContextPath()%>/user/findUsers2">
				<input type="text" name="userId" placeholder="사용자 아이디"> 
				<input type="text" name="userName" placeholder="사용자 이름">
				<button type="submit" class="btn btn-primary">검색</button>
			</form>
		</div>
	</div>
	<div class="row">
	<h1>사용자 조회 실습</h1>
	<br>
	<table class="table table-striped table-bordered table-condensed" id="hor-minimalist-b">
		<thead>
			<tr>
				<td scope="col">아이디</td>
				<td scope="col">사용자 이미지</td>
				<td scope="col">이름</td>
				<td scope="col">이메일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td><a href='<%=request.getContextPath()%>/user/info/${user.userId}'>${user.userId}</a></td>
					<td><img class="userImage" src='<%=request.getContextPath()%>/file/fileView/${user.userId}'></div>
					<td>${user.userName}</td>
					<td>${user.userEmail}</td>
<%-- 					<td>
						<c:set var="temp">0</c:set> 
						<c:forEach
							items="${myFollowing}" var="following">
							<c:if test="${user.userId == following.followingId}">
								<a href="./unFollow.action?id=${user.userId}">unfollow 하기</a>
								<c:set var="temp">1</c:set>
							</c:if>
						</c:forEach> <c:if test="${temp == 0}">
							<a href="./follow.action?id=${user.userId}">follow 하기</a>
						</c:if>
					</td> --%>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	</div>
	<a class="btn btn-success" href='<%=request.getContextPath()%>/user/create.action'>등록</a>
</div>
</body>
</html>
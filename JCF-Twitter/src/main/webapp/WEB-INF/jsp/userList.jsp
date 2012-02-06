<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet'
	href='<%=request.getContextPath()%>/css/style.css'>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>
</head>
<body>
	<a href="../twitters/list.action">Enjoy Twitting!!</a>
	<br />
	<a href="../j_spring_security_logout">로그아웃</a>
	<form method="post" action="../user/findUsers.action">
		사용자 아이디 <input type="text" name="userId"> 사용자 이름 <input
			type="text" name="userName"> <input type="submit" value="검색">
	</form>
	<h1>사용자 조회 실습</h1>
	<table id="hor-minimalist-b">
		<thead>
			<tr>
				<td scope="col">아이디</td>
				<td scope="col">패스워드</td>
				<td scope="col">이름</td>
				<td scope="col">팔로어하기</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td><a
						href='<%=request.getContextPath()%>/user/info.action?userId=${user.userId}'>${user.userId}</a>
					</td>
					<td>${user.password}</td>
					<td>${user.userName}</td>
					<td><c:set var="temp">0</c:set> <c:forEach
							items="${myFollowing}" var="following">
							<c:if test="${user.userId == following.followingId}">
								<a href="./unFollow.action?id=${user.userId}">unfollow 하기</a>
								<c:set var="temp">1</c:set>
							</c:if>
						</c:forEach> <c:if test="${temp == 0}">
							<a href="./follow.action?id=${user.userId}">follow 하기</a>
						</c:if></td>

				</tr>
			</c:forEach>

		</tbody>
	</table>
	<a href='<%=request.getContextPath()%>/user/create.action'>등록</a>
</body>
</html>
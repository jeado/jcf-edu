<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet'
	href='<%=request.getContextPath()%>/css/style.css'>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>
</head>
<body>
	<form method="post" action="../user/findUsers.action">
		사용자 아이디<input type="text" name="userId"> 사용자 이름<input
			type="text" name="userName"> <input type="submit" value="검색">
	</form>

	<h1>사용자 조회 실습</h1>
	<table id="hor-minimalist-b">
		<thead>
			<tr>
				<td scope="col">권한</td>
				<td scope="col">아이디</td>
				<td scope="col">패스워드</td>
				<td scope="col">이름</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.userRole}</td>
					<td><a href="../user/info.action?id=${user.userId}">${user.userId}</a>
					</td>
					<td>${user.password}</td>
					<td>${user.userName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href='<%=request.getContextPath()%>/user/create.action'>등록</a>
</body>
</html>
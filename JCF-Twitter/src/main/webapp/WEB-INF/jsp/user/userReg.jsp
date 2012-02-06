<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet'
	href='<%=request.getContextPath()%>/css/style.css'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Registration</title>
</head>
<body>
	<h2>사용자 등록 실습</h2>
	<div id="inputArea">
		<form method="post" action="../user/insertUser.action">
			<label>아이디</label> <input type="text" class="idle" name="userId"
				value="${user.userId}" /> <label>패스워드</label> <input type="text"
				class="idle" name="password" value="${user.password}" /> <label>이름
			</label> <input type="text" class="idle" name="userName"
				value="${user.userName}" /> <SELECT name="userRole">
				<OPTION selected="selected">${user.userRole}</OPTION>
				<c:forEach items="${userRole}" var="role">
					<OPTION>${role.authority}</OPTION>
				</c:forEach>
			</SELECT>

			<p class="submit">
				<input type="submit" value="저장 또는 수정" />
			</p>
		</form>

		<form method="post" action="../user/deleteUser.action">
			<label>아이디</label> <input type="text" class="idle" name="userId"
				value="${user.userId}" /> <label>패스워드</label> <input type="text"
				class="idle" name="password" value="${user.password}" /> <label>이름
			</label> <input type="text" class="idle" name="userName"
				value="${user.userName}" /> <SELECT name="userRole">
				<OPTION selected="selected">${user.userRole}</OPTION>
				<c:forEach items="${userRole}" var="role">
					<OPTION>${role.authority}</OPTION>
				</c:forEach>
			</SELECT>

			<p class="submit">
				<input type="submit" value="삭제" />
			</p>
		</form>
	</div>

</body>
</html>


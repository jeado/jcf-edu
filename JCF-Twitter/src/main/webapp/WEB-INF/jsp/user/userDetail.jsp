<%@ page contentType="text/html; charset=UTF-8" import="model.UserVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Details</title>
</head>
<body>
	User Id : ${user.userId}
	<br /> Password : ${user.password}
	<br /> Name : ${user.userName}
	<br />
	<a href='<%=request.getContextPath()%>/user/userReg.jsp'>입력하기</a>
	<a href='<%=request.getContextPath()%>/user'>목록으로 </a>
</body>
</html>
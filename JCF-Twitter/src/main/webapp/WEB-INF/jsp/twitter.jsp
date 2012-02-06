<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Twitter</title>
</head>

<SCRIPT type='text/javascript'>
	function submit() {
		document.tweetForm.submit();
		return;
	}
</SCRIPT>

<body>
	<form name="tweetForm" action="insert.action" method="post"
		enctype="multipart/form-data">
		<b><sec:authentication property="principal.username" var="myId" />
		</b>${myId}님 반갑습니다. <br> <input type=hidden name="register"
			value='${myId}' /> <a href="../user/findUsers.action"><b><font
				color="b450ff">Follow Management</font> </b> </a><br /> <a
			href="../j_spring_security_logout">로그아웃</a>

		<table>
			<tr>
				<td align='center'>내용</td>
				<td colspan="3"><textarea rows="5" cols="80" name="tweets"></textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td align='left' colspan="3"><input name="upfile" type="file">
					<c:if test="${nullTweetsCheck == 0}">
						<br />
						*경고*내용을 입력하세요*경고*
						<br />
					</c:if> <input type="submit" value="저장" /></td>
			</tr>
			<tr>
				<td bgcolor='5050ff' align='center'><font color='white'>등록자</font>
				</td>
				<td bgcolor='5050ff' align='center'><font color='white'>내용</font>
				</td>
				<td bgcolor='5050ff' align='center'><font color='white'>등록시간</font>
				</td>
				<td bgcolor='5050ff' align='center'><font color='white'>관리</font>
				</td>
			</tr>
			<c:forEach items="${tweetList}" var="list">
				<tr>
					<td align='center' width=70>${list.register}</td>
					<td width=400>${list.tweets}&nbsp;&nbsp; <c:forEach
							items="${fileList}" var="fileList">
							<c:if test="${list.id == fileList.contentsId}">
								<img
									src="/file/fileView.action?id=${list.id}"
									width="100" height="100">
							</c:if>
						</c:forEach></td>

					<td width=180>${list.regDate}</td>
					<c:if test="${list.register == myId}">
						<td width=50 align='center'><a
							href="./delete.action?id=${list.id}">삭제</a></td>
					</c:if>
				</tr>
				<tr>
					<td height='1' bgcolor='b450ff' colspan='4'></td>
				</tr>
			</c:forEach>

		</table>

	</form>
</body>
</html>
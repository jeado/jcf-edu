<%@page import="jcf.edu.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome to Twitter</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.min.js"></script>
</head>
<SCRIPT type='text/javascript'>
$(document).ready(function(){
	$('.main-content').height($('.stream-manager').height()+167);
 });
</SCRIPT>
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
			<div class="active-links">
				<span id="session" class="loggedin js-session"> <a
					class="profile-links" href="#"> <img
						src="<%=request.getContextPath()%>/file/fileView/${currentUser.userId}">
				</a> <span id="screen-name">${currentUser.userName}</span>
				</span>
			</div>
		</div>
	</div>
</div>
<body>
<div id="page-outer">
<div id="page-container" class="page-container  home-container">
<div class="main-content" style="min-height: 396px; ">
	<div class ="page-header home-header">
		<form  name="tweetForm" action="<%=request.getContextPath()%>/tweet/insert" method="post" >
		<div class="tweet-box condensed">
		      <div class="tweet-box-title">
		      		<h2>What’s happening?</h2>
		      </div>
			  <div class="text-area">
			    <div class="text-area-editor twttr-editor"><textarea name="tweets" class="twitter-anywhere-tweet-box-editor" style="width: 482px; height: 56px; "></textarea></div>
			  </div>
		  </div>
		  <input type="submit"  class="btn" value="저장" >
	 	 </form>
	</div>
	<div class="stream-manager js-stream-manager-container">
		<div class="stream-title"><h2>Favorites, follows, retweets, and more by people you follow</h2></div>
		<div class="stream-container">
			<div class="stream">
					<c:forEach items="${tweetList}" var="list">
						<div class="stream-item">
						<div class="stream-item-content clearfix stream-item-activity stream-item-favorite stream-item-activity-network">
							<div class="activity-timestamp">
						       <span class="_timestamp">${list.regDate}</span>
						     </div>
							<div class="stream-item-activity-line js-actionable-user stream-item-activity-line-network">
								<img  height="48" width="48" src="<%=request.getContextPath()%>/file/fileView/${list.register}">
								<span class="user"><a class="user-profile-link pretty-link" data-user-id="23556991" href="<%=request.getContextPath()%>/tweet/${list.register}" title="${list.register}"><s>@</s><b>${list.register}</b></a></span>
							</div>
							<div class="activity-supplement ">
								<div class="sub-stream-item clearfix ">
									<div class="js-stream-tweet js-actionable-tweet stream-item-content tweet stream-tweet activity-item-tweet callout-tweet-actions">
									<div class="tweet-content activity-item-tweet-content clearfix">
										<div class="tweet-row">
											<div class="tweet-text js-tweet-text">${list.tweets}</div>
										</div>
									</div>
									</div>
								</div>
							</div>
							<c:if test="${list.register == currentUser.userId}">
								<a href="<%=request.getContextPath()%>/tweet/delete?id=${list.id}" class="view-all-supplements show">
									<span class="show-text">삭제</span>
								</a>
							</c:if>
						</div>
						</div>
					</c:forEach>
			</div>
		</div>
	</div>
</div>
<div class="dashboard">
		<div class="component">
			<div class="user-rec-inner ">
		      <h2 class="user-rec-component">
		      <span class="dashboard-component-title">Who to follow</span>
		    </h2>
		    <ul class="js-recommended-followers recommended-followers user-rec-component" data-section-id="wtf">
		    
		    <c:forEach items="${userList}" var="uList">
		    	<li class="user-small-list-item js-actionable-user " data-user-id="39674432" data-feedback-token="1">
		    		<a href="<%=request.getContextPath()%>/tweet/${uList.userId}" class="js-recommend-link user-profile-link user-thumb"><img src="<%=request.getContextPath()%>/file/fileView/${uList.userId}" alt="${uList.userId}" class="js-action-profile-avatar user-content-image"></a>
		    		<div class="user-name-info">
			          <a class="js-recommend-link user-profile-link user-screen-name js-action-profile-name" href="/#!/sh_domac" >${uList.userId}</a>
			          <span class="user-name-and-actions">
			        	<span class="user-full-name">${uList.userName}</span>
		        		<span class="js-follow-state user-follow-state">
		      			  <span class="dot">·</span>
		      			  <c:set var="temp">0</c:set> 
		      			  <c:forEach	items="${followingList}" var="following">
							<c:if test="${uList.userId == following.followingId}">
								<a href="<%=request.getContextPath()%>/unfollow?id=${uList.userId}" class="user-follow-link js-action-follow js-link unfollow-action" >Unfollow</a>
								<c:set var="temp">1</c:set>
							</c:if>
							</c:forEach> <c:if test="${temp == 0}">
								 <a href="<%=request.getContextPath()%>/follow?id=${uList.userId}" class="user-follow-link js-action-follow js-link follow-action" >Follow</a>
							</c:if>
					    </span>
					   </span>
				   	   <!-- 
				   	   	<div class="user-meta">
						Followed by <span class="user"><a class="user-profile-link pretty-link" data-user-id="23556991" href="/#!/joomanba" title="Jang Eui Jin"><s>@</s><b>joomanba</b></a></span> and <a href="/#!/sh_domac/followers_you_follow" class="pretty-link"><b>others</b></a>
				       </div>
				        -->
			  		</div>
		    	</li>
		    </c:forEach>
		    
		    </ul>
			</div>
			<hr class="component-spacer">
		</div>
	</div>
</div>
</div>
</body>
</html>
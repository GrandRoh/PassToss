<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>왼쪽 메뉴 바</title>
<link rel="stylesheet" href="css/leftMenu.css" type="text/css">
<script src="js/jquery-3.6.3.js"></script>
<script>
	$(function() {
		$(".dropdown-btn").each(function() {
			$(this).click(function() {
				$(this).next(".dropdown-container").slideToggle();
			})
		})
	})
</script>
</head>
<body>
	<div class="sidenav">
		<button class="dropdown-btn">
			관리자페이지 <i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a href="joinList.net">회원가입명단</a> 
			<a href="memberList.net">회원정보</a> 
			<a href="boardManage.net">게시물관리</a>
		</div>
		<a href="#contact">사내게시판</a>
		<button class="dropdown-btn">
			부서게시판 <i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a href="#">게시물</a> <a href="#">캘린더</a>
		</div>
		<button class="dropdown-btn">
			마이페이지 <i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a href="#">정보수정</a> <a href="#">게시물관리</a>
		</div>
	</div>
</body>
</html>
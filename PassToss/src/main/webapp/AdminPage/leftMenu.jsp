<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix ="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/leftMenu.css" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<c:if test="${empty id }">
	<script>
		location.href="LoginAction.net";
	</script>
</c:if>
<script>
	$(function() {
		$(".dropdown-btn").each(function() {
			$(this).click(function() {
				$(this).next(".dropdown-container").slideToggle();
			})
		})
	})
</script>

<div class="sidenav">
	<b style='color: #fff;margin: 10px auto;display: block;text-align: center;'>${id}님 안녕하세요.</b>
	<c:if test="${id=='admin' }">
		<button class="dropdown-btn">
			관리자페이지 <i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a href="AdminJoinList.net">회원가입명단</a> 
			<a href="AdminMemberList.net">회원관리</a> 
			<a href="AdminboardManage.net">게시물관리</a>
		</div>
	</c:if>	
	<a href="FreeList.bof">사내게시판</a>
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
	<a href="Business_status.bs">업무현황</a>
</div>
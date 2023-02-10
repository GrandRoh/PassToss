<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/head.css" type="text/css">

<script>
	$(function(){
	$(".gnb_profile_close_btn").click(function(){
			
			$(".profile_wrap").hide();
		})
		$(".head_icon").click(function(){
			
			$(".profile_wrap").toggle();
		})
		
	})
</script>
<style>
	
</style>
<div id="gnb_header " class='container' class='rel'>
	<h2 id='gnb_title' style='font-weight:bold;padding-lefT:20px'>head부분입니다</h2>

	<div class='head_icon abo'>
		
		<c:if test="${empty profileimg}">
		   <img src="image/delete.png" width=30px>
		 </c:if>
		
		<c:if test="${!empty profileimg}">
		   <img src="memberupload/${profileimg}" width=30px>
		 </c:if>
		
	</div>
	
	<div class='abo profile_wrap'>
			<img class='abo gnb_profile_close_btn'   src="image/error.png" width=30px>
	
			<div class="center" style='padding-bottom:20px'>
		  		<img src="memberupload/${profileimg}" width=70px>
		  	</div>
		  <div>
		  	<b>아이디: </b> ${id}
		  </div>
		  <div>
		  	<b>이름: </b>  ${memberinfo.name}
		  </div>
		  <div>
		  	<b>연락처: </b> ${memberinfo.phone}
		  </div>
		  <div>
		  	<b>Email: </b> ${memberinfo.email}
		  </div>
		  <p class='gnb_logout_btn center'><a href='logout.net'>로그아웃</a></p>
	</div>
	
</div>


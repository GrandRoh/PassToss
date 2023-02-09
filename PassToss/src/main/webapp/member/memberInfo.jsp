<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../AdminPage/leftMenu.jsp"/>
<jsp:include page="../include/head.jsp"/>


<!DOCTYPE html>

<head>
<meta charset="UTF-8">
<title>회원정보 변경</title>

<style>
	img {width:20px}
	input:read-only {background-color: #eee;}  
</style>
 <link rel='stylesheet' href="${pageContext.request.contextPath}/css/join.css">
   <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  <script src="${pageContext.request.contextPath}/js/joinUpdate.js"></script>
</head>




	<div class='container box_radius15 board_container'>
		  <form name="joinform" method="post" action="updateProcess.net" id="joinform"  enctype="multipart/form-data">
		     <h1>정보수정</h1>
		      <hr/>
		      
		      <b><label for="id">아이디</label></b>
		      <input type="text" name="id" placeholder ="enter id" maxlength="12" value="${memberinfo.id }" readonly>
	
	
		      <b><label for="name">이름</label></b>
		      <input type="text" name="name" placeholder ="enter name" value="${memberinfo.name }" required>
		      
		      
		      
		       <b><label for="phone">휴대폰번호</label></b>
		       <input type="text" placeholder="(-)하이픈을 추가하세요" value="${memberinfo.phone }" name="phone" id="phone">


		       <b><label for='deptno'>부서번호</label></b>
				<input id='email' type='text' name='deptno'  placeholder =deptno value="${memberinfo.deptno }"  required readonly>
		     	<span id ="email_message"></span>
		       
		        <b><label for="email">이메일주소</label></b>
		      	<input type='text' name='email'  placeholder ="enter email" value="${memberinfo.email }"  maxlength="30" required>
		     	<span id ="email_message"></span>
		     	
		     	
		     	
		     
		     
		     		<div style='margin-bottom:30px'>
						<label for="post1">우편번호</label>
				      <input type="text"  name="post1" value="${memberinfo.post }" id="post1" readonly> 
				      <input type="button" value="우편검색" id='postcode' onclick="Postcode()">
			      </div>
			      
			      <label for="address">주소</label>
			      <input type="text"  name="address" id="address" value="${memberinfo.address }">
		     
		     		<div class='form-group'>
		     			<input type="hidden" name="check" value="${memberinfo.profileImg}">
						<label >
							파일 첨부
							<img src="image/attach.png" alt="파일첨부">
							<input type="file" id="upfile" name="member_file">
						</label>
						<span id="filevalue"></span>
						<img src="memberupload/${memberinfo.profileImg}">
					
					</div>
		     
		     
		     	<div class='clearfix'>
		     		<button type="submit" class="submitbtn" style='width:100%;margin-top:50px'>수정</button>
		     	</div>
		     	
		     	
		  </form>
  	</div>

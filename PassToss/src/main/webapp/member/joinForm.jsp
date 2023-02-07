<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 페이지</title>
 <script src="http://code.jquery.com/jquery-latest.min.js"></script>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  <script src="${pageContext.request.contextPath}/js/join.js"></script>
 <link rel='stylesheet' href="${pageContext.request.contextPath}/css/join.css">


</head>
<body>

  <form name="joinform" method="post" action="joinProcess.net" id="joinform" >
     <h1>회원가입</h1>
      <hr/>
      
      <b><label for="id">아이디</label></b>
      <input type="text" name="id" placeholder ="enter id" maxlength="12" required>
      <span id="message"></span>
      
      <b><label for="pass">비밀번호</label></b>
      <input type="password" name="pass" placeholder ="enter pass"  required>
      <b><label for="name">이름</label></b>
      <input type="text" name="name" placeholder ="enter name" value='test11' required>
      
      
      
       <b><label for="phone">휴대폰번호</label></b>
       <input type="text" placeholder="(-)하이픈을 추가하세요" value='010-1100-1100' name="phone" id="phone">
      
       <b><label for="jumin1">주민번호</label></b>
       <input type="text" placeholder="주민번호 앞자리" size="6" maxLength="6" 
              name="jumin1" id="jumin1"  value='808088'>      
       <span>-</span>
       <input type="text" placeholder="주민번호 뒷자리" size="7" maxLength="7" 
              name="jumin2" id="jumin2" value='1231232'>
      
       <b>부서번호</b>
       <select name="deptno">
	  		<option value="10">10</option>
	  		<option value="20">20</option>
	  		<option value="30">30</option>
	  		<option value="40">40</option>
       </select>
       
        <b><label for="email">이메일주소</label></b>
      	<input type='text' name='email'  placeholder ="enter email"  maxlength="30" required>
     	<span id ="email_message"></span>
     	
     	
     	
     
     
     		<div>
				<label for="post1">우편번호</label>
		      <input type="text" value='' maxLength="5" name="post1" id="post1" readonly> 
		      <input type="button" value="우편검색" id='postcode' onclick="Postcode()">
	      </div>
	      <label for="address">주소</label>
	      <input type="text" value='' name="address" id="address">
     
     
     	<div class='clearfix'>
     		<button type="submit" class="submitbtn">회원가입</button>
     		<button type="reset" class="cancelbtn">다시작성</button>
     	</div>
     	
     	
  </form>
  

</body>
</html>
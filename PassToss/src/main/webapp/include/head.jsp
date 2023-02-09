<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/head.css" type="text/css">

<script>
	console.log('222 =  ${profileimg}');
</script>

<div id="gnb_header " class='container'>
	<h2 id='gnb_title' style='font-weight:bold;padding-lefT:20px'>head부분입니다</h2>

	<div class='head_icon abo'>
		
		<c:if test="${empty profileimg}">
		   <img src="image/delete.png" width=30px>
		 </c:if>
		
		<c:if test="${!empty profileimg}">
		   <img src="memberupload/${profileimg}" width=30px>
		 </c:if>
		
	</div>
	
</div>
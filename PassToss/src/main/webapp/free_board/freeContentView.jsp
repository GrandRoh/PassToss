<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>사내게시판 글 내용 보기</title>
<jsp:include page="../AdminPage/leftMenu.jsp"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
<script src="js/jquery-3.6.3.js"></script>
<style>
	*{margin:0;padding:0}
	body{background:#e2e2e2;min-width:1400px}
 	.container{padding:10px; background:#fff;
 				margin:10px; display:inline-block; vertical-align:top;
 				 min-width:1140px}
    .sidenav{display:inline-block}
 	.box_radius15{border-radius:5px}
 	
 	table caption {
		caption-side: top;
		text-align: center
	}
</style>
</head>
<body>
  <input type="hidden" id="loginid" value="${id}" name="loginid">
  <div class='container box_radius15'>
		
  </div>
</body>
</html>
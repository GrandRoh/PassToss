<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>사내게시판 글쓰기</title>
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
 				 width:80%}
    .sidenav{display:inline-block}
 	.box_radius15{border-radius:5px}
 	
 	table caption {
		caption-side: top;
		text-align: center
	}
	
	#upfile{display:none}
	img{width:20px;}
	
	input[type=reset]{
		text-align : left;
	}
	
	input[type=submit]{
		text-align : rigth;
	}
</style>
</head>
<body>
<div class='container box_radius15'>
    <form action="BoardFreeAdd.bof" method="post" enctype="multipart/form-data" name="boardform">
    <input type="hidden" id="loginid" value="${id}" name="loginid">
    	<h3>사내게시판 글쓰기</h3>
    	<div class="from-group">
    		<select id="board_notice" name="board_notice"> <%-- 권한이 2거나 아이디가 admin 아니면 바꿀수 없어야함 --%>
    		<%-- <c:if test="!${id} == 'admin'">
				<option value="1" selected>게시물</option>
			</c:if>
			<c:if test="${id} == 'admin'"> --%>
				<option value="0">공지사항</option>
				<option value="1" selected>게시물</option>
			<%-- </c:if> --%><%-- 나중에 id 받아올때 제대로 되는지 확인하기  --%>
			</select>
    	</div>
    	<div class="form-group">
    		<label for="board_subject"></label>
    		<input name="board_subject" id="board_subject" maxlength="100"
    			   type="text" class="form-control"
    			   placeholder="제목을 입력하세요">
    	</div>
    	<div class="form-group">
    		<label>
    		 	<img src = "image/file.png" alt="파일첨부">
    			<input type="file" id="upfile" name="board_file">
    		</label>
    		<span id="filevalue"></span>
    	</div>
    	<div class="form-group">
    		<label for="board_content"></label>
    		<input name="board_content" id="board_content" rows="10"
    			   class="form-control" placeholder="내용을 입력하세요">
    	</div>
    	<div class="form-group">
    		<button type=submit class="">등록</button>
    		<button type=reset class="">취소</button>
    	</div>
    </form>
</div>
</body>
</html>
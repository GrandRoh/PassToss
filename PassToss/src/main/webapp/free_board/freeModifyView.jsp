<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>사내게시판 글 수정 페이지</title>
<jsp:include page="../AdminPage/leftMenu.jsp"/>
<script src="js/jquery-3.6.3.js"></script>
<style>
	*{margin:0;padding:0}
	body{background:#e2e2e2;min-width:1400px}
    .sidenav{display:inline-block}
 	.box_radius15{border-radius:5px}
</style>
</head>
<body>
  <div class='container box_radius15'>
  <jsp:include page="../include/head.jsp"/>
	 <form action="FreeModifyAction.bof" method="post" enctype="multipart/form-data" name="modifyform">
	 	<input type="hidden" name="board_num" value="${board.board_num}">
	 	<h3>글 수정하기</h3>
	 	<div class="from-group">
    		 <c:if test="${!id=='admin'}">
    		<select id="board_notice" name="board_notice"> <%-- 권한이 2거나 아이디가 admin 아니면 바꿀수 없어야함 --%>
				<option value="1" selected>게시물</option>
			</select>
		   </c:if>
		   <c:if test="${id=='admin'}">
		     <select id="board_notice" name="board_notice">
				<option value="0">공지사항</option>
				<option value="1" selected>게시물</option>
			 </select>
		   </c:if>
    	</div>
	 	
	 	<div class="form-group">
    		<label for="board_subject"></label>
    		<input name="board_subject" id="board_subject" maxlength="100"
    			   type="text" class="form-control" rows ="1"
    			   value="${board.board_subject}">
    	</div>
    	
    	
      <c:if test="${board.board_re_lev == 0}">
    	<div class="form-group">
    		<label>
    		 	<img src = "image/file.png" alt="파일첨부" width ="20px">
    			<input type="file" id="upfile" name="board_file">
    		</label>
    		<span id="filevalue">${board.board_file}</span>
    		<img src="image/remove.png" alt="파일삭제" width="10px" class="remove">
     	</div>
      </c:if>
      
      <div class="form-group">
    		<label for="board_content"></label>
    		<input name="board_content" id="board_content" rows="10"
    			   class="form-control" value="${board.board_content}">
      </div> 
      
      <div class="form-group">
      	<button type=submit class="btn btn-primary">수정</button>
    	<button type=reset class="btn btn-danger" onClick="history.go(-1)">취소</button>
      </div>
	 </form>
  </div>
</body>
</html>
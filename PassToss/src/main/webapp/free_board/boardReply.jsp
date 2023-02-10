<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>사내게시판 답글 달기</title>
<jsp:include page="../AdminPage/leftMenu.jsp"/>
<jsp:include page="../include/head.jsp"/>
<script src="js/jquery-3.6.3.js"></script>
<style>
	*{margin:0;padding:0}
	body{background:#e2e2e2;min-width:1400px}
    .sidenav{display:inline-block}
 	.box_radius15{border-radius:5px}
	
</style>
</head>
<body>

  <div class='container box_radius15 board_container'>
   <form action="FreeReplyAction.bof" method="post" name="replyform">
      <input type="hidden" name="board_re_ref"  value="${board.board_re_ref}"> 
      <input type="hidden" name="board_re_lev"  value="${board.board_re_lev}"> 
      <input type="hidden" name="board_re_seq"  value="${board.board_re_seq}">
      <input type="hidden" name="board_name"  value="${board.board_name}">
      
      <h3>사내게시판 답글 작성</h3>
      <div class="form-group">
    	<label for="board_subject"></label>
    	<textarea name="board_subject" id="board_subject" rows="1"
    			   class="form-control" maxlength="100">Re:${board.board_subject}</textarea>
      </div>
      
      <div class="form-group">
    	<label for="board_content"></label>
    	<textarea name="board_content" id="board_content" rows="10"
    			  class="form-control"></textarea>
      </div>
    	
      <div class="form-group">
    	<input type="submit" class="btn btn-primary" value="등록">
    	<input type ="button" class="btn btn-danger" value="취소"	onClick="history.go(-1)">
      </div>
   </form>
  </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>부서게시판 답글 달기</title>
<jsp:include page="../AdminPage/leftMenu.jsp"/>
<jsp:include page="../include/head.jsp"/>
<script src="js/jquery-3.6.3.js"></script>
<style>
	.subject{
	    border-color : White;
		border-bottom : solid 1px silver; 
	}
	
	#board_content{
		border-color : White; 
	}
	
	tr.sharing {
		text-align: right;
	}
	
	td#sharing{
		border-color : White;
		border-bottom : solid 1px Silver;
	}
	
	.submit{
	  padding: 0.275rem 0.75rem;
	  /*border-color: black; border테두리*/
      border-radius: 1;
      margin: 5;
	}
	
	.cancel{
	  padding: 0.275rem 0.75rem;
	  border-color: black;
      border-radius: 1;
      margin: 5;
	}
	
	.container {
	width: 70%
	}
	
	textarea{
	 resize: none;
	}
	
</style>
</head>
<body>
	<div class='container box_radius15 board_container'>
   <form action="DeptReplyAction.bod" method="post" name="replyform">
      <input type="hidden" name="board_re_ref"  value="${board.board_re_ref}"> 
      <input type="hidden" name="board_re_lev"  value="${board.board_re_lev}"> 
      <input type="hidden" name="board_re_seq"  value="${board.board_re_seq}">
      <input type="hidden" name="board_name"  value="${board.board_name}">
      <input type="hidden" name="board_deptno" value="${deptno}">
      
      <h3>${dname} 게시판 답글쓰기</h3>
      <div class="form-group">
    	<label for="board_subject"></label>
    	<textarea name="board_subject" id="board_subject" rows="1"
    			   class="form-control subject" maxlength="100">Re:${board.board_subject}</textarea>
      </div>
      
      <div class="form-group">
    	<label for="board_content"></label>
    	<textarea name="board_content" id="board_content" rows="10"
    			  class="form-control" placeholder="내용을 입력하세요"></textarea>
      </div>
    	
      <div class="form-group">
    	<input type="submit" class="btn btn-dark submit" style="float: right" value="등록">
    	<input type ="button" class="btn btn-light cancel" value="취소"	onClick="history.go(-1)">
      </div>
   </form>
  </div>
</body>
</html>
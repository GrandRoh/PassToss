<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>부서게시판 글 내용 보기</title>
<jsp:include page="../AdminPage/leftMenu.jsp"/>
<jsp:include page="../include/head.jsp"/>
<script src="js/Deptview.js"></script>
<script src="js/jquery-3.6.3.js"></script>
<link rel="stylesheet" href="css/view.css" type="text/css">
<style>
	*{margin:0;padding:0}
	body{background:#e2e2e2;min-width:1400px}
    .sidenav{display:inline-block}
 	.box_radius15{border-radius:5px}
	
</style>
<script>
	$(function(){
		$("#deletebutton").click(function(event){
			var answer = confirm("정말 삭제하시겠습니까?");
			let num = '${param.num}' //주소줄에서 넘어오는거 가져옴 
			
			if(answer){
				location.href="DeptDeleteAction.bod?num="+num;
			}else {
				event.preventDefault();
			}
		})
	})
</script>
</head>
<body>
	<div class='container box_radius15 board_container'>  
  <input type="hidden" id="loginid" value="${id}" name="loginid">
  <input type="hidden" name="num" value="${param.num}" id="comment_board_num">
  <input type="hidden" name="board_name" value="${board.board_name}">
   <div class='modifybutton'>
	<c:if test="${board.board_name == id || id == 'admin'}">
	  <a href="DeptModifyView.bod?num=${board.board_num}">
		<button class="btn btn-info">수정</button>
	  </a>
	  <%-- href의 주소를 #으로 설정합니다. --%>
	  <a href="#">
		<button class="btn btn-danger" id="deletebutton">삭제</button>
	  </a>
	</c:if>
   </div>
   
   <table class="table">
   	 <tr>
		<td colspan="4"><c:out value="${board.board_subject}"/></td>
	 </tr>
	 <tr>
	 	<td>작성자 ${board.board_name}</td>
	 	<td>조회수&nbsp;${board.board_readcount}</td>
	 	<td>작성일자&nbsp;${board.board_date}</td>
	 	<td><a href="#comment-area">댓글 <sup class="count"></sup></a></td> <%-- 클릭하면 댓글로 이동 --%>
	 </tr>
	 <tr>
	 	<td colspan="4"><c:out value="${board.board_content}" escapeXml = "true"/></td>
	 </tr>
	 <tr>
	 	<td colspan="4" id="sharing"><img src="image/sharing.png" width="20px"> 공유 </td>
	 </tr>
	 
	 <c:if test="${board.board_re_lev==0}">
		<%-- 원문글인 경우에만 첨부파일을 추가 할 수 있습니다. --%>
		<tr>
			<%-- 파일을 첨부한 경우 --%>
			<c:if test="${!empty board.board_file}">
			<td><div>첨부파일</div></td>
				<td><label><img src="image/down.png" width="10px">
					<a href="DeptFileDown.bod?filename=${board.board_file}">${board.board_file}
					</a></label></td>
			</c:if>
					
			<%-- 파일을 첨부하지 않은 경우 --%>
			<c:if test="${empty boarddata.board_file}">
				
			</c:if>
		</tr>
	 </c:if>
	 
   </table>
   
   <div class="comment-area" id="comment-area">
  		<div class="comment-head">
  			<h3 class="comment-count">
  				댓글 <sup class="count"></sup>
  			</h3>
  			<div class="comment-order">
  				<ul class="comment-order-list">
  				</ul>
  			</div>
  		</div>
  		<ul class="comment-list">
  		</ul>
  		<div class="comment-write">
  			<div class="comment-write-area">
  				<b class="comment-write-area-name">${id}</b> <span
  					class="comment-write-area-count">0/200</span>
  				<textarea placeholder="댓글을 남겨보세요" rows="1"
  					class="comment-write-area-text" maxLength="200"></textarea>
  			</div>
  			<div class="register-box">
  				<div class="button btn-cancel">취소</div><%-- 댓글의 취소는 display:none, 등록만 보이도록 합니다. --%>
  				<div class="button btn-register">등록</div>
  			</div>
  		</div>
  	</div>
  	
  	<div>
  		<a href="DeptReplyView.bod?num=${board.board_num}"><button class="">답글쓰기</button></a>
  		
  		
  	   <c:forEach var = 'bnum' items="${board_num_next_prev}">
	  	 <c:if test="${bnum.board_prev_num != -1}"> 
	  		<a href="DeptDetailAction.bod?num=${bnum.board_prev_num}"><button class="">이전글</button></a>
	  	 </c:if> 
  	   
  		<a href="DeptList.bod"><button class="">목록</button></a>
  		
  	  	<c:if test="${bnum.board_next_num != -1}"> 
  			<a href="DeptDetailAction.bod?num=${bnum.board_next_num}"><button class="">다음글</button></a>
  	   	</c:if>
  	   
  	   </c:forEach>
  	</div>
  </div>
</body>
</html>
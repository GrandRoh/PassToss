<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>사내게시판</title>
<jsp:include page="../AdminPage/leftMenu.jsp"/>
<script src = "js/list.js"></script>
<script src="js/jquery-3.6.3.js"></script>
<script>
	$(function(){
		$("button").click(function(){
			location.href="FreeWrite.bof";
		})
	});
</script>
<style>
	*{margin:0;padding:0}
	body{background:#e2e2e2}
 	.container{padding:10px; background:#fff; margin:10px auto; min-width:1000px}
 	.box_radius15{border-radius:5px}
 	
 	table caption {
		caption-side: top;
		text-align: center
	}
	
	.rows{
 		text-align : left;
 	}
</style>
</head>
<body>
	<div class='container box_radius15'>
	  <form action="FreeList.bof" method="post">
			<div class="input-group">
				<select id="viewcount" name="search_field">
					<option value="0" selected>전체</option>
					<option value="1">제목</option>
					<option value="2">작성자</option>
				</select>
					<input name="search_word" type="text" class="form-control"
						   placeholder="검색어를 입력하세요" value="${search_word}">
					<button type="submit">검색</button>
			</div>
		</form>
		
		<c:if test="${listcount > 0}">
		
		  <table class="table table-striped">
		  	<thead>
		  		<tr>
		  			 <th colspan="2">사내게시판 list</th>
		  			 <th><span>글 갯수 : ${listcount}</span></th>
		  		</tr>
		  		<tr>
		  			<td>번호</td>
		  			<td>제목</td>
		  			<td>작성자</td>
		  			<td>등록일</td>
		  			<td>조회수</td>
		  		</tr>
		    </thead>
		    <tbody>
		     <c:set var="num" value="${listcount- (page-1)*limit}"/>
	   		 <c:forEach var="b" items="${boardlist}">
	   		 	<tr>
	   		 	  <td> <%-- 번호 --%>
	   		 	    <c:out value="${num}"/>
	   		 	    <c:set var="num" value="${num-1}"/>
	   		 	  </td>
	   		 	  <td> <%-- 제목 --%>
	   		 	    <div>
	   		      	<c:if test="${b.board_re_lev != 0}"> <%-- 답글인 경우 --%>
	   		      	<c:forEach var="a" begin="0" end="${b.board_re_lev*2}" step="1">
	   		      	 &nbsp;
	   		      	</c:forEach>
	   		      	<img src='image/reply.png'>
	   		      	</c:if>
	   		      
	   		      	<c:if test="${b.board_re_lev == 0}"> <%-- 원문인 경우 --%>
	   		          &nbsp;
	   		      	</c:if>
	   		        
	   		      	<a href="FreeDetailAction.bo?num=${b.board_num}">
	   		      	<c:if test="${b.board_subject.length() >= 20}">
	   		      	  <c:out value="${b.board_subject.substring(0,20)}..."/>
	   		      	</c:if>
	   		      	<c:if test="${b.board_subject.length() < 20}">
	   		      	   <c:out value="${b.board_subject}"/>
	   		      	</c:if>
	   		        </a>[${b.cnt}]
	   		    	</div>
	   		   	 </td>
	   		   	 <td><div>${b.board_name}</div></td>
	   		   	 <td><div>${b.board_date}</div></td>
	   		   	 <td><div>${b.board_readcount}</div></td>
	   		 	</tr>
	   		 </c:forEach>
		    </tbody>
		  </table>
		  
		  <div class="center-block">
	  	 	<ul class="pagination justify-content-center">
	  	 		<c:if test="${page <= 1}">
	  	 			<li class="page-item">
	  	 			  <a class="page-link gray">이전 &nbsp;</a>
	  	 			</li>
	  	 		</c:if>
	  	 		<c:if test="${page > 1}">
	  	 			<c:url var="prev" value="FreeList.bof">
	  	 	  		<c:param name="search_field" value="${search_field}" />
	  	 	  		<c:param name="search_word" value="${search_word}" />
	  	 	  		<c:param name="page" 	value="${page-1}" />
	  	 	  	</c:url>
	  	 		<li class="page-item">
	  	 		  <a href="${prev}"
	  	 		     class="page-link">&nbsp;다음</a>
	  	 		</li>
	  	 		</c:if>
	  	 	
	  	 		<c:forEach var="a" begin="${startpage}" end="${endpage}">
	  	 	 	 <c:if test="${a == page}">
	  	 	  		<li class="page-item active">
	  	 	  			<a class="page-link">${a}</a>
	  	 	  		</li>
	  	 	 	 </c:if>
	  	 	 		 <c:if test="${a != page}">
	  	 	  			<c:url var="go" value="memberList.net">
	  	 	  			<c:param name="search_field" value="${search_field}" />
	  	 	  			<c:param name="search_word" value="${search_word}" />
	  	 	  			<c:param name="page" 	value="${a}" />
	  	 	  		</c:url>
	  	 	  		<li class="page-item">
	  	 	  			<a href="${go}"
	  	 	  		  	 class="page-link">${a}</a>
	  	 	  		</li>
	  	 	  		</c:if>
	  	 		</c:forEach>
	  	 	
	  	 		<c:if test="${page >= maxpage}">
	  	 			<li class="page-item">
	  	 			  <a class="page-link gray">&nbsp;다음</a>
	  	 			</li>
	  	 		</c:if>
	  	 		<c:if test="${page < maxpage}">
	  	 			<c:url var="next" value="FreeList.bof">
	  	 	  		<c:param name="search_field" value="${search_field}" />
	  	 	  		<c:param name="search_word" value="${search_word}" />
	  	 	  		<c:param name="page" 	value="${page+1}" />
	  	 	  	</c:url>
	  	 		<li class="page-item">
	  	 		  <a href="${next}"
	  	 		     class="page-link">&nbsp;다음</a>
	  	 		</li>
	  	 		</c:if>
	  		 </ul>
	 	 </div>
		  
		  <div class="rows">
	  		<span>줄보기</span>
	  		<select class="form-control" id="lineviewcount">
	  			<option value="5">5</option>
	  			<option value="10" selected>10</option>
	  			<option value="15">15</option>
	  			<option value="20">20</option>
	  		</select>
	  	 </div>
		  
		</c:if>
		
		<button type="button" class="btn btn-info float-right">글 쓰 기</button>
	</div>
	
	<c:if test="${listcount == 0 && empty search_word}" >
		<h1 style ="text-align:center">등록된 글이 없습니다.</h1>
	</c:if>
  	<c:if test="${listcount == 0 && !empty search_word}">
  		<h1 style ="text-align:center">검색 결과가 없습니다.</h1>
  	</c:if>
</body>
</html>
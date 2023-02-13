<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<jsp:include page="../AdminPage/leftMenu.jsp" />
<jsp:include page="../include/head.jsp" />
<script src="js/adminboard.js"></script>
<link rel="stylesheet" href="css/adminboard.css" type="text/css">
<title>ADMIN - 게시물 관리</title>
</head>
<body>
	<div class='container box_radius15 board_container'>

		<input type="hidden" id="search_field" value="${search_field}">
		<form action="AdminboardList.net?category=${category_index}"
			method="post">
			<div class="input-group">
				<select id="select_value" name="search_field">
					<option value="0" selected>제목</option>
					<option value="1">작성자</option>
				</select> <input name="search_word" type="text" class="form-control"
					placeholder="검색어를 입력하세요" value="${search_word}">
				<button class="btn btn-primary" type="submit" name="searchbutton">검색</button>
			</div>
		</form>
		<div class="header">
			<span class="title">Admin - ${category}</span> <span class="count">|
				글 개수 : ${listcount}</span>
		</div>
		<br> <span class="category"><a
			href="AdminboardList.net?category=0">사내게시판</a></span> <span class="category"><a
			href="AdminboardList.net?category=1">부서게시판</a></span>
		<form class="delete" action="AdminBoardDelete.net" method="post">
			<input type="hidden" name="category" value="${category_index}">
			<input type="image" src="image/delete.png">
		</form>

		<%-- 게시글이 있는 경우 --%>
		<c:if test="${listcount > 0}">
			<table class="table">
				<thead class="bg-dark text-white thead-dark">
					<tr>
						<th><input type="checkbox" class="selectAll"></th>
						<th><div>번호</div></th>
						<th><div>제목</div></th>
						<th><div>작성자</div></th>
						<c:if test="${category_index == 1}">
							<td><div>부서번호</div></td>
						</c:if>
						<th><div>작성날짜</div></th>
						<th><div>조회수</div></th>
					</tr>
				</thead>

				<tbody>
					<c:set var="num" value="${listcount-(page-1)*limit}" />
					<c:forEach var="b" items="${boardlist}">
						<tr>
							<td><input type="checkbox" class="select"
								value="${b.board_num}"></td>
							<td>
								<%-- 번호 --%> <c:out value="${num}" /> <%-- num 출력 --%> <c:set
									var="num" value="${num-1}" /> <%-- num = num-1; 의미 --%>
							</td>
							<td>
								<%-- 제목 --%>
								<div>
									<%-- 답변글 제목앞에 여백 처리 부분
										board_re_lev,, board_num,
										board_subject, board_name, board_date,
										board_readcount : property 이름 --%>
									<c:if test="${b.board_re_lev != 0}">
										<%-- 답글인 경우 --%>
										<c:forEach var="a" begin="0" end="${b.board_re_lev*2}"
											step="1">
										&nbsp;
										</c:forEach>
										<img src='image/reply.png' style="width: 30px; height: 30px">
									</c:if>

									<c:if test="${b.board_re_lev == 0}">
										<%-- 원문인 경우 --%>
										&nbsp;
									</c:if>
									<c:if test="${category_index == 0}">
										<a href="FreeDetailAction.bof?num=${b.board_num}"> <c:if
												test="${b.board_subject.length()>=20}">
												<c:out value="${b.board_subject.substring(0,20)}..." />
											</c:if> <c:if test="${b.board_subject.length()<20}">
												<c:out value="${b.board_subject}" />
											</c:if>
										</a>[${b.cnt}]</c:if>
									<c:if test="${category_index == 1}">
										<a href="DeptDetailAction.bof?num=${b.board_num}"> <c:if
												test="${b.board_subject.length()>=20}">
												<c:out value="${b.board_subject.substring(0,20)}..." />
											</c:if> <c:if test="${b.board_subject.length()<20}">
												<c:out value="${b.board_subject}" />
											</c:if>
										</a>[${b.cnt}]</c:if>
								</div>
							</td>
							<td><div>${b.board_name}</div></td>
							<c:if test="${category_index == 1}">
								<td><div>${b.board_deptno}</div></td>
							</c:if>
							<td><div>${b.board_date}</div></td>
							<td><div>${b.board_readcount}</div></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div class="rows">
				<span>줄보기</span> <select class="form-control" id="viewcount">
					<option value="1">1</option>
					<option value="3">3</option>
					<option value="5">5</option>
					<option value="7">7</option>
					<option value="10" selected>10</option>
				</select>
			</div>

			<div class="center-block">
				<ul class="pagination justify-content-center">
					<c:if test="${page <= 1}">
						<li class="page-item"><a class="page-link gray">이전&nbsp;</a>
						</li>
					</c:if>
					<c:if test="${page > 1}">
						<li class="page-item"><a
							href="AdminboardList.net?page=${page - 1}" class="page-link">이전&nbsp;</a>
						</li>
					</c:if>

					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page}">
							<li class="page-item active"><a class="page-link">${a}</a></li>
						</c:if>
						<c:if test="${a != page}">
							<li class="page-item"><a href="AdminboardList.net?page=${a}"
								class="page-link">${a}</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${page >= maxpage}">
						<li class="page-item"><a class="page-link gray">&nbsp;다음</a>
						</li>
					</c:if>
					<c:if test="${page < maxpage}">
						<li class="page-item"><a
							href="AdminboardList.net?page=${page + 1}" class="page-link">&nbsp;다음</a>
						</li>
					</c:if>
				</ul>
			</div>
		</c:if>
		<%-- <c:if test="${listcount > 0}"> --%>

		<%-- 게시글이 없는 경우 --%>
		<c:if test="${listcount == 0}">
			<table class="table table-striped">
				<thead class="bg-dark text-white thead-dark">
					<tr>
						<th><input type="checkbox" class="selectAll"></th>
						<th><div>번호</div></th>
						<th><div>제목</div></th>
						<th><div>작성자</div></th>
						<c:if test="${category_index == 1}">
							<td><div>부서번호</div></td>
						</c:if>
						<th><div>작성날짜</div></th>
						<th><div>조회수</div></th>
					</tr>
				</thead>
			</table>
			<h3 style="text-align: center">등록된 글이 없습니다.</h3>
		</c:if>
	</div>
</body>
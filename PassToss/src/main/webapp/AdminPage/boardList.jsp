<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta charset="UTF-8">
<jsp:include page="../AdminPage/leftMenu.jsp" />
	<jsp:include page="../include/head.jsp"/>
<title>ADMIN - 게시물 관리</title>
</head>
<body>	
	<div class='container box_radius15'>
		<form action="AdminBoardList.net?" method="post">
			<div class="input-group">
				<select id="viewcount" name="search_field">
					<option value="0" selected>id</option>
					<option value="1">제목</option>
				</select> <input name="search_word" type="text" class="form-control"
					placeholder="검색어를 입력하세요" value="${search_word}">
				<button class="btn btn-primary" type="submit" name="searchbutton">검색</button>
			</div>
		</form>

		<div class="category">
			<ul>
				<li><a href="AdminboardList.net?category=0">사내게시판</a></li>
				<li><a href="AdminboardList.net?category=1">부서게시판</a></li>
			</ul>
		</div>
		<form action="AdminBoardDelete.net" method="post" id="board">

			<div class="title">${category} 게시물</div>
			<div class="count">| 작성된 게시물 수 : ${listcount}</div>
			<div class="board">
				<input type="image" src="image/delete.png" title="삭제">
			</div>
			<c:if test="${listcount > 0}">
				<table class="table">
					<thead>
						<tr>
							<th><input type="checkbox" id="selectAll"></input></th>
							<th>번호</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>작성일자</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="num" value="${listcount-(page-1)*limit}" />
						<c:forEach var="m" items="${boardlist}">
							<tr>
								<td><input type="checkbox" class="select" value="${m.id}"></td>
								<td><c:out value="${num}" /> <c:set var="num"
										value="${num + 1}" /></td>
								<td><button type="button" data-bs-toggle="modal"
										data-id="${m.id}" data-bs-target="#idInfo">${m.id}</button></td>
								<td>${m.name}</td>
								<td>${m.deptno}</td>
								<td>${m.joindate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="center-block">
					<ul class="pagination justify-content-center">
						<c:if test="${page <= 1}">
							<li class="page-item"><a class="page-link gray">이전&nbsp;</a>
							</li>
						</c:if>
						<c:if test="${page > 1}">
							<li class="page-item"><a
								href="AdminBoardList.net?page=${page-1}&search_field=${search_field}&search_word=${search_word}"
								class="page-link">이전</a>&nbsp;</li>
						</c:if>

						<c:forEach var="a" begin="${startpage}" end="${endpage}">
							<c:if test="${a == page}">
								<li class="page-item active"><a class="page-link">${a}</a></li>
							</c:if>
							<c:if test="${a != page}">
								<c:url var="go" value="AdminBoardList.net">
									<c:param name="search_field" value="${search_field}" />
									<c:param name="search_word" value="${search_word}" />
									<c:param name="page" value="${a}" />
								</c:url>
								<li class="page-item"><a href="${go}" class="page-link">${a}</a>
								</li>
							</c:if>
						</c:forEach>

						<c:if test="${page >= maxpage}">
							<li class="page-item"><a class="page-link gray">&nbsp;다음</a>
							</li>
						</c:if>
						<c:if test="${page < maxpage}">
							<c:url var="next" value="AdminBoardList.net">
								<c:param name="search_field" value="${search_field}" />
								<c:param name="search_word" value="${search_word}" />
								<c:param name="page" value="${page+1}" />
							</c:url>
							<li class="page-item"><a href="${next}" class="page-link">&nbsp;다음</a>
							</li>
						</c:if>
					</ul>
				</div>
			</c:if>
		</form>

		<c:if test="${listcount == 0 && empty search_word}">
			<h1>작성된 게시물이 없습니다.</h1>
		</c:if>

		<c:if test="${listcount == 0 && !empty search_word}">
			<h1>검색결과가 없습니다.</h1>
		</c:if>
	</div>
</body>
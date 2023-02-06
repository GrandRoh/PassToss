<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>ADMIN - 회원가입 목록</title>
<link rel="stylesheet" href="css/joinList.css" type="text/css">
</head>
<body>
	<jsp:include page="../AdminPage/leftMenu.jsp" />
	<div class='container box_radius15'>
		<form action="joinList.net" method="post">
			<div class="input-group">
				<select id="viewcount" name="search_field">
					<option value="0" selected>id</option>
					<option value="1">이름</option>
					<option value="2">부서번호</option>
				</select> <input name="search_word" type="text" class="form-control"
					placeholder="검색어를 입력하세요" value="${search_word}">
				<button class="btn btn-primary" type="submit" name="searchbutton">검색</button>
			</div>
		</form>

		<form action="authorize.net" method="post" id="authorize">
			<div class="title">회원가입 명단</div>
			<div class="count">| 회원가입 인원 : ${listcount}</div>
			<div class="authorize">
				<input type="image" src="image/authorize.png" title="가입승인">
			</div>
			<c:if test="${listcount > 0}">
				<table class="table table-striped">
					<thead>
						<tr>
							<th><input type="checkbox" id="selectAll"></input></th>
							<th>번호</th>
							<th>id</th>
							<th>이름</th>
							<th>부서번호</th>
							<th>가입일자</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="num" value="${limit * page - 9}" />
						<c:forEach var="m" items="${joinlist}">
							<tr>
								<td><input type="checkbox" name="select" value="${m.id}"></td>
								<td><c:out value="${num}" /> <c:set var="num"
										value="${num + 1}" /></td>
								<td><button type="button" data-bs-toggle="modal"
										data-bs-target="#exampleModal" id="idInfo">${m.id}</button></td>
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
								href="joinList.net?page${page-1}&search_field=${search_field}&search_word=${search_word}"
								class="page-link">이전</a>&nbsp;</li>
						</c:if>

						<c:forEach var="a" begin="${startpage}" end="${endpage}">
							<c:if test="${a == page}">
								<li class="page-item active"><a class="page-link">${a}</a></li>
							</c:if>
							<c:if test="${a != page}">
								<c:url var="go" value="joinList.net">
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
							<c:url var="next" value="joinList.net">
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
			<h1>회원이 없습니다.</h1>
		</c:if>

		<c:if test="${listcount == 0 && !empty search_word}">
			<h1>검색결과가 없습니다.</h1>
		</c:if>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<script>
		
	</script>
</body>
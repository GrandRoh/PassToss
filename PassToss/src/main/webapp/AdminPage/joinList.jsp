<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>ADMIN - 회원 관리</title>
<link rel="stylesheet" href="css/joinList.css" type="text/css">
<script src="js/jquery-3.6.3.js"></script>
<script src="js/joinList.js"></script>
</head>
<jsp:include page="../AdminPage/leftMenu.jsp" />
<body>
	<div class='container box_radius15'>
		<form action="AdminMemberList.net?authority=${authority}" method="post">
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

		<div class="title">${category} 명단</div>
		<div class="count">| 가입인원 : ${listcount}</div>

		<div class="dropdown float-end">
			<a class="btn btn-secondary btn-sm dropdown-toggle" role="button"
				data-bs-toggle="dropdown" aria-expanded="false"> 액션 </a>
			<ul class="dropdown-menu">
				<li><button type="button" class="dropdown-item authorize" data-bs-toggle="modal"
					data-bs-target="#modal">권한수정</button></li>
				<li><a class="dropdown-item delete" href="AdminDelete.net">삭제</a></li>
			</ul>
		</div>

		<div class="category">
			<ul>
				<li><a href="AdminMemberList.net?authority=0"
					class="authority-0">준회원</a></li>
				<li><a href="AdminMemberList.net?authority=1"
					class="authority-1">정회원</a></li>
			</ul>
		</div>

		<div>
			<c:if test="${listcount > 0}">
				<table class="table table-striped">
					<thead class="bg-dark text-white thead-dark">
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
						<c:set var="num" value="${listcount-(page-1)*limit}" />
						<c:forEach var="m" items="${joinlist}">
							<tr>
								<td><input type="checkbox" name="select" value="${m.id}"></td>
								<td><c:out value="${num}" /> <c:set var="num"
										value="${num - 1}" /></td>
								<td><button type="button" data-bs-toggle="modal" id="idInfo"
										data-id="${m.id}" data-bs-target="#modal">${m.id}</button></td>
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
								href="AdminMemberList.net?page${page-1}&search_field=${search_field}&search_word=${search_word}"
								class="page-link">이전</a>&nbsp;</li>
						</c:if>

						<c:forEach var="a" begin="${startpage}" end="${endpage}">
							<c:if test="${a == page}">
								<li class="page-item active"><a class="page-link">${a}</a></li>
							</c:if>
							<c:if test="${a != page}">
								<c:url var="go" value="AdminMemberList.net">
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
							<c:url var="next" value="AdminMemberList.net">
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
		</div>

		<c:if test="${listcount == 0 && empty search_word}">
			<h1>회원이 없습니다.</h1>
		</c:if>

		<c:if test="${listcount == 0 && !empty search_word}">
			<h1>검색결과가 없습니다.</h1>
		</c:if>
	</div>

	<!-- Modal -->
	<div class="modal fade test_modal" id="modal" tabindex="-1"
		aria-labelledby="idInfoLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="idInfoLabel">Modal title</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">내용</div>
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
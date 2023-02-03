<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>ADMIN - 회원가입 목록</title>

<style>
</style>
</head>
<body>
	<div class='container box_radius15'>
		<aside><jsp:include page="../AdminPage/leftMenu.jsp" /></aside>
		<c:if test="${listcount > 0}">
			<table class="table table-striped">
				<thead>
					<tr>
						<th colspan="3">회원가입 명단</th>
						<th colspan="2"><span>회원가입 인원 : ${listcount}</span></th>
					</tr>
					<tr>
						<th><input type="checkbox" id="selectAll"></input></th>
						<th>번호</th>
						<th>id</th>
						<th>이름</th>
						<th>부서번호</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="num" value="${limit*page-9}" />
					<c:forEach var="m" items="${joinlist}">
					<tr>
						<td><input type="checkbox" id="select"></td>
						<td><c:out value="${num}" /> <c:set var="num"
								value="${num + 1}" /></td>
						<td><a href="memberInfo.net?id=${m.id}">${m.id}</a>

						</td>
						<td>${m.name}</td>
						<td>${m.deptno}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div class="center-block">
				<ul class="pagination justify-content-center">
					<c:if test="${page <= 1}">
						<li class="page-item">
							<a class="page-link gray">이전&nbsp;</a>
						</li>
					</c:if>
					<c:if test="${page > 1}">
						<li class="page-item">
							<a href="joinList.net?page${page-1}&search_field=${search_field}&search_word=${search_word}"
								class="page-link">이전</a>&nbsp;
						</li>
					</c:if>
					
					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page}">
							<li class="page-item active">
								<a class="page-link">${a}</a>
							</li>
						</c:if>
						<c:if test="${a != page}">
							<c:url var="go" value="joinList.net">
								<c:param name="search_field" value="${search_field}"/>
								<c:param name="search_word" value="${search_word}"/>
								<c:param name="page" value="${a}"/>
							</c:url>
							<li class="page-item">
								<a href="${go}" class="page-link">${a}</a>
							</li>
						</c:if>
					</c:forEach>
					
					<c:if test="${page >= maxpage}">
						<li class="page-item">
							<a class="page-link gray">&nbsp;다음</a>
						</li>
					</c:if>
					<c:if test="${page < maxpage}">
						<c:url var="next" value="joinList.net">
								<c:param name="search_field" value="${search_field}"/>
								<c:param name="search_word" value="${search_word}"/>
								<c:param name="page" value="${page+1}"/>
						</c:url>
						<li class="page-item">
								<a href="${next}" class="page-link">&nbsp;다음</a>
						</li>
					</c:if>
				</ul>
			</div>
		</c:if>
	</div>
</body>
</html>
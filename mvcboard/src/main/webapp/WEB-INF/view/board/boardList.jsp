<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board list</title>
</head>
<body>
<header>
</header>
	<h1>게시판</h1>
	<div>
		<c:forEach var="m" items="${localNameList}">
			<a href="">${m.localName}(${m.cnt})</a>
		</c:forEach>
	</div>
	
	<!-- boardList시작 -->
	<div>
		<table border="1">
			<thead>
				<tr>
					<th>local name</th>
					<th>title</th>
					<th>id</th>
					<th>write_date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="b" items="${boardList}">
					<tr>
						<td>${b.localName}</td>
						<td>
							<a href="/board/boardOne?boardNo=${b.boardNo}">${b.boardTitle}
							</a>
						</td>
						<td>${b.memberId}</td>
						<td>${b.createdate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- boardList 끝 -->
		<!-- 페이지네이션 시작 -->
		<div>
			<c:if test="${currentPage > 1}">
				<a href="/board/boardList?currentPage=${currentPage - 1}">이전</a>
			</c:if>
			<c:if test="${currentPage < lastPage}">
				<a href="/board/boardList?currentPage=${currentPage + 1}">다음</a>
			</c:if>
		</div>
		<!-- 페이지네이션 끝 -->
	</div>
<footer>
</footer>
</body>
</html>
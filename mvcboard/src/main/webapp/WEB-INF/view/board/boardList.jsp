<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board list</title>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
	<h1>게시판</h1>
	
	<div>
		<a href="/board/addBoard">게시글 입력</a>
	</div>
	
	<div>
		<c:forEach var="m" items="${localNameList}">
			<a href="/board/boardList?localName=${m.localName}">${m.localName}(${m.cnt})</a>
		</c:forEach>
	</div>
	
	<!-- boardList시작 -->
	<div>
		<div>
			<table class="table">
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
		</div>
		<!-- boardList 끝 -->
		<!-- 페이지네이션 시작 -->
		<div>
			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-center">
					<c:if test="${currentPage > 1}">
						<li class="page-item">
							<a class="page-link" href="/board/boardList?currentPage=${beginPage - 1}&localName=${localName}" tabindex="-1">이전</a>
				    	</li>
					</c:if>
					<c:forEach var="i" begin="${beginPage}" end="${endPage}" step="1">
						<li class="page-item"><a class="page-link" href="/board/boardList?currentPage=${i}&localName=${localName}">${i}</a></li>
					</c:forEach>
					<c:if test="${endPage < lastPage}">
						<li class="page-item">
							<a class="page-link" href="/board/boardList?currentPage=${endPage + 1}&localName=${localName}">다음</a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>
		<!-- 페이지네이션 끝 -->
	</div>
</div>
</body>
</html>
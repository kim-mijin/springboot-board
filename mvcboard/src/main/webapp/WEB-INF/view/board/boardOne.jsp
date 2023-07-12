<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<title>board one</title>
</head>
<body>
<div class="container">
	<h1>상세보기</h1>
	<div>
		<div>
			<table>
				<tr>
					<td>board_no</td>
					<td>${board.boardNo}</td>
				</tr>
				<tr>
					<td>local_name</td>
					<td>${board.localName}</td>
				</tr>
				<tr>
					<td>board_title</td>
					<td>${board.boardTitle}</td>
				</tr>
				<tr>
					<td>board_content</td>
					<td>${board.boardContent}</td>
				</tr>
				<tr>
					<td>member_id</td>
					<td>${board.memberId}</td>
				</tr>
				<tr>
					<td>createdate</td>
					<td>${board.createdate}</td>
				</tr>
				<tr>
					<td>updatedate</td>
					<td>${board.updatedate}</td>
				</tr>
			</table>
		</div>
		<div>
			<a href="/board/modifyBoard?boardNo=${board.boardNo}">수정</a>
			<a href="/board/removeBoard?boardNo=${board.boardNo}">삭제</a>
		</div>
	</div>
</div>
</body>
</html>
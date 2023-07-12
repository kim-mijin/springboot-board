<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<title>modify board</title>
</head>
<body>
<div>
	<h1>게시글 수정</h1>
	<div>
		<form action="/board/modifyBoard" method="post">
			<div>
				<table>
					<tr>
						<td><label for="boardNo">board_no</label></td>
						<td>
							<input type="text" id="boardNo" name="boardNo" value="${board.boardNo}" readonly>
						</td>
					</tr>
					<tr>
						<td><label for="localName">local_name</label></td>
						<td>
							<select id="localName" name="localName" required>
								<option value="하남">하남</option>
								<option value="인천">인천</option>
								<option value="오산">오산</option>
								<option value="성남">성남</option>
								<option value="서울">서울</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label for="boardTitle">board_title</label></td>
						<td>
							<input type="text" id="boardTitle" name="boardTitle" value="${board.boardTitle}">
						</td>
					</tr>
					<tr>
						<td><label for="boardContent">board_content</label></td>
						<td>
							<textarea id="boardContent" name="boardContent" cols="30" rows="10">${board.boardContent}</textarea>
						</td>
					</tr>
					<tr>
						<td><label for="memberId">member_id</label></td>
						<td>
							<input type="text" id="memberId" name="memberId" value="${board.memberId}" readonly>
						</td>
					</tr>
				</table>
			</div>
			<div>
				<button type="submit">수정하기</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>
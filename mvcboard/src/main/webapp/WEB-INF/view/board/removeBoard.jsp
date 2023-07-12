<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>remove board</title>
</head>
<body>
	<div>
	<h1>게시글 삭제</h1>
	<div>
		<form action="/board/removeBoard" method="post">
			<div>
				<table>
					<tr>
						<td><label for="boardNo">board_no</label></td>
						<td>
							<input type="text" id="boardNo" name="boardNo" value="${board.boardNo}" readonly>
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
				<button type="submit">삭제하기</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>
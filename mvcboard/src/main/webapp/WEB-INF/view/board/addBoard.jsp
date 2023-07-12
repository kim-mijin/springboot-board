<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<title>add board</title>
</head>
<body>
<div class="container">
	<h1>게시글 입력</h1>
	<div class="d-flex">
		<form action="/board/addBoard" method="post">
			<div class="row">
				<div class="col-6"><label for="localName">local_name</label></div>
				<div class="col-6">
					<select id="localName" name="localName" required>
						<option value="하남">하남</option>
						<option value="인천">인천</option>
						<option value="오산">오산</option>
						<option value="성남">성남</option>
						<option value="서울">서울</option>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-6"><label for="boardTitle">title</label></div>
				<div class="col-6">
					<input type="text" id="localName" name="boardTitle" required>
				</div>
			</div>
			<div class="row">
				<div class="col-6"><label for="boardContent">content</label></div>
				<div class="col-6">
					<textarea id="boardContent" name="boardContent" cols="30" rows="10" required></textarea>
				</div>
			</div>
			<div class="row">
				<div class="col-6"><label for="memberId">id</label></div>
				<div class="col-6">
					<input type="text" id="memberId" name="memberId" required>
				</div>
			</div>
			<div>
				<button type="submit" class="btn btn-warning">추가하기</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>
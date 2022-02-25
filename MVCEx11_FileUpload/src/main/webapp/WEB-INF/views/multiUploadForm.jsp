<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 1개 업로드 하기</title>
</head>
<body>
	<form action="multiUploadOk" method="post" enctype="multipart/form-data">
		<input type="file" name="files" id="file1"/> <br />
		<input type="file" name="files" id="file2"/> <br />
		<input type="file" name="files" id="file3" /> <br />
		<input type="file" name="files" id="file4"/> <br />
		<input type="file" name="files" id="file5"/> <br />
		<textarea name="content" id="content" rows="5" cols="50" required="required" placeholder="내용 입력"></textarea>
		<br />
		<input type="submit" value="업로드하기" />
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	 <form action="/member/save" method="post">
	 <p>ID : <input type="text" name="memberEmail" placeholder="이메일"></p>
	 <p>PWD : <input type="text" name="memberPassword" placeholder="비밀번호"></p>
	 <p>Name : <input type="text" name="memberName" placeholder="이름"></p>
	 <p>Age : <input type="text" name="memberAge" placeholder="나이"></p>
	 <p>Mobile : <input type="text" name="memberMobile" placeholder="전화번호"></p>
	 <input type="submit" value="회원가입">
	 </form>
</body>
</html>
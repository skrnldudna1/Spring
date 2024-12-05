<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>

<style>
    @font-face {
         font-family: 'S-CoreDream-3Light';
         src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff') format('woff');
         font-weight: normal;
         font-style: normal;
    }
</style>

<style>
  @keyframes fadeIn {
    0% { opacity: 0; }
    100% { opacity: 1; }
  }

  .fade-in {
    animation: fadeIn 2s ease-in;
  }
</style>

</head>
<body>
    <!-- 오른쪽으로 50px 이동 추가 -->
    <h1 class="fade-in" style="font-family: 'S-CoreDream-3Light'; margin-left: 150px;"> Hello Springframework 📑 </h1><br><br><br><br>

    <!-- 각 링크에 margin-left 추가하여 오른쪽 이동 -->
    <a style="font-family: 'S-CoreDream-3Light'; text-decoration: none; color: #333; margin-left: 170px;" href="/board/save"> ▫️ 글 작성</a><br><br><br><br>
    <a style="font-family: 'S-CoreDream-3Light'; text-decoration: none; color: #333; margin-left: 170px;" href="/board/"> ▫️ 글 목록</a><br><br><br><br>
	<a style="font-family: 'S-CoreDream-3Light'; text-decoration: none; color: #333; margin-left: 170px;" href="/board/paging"> ▫️ 페이징 목록</a>

</body>
</html>

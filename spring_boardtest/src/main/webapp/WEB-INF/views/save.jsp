<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>save</title>
    
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
<br><br>
<h1 class="fade-in" style="font-family: 'S-CoreDream-3Light'; margin-left: 150px;"> 작성 폼 </h1><br><br>

    <!-- 수정된 폼: 왼쪽에서 50px 정도 오른쪽으로 이동 -->
    <form action="/board/save" method="post" style="margin-left: 150px;">
        <input type="text" name="boardWriter" placeholder="작성자"><br><br>
        <input type="text" name="boardPass" placeholder="비밀번호"><br><br>
        <input type="text" name="boardTitle" placeholder="제목"><br><br>
        <textarea name="boardContents" cols="30" rows="10" placeholder="내용을 입력하세요"></textarea><br><br>
        <input type="submit" value="작성">
    </form>
</body>
</html>

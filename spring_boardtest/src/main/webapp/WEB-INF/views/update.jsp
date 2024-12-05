<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update.jsp</title>
    
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
<h1 class="fade-in" style="font-family: 'S-CoreDream-3Light'; margin-left: 150px;"> 글 수정 📑 </h1><br><br><br><br>
<form action="/board/update" method="post" name="updateForm" style="margin-left: 150px;">
    <input type="hidden" name="id" value="${board.id}" ><br><br>
    <input type="text" name="boardWriter" value="${board.boardWriter}" disabled><br><br>
    <input type="text" name="boardPass" id="boardPass" placeholder="비밀번호"><br><br>
    <input type="text" name="boardTitle" value="${board.boardTitle}"><br><br>
    <textarea name="boardContents" cols="30" rows="10">${board.boardContents}</textarea><br><br>
    <input type="button" value="수정" onclick="updateReqFn()">
</form>

</div>
</body>
<script>
    const updateReqFn = () => {
        const passInput = document.getElementById("boardPass").value;
        const passDB = '${board.boardPass}';
        if (passInput == passDB) {
            document.updateForm.submit();
        } else {
            alert("비밀번호가 일치하지 않습니다!!");
        }
    }
</script>
</html>
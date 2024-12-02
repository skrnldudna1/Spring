<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<style>
    .container {
        text-align: center;
        font-family: Arial, sans-serif;
    }
    h1 {
        color: #7BAEE1;
    }
    .link-section-container {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 40px;
        margin-top: 20px;
    }
    .link-section {
        text-align: center;
    }
    .emoji {
        font-size: 2rem;
        margin-bottom: 10px;
    }
</style>
</head>
<body>
  <div class="container">
        <h1>Hello Spring Framework 회원 프로젝트</h1>

        <div class="link-section-container">
            <div class="link-section">
                <!-- 회원 가입 -->
                <div class="emoji">ヽ(＊>∇<)ﾉ</div>
                <a href="/member/save">회원 가입</a>
            </div>

            <div class="link-section">
                <!-- 로그인 -->
                <div class="emoji">(*´﹃｀*)</div>
                <a href="/member/login">로그인</a>
            </div>

            <div class="link-section">
                <!-- 회원 목록 조회 -->
                <div class="emoji">(*｀へ´*)</div>
                <a href="/member/">회원목록 조회</a>
            </div>
        </div>
    </div>

</body>
</html>
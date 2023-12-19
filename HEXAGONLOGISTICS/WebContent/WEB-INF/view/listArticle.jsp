<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<style>


header h1 {
  text-align: center;
}

footer{
text-align:center;
}

  body {
    display: flex;
    flex-direction: column;
	font-family: Arial, sans-serif;
  }


nav {
  display: flex;
  justify-content: center; /* 좌우 여백을 최대로 확보하고 버튼을 오른쪽으로 정렬 */
  align-items: center; /* 수직 정렬을 위해 추가 */
  margin-top: 20px;
  margin-bottom: 25px;
}

    nav ul {
      list-style: none;
      padding: 0;
      display: flex;
      margin: 0;
    }

    nav li {
      margin: 0 50px; /* 메뉴 간 간격 조절 */
    }

    nav a {
      text-decoration: none;
      padding: 15px 40px; /* 위아래 여백, 좌우 여백 */
      border-radius: 10px; /* 모서리 둥글게 만들기 */
      background-color: #3498db; /* 배경 색상 */
      color: #fff; /* 글자색 */
      transition: background-color 0.3s ease; /* 배경 색상 변경 효과 */
    }

    nav a:hover {
      background-color: #2980b9; /* 호버 시 배경 색상 변경 */
    }
    .content, section {
        margin-bottom: 20px;
    }
    .content {
        display: flex;
        justify-content: space-between;
    }
    table {
        border-collapse: collapse;
        width: 100%;
        margin-bottom:10px;
    }
    th, td {
        border: 1px solid black;
        text-align: center;
        padding: 8px;
    }
    th {
        background-color: #f2f2f2;
    }
    button {
        background-color: #add8e6; /* 연한 푸른색 */
        color: black;
        padding: 10px 15px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    button:hover {
        background-color: #9acfe4;
    }
    
        .delete-button {
        background-color: #add8e6; /* 연한 푸른색 */
        color: black;
        padding: 10px 15px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    
    .submit-button {
    background-color: #4CAF50; /* 짙은 녹색 */
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    float: right;
    }
      section {
    position: relative;
	padding: 20px; /* 적절한 여백 추가 */
  }
  
  #loginButton {
float: right;
}

#loginButton a {
  display: inline-block;
  padding: 10px;
  border-radius: 5px;
  background-color: #3498db;
  color: #fff;
  text-decoration: none;
  text-align: center;
}

#loginButton a:hover {
  background-color: #2980b9;
}
    
</style>
</head>
<body>
	<link rel="stylesheet" type="text/css" href="css/main.css">
<header>

<a href="index.jsp"><h1>Hexagon Logistics ver 1.0</h1></a>

    <div id="loginButton">
    <u:notLogin>
    <a href="login.do">로그인</a>
    </u:notLogin>
    <u:isLogin>
    ${authUser.name}님, 안녕하세요.
<a href="logout.do">[로그아웃하기]</a>
<!-- <a href="changePwd.do">[암호변경하기]</a> -->
</u:isLogin>


  </div>
</header>
<nav>
  <ul>
    <li><a href="productlist.do">재고현황</a></li>
    <li><a href="productregi.do">재고관리</a></li>
    <li><a href="registSales.do">판매/이력</a></li>
    <li><a href="list.do">공지사항</a></li>
  </ul>

</nav>
<table border="1">
<tr>
<td colspan="4"><a href="write.do">[게시글쓰기]</a></td>
</tr>
<tr>
<td>번호</td>
<td>제목</td>
<td>작성자</td>
<td>조회수</td>
</tr>
<c:if test="${articlePage.hasNoArticles()}">
<tr>
<td colspan="4">게시글이 없습니다.</td>
</tr>
</c:if>
<c:forEach var="article" items="${articlePage.content}">
<tr>
<td>${article.number}</td>
<td>
<a href="read.do?no=${article.number}&pageNo=${articlePage.currentPage}">
<c:out value="${article.title}"/>
</a>
</td>
<td>${article.writer.name}</td>
<td>${article.readCount}</td>
</tr>
</c:forEach>
<c:if test="${articlePage.hasArticles()}">
<tr>
<td colspan="4">
<c:if test="${articlePage.startPage > 5}">
<a href="list.do?pageNo=${articlePage.startPage - 5}">[이전]</a>
</c:if>
<c:forEach var="pNo"
begin="${articlePage.startPage}"
end="${articlePage.endPage}">
<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
</c:forEach>
<c:if test="${articlePage.endPage < articlePage.totalPages}">
<a href="list.do?pageNo=${articlePage.startPage + 5}">[다음]</a>
</c:if>
</td>
</tr>
</c:if>
</table>
</body>
</html>
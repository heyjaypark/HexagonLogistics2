<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 읽기</title>

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
        width: 80%;
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
    
     section {
   display: flex;
       justify-content: center;
       align-items: center;
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
    margin-right: 2px;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    float: center;
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
<header>
<h1><a href="index.jsp">Hexagon Logistics ver 1.0</a></h1>
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

<div align="center">
	<!-- <link rel="stylesheet" type="text/css" href="css/main.css"> -->
<table border="1">
<tr>
<td>번호</td>
<td>${articleData.article.number}</td>
</tr>
<tr>
<td>작성자</td>
<td>${articleData.article.writer.name}</td>
</tr>
<tr>
<td>제목</td>
<td><c:out value='${articleData.article.title}'/></td>
</tr>
<tr>
<td>내용</td>
<td><u:pre value='${articleData.content}'/></td>
</tr>
<tr>
<td colspan="2">
<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo}" />
<a href="list.do?pageNo=${pageNo}"><button class=submit-button>목록</button></a>
<c:if test="${authUser.id == articleData.article.writer.id}">
<a href="modify.do?no=${articleData.article.number}"><button class=submit-button>게시글수정</button></a>
<a href="delete.do?no=${articleData.article.number}"><button class=submit-button>게시글삭제</button></a>
</c:if>
</td>
</tr>
</table>
</div>

</body>
</html>
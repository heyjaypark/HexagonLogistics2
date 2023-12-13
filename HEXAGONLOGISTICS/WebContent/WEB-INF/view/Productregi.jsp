<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 관리</title>
</head>

<body>
<link rel="stylesheet" type="text/css" href="css/main.css">
	<header>
<a href="index.jsp">
<h1>Hexagon Logistics ver 1.0</h1>
</a>
    <div id="loginButton">
    
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
    <li><a href="#">판매/이력</a></li>
    <li><a href="list.do">공지사항</a></li>
  </ul>

</nav>
<div align="center">
<form action="productregi.do" method="post">	
	<table border="1">

<tr>
<td>품목코드</td>
<td>품목명</td>
<td>서울점</td>
<td>수원점</td>
<td>인천점</td>
<td>가격</td>
</tr>
<tr>
<td>
<input type="text" name="p_no" size="27%" value="${param.p_no }"/>

</td>
<td>
<input type="text" name="p_name" size="27%"/>
</td>
<td>
<input type="text" name="p_seoul" size="27%"/>
</td>
<td>
<input type="text" name="p_suwon" size="27%"/>
</td>
<td>
<input type="text" name="p_incheon" size="27%"/>
</td>
<td>
<input type="text" name="price" size="27%"/>
</td>
</tr>
</table>

<c:if test="${errors.numberInsert}">품목코드를 입력하세요.</c:if>
<c:if test="${errors.duplicateno}">이미 등록된 품목코드입니다.</c:if>
<input type="submit" value="등록"/>

</form>
<a href="/HEXAGONLOGISTICS/productupdate.do"><input type="submit" value="수정"/>	</a>

</div>




</body>
</html>
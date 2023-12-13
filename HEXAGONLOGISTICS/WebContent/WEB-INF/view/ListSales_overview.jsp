<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
a {
	text-decoration: none;
	color: #000;
}

header {
	margin-bottom: 2px;
}

header>a>h1 {
	text-align: center;
}

header>div {
	
}

nav {
	display: flex;
	justify-content: center;
}

nav a {
	font-size: 20px;
	margin: 2em;
}

div>form {
	display: inline-block;
}
table {
	width: 1000px;
	cellpadding: 20;
}
</style>
</head>
<body>
	<header>
		<a href=index.jsp><h1>Hexagon Logistics ver 1.0</h1></a>
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
		<a href="productlist.do">재고현황</a> 
		<a href="productsearch.do">재고관리</a> 
		<a href="#">판매/이력</a>
		<a href="list.do">공지사항</a>
	</nav>
	<div>
		<button>판매 등록</button>
		<form action="method" name="p_no" method="get">
			<div>
				<label for="notice">품목 코드</label> <input type="text" name="p_no"
					id="p_no" required>
			</div>
			<div>
				<input type="submit" value="검색">
			</div>
		</form>
	</div>
	<div align="center">
		<table border="1">
			<tr>
				<th>거래번호</th>
				<th>품목코드</th>
				<th>품목명</th>
				<th>서울점</th>
				<th>수원점</th>
				<th>인천점</th>
				<th>날짜</th>
			</tr>
			<!-- 아래 salesPage란 이름은 내가 가라로 넣은 것  -->
			<c:if test="${salesPage.hasNoArticles()}">
				<tr>
					<td colspan="7">게시글이 없습니다</td>
				</tr>
			</c:if>
			
			<c:forEach var="sales" items="${salesPage.content}">
				<tr>
					<!-- 거래 번호  -->
					<td>${sales.s_Num}</td>
					<!-- 품목번호 -->
					<td>${sales.p_No}</td>
					<!-- 타이틀 -->
					<td><a
						href="read.do?no=${article.number}&pageNo=${articlePage.currentPage}">
							<c:out value="${article.title}" />
					</a></td>
					<!-- 서울점 -->
					<td>${sales.p_Seoul}</td>
					<!-- 수원점 -->
					<td>${sales.p_Suwon}</td>
					<!-- 인천점 -->
					<td>${sales.p_Incheon}</td>
					<!-- 날짜 -->
					<td>${sales.p_Date}</td>
				</tr>
			</c:forEach>
			<c:if test="${salesPage.hasArticles()}">		
			<tr>
				<td colspan="7"></td>
			</tr>	
			<tr>
			<td colspan="4">
				<c:if test="${salesPage.startPage > 5}">
					<a href="saleslist.do?pageNo=${productPage.startPage - 5}">[이전]</a>
				</c:if>
				<c:forEach var="pNo"
					begin="${salesPage.startPage}"
					end="${salesPage.endPage}">
					<a href="saleslist.do?pageNo=${pNo}">[${pNo}]</a>
				</c:forEach>
				<c:if test="${salesPage.endPage < salesPage.totalPages}">
				<a href="saleslist.do?pageNo=${salesPage.startPage + 5}">[다음]</a>
				</c:if>
			</td>
		</tr>
		</c:if>
		</table>
	</div>
</body>
</html>
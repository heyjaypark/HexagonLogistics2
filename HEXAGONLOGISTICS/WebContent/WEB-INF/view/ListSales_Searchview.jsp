<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
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
        width: 85%;
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
  <ul>
    <li><a href="productlist.do">재고현황</a></li>
    <li><a href="productregi.do">재고관리</a></li>
    <li><a href="registSales.do">판매/이력</a></li>
    <li><a href="list.do">공지사항</a></li>
  </ul>
</nav>
	<div>
		<a href="registSales.do"><button>판매 등록</button></a>
		
		<form action="salesSearch.do" name="p_no" method="post">
			<div>
				<select name="select_num" >
    				<option value="1">거래번호</option> 
    				<option value="2">품목번호</option>
				</select>
				
				<input type="text" name="code" value = "${param.code}" required>
			</div>
			<div>
				<input type="submit" value="검색">
				<c:if test="${errors.Notnum}">숫자만 입력하세요.</c:if>
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
				<th>가격</th>
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
					<!-- 품목명 -->
					<td>${sales.p_Name}</td>
					<!-- 서울점 -->
					<td>${sales.s_Seoul}</td>
					<!-- 수원점 -->
					<td>${sales.s_Suwon}</td>
					<!-- 인천점 -->
					<td>${sales.s_Incheon}</td>
					<!-- 날짜 -->
					<td>${sales.s_Date}</td>
					<!-- 가격 -->
					<td>${sales.price}</td>
				</tr>
			</c:forEach>
			<c:if test="${salesPage.hasArticles()}">		
			<tr>
				<td colspan="7"></td>
			</tr>	
			<tr>
			<td colspan="4">
				<c:if test="${salesPage.startPage > 5}">
					<a href="salesSearch.do?select_num=1&code=${param.code}&pageNo=${salesPage.startPage - 5}">[이전]</a>
				</c:if>
				<c:forEach var="pNo"
					begin="${salesPage.startPage}"
					end="${salesPage.endPage}">
					<a href="salesSearch.do?select_num=1&code=${param.code}&pageNo=${pNo}">[${pNo}]</a>
				</c:forEach>
				<c:if test="${salesPage.endPage < salesPage.totalPages}">
				<a href="salesSearch.do?select_num=1&code=${param.code}&pageNo=${salesPage.startPage + 5}">[다음]</a>
				</c:if>
			</td>
		</tr>
		</c:if>
		</table>
	</div>
</body>
</html>
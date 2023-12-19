<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매등록</title>
<script> // 행 추가하는 버튼 함수
function addRow() {
    var table = document.getElementById("myTable");
    var newRow = table.insertRow(-1); // 테이블 마지막에 새 행 추가

    var cell1 = newRow.insertCell(0); // 행에 새로운 셀 추가
    var input1 = document.createElement("input");
    input1.type = "text";
    input1.name = "p_no[]";
    cell1.appendChild(input1);

    var cell2 = newRow.insertCell(1); // 행에 새로운 셀 추가
    var input2 = document.createElement("input");
    input2.type = "text";
    input2.name = "p_name[]";
    cell2.appendChild(input2);
    
    newRow.insertCell(2);
    
    var cell4 = newRow.insertCell(3); // 행에 새로운 셀 추가
    var input4 = document.createElement("input");
    input4.type = "text";
    input4.name = "s_seoul[]";
    cell4.appendChild(input4);

    newRow.insertCell(4);

    var cell6 = newRow.insertCell(5); // 행에 새로운 셀 추가
    var input6 = document.createElement("input");
    input6.type = "text";
    input6.name = "s_suwon[]";
    cell6.appendChild(input6);

    newRow.insertCell(6);


    var cell8 = newRow.insertCell(7); // 행에 새로운 셀 추가
    var input8 = document.createElement("input");
    input8.type = "text";
    input8.name = "s_incheon[]";
    cell8.appendChild(input8);

    var cell9 = newRow.insertCell(8); // 행에 새로운 셀 추가
    var input9 = document.createElement("input");
    input9.type = "text";
    input9.name = "price[]";
    cell9.appendChild(input9);

    var cell10 = newRow.insertCell(9); // 행에 새로운 셀 추가
    var input10 = document.createElement("input");
    input10.type = "date";
    input10.name = "s_date[]";
    cell10.appendChild(input10);

    var cell11 = newRow.insertCell(10); // 행에 새로운 셀 추가
    var deleteButton = document.createElement("button");
    deleteButton.type = "button";
    deleteButton.className = "delete-button";
    deleteButton.innerHTML = "삭제";
    deleteButton.onclick = function() {
        deleteRow(this);
    };
    cell11.appendChild(deleteButton);
}

function deleteRow(element) {
    var row = element.parentNode.parentNode; // 버튼의 부모의 부모는 해당 행(tr)입니다.
    var table = row.parentNode; // 행을 포함한 테이블을 찾습니다.
    table.deleteRow(row.rowIndex); // 해당 행을 삭제합니다.
}



<c:if test="${errors.salesDateError}">
alert("판매날짜를 선택해주세요.");
</c:if>

<c:if test="${errors.salesError}">
    alert("최소한 하나의 판매량을 입력해주세요.");
</c:if>

<c:if test="${errors.databaseError}">
alert("${databaseErrorMessage}");
</c:if>

<c:if test="${errors.numberFormat}">
alert("숫자만 입력 가능합니다.");
</c:if>

<c:if test="${errors.unknwonError}">
alert("${unknownErrorMessage}");
</c:if>

</script>




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

    <div class="content">
        <div>
            <form action="salesList.do" method="get">
                <button type="submit">판매이력</button>
            </form>
        </div>
        <div>
            <c:if test="${errors.p_no}">품목번호를 입력하세요.</c:if>
            <form action="selectSales.do" method="post">
                <input type="text" name="p_no" placeholder="품목번호를 입력하세요.">
                <button type="submit">검색</button>
            </form>
        </div>
    </div>

    <section>
    <form action="registSales.do" method="POST">
    <table id="myTable">
    <tr>
        <th>품목번호</th>
        <th>품목이름</th>
        <th>서울점재고</th>
        <th>서울점판매</th>
        <th>인천점재고</th>
        <th>인천점판매</th>
        <th>수원점재고</th>
        <th>수원점판매</th>
        <th>단가</th>
        <th>판매일</th>
        <th>action</th>
    </tr>
    <c:if test="${not empty prod.p_no }">
    <tr>
    <td><input type="hidden" name="p_no" value="${prod.p_no}">${prod.p_no }</td>
    <td>${prod.p_name }</td>
    <td>${prod.p_seoul }</td>
    <td><input type="text" name="s_seoul"></td>
    <td>${prod.p_suwon }</td>
        <td><input type="text" name="s_suwon"></td>
    <td>${prod.p_incheon }</td>
        <td><input type="text" name="s_incheon"></td>
    <td>${prod.price }</td>
    <td><input type="date" name="s_date" id="today"></td>
    <td><button onclick="deleteRow(this)">삭제</button>
    </tr>
    </c:if>
    </table>
<!--    <button onclick="addRow()">+</button>-->
    <button type="submit" class="submit-button">등록</button>
    </form>
    </section>
    <footer>
<p>&copy; 2023 Hexagon Logistics. All rights reserved.</p>
</footer>
</body>

</html>
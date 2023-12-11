<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
</head>
<body>

<table border="1">
<tr>
</tr>
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
${product1.p_no}
</td>
<td>
${product1.p_name}
</td>
<td>
${product1.p_seoul}
</td>
<td>
${product1.p_suwon}
</td>
<td>
${product1.p_incheon}
</td>
<td>
${product1.price}
</td>
</tr>
</table>

</body>
</html>
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
<form action="productsearch.do" method="post">
<p>
품목코드:<input type="text" name="p_no" value="${param.p_no}"><input type="submit" value="검색">
</p>
</form>

<table border="1">
<tr>

</tr>
<tr>
<td>품목코드</td>
<td>품목명</td>
<td>서울점</td>
<td>수원점</td>
<td>인천점</td>

</tr>



<c:if test="${productPage.hasNoArticles()}">
<tr>
<td colspan="4">물품이 없습니다.</td>
</tr>
</c:if>
|


<c:forEach var="product" items="${productPage.content}">
<tr>
<td>
${product.p_no}
</td>
<td>
${product.p_name}
</td>
<td>
${product.p_seoul}
</td>
<td>
${product.p_suwon}
</td>
<td>
${product.p_incheon}
</td>
<td>
${product.price}
</td>

</tr>
</c:forEach>
<c:if test="${productPage.hasArticles()}">
<tr>
<td colspan="4">
<c:if test="${productPage.startPage > 5}">
<a href="productlist.do?pageNo=${productPage.startPage - 5}">[이전]</a>
</c:if>
<c:forEach var="pNo"
begin="${productPage.startPage}"
end="${productPage.endPage}">
<a href="productlist.do?pageNo=${pNo}">[${pNo}]</a>
</c:forEach>
<c:if test="${productPage.endPage < productPage.totalPages}">
<a href="productlist.do?pageNo=${productPage.startPage + 5}">[다음]</a>
</c:if>
</td>
</tr>
</c:if>
</table>
</body>
</html>
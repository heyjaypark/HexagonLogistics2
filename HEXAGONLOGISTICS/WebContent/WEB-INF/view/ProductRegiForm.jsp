<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입</title>
</head>
<body>
<form action="join.do" method="post">
<p>
품목코드:<br/><input type="text" name="p_no" value="${param.p_no}">
<c:if test="${errors.id}">품목코드를 입력하세요.</c:if>
<c:if test="${errors.duplicateId}">이미 등록된 코드입니다.</c:if>
</p>
<p>
제품이름:<br/><input type="text" name="p_name" value="${param.p_name}">
<c:if test="${errors.name}">제품이름을 입력하세요.</c:if>
</p>


<input type="submit" value="가입">
</form>

</body>
</html>
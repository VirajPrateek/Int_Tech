<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
<form action="q5.jsp">
	<input type="text" name="word" placeholder="Enter word"></br>
	<input type="radio" name="place" value="even">Even
	<br><input type="radio" name="place" value="odd"> Odd
	<br><input type="Submit" name="submit" value="Play!">
	
	<c:if test="${param.word!=''}">
	<c:set var="str" value ="${param.word}"/>	 
	 <c:if test="${param.place==even}">
	  		<c:forEach var="i" begin="0" end="${fn:length(str)}" step="2">
  			<c:set var="newstr" value="${fn:substring(str, i, i + 1)}" /> 
			</c:forEach>
	  </c:if>
	  <c:if test="${param.place==odd}">
	  		<c:forEach var="i" begin="1" end="${fn:length(str)}" step="2">
  			<c:set var="newstr" value="${fn:substring(str, i, i + 1)}" /> 
			</c:forEach>
	  </c:if>
	</c:if>
</form>
</body>
</html>
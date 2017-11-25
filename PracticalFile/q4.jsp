<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
 <title>Greetings</title>
</head>
<body>
<form action="q4.jsp" method="post">
Enter your name <input type="text" name="uname" >
<input type="hidden" name="submitted" value="true">
   <input type="submit" value="Submit">

<c:if test="${param.submitted}">
   <% java.util.Date clock = new java.util.Date( ); %>
    <% if (clock.getHours( ) < 12) { %>
      <h2>Good morning!</h2>
   <% } else if (clock.getHours( ) < 17) { %>
      <h2>Good day!</h2>
   <% } else { %>
      <h2>Good evening!</h2>
   <% } %>
   <c:out value="${param.uname}"/>
</c:if>
 
</form>

</html>
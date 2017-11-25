<%@ page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Your Answer</title>
</head>
<body>
 Using Expression language :
 <br><br>
  Your Answer is
  <c:if test="${param.operation=='a'}">
     ${param.first + param.second}
  </c:if>
  <c:if test="${param.operation=='s'}">
     ${param.first - param.second}
  </c:if>
  <c:if test="${param.operation=='m'}">
     ${param.first * param.second}
  </c:if>
 <br><br><br><br><br>
 Using scriplets :
 <br>
  Your Answer is
  <% String op=request.getParameter("operation");
     int num1=Integer.parseInt(request.getParameter("first"));
     int num2=Integer.parseInt(request.getParameter("second"));
     if(op.equals("a"))
      { out.print(num1+num2);}
     else if(op.equals("s"))
      { out.print(num1-num2);}
     else if(op.equals("m"))
      { out.print(num1*num2);}
  %>
</body>
</html>
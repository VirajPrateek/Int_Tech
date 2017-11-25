<%@ page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <title>Number Printer</title>
</head>
<body>
  <form method="post" action="q1.jsp">
   Enter the number of levels <input type="text" name="no" min="1">
   <input type="submit" value="Submit">
   <input type="hidden" name="submitted" value="true">
   <c:if test="${param.submitted}">
     <br><br><br>
     Using scriplets :
         <% int num=Integer.parseInt(request.getParameter("no"));
             for(int i=1;i<=num;i++)
             {  
         %>
            <br>
         <%
                for(int j=1;j<=i;j++)
                  { out.print(j + " ");  }
             }  
         %>
     <br><br><br><br>
      Using  &ltc:forEach&gt loop :
      <c:forEach begin="1" end="${param.no}" step="1" var="current">
         <br>
         <c:forEach begin="1" end="${current}" step="1" var="print">
           <c:out value="${print}"/>
         </c:forEach>  
      </c:forEach>
   </c:if>
  </form>
</body>
</html>
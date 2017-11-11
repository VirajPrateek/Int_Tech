<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <title>Greetings</title>
</head>
<body>
   <header>
      <h1>Greeting User</h1><h3>(Based on time of Day)</h3>
   </header>
   <article>
      <form action="greetings.jsp" method="post">
         <input type="text" name="uname" placeholder="Your name"><br/>
         <input type="hidden" name="submitted" value="true">
         <input type="submit" value="Submit">
         <br />
         <c:if test="${param.submitted}">
            <% java.util.Date clock = new java.util.Date( ); %>
             <% if (clock.getHours() < 12) { %>
               Good morning!
            <% } else if (clock.getHours() < 17) { %>
               Good day!
            <% } else { %>
               Good evening!
            <% } %>
            <c:out value="${param.uname}"/>
         </c:if>
   </form>
   </article>
   <footer>Code by: Kumar Prateek Viraj</footer>
</body>
<style type="text/css">
   body{
      text-align: center;
   }
   header,footer{
      text-align: center;
      background-color: black;
      color: white;
   }
   input{
      text-align: center;
      width: 200px;
      padding: 8px;
      margin-bottom: 5px;
   }
   form{
      color: red;
   }
</style>
</html>
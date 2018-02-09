<%-- 
    Document   : homes
    Created on : 09-Feb-2018, 06:07:31
    Author     : shivasairajuomkar
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <%@include file="student.jsp" %>
    <body>
         <div class="container">
             <h1>Quiz</h1>
             <h2>please select quiz</h2>
             <ul>
             <c:forEach items="${quizlist}" var="qz">
                 <li>
                 <h3> <a href="takeqz?qzid=${qz.quizid}&qzname=${qz.quizname}">${qz.quizname}</a></h3>
                 </li>
             </c:forEach>
             </ul>
         </div>
    </body>
</html>

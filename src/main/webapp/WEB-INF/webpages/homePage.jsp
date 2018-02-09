<%-- 
    Document   : homePage
    Created on : 09-Feb-2018, 05:27:04
    Author     : shivasairajuomkar
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Quiz Manager</title>
	<link rel="stylesheet" type="text/css" href="hpage.css">

	
	<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!--  <link rel="stylesheet" href="/resources/styles.css">  -->

</head>
<body>


<div class = "container">




<div class = "container">

	<div class="wrapper">
		<form:form action="logincheck" modelAttribute="User" name="Login_Form" class="form-signin">   
		    <h3 class="form-signin-heading">Welcome to Quiz Manager! Please Sign In</h3>
			  <hr class="colorgraph"><br>
			  <c:if test="${not empty error}">
   <font color="red">Error: ${error}</font>
</c:if>
			  <form:input path="userLoginId" class="form-control"  placeholder="Username" required="" autofocus=""  />
			  
			  <form:input path="userpassword" type="password" class="form-control"  placeholder="Password" required=""/>
			  <button class="btn btn-lg btn-primary btn-block"  name="Submit" value="Login" type="Submit">Login</button>  			
                  
                       
                </form:form>	
                          
                          
                          


              
	</div>
</div>
</div>
</body>







</html>

<%-- 
    Document   : student
    Created on : 09-Feb-2018, 05:18:46
    Author     : shivasairajuomkar
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
    <head>


   <link rel="stylesheet" type="text/css" href="mainCSS.css">

        <title>Quiz Manager</title>


        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!--  <link rel="stylesheet" href="/resources/styles.css">  -->



        



    </head>
    <body>


        <nav class="navbar navbar-inverse navbar-fixed-top navbar-custom">

            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#" ><font color="white" style="font-style:oblique ">Quiz Manager</font></a>
                </div>
                <ul class="nav navbar-nav">
                    <li ><a href="shome">DashBoard</a></li>
                    
                </ul>
                
           

<p  class="right-button">${User.userName} &nbsp;&nbsp;
                    <a href="home"> <button type="button" class="btn btn-default btn-sm">
                            <span class="glyphicon glyphicon-user"></span> logout
                        </button></a>
                </p>
            </div>
        </nav>

    </body>
</html>
<br/><br/>
<%-- 
    Document   : createQuizz
    Created on : 09-Feb-2018, 00:00:07
    Author     : shivasairajuomkar
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>create quiz</title>
        <script>
            function validate(){
                q1=document.getElementById("quizname").value;
                if(q1==""){
                    alert("please enter quiz name");
                    return false;
                }else{
                      return true;
                }
              
            }
            
            </script>
        <%@include file="professors.jsp" %>
    </head>
    <body>
        <div class="container">
            
            <br/> <br/> <br/> <br/> <br/>
            <form onsubmit="return validate()" action="submitqz">
               Quiz Name: <input type="text" id="quizname" class="form-control" placeholder="quiz name" name="quizname"/>
                
              
            
            
              <c:forEach items="${questionsList}" var="question">
            
            <div id="accordion" role="tablist" aria-multiselectable="true">
  <div class="card">
    <div class="card-header" role="tab" id="headingOne">
      <h5 class="mb-0"> <h2><input type="checkbox" name="questions"  value="${question.question_id}"/>
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne${question.question_id}" aria-expanded="true" aria-controls="collapseOne${question.question_id}">
            ${question.question_title}
        </a></h2>
      </h5>
    </div>

    <div id="collapseOne${question.question_id}" class="collapse" role="tabpanel" aria-labelledby="headingOne">
      <div class="card-block">
          <ol type="A">
          <li>  ${question.option_one}</li>
           <li> ${question.option_two}</li>
           <li> ${question.option_three}</li>
           <li> ${question.option_four}</li>
           
           
          </ol>
      
      </div>
    </div>
  </div>
            </c:forEach>

                  <input type="submit"/>
                
            </form>

</div>
            
    </body>
</html>

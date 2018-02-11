<%-- 
    Document   : addQuestion
    Created on : 08-Feb-2018, 23:56:46
    Author     : shivasairajuomkar
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <script>
            
            function myFunction(){
               
                
                
                var q1=document.getElementById("questiontext").value ;
                var o1=document.getElementById("option_one").value;
                var o2=document.getElementById("option_two").value;
                var o3=document.getElementById("option_three").value;
                var o4=document.getElementById("option_four").value;
                
                
                if(q1==""){
                    alert(" enter question title");
                     
                return false;
                }else if(o1==""){
                    alert(" enter option one");
                     
                return false;
                }else
                if(o2==""){
                    alert(" enter option two");
                     
                return false;
                }else
                     if(o3==""){
                    alert(" enter option three");
                     
                return false;
                }else
                if(o4==""){
                    alert("enter option four");
                     
                return false;
                }else
                
                if(o1.charAt(0)!="+"&&o1.charAt(0)!="-"){
                    alert("option 1, first charecter should be (+) or (-)");
                     
                return false;
                }else
                  if(o2.charAt(0)!="+"&&o2.charAt(0)!="-"){
                    alert("option 2, first charecter should be (+) or (-)");
                     
                return false;
                }else
                  if(o3.charAt(0)!="+"&&o3.charAt(0)!="-"){
                    alert("option 3, first charecter should be (+) or (-)");
                     
                return false;
                }else
                      if(o4.charAt(0)!="+"&&o4.charAt(0)!="-"){
                    alert("option 4, first charecter should be (+) or (-)");
                     
                return false;
                }else
                {
                 return true;
                }
                
                
               
            }
            
            
            </script>
        
        
        
        <title>JSP Page</title>
        <style>
            
        label {
    color: rgba(4, 32, 55,0.5);
    font-style: bold;
}
</style>
           <%@include file="professor.jsp" %>
    </head>
    <body>
     
      
        

<div class="container">
  <h2>Add Question</h2>
  <form:form action="submitquestion"  onsubmit="return myFunction()"  modelAttribute="question" name="Login_Form" class="form-signin">
    <div class="form-group">
      <label for="questiontext">Question Text</label>
      <form:input path="question_title" type="text" class="form-control" id="questiontext" placeholder="Question text" name="questiontext"/>
    </div>
      <br/><br/>
      <font color="red"><h2>* indicate should be +(plus)right answer -(minus) wrong answer</h2> </font>
    <div class="form-group">
      <label for="option_one">Option 1.</label>
      <form:input path="option_one"  class="form-control" id="option_one" placeholder="option one" name="option_one"/>
    </div>
            
         <div class="form-group">
      <label for="option_two">Option 2.</label>
      <form:input path="option_two" class="form-control" id="option_two" placeholder="option two" name="option_two"/>
    </div>
      
         <div class="form-group">
      <label for="option_three">Option 3.</label>
      <form:input path="option_three" class="form-control" id="option_three" placeholder="option three" name="option_three"/>
    </div>
         <div class="form-group">
      <label for="option_four">Option 4.</label>
      <form:input path="option_four" class="form-control" id="option_four" placeholder="option four" name="option_four"/>
    </div>
     
<center>  <button type="submit" class="btn btn-primary">Submit</button></center>
  </form:form>
</div>

        
    </body>
</html>


<!DOCTYPE html>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html lang="en">
<head>

<%@include file="include/head.jsp" %>  

  
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  
 <link rel="stylesheet" href="<c:url value='/resources/latex/mathquill-basic.css' />">
 <link rel="stylesheet" href="<c:url value='/resources/latex/mathquill.css' />">
  <script src="<c:url value='/resources/latex/mathquill.min.js' />"></script>
  
  <script>
var MQ = MathQuill.getInterface(2);
 
</script>

<style type="text/css">
.answer{
	width:150px;
}
</style>
</head>
<body ng-app="modeltestapp" ng-controller="modeltestController">

 
   <hr class="red_bg" color="red" />
   <div class="container">
     test
     
     <p>Solve <span id="latex1" class="latex">ax^2 + bx + c = 0, \sqrt{x^2+1}-\frac{5}{7}</span>.</p>

 

   <div class="container">
     test
     
     <p>Solve <span id="latex2" class="latex2">ax^2 - bx + c = 0, \sqrt{x^2+1}-\frac{5}{7}</span>.</p>

<script>


</script>

<p><span id="answer" class="answer">    </span></p>

</div>

</div>
<%@include file="include/footer.jsp" %>  
</body>
</html>

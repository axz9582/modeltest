 <!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html lang="en">

<%@include file="include/head.jsp" %>  
<head>

  <title>Math Model Test Main page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    
 <link rel="stylesheet" href="<c:url value='/resources/latex/mathquill-basic.css' />">
 <link rel="stylesheet" href="<c:url value='/resources/latex/mathquill.css' />">
  <script src="<c:url value='/resources/latex/mathquill.min.js' />"></script>
  
  <script>
  
  var x;
  function showTimer(){
	  clearInterval(x);
  var modeltestTotal =61;
  var now = 0;

//Update the count down every 1 second
 x = setInterval(function() {

 // Get todays date and time
 now++;
   
 // Find the distance between now and the count down date
 var distance = modeltestTotal - now;
   
  
 $('#timer').html(distance+ " ");
   
 // If the count down is over, write some text 
 if (distance < 0) {
   clearInterval(x);
   $('#timer').html( "Time is up");

	  $('#start_button').prop('disabled',false);//removeAttr('disabled');
 }
}, 1000);

}
 
 
  </script>
  <script>
var MQ = MathQuill.getInterface(2);

var ansString = "";
function checkAnswer(exp){
	ansString = exp;
}

function start_to_prac(){
	$('#start_button').text("Show me the answer");
	showTimer();
	  $('#start_button').prop('disabled',true);

	  $('#next_question_button').prop('disabled',true);
	$('#start_button').click(function(){
		show_answer();
	});
}
function submit_answer(){
	try{
		var ind = ansString.indexOf("\frac");
		
		ansString = ansString.replace("\\frac", "");

		ansString = ansString.replace("\\frac", "");
		ansString = ansString.replace("}{", ")/(");

		ansString = ansString.replace("}{", ")/(");
		

		ansString = ansString.replace("\\cdot", "*");

		ansString = ansString.replace("\\cdot", "*");

		ansString = ansString.replace("\\cdot", "*");

		var leftp1 = ansString.indexOf("\\left(");
		if(leftp1 > 0){
			var ch = ansString.charAt(leftp1-1);
			if(!isNaN(ch)){
				ansString = ansString.replace("\\left(", "*(");
			}
		}
		var leftp2 = ansString.indexOf("\\left(");
		if(leftp2 > 0){
			var ch = ansString.charAt(leftp2-1);
			if(!isNaN(ch)){
				ansString = ansString.replace("\\left(", "*(");
			}
		}
		ansString = ansString.replace("\\left(", "(");

		ansString = ansString.replace("\\left(", "(");

		ansString = ansString.replace("\\right)", ")");

		ansString = ansString.replace("\\left(", "(");

		ansString = ansString.replace("\\right)", ")");
				
		
		ansString = ansString.replace("{", "(");

		ansString = ansString.replace("}", ")");
		

		ansString = ansString.replace("{", "(");

		ansString = ansString.replace("}", ")");
		
		
		var endResult = eval(ansString);
		var endResultValue=(Math.round(endResult));
		if(endResultValue !=24){
			var r = confirm("Please check your answer, are you sure to submit anyway?");
			if(r){
				submitToBackend(ansString);
			}
			else{
				
			}
		}
		
		submitToBackend(ansString);
		
	}
	catch(err){
		console.log(err);
	}
	 

	
}

	function submitToBackend(ans){
	 
		var data = {ans:ans, a:"2",b:"3",c:"4"};//unfortunately jquery has some problems for sending the object, it is a string at the end
          
          $.post("http://localhost:8080/modeltest/submit_answer/", JSON.stringify(data), function(result){
        	  console.log("result="+result);
          });
	}

	var currentProblemNumber = 1;
  function next_question(increment){
	 
 //will get the next question to display
 	var data="2";

	  currentProblemNumber=currentProblemNumber+increment;
 $.get( "http://localhost:8080/modeltest/problem/SAT2/1/"+currentProblemNumber, function( data ) {

		  console.log(data);
		  var json = JSON.parse(data);
		  console.log("aa id ="+ json.id+" content=:"+json.content);
		  $('#problem_number').html("Problem "+ json.id);
		  $('#latex1').html(json.content);
		  $('#answer_a').html(json.a);
		  $('#answer_b').html(json.b);
		  $('#answer_c').html(json.c);
		  $('#answer_d').html(json.d);
		  var problemSpan = document.getElementById('latex1');
		  MQ.StaticMath(problemSpan);

		  MQ.StaticMath(document.getElementById('answer_a'));
		  MQ.StaticMath(document.getElementById('answer_b'));
		  MQ.StaticMath(document.getElementById('answer_c'));
		  MQ.StaticMath(document.getElementById('answer_d')); 
		  if(currentProblemNumber==Number(json.total)){
			  $('#next_question_button').prop('disabled', true);
		  }
		  else{
			  $('#next_question_button').prop('disabled', false);
		  }
		  
		  if(currentProblemNumber==1){
			  $('#prev_question_button').prop('disabled', true);
		  }
		  else{
			  $('#prev_question_button').prop('disabled', false); 
		  }
		});
	
}
  
  function show_answer(){ 
  }
 
 
</script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;} 
    }
    
    .navbar-inverse, footer{
    	background-color:	#228B22 !important;
    }
    
    .bigfont{
    
    	background-color: green;
    	 
    	margin:2px;
    	padding:5px;
    	font-size:20px;
    	color:yellow;
    }
    .inputradio{
    	margin:5px;
    }
    .choice{
    	margin:5px;
    	padding:5px;
    }
    .number{
    	background-color: green;
    	width:100px;
    	height:100px;
    	
    	margin:20px;
    	padding:5px;
    	font-size:70px;
    	text-align:center;
    	color:yellow;
    
    }
    
    #answer{
    	width:200px;
    	height:60px;
    	background-color:#fcf3fc;
    }
    .midalign{
    	padding: 10px 0px 0px 0px;
    }
    .results{
    	padding-top:40px;
    }
    .show_me{
    padding-top:40px;
    }
    .right{float:right;
    color:red;
    }
    
    .number_pad{
    	width:180px;
    }
    .single_cell{
    background-color:gray;
    margin-right:1px;
    margin-bottom:1px;
    color:yellow;
    }
  </style>
</head>

<body ng-app="modeltestapp" ng-controller="modeltestController" data-ng-init = "init();">
  
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Projects</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
         <%
  	String email =(String) session.getAttribute("email");
         System.out.println("email from the session="+email);
    if(email ==null){
  %>
        <li><a href="login.jsp"><span class="glyphicon glyphicon-log-out"></span> Login</a></li>
        <%}
    else {%>
    
        <li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    <%} %>
      </ul>
    </div>
  </div>
</nav>
  
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
      
      
     <ul>
     
	<li><a ui-sref="home" ui-sref-active="active">Home</a> </li>
	
	<li><a ui-sref="sat" ui-sref-active="active">SAT</a> </li>
	
	<li><a ui-sref="sat2" ui-sref-active="active">SAT2</a> </li>
	<li><a ui-sref="account" ui-sref-active="active">My Account</a> </li>
	</ul>
    </div>
    <div class="col-sm-8 text-left"> 
       <ui-view>
       This is the home display
       
	<li><a ui-sref="sat" ui-sref-active="active">SAT</a> </li>
	
	<li><a ui-sref="sat2" ui-sref-active="active">SAT2</a> </li>
       </ui-view>
    </div>
    
  
    <div class="col-sm-2 sidenav">
      <div class="well">
        <p>Online Users</p>
      </div>
     
    </div>
  </div>
</div>

<footer class="container-fluid text-center">
  <p>&copy; Math Model Test LLC.</p>
</footer>


    
 
<%@include file="include/footer.jsp" %>    
</body>
</html>


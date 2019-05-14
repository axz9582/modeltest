
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

	function submit_test(){
		var data={setId:model_test_set_id};
		   $.post("http://localhost:8080/modeltest/submit_a_test/", JSON.stringify(data), function(result){
	        	  console.log("result="+result);
	        	  if(result){
	        		  location.href="thank.jsp";
	        	  }
	        	  else{
	        		  location.href="notwork.jsp";
	        	  }
	          });
	}
	function choose_answer(choice){
		var data = {model_test_set:model_test_set_id, problemNumber: currentProblemNumber,ans:choice};
		$.post("http://localhost:8080/modeltest/choose_answer/", JSON.stringify(data), function(result){
			console.log("result="+result);
		});
	}
	var currentProblemNumber = 0;
	var model_test_set_id = 1;//the model test set id
  function next_question(increment){
	 
 //will get the next question to display
 	var data="2";

	  currentProblemNumber=currentProblemNumber+increment;
 $.get( "http://localhost:8080/modeltest/problem/SAT2/"+ model_test_set_id+"/"+currentProblemNumber, function( data ) {

		  console.log(data);
		  var json = JSON.parse(data);
		  console.log("aa id ="+ json.id+" content=:"+json.content);
		  var user_answer = json.user_answer;
		  $('#problem_number').html("Problem "+ json.id);
		  $('#latex1').html(json.content);
		  $('#answer_a').html("A)"+json.a);
		  $('#answer_b').html("B)"+json.b);
		  $('#answer_c').html("C)"+json.c);
		  $('#answer_d').html("D)"+json.d);
		  
		
		  
		  var abcd = "abcd";
		  var user_answer_index=0;
		  var j = 0;
		  for(j=0; j< abcd.length; j++){
			  
			  console.log("html="+$('#answer_'+x).html()+ "x = "+x);
			  if(($('#answer_'+abcd.charAt(j)).html()+"").startsWith(user_answer)){
			 
				  user_answer_index=j;
				  console.log("user_answer="+user_answer+" but j ="+j);
			  }
		  }
		  
		  var jk=0;
		  $('.inputradio').each(function(){
			
			  $(this).prop('checked', false);
			  if(jk==user_answer_index){

				  $(this).prop('checked', true);
			  }
			  jk++;
			
		  });
		  
		  var problemSpan = document.getElementById('latex1');
		  MQ.StaticMath(problemSpan);

		  MQ.StaticMath(document.getElementById('answer_a'));
		  MQ.StaticMath(document.getElementById('answer_b'));
		  MQ.StaticMath(document.getElementById('answer_c'));
		  MQ.StaticMath(document.getElementById('answer_d')); 
		  if(currentProblemNumber==Number(json.total)){
			  $('#next_question_button').hide();//.prop('disabled', true);

			  $('#submit_button').show();
		  }
		  else{

			  $('#submit_button').hide();
			  $('#next_question_button').show();
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

<body ng-app="modeltestapp" ng-controller="modeltestController" data-ng-init = "init();" onload="next_question(1)">
      <%
  	String email =(String) session.getAttribute("email");
         System.out.println("email from the session="+email);
    if(email !=null){
  %> 
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
   
    
        <li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
  
      </ul>
    </div>
  </div>
</nav>
    
      <h1>SAT Math Level 2 Model Test</h1>
      
     
   <div class="test_test">   
      <div class="right"><span id="timer"></span></div>
 	<div class="panel panel-default">
 	<div class="panel-heading" id="problem_number">Problem 1</div>
 	<div class="panel-body">
      <div class="numbers row">
      <div><div class="bigfont col-sm-12"><span id="latex1">2+3-1+\sqrt{4}=</span></div> 
      
    </div>
    </div>
    </div>
    </div>
      <hr>
       <div class=" row answer">
     
      <div id="result" class="col-sm-1"> </div>
      <div class="col-sm-10 midalign">
         <div>  <input class="inputradio" type="radio" onclick="choose_answer('A');" name="lanswer"/><span class="choice" id="answer_a">A. 3</span> </div>
         <div>  <input class="inputradio" type="radio" onclick="choose_answer('B');"  name="lanswer"/><span class="choice" id="answer_b">B. 4 </span></div>
         <div>  <input class="inputradio" type="radio" onclick="choose_answer('C');"  name="lanswer"/><span class="choice" id="answer_c">C. 5 </span> </div>
         <div>  <input class="inputradio" type="radio" onclick="choose_answer('D');"  name="lanswer"/><span class="choice" id="answer_d">D. 6 </span></div>
	   </div> 
      <div id="result" class="col-sm-1"> </div>
      
      
    </div> 
    
    <div class="row">
    </div>
    
    <hr>
    <div class="row">
     <div class="col-sm-4"></div>
     
      <div class="col-sm-4 midalign"><button id="prev_question_button" class="btn btn-primary" onclick="next_question(-1)">Previous Question</button></div>
      <div class="col-sm-4 midalign"><button id="next_question_button" class="btn btn-primary" onclick="next_question(1)">Next Question</button></div>
      <div class="col-sm-4 midalign"><button id="submit_button" class="btn btn-primary" onclick="submit_test()">Submit</button></div>
      
     <div class="col-sm-4"></div>
    </div>
    
    </div>
      <script>
	  var answerSpan = document.getElementById('answer');
	  var answerMathField = MQ.MathField(answerSpan, {
	    handlers: {
	      edit: function() {
	        var enteredMath = answerMathField.latex(); // Get entered math in LaTeX format
	        checkAnswer(enteredMath);
	      }
	    }
	  });
	</script>
	
<script>
	for(var i=0; i<20;i++){
		  var problemSpan2 = document.getElementById('latex'+i);		 
		  if(problemSpan2!=null){
		  	MQ.StaticMath(problemSpan2);
		  }
	}
</script>

<%
    }else{
%>
Please  <a href="login.jsp">login</a> first.
<%} %>
</body>

</html>
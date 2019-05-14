
      <h1>SAT Math Level 2 Model Test</h1>
      
      <div>
      <div class="individual_test"><a  href="testcontent" class="btn btn-success">Test 1</a></div> 
      <div class="individual_test"><button class="btn btn-success">Test 2</button></div>
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
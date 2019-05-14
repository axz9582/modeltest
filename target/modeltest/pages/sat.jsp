
      <h1>SAT Model Test</h1>
      <p>
      Using the following four numbers to get 24, you can only use addition, subtraction, multiplication and division. <br>(Example: 3,4,5,6, we will have
      <span id="latex1"> (3+5-4)\times 6= 24</span>)
      </p>
      <div class="numbers row">
      <div><div class="number col-sm-3">7</div><div class="number col-sm-3">28</div><div class="number col-sm-3">8</div><div class="number col-sm-3">9</div></div>
      </div>
      <hr>
       <div class=" row answer">
     
      <div class="col-sm-2 midalign">Your answer:</div>
      <div class="col-sm-8" id="answer" ></div>
      <div class="col-sm-1 midalign"><button class="btn btn-primary" onclick="submit_answer()">Submit</button></div>
      <div id="result" class="col-sm-1"> </div>
      
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
    </div> 
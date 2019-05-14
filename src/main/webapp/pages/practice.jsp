
      <h1>Twenty four game</h1>
      <div class="right"><span id="timer"></span></div>
      <p>
      Using the following four numbers to get 24, you can only use addition, subtraction, multiplication and division. <br>(Example: 3,4,5,6, we will have
      <span id="latex1"> (3+5-4)\times 6= 24</span>)
      </p>
      <div class="numbers row">
      <div><div class="number col-sm-2"><span id="practice_numbera">1</span></div>
      <div class="number col-sm-2"><span id="practice_numberb">2</span></div>
      <div class="number col-sm-2"><span id="practice_numberc">3</span></div>
      <div class="number col-sm-2"><span id="practice_numberd">4</span></div></div>
        <div class="col-sm-4 midalign">
        
	    <span class="results" id="latex24"></span><div class="show_me">
	    <button id="start_button" class="btn btn-primary" onclick="start_to_prac();">Start </button> 
	 	 </div>
      </div>
    </div>
      <hr>
       <div class=" row answer">
     
      <div class="col-sm-2 midalign">Your answer:</div>
      <div class="col-sm-8" id="answer" ></div>
      <div class="col-sm-1 midalign"><button class="btn btn-primary" onclick="submit_answer()">Submit</button></div>
      <div id="result" class="col-sm-1"> </div>
      
      
    </div> 
    
    
    <div class="row number_pad">
    
    	<div class="row">
    	<div class="col-sm-2 single_cell">0</div>
    	<div class="col-sm-2 single_cell">1</div>
    	<div class="col-sm-2 single_cell">2</div>
    	<div class="col-sm-2 single_cell">3</div>
    	</div>
    	
    	
    	<div class="row">
    	<div class="col-sm-2 single_cell">4</div>
    	<div class="col-sm-2 single_cell">5</div>
    	<div class="col-sm-2 single_cell">6</div>
    	<div class="col-sm-2 single_cell">7</div>
    	</div>
    	
    	<div class="row">
    	<div class="col-sm-2 single_cell">8</div>
    	<div class="col-sm-2 single_cell">9</div>
    	<div class="col-sm-2 single_cell">(</div>
    	<div class="col-sm-2 single_cell">)</div>
    	</div>
    	 
    	<div class="row">
    	<div class="col-sm-2 single_cell"><span id="latex6">+</span></div>
    	<div class="col-sm-2 single_cell"><span id="latex7">-</span></div>
    	<div class="col-sm-2 single_cell"><span id="latex8">\times</span></div>
    	<div class="col-sm-2 single_cell"><span id="latex9">\div</span></div>
    	</div>
    </div>
    
    
    <div class="row">
    </div>
    <div class="row">
     <div class="col-sm-4"></div>
      <div class="col-sm-4 midalign"><button id="next_question_button" class="btn btn-primary" onclick="next_question()">Next Question</button></div>
      
     <div class="col-sm-4"></div>
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
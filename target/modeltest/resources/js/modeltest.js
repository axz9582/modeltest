var modeltestapp = angular.module('modeltestapp', ['ui.router', 'ui.bootstrap']);


modeltestapp.controller('modeltestController', ['$scope', '$http', '$dialog', function($scope, $http, $dialog) {
  

	$scope.practice_numbera=1;
	$scope.practice_numberb=2;
	$scope.practice_numberc=3;
	$scope.practice_numberd=4;
	$scope.login=true;
	$scope.register_me=false;
	$scope.reset_password=false;

  	$scope.has_logged_in= false;
	$scope.new_user_flag=function(){
		$scope.login=false;
		$scope.register_me=true;
		$scope.reset_password = false;
	}
	

	$scope.login_to= function (){
		 if(!$scope.validate_email()){return;}
		 if(!$scope.validate_pwd()) { return;}
		 
		var userData = {email:$scope.email, password:$scope.pwd};
			  $http.post("http://localhost:8080/modeltest/login_me_submit/", userData)
	          .then(
	          function (response) {
	          	console.log(response);
	              //deferred.resolve(response.data);
	          	if(response.data==false){
	          		//alert("User with this email already exists! Do you forget your password?");
	          		$scope.email_error_message="Username/password don't match! Do you forget your password?";
	          		return;
	          	}
	          	else{
	          		$scope.email_error_message="";
	          		
	          	}
	          	$scope.has_logged_in= true;
	          	window.location.href="index.jsp";
	          },
	          function(errResponse){
	              console.error('Error while sending email:'+ errResponse);
	             // deferred.reject(errResponse);
	          }
	      );
		

		
	}
	
	$scope.init = function (){

		$scope.login=true;
		$scope.register_me=false;
		$scope.reset_password=false;
	}
	
	$scope.forget_password_flag = function (){

		$scope.email_error_message="";
		$scope.login=false;
		$scope.register_me=false;
		$scope.reset_password = true;
		
		
	}
	
	$scope.reset_password = function (){
		
		  $http.post("http://localhost:8080/modeltest/reset_pwd/", $scope.pwd)
          .then(
          function (response) {
          	console.log(response);
              //deferred.resolve(response.data);
          	if(response.data==false){
          		alert("The password didn't get updated.");
          		//$scope.email_error_message="User with this email already exists! Do you forget your password?";
          		 
          		return;
          	}
          	else{
          		$scope.email_error_message="";

          		alert("The password  get updated.");
          		
          	}
          	window.location.href="index.jsp";//should be login?
          },
          function(errResponse){
              console.error('Error while sending email:'+ errResponse);
             // deferred.reject(errResponse);
          }
      );
		
	}
	
	$scope.validate_email = function(){
		if($scope.email=="undefined" || $scope.email==""){

			$('#email').addClass("ng-invalid");
			$scope.email_error_message=("Please input your email address");
			return false;
		}
		
		 if(!$scope.checkEmail($scope.email)){

				$('#email').addClass("ng-invalid");
				$scope.email_error_message=("Please input a valid email address");
				return false;
		}
		 

			$('#email').removeClass("ng-invalid");
			$scope.email_error_message="";
			return true;
	}

	$scope.validate_pwd = function(){
		if($scope.pwd=="undefined" || $scope.pwd==""){

			$('#pwd').addClass("ng-invalid");
			$scope.pwd_error_message=("Please input a password");
			return false;
		} 
		
		if($scope.pwd.length < 8){

			$('#pwd').addClass("ng-invalid");
			$scope.pwd_error_message=("Password is too short, should be at least 8 characters");
			return;
		}

		$('#pwd').removeClass("ng-invalid");
		$scope.pwd_error_message="";
		return true;
	}

	$scope.checkAnswer=function (exp){
		console.log(exp);
	}
	$scope.validate_pwd2 = function(){

		if($scope.pwd !=$scope.pwd2){

			$('#pwd2').addClass("ng-invalid");
			$scope.pwd2_error_message=("Passwords don't match!");
			return false;
		}
		

		$('#pwd2').removeClass("ng-invalid");
		$scope.pwd2_error_message="";
		return true;
	}
	$scope.register_me_submit = function (){
		//need to verify email and pwd match
		
		 if(!$scope.validate_email()){return;}
		 if(!$scope.validate_pwd()) { return;}

		 if(!$scope.validate_pwd2()) { return;}
		
		var userData = {email:$scope.email, password:$scope.pwd};
		  $http.post("http://localhost:8080/modeltest/register_me_submit/", userData)
          .then(
          function (response) {
          	console.log(response);
              //deferred.resolve(response.data);
          	if(response.data==false){
          		//alert("User with this email already exists! Do you forget your password?");
          		$scope.email_error_message="User with this email already exists! Do you forget your password?";
          		return;
          	}
          	else{
          		$scope.email_error_message="";
          		
          	}
          	window.location.href="thankyou.jsp";
          },
          function(errResponse){
              console.error('Error while sending email:'+ errResponse);
             // deferred.reject(errResponse);
          }
      );
	
		
	}
	$scope.forget_password=function (){
		var courses=[];
		var emailAddress = {email:$scope.email};
		  $http.post("http://localhost:8080/modeltest/forget_password/", emailAddress)
          .then(
          function (response) {
          	console.log(response);
              //deferred.resolve(response.data);
          	if(response.data==false){
          		alert("User with "+ $scope.email+ " doesn't exist, please register first.");
          		return;
          	}
          	alert("A reset password link has been sent to the email:"+ $scope.email+", please check your email to reset. Thank you!")
          	window.location.href="thankyou.jsp";
          	
          },
          function(errResponse){
              console.error('Error while sending email:'+ errResponse);
             // deferred.reject(errResponse);
          }
      );
	}
	
	$scope.checkEmail = function(email) {

		var EMAIL_REGEXP = /^[_a-z0-9]+(\.[_a-z0-9]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/;
		var isMatchRegex = EMAIL_REGEXP
				.test(email);
		if (!isMatchRegex) {
			$('#email').addClass("ng-invalid");
			return false;
		} else {
			$('#email').removeClass("ng-invalid");
			return true;
		}
	}
	
}]);

 

modeltestapp.config(function($stateProvider) {
	  var homeState = {
			    name: 'home',
			    url: '/home',
			    templateUrl: 'pages/home.jsp'
			  }
	  var satState = {
			    name: 'sat',
			    url: '/sat',
			    templateUrl: 'pages/sat.jsp'
			  }
	  var practiceState = {
			    name: 'practice',
			    url: '/practice',
			    templateUrl: 'pages/practice.jsp'
			  }
	  var sat2State = {
			    name: 'sat2',
			    url: '/sat2',
			    templateUrl: 'pages/sat2.jsp'
			  }
	  var accountState = {
			    name: 'account',
			    url: '/account',
			    templateUrl: 'pages/account.jsp'
			  }
			  $stateProvider.state(homeState);
			  $stateProvider.state(satState);

			  $stateProvider.state(accountState);
			  $stateProvider.state(practiceState);
			  $stateProvider.state(sat2State);
			});


  
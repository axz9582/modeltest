<!DOCTYPE html>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html lang="en">
<head>

<%@include file="include/head.jsp" %>  

 
<head>
  <title>Math Model Test Login</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body ng-app="modeltestapp" ng-controller="modeltestController" data-ng-init = "init();">

<div class="container">
  <h2 align="center" class="header_">Welcome to Model Test</h2>
    <div class="row">
      <label class="control-label col-sm-2" for="email">Email:</label>
      <div class="col-sm-4">
        <input type="email" class="form-control" ng-model="email" ng-blur="validate_email();" id="email" placeholder="Enter email" name="email" size="10">
      </div>
      <div class="col-sm-4">
      <span class="error_message red">{{email_error_message}}</span>
      </div>
    </div>
    <div class="row" ng-show="login || register_me">
      <label class="control-label col-sm-2" for="pwd">Password:</label>
      <div class="col-sm-4">          
        <input type="password" class="form-control" ng-model="pwd" ng-blur="validate_pwd();" id="pwd" placeholder="Enter password" name="pwd" size="12">
      </div>
      
      <div class="col-sm-4">
      <span class="error_message red">{{pwd_error_message}}</span>
      </div>
    </div>
        <div class="row" ng-show="register_me">
      <label class="control-label col-sm-2" for="pwd">Password(Retype):</label>
      <div class="col-sm-4">          
        <input type="password" class="form-control" ng-blur="validate_pwd2();" ng-model="pwd2" id="pwd2" placeholder="Enter password" name="pwd2" size="12">
      </div>
      
      
      <div class="col-sm-4">
      <span class="error_message red">{{pwd2_error_message}}</span>
      </div>
    </div>
    <div class="row">        
      <div class="col-sm-offset-2 col-sm-10">
       
      </div>
    </div>
    <div class="row" ng-show="login">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn  btn-primary" ng-click="login_to();">Submit</button>
      </div>
    </div>
    
       <div class="row" ng-show="register_me">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-primary" ng-click="register_me_submit();">Register Me</button>
      </div>
    </div>
    
       <div class="row" ng-show="reset_password">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-primary" ng-click="forget_password();">Submit to Reset Password</button>
      </div>
    </div>
  </div>
   <hr class="red_bg" color="red" />
   <div class="container">
  <div class="row">
  	<div class="col-sm-offset-2 col-sm-10">
  	<a href="#" ng-click="new_user_flag();">New User</a>
  </div>
  
   <hr class="orange" color="orange" />
  </div>
  <div class="row">
  <div class="col-sm-offset-2 col-sm-10">
  	<a href="#" ng-click="forget_password_flag();">Forget Password?</a>
  </div> 
  </div>
</div>

</body>
</html>

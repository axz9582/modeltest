<!DOCTYPE html>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html lang="en">
<head>

<%@include file="include/head.jsp" %>  

 
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body ng-app="modeltestapp" ng-controller="modeltestController">

<div class="container">
  <h2 align="center" class="header_">Welcome to Math Count Down</h2>
    
    <div class="row" ng-show="login || register_me">
      <label class="control-label col-sm-2" for="pwd">Password:</label>
      <div class="col-sm-4">          
        <input type="password" class="form-control" ng-model="pwd" ng-blur="validate_pwd();" id="pwd" placeholder="Enter password" name="pwd" size="12">
      </div>
      
      <div class="col-sm-4">
      <span class="error_message red">{{pwd_error_message}}</span>
      </div>
    </div>
        <div class="row" >
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
    
    
       <div class="row" >        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-primary" ng-click="reset_password();">Reset Password</button>
      </div>
    </div>
  
  </div> 
   
</body>
</html>

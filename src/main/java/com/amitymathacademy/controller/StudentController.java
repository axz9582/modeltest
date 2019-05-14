package com.amitymathacademy.controller;



 

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.amitymathacademy.model.AnyTest;
import com.amitymathacademy.model.Email;
import com.amitymathacademy.model.Exercise;
import com.amitymathacademy.model.StudentUser;
import com.amitymathacademy.service.StudentService;


@RestController
public class StudentController {
   
	@Autowired
	StudentService studentService;
    
	 @RequestMapping(value = "/forget_password/", method = RequestMethod.POST)//what is this doing?
	    public ResponseEntity<Boolean> forgetPassword(@RequestBody StudentUser user,    UriComponentsBuilder ucBuilder, HttpServletRequest request) {
	        HttpSession s = request.getSession(true);
	        System.out.println(">>>>>>>>>>>>>>>cforeget password ......>>>>>>> " + user);
	        boolean exists = studentService.isExist(user);
	        System.out.println("exists ??? ="+exists);
	        if(!exists){
	        	HttpHeaders headers = new HttpHeaders();
	            return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.CREATED);
	        }
	        //send an email to let the user activate the registration process
	        
	        user = studentService.getStudentByEmail(user.getEmail());
	        
	        Email emailMessage = new Email();
	        emailMessage.setFrom("amitymathacademy@gmail.com");//todo, should be another new email now......
	        emailMessage.setTo(user.getEmail()); 

			String resetCode = UUID.randomUUID().toString();
			user.setPwdResetCode(resetCode);
			
	        String body = "Please click to reset your password:"
	        		+ " http://localhost:8080/modeltest/reset?r="+user.getPwdResetCode() //todo, need to writhe the verify page, it is a restful url
	        		+ " \nThank you very much! "
	        		+ ""
	        		+ " \n\nCount Down Team!!";
	        emailMessage.setBody(body);
	        emailMessage.setSubject("Reset Password Link"); //after the student reset password, the reset code will be deleted, i.e. can only be reset once
	        studentService.update(user);
	        studentService.sendGmail(emailMessage);
	        
	        
	        HttpHeaders headers = new HttpHeaders();
	        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.CREATED);
	    } 
	
	
	 
	 @RequestMapping(value = "/reset_pwd/", method = RequestMethod.POST)//what is this doing?
	    public ResponseEntity<Boolean> resetPwd(@RequestBody String pwd,    UriComponentsBuilder ucBuilder, HttpServletRequest request) {
	        HttpSession s = request.getSession(true);
	        String resetCode = (String) s.getAttribute("resetCode");
	        System.out.println(resetCode+">>>>>>>>>>>>>>>count student controller down 2......>>>>>>> " + pwd);
	        
	        
	        if(resetCode==null || resetCode.equals("") || pwd==null || pwd.equals("")){

		        HttpHeaders headers = new HttpHeaders();
		        return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.CREATED);
	        }
	        boolean resetStatus = studentService.resetPassword(resetCode, pwd);
	        
	        //send an email to let the user activate the registration process
	        
	        /*
	        Email emailMessage = new Email();
	        emailMessage.setFrom("amitymathacademy@gmail.com");//todo, should be another new email now......
	        emailMessage.setTo(user.getEmail()); 
	        String body = "Thank you for sigining up, please click to activate your sign up."
	        		+ " http://localhost:8080/countdown/verify?c="+user.getVerifyCode() //todo, need to writhe the verify page, it is a restful url
	        		+ " \nThank you very much! "
	        		+ ""
	        		+ " \n\nCount Down Team!!";
	        emailMessage.setBody(body);
	        emailMessage.setSubject("Thank you for sign up");
	        studentService.sendGmail(emailMessage);
	         */
	        
	        HttpHeaders headers = new HttpHeaders();
	        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.CREATED);
	    } 
	 
	 
	 
	   @RequestMapping(value = "/login_me_submit/", method = RequestMethod.POST)//what is this doing?
	    public ResponseEntity<Boolean> login_me_submit(@RequestBody StudentUser user,    UriComponentsBuilder ucBuilder, HttpServletRequest request) {
	        HttpSession s = request.getSession(true);
	        System.out.println(">>>>>>>>>>>>>>>count student controller down ......>>>>>>> " + user);
	        
	        	
	        boolean credentialCheck = studentService.checkUser(user);
	        
	        if(credentialCheck){
	        	s.setAttribute("email", user.getEmail());
	        //	s.setAttribute("username", user.getEmail());
	        }
	        HttpHeaders headers = new HttpHeaders();
	        return new ResponseEntity<Boolean>(credentialCheck, HttpStatus.CREATED);
	    } 
	 
	   
	   
	   
		 @RequestMapping(value = "/choose_answer/", method = RequestMethod.POST)//what is this doing?
		    public ResponseEntity<Boolean> chooseAnswer(@RequestBody String answer,    UriComponentsBuilder ucBuilder, HttpServletRequest request) {
		        HttpSession s = request.getSession(true);
		        String username = (String)s.getAttribute("email");
		        try{
		        String result = java.net.URLDecoder.decode(answer, StandardCharsets.UTF_8.name());
		        System.out.println("in choose_anser , the answer="+result+" for "+username);
		        studentService.saveUserTestAnswer(result, username);
		        }
		        catch(Exception e){e.printStackTrace();}
		        HttpHeaders headers = new HttpHeaders();
	            return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.CREATED);
		 }
		 
	 
	   
		 @RequestMapping(value = "/submit_answer/", method = RequestMethod.POST)//what is this doing?
		    public ResponseEntity<Boolean> submitAnswer(@RequestBody String answer,    UriComponentsBuilder ucBuilder, HttpServletRequest request) {
		        HttpSession s = request.getSession(true);
		        String username = (String)s.getAttribute("email");
		        try{
		        String result = java.net.URLDecoder.decode(answer, StandardCharsets.UTF_8.name());
		        System.out.println("in submit_answer , the answer="+result+" for "+username);
		        }
		        catch(Exception e){e.printStackTrace();}
		        HttpHeaders headers = new HttpHeaders();
	            return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.CREATED);
		 }
    @RequestMapping(value = "/register_me_submit/", method = RequestMethod.POST)//what is this doing?
    public ResponseEntity<Boolean> registerNewUser(@RequestBody StudentUser user,    UriComponentsBuilder ucBuilder, HttpServletRequest request) {
        HttpSession s = request.getSession(true);
        System.out.println(">>>>>>>>>>>>>>>count student controller down 1 ......>>>>>>> " + user);
        boolean exists = studentService.isExist(user);
        System.out.println("exists ??? ="+exists);
        if(exists){
        	HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.CREATED);
        }
        	
        studentService.save(user);
        
        //send an email to let the user activate the registration process
        
        
        Email emailMessage = new Email();
        emailMessage.setFrom("amitymathacademy@gmail.com");//todo, should be another new email now......
        emailMessage.setTo(user.getEmail()); 
        String body = "Thank you for sigining up, please click to activate your sign up."
        		+ " http://localhost:8080/modeltest/verify?c="+user.getVerifyCode() //todo, need to writhe the verify page, it is a restful url
        		+ " \nThank you very much! "
        		+ ""
        		+ " \n\nCount Down Team!!";
        emailMessage.setBody(body);
        emailMessage.setSubject("Thank you for sign up");
        studentService.sendGmail(emailMessage);
        
        
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/submit_a_test/", method = RequestMethod.POST)//what is this doing?
    public ResponseEntity<Boolean> submitATest(@RequestBody String testSetId,    UriComponentsBuilder ucBuilder, HttpServletRequest request) {
        HttpSession s = request.getSession(true);
        System.out.println(">>>>>>>>>>>>>>>in the submit test " + testSetId);
        try{
        String result = java.net.URLDecoder.decode(testSetId, StandardCharsets.UTF_8.name());
       
        System.out.println("json object="+ result);
        JSONObject jsonObject = new JSONObject(result);
        

        System.out.println("json object="+  jsonObject);
        
        }
        catch(Exception e){
        	e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.CREATED);
   
    }
    
    
    /*
     * 
     *  
		 @RequestMapping(value = "/choose_answer/", method = RequestMethod.POST)//what is this doing?
		    public ResponseEntity<Boolean> chooseAnswer(@RequestBody String answer,    UriComponentsBuilder ucBuilder, HttpServletRequest request) {
		        HttpSession s = request.getSession(true);
		        String username = (String)s.getAttribute("email");
		        try{
		        String result = java.net.URLDecoder.decode(answer, StandardCharsets.UTF_8.name());
		        System.out.println("in choose_anser , the answer="+result+" for "+username);
		        studentService.saveUserTestAnswer(result, username);
		        }
		        catch(Exception e){e.printStackTrace();}
		        HttpHeaders headers = new HttpHeaders();
	            return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.CREATED);
		 }
		 
     */
    
    @RequestMapping(value = "/problem/{test_type}/{set}/{id}", method = RequestMethod.GET )
    public ResponseEntity<String>  getSchecule(@PathVariable("test_type") String type,@PathVariable("set") int setId,@PathVariable("id") int id,HttpServletRequest request) {
      System.out.println(type+" ? "+ setId+ " ?id="+id);
      
      HttpSession s = request.getSession(true);
      String username = (String)s.getAttribute("email");
      
      Exercise exercise = studentService.getExercise(type, id);
      AnyTest modelTest = studentService.getAnyModelTest(setId);
      JSONObject json = new JSONObject();
      String answer = studentService.getStudentAnswer(username, setId, id);
      json.put("id", id);
      System.out.println(" in the problems set ..... exercise = "+exercise);
      if(exercise!=null){
	      json.put("content", exercise.getContent());
	      json.put("a", exercise.getChoiceA());
	      json.put("b", exercise.getChoiceB());
	      json.put("c", exercise.getChoiceC());
	      json.put("d",exercise.getChoiceD());
	      json.put("total", modelTest.getTotal());
	      json.put("user_answer", answer);
	      System.out.println("put the user_answer there  answer="+answer);
	 
      }
        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);  
    }	
    
    
    String getTwoDigits(int k){
    	if(k<10) return "0"+k;
    	return ""+k;
    }
    
    
    
    
}

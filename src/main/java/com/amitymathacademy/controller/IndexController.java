package com.amitymathacademy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amitymathacademy.service.StudentService;
 

@Controller
public class IndexController {

	  
		@Autowired
		StudentService studentService;

	  @RequestMapping("/")
	//  @RequestMapping(method = RequestMethod.GET)
	    public String getIndexPage(HttpServletRequest request) {
		  System.out.println(request.getRemoteAddr()+" ??? "+ request.getRemoteHost());
		  String remote = request.getRemoteAddr();
		 
		  System.out.println("............1...");
	        return "index";//schedule
	    }
	  
		
	  @RequestMapping(value = "/reset")
	    public String resetPassword(HttpServletRequest request) {
		  System.out.println(request.getRemoteAddr()+" ??? "+ request.getRemoteHost());
		 
		  String resetCode = request.getParameter("r");
		  HttpSession session = request.getSession(true);
		  session.setAttribute("resetCode", resetCode);
		  System.out.println("............1..."+request.getParameter("r"));
	        return "pwdreset";
	    }
	  
	  @RequestMapping(value = "/verify")
	    public String getVerified(HttpServletRequest request) {
		  System.out.println(request.getRemoteAddr()+" ??? "+ request.getRemoteHost());
		  String remote = request.getRemoteAddr();
		  String code = request.getParameter("c");
		  studentService.updateVerification(code);
	      return "thankyou";
	    } 
	  
	  @RequestMapping(value = "/testcontent")
	    public String getTestContent(HttpServletRequest request) {
		  System.out.println(request.getRemoteAddr()+" ??? "+ request.getRemoteHost());
		  String remote = request.getRemoteAddr();
		  String type = request.getParameter("type");
		  String id = request.getParameter("id");
		  System.out.println("testcontent........");
		 // studentService.updateVerification(code);
	      return "sat2_content";
	    } 

}
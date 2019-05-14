package com.amitymathacademy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.amitymathacademy.model.Register;
import com.amitymathacademy.model.StudentUser;


@RestController
public class EventController {
	//public static final Calendar startDate = new GregorianCalendar(2018,8,3);//september 3
	public static final int oneMonth = 30;
	//  @Autowired
	 // EventService eventService;
	 
    @RequestMapping(value = "/register/", method = RequestMethod.POST)//what is this doing?
    public ResponseEntity<Void> registerCourses(@RequestBody Register register,    UriComponentsBuilder ucBuilder, HttpServletRequest request) {
        HttpSession s = request.getSession(true);
        System.out.println(">>>>>>>>>>>>>>>count down ......>>>>>>> " );
       
      
      // eventService.
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    } 
    
  
    String getTwoDigits(int k){
    	if(k<10) return "0"+k;
    	return ""+k;
    }
    
}

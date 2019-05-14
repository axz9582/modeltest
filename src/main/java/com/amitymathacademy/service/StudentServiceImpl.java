package com.amitymathacademy.service;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amitymathacademy.db.StudentDao;
import com.amitymathacademy.model.AnyTest;
import com.amitymathacademy.model.Email;
import com.amitymathacademy.model.Exercise;
import com.amitymathacademy.model.StudentUser;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDao studentDao;

	@Override
	public void save(StudentUser student) {
		studentDao.save(student);
		
	}

	@Override
	public StudentUser getStudentById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentUser> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(StudentUser student) {
		studentDao.update(student);
		
	}
	
	public void sendGmail(Email email){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		System.out.println("sending gmail............................................");
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("amitymathacademy", "math@Amity");
					}
				  });
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("amitymathacademy@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email.getTo()));
			message.setSubject(email.getSubject());
			message.setText(email.getBody());
			String bcc = email.getBcc();
			if (bcc ==null) bcc = "amitymathacademy@gmail.com";
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress(
		            bcc));
			Transport.send(message);

			System.out.println("Done");
			 

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

	public boolean isExist(StudentUser student){
		return studentDao.isExist(student);
	}
	

	public StudentUser getStudentByEmail(String email){
		return studentDao.getStudentByEmail(email);
	}
	

	public boolean resetPassword(String resetCode, String pwd){
		return studentDao.resetPassword(resetCode, pwd);
	}
	

	public boolean checkUser(StudentUser user){
		return studentDao.checkUser(user);
	}

	public void updateVerification(String code){
		studentDao.updateVerification(code);
	}
	
	public Exercise getExercise(String type, int id){
		return studentDao.getExercise(type, id);
	}
	

	public AnyTest getAnyModelTest(int id){
		return studentDao.getAnyModelTest(id);
	}


	public void saveUserTestAnswer(String result, String email){
		studentDao.saveUserTestAnswer(result,  email);
	}


	public String getStudentAnswer(String email, int setId, int problemNumber){
		return studentDao.getStudentAnswer(email, setId, problemNumber);
	}
}

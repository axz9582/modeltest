package com.amitymathacademy.service;

import java.util.List;

import com.amitymathacademy.model.AnyTest;
import com.amitymathacademy.model.Email;
import com.amitymathacademy.model.Exercise;
import com.amitymathacademy.model.StudentUser;

public interface StudentService {
	
public void save(StudentUser student);
	
	public StudentUser getStudentById(int id);
	
	public StudentUser getStudentByEmail(String email);
	public List<StudentUser> getAllStudents();
	
	public void update(StudentUser student);
	
	public boolean isExist(StudentUser student);
	public void sendGmail(Email email);
	
	public boolean resetPassword(String resetCode, String pwd);
	
	public boolean checkUser(StudentUser user);

	public void updateVerification(String code);
	
	public Exercise getExercise(String type, int id);
	public AnyTest getAnyModelTest(int id);
	
	public void saveUserTestAnswer(String result, String email);
	
	public String getStudentAnswer(String email, int setId, int problemNumber);

}

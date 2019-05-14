package com.amitymathacademy.db;

import java.util.List;

import com.amitymathacademy.model.AnyTest;
import com.amitymathacademy.model.Exercise;
import com.amitymathacademy.model.StudentUser;

public interface StudentDao {
	
	public void save(StudentUser student);
	
	public StudentUser getStudentById(int id);
	
	public List<StudentUser> getAllStudents();
	
	public void update(StudentUser student);
	

	public boolean isExist(StudentUser student);

	public StudentUser getStudentByEmail(String email);

	public boolean resetPassword(String resetCode, String pwd);
	

	public boolean checkUser(StudentUser user);
	public void updateVerification(String code);
	public Exercise getExercise(String type, int id);

	public AnyTest getAnyModelTest(int id);

	public void saveUserTestAnswer(String result, String email);

	public String getStudentAnswer(String email, int setId, int problemNumber);

}

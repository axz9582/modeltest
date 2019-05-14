package com.amitymathacademy.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.amitymathacademy.model.AnyTest;
import com.amitymathacademy.model.Exercise;
import com.amitymathacademy.model.StudentUser;


@Repository("studentDao")
public class StudentDaoImpl implements StudentDao{

	@Override
	public void save(StudentUser student) {
		AQuery q = new AQuery();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String verificationCode = UUID.randomUUID().toString();
		student.setVerifyCode(verificationCode);
		String createdDate = (dateFormat.format(date));
		 

		String sql = "insert into user(email,pass_word, verify_code, grade, created_on, is_active) "
				+ "values( ?,to_base64(?),?,?,?, ?)";
		 
		try{
			PreparedStatement p = q.preparedStatement(sql);
			 
			int counter=1;
			p.setString(counter++, student.getEmail());
			p.setString(counter++, student.getPassword());
			p.setString(counter++,verificationCode);
			p.setInt(counter++, 0);
			p.setString(counter++, createdDate);
			p.setString(counter++, "N");  //when you created a user, it is not active yet				
			p.executeUpdate();
			p.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
			
		
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
		AQuery q = new AQuery ();
		String sql = "update user set pass_word=?, first_name=?, last_name=?, phone=?, "
				+ "verify_code=?, reset_code=?,is_active=?, grade=?  where email=?";
		try{
			PreparedStatement p = q.preparedStatement(sql);
			 
			int counter=1;
			p.setString(counter++, student.getPassword());
			p.setString(counter++, student.getFirstName());

			p.setString(counter++, student.getLastName());

			p.setString(counter++, student.getPhone());
			p.setString(counter++,student.getVerifyCode());
			p.setString(counter++, student.getPwdResetCode());

			p.setString(counter++, student.isActive()?"Y":"N");
			p.setInt(counter++, student.getYearLevel());
			p.setString(counter++, student.getEmail());			
			p.executeUpdate();
			p.close();
			//System.out.println("updated the user "+sql+" >> "+ student);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	public boolean resetPassword(String resetCode, String pwd){

		AQuery q = new AQuery ();
		String sql = "update user set pass_word=to_base64(?)   where reset_code=?";
		try{
			PreparedStatement p = q.preparedStatement(sql);
			 
			int counter=1;
			p.setString(counter++, pwd);
			p.setString(counter++, resetCode);
 			
			int records = p.executeUpdate();
			p.close();
			//System.out.println("updated the user "+sql+" >> "+ student);
			if(records ==0) return false;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	
	}

	public boolean isExist(StudentUser student){
		AQuery q = new AQuery();
		boolean exist = false;
		String sql = "select 1 from user where email=?";
		PreparedStatement p = null;
		 
		try{
			p = q.preparedStatement(sql);
			p.setString(1, student.getEmail());
			ResultSet rs = p.executeQuery();
			if(rs.next()){
				exist = true;
			}
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{p.close();}catch(Exception e){}
		}
		
		return exist;
	}
	

	public boolean checkUser(StudentUser user){
		

		 
		AQuery q = new AQuery();
		boolean valid = false;
		String sql = "select * from user where email=? and pass_word=to_base64(?)";
		PreparedStatement p = null;
		 
		try{
			p = q.preparedStatement(sql);
			p.setString(1, user.getEmail());
			p.setString(2, user.getPassword());
			ResultSet rs = p.executeQuery();
			if(rs.next()){
				valid = true;
			}
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{p.close();}catch(Exception e){}
		}
		System.out.println("valid ...."+valid);
		return valid;
	}

	public StudentUser getStudentByEmail(String email){
		StudentUser user = new StudentUser();
		AQuery q = new AQuery();
		boolean exist = false;
		String sql = "select * from user where email=?";
		PreparedStatement p = null;
		 
		try{
			p = q.preparedStatement(sql);
			p.setString(1, email);
			ResultSet rs = p.executeQuery();
			if(rs.next()){
				user.setVerifyCode(rs.getString("verify_code"));
				user.setEmail(rs.getString("email"));
			}
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{p.close();}catch(Exception e){}
		}
		
		return user;
	
	}
	
	public void updateVerification(String code){
		String sql = "update user set is_active='Y' where verify_code=?";
	
		AQuery q = new AQuery ();
		try{
			PreparedStatement p = q.preparedStatement(sql);
			p.setString(1, code);
			p.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			q.close();
		}catch(Exception e2){}
	}
	
	public Exercise getExercise(String type, int id){
	      String sql = "select e.content, e.choice_a, e.choice_b, e.choice_c, e.choice_d, e.answer, s.problems_total total "+
	    		   " from exercise e, any_test_content t, model_test_set s where e.id=t.exercise_id and s.id=t.model_test_id and t.test_type='"+type+"' and t.problem_no="+id;
	      AQuery q = new AQuery(sql);
	      System.out.println(sql);
	      Exercise exercise = new Exercise();
	      if(q.next()){
	    	  exercise.setContent(q.getString("content"));
	    	  exercise.setChoiceA(q.getString("choice_a"));
	    	  exercise.setChoiceB(q.getString("choice_b"));
	    	  exercise.setChoiceC(q.getString("choice_c"));
	    	  exercise.setChoiceD(q.getString("choice_d"));
	    	  exercise.setAnswer(q.getString("answer"));
	      }
	      System.out.println(exercise);
	      return exercise;
	}
	

	public void saveUserTestAnswer(String result, String email){

		JSONObject jsonObject = new JSONObject(result);
		String answer = (String)jsonObject.get("ans");
		int modelTestSetId = (int) jsonObject.get("model_test_set");
		int problemNo = (int) jsonObject.get("problemNumber");
		String sql0= "select 1 from user_test_answer where user_email=? and model_test_id=? and problem_no=?";

		AQuery q = new AQuery ();
		PreparedStatement p = null;
		boolean existed = false; 
		try{
			p = q.preparedStatement(sql0);
			p.setString(1, email);
			p.setInt(2, modelTestSetId);
			p.setInt(3, problemNo);
			ResultSet rs = p.executeQuery();
			if(rs.next()){
				existed = true;
			}
			rs.close();
			p.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		String sql = "insert into user_test_answer(answer, user_email, model_test_id,problem_no) values(?, ? ,?, ?)";
		if(existed){
		
		    sql = "update user_test_answer set  answer=? where user_email=? and model_test_id=? and problem_no=?";
		}
	System.out.println("sql = "+ sql);
		try{
		PreparedStatement	p2 = q.preparedStatement(sql,true);
			p2.setString(1, answer);
			p2.setString(2, email);
			p2.setInt(3, modelTestSetId);
			p2.setInt(4, problemNo);
			p2.executeUpdate();
			p2.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			q.close();
		}catch(Exception e2){}
	
	}

	public AnyTest getAnyModelTest(int id){
		AnyTest test = new AnyTest();
		 String sql = "select  * from  model_test_set s where  s.id="+id;
	      AQuery q = new AQuery(sql);
	      if(q.next()){
	    	  test.setDescription(q.getString(("description_")));
	    	  test.setTotal(q.getInt("problems_total"));
	      }
	      
		return test;
	}


	public String getStudentAnswer(String email, int setId, int problemNumber){

		String answer = null;
		AQuery q = new AQuery ();
		 String sql = "select  answer from  user_test_answer u where  u.user_email=? and u.model_test_id=? and u.problem_no=?";
				 PreparedStatement p = null;
		 
			try{
				p = q.preparedStatement(sql);
				p.setString(1, email);
				p.setInt(2, setId);
				p.setInt(3, problemNumber);
				ResultSet rs = p.executeQuery();
				if(rs.next()){
					answer = rs.getString("answer");
				}
				rs.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try{p.close();}catch(Exception e){}
			}
				    
			System.out.println(sql);
System.out.println(" the answer from the db query = "+answer+" for email:"+email+" setid:"+setId + " probno:"+problemNumber);
	      
		return answer;
		
	}
	
}
  
	
create table user(id int primary key auto_increment, 
email varchar(100),
pass_word varchar(100),
first_name varchar(100),
last_name varchar(100),
phone varchar(15),
verify_code varchar(100),
reset_code varchar(100),
is_active varchar(1),
grade int,
created_on long,
 created_date varchar(30)); 
 
 
 
create table exercise(
	 id int primary key auto_increment,
	 content text,
	 year_ int,
	 number_ int,
	 is_multiple varchar(1),
	 choice_a varchar(50),
	 choice_b varchar(50),
	 choice_c varchar(50),
	 choice_d varchar(50),
	 choice_e varchar(50),
	 answer varchar(1),
	 answer_text varchar(50),
	 explanation text,
	 sources varchar(200),
	 purpose varchar(200),
	 subject varchar(30),
	 grade varchar(15),
	 difficulty int,
	 keywords varchar(50),
	 tag varchar(50),
	 external_image_link varchar(50),
	 comment varchar(200),
	 created_by varchar(50),
	 created_on long,
	 created_on_v varchar(50),
	 updated_by varchar(50),
	 updated_at long,
	 updated_at_v varchar(50)
);


create table model_test_set(
	 id int primary key auto_increment,
	 problems_total int,
	 description_ text
);

create table any_test_content(

	 id int primary key auto_increment,
	 test_type varchar(20),
	 model_test_id int,
	 exercise_id int,
	 section varchar(20),
	 problem_no int,
	 foreign key(model_test_id) references model_test_set(id),
	 foreign key(exercise_id) references exercise(id)
);
 
--3 test values
insert into exercise(content, choice_a, choice_b, choice_c, choice_d, answer)
values('\\sqrt{4}-\\sqrt{1}=', '1', '2', '3', '4', 'A');


insert into exercise (content, choice_a, choice_b, choice_c, choice_d, answer)
values('\\sqrt{4}\\times \\sqrt{1}=', '1', '2', '3', '4', 'B');



insert into exercise(content, choice_a, choice_b, choice_c, choice_d, answer)
values('\\sqrt{4}+\\sqrt{1}=', '1', '2', '3', '4', 'C');

insert into exercise(content, choice_a, choice_b, choice_c, choice_d, answer)
values('\\text{The length of the hypothenuse of a triangle with leges 4, 5 is} ', '\\sqrt{3}', '5', '\\sqrt{41}', '\\sqrt{43}', 'C');

insert into exercise(content, choice_a, choice_b, choice_c, choice_d, answer)
values('\\text{The length of the hypothenuse of a triangle with leges 5, 6 is} ', '\\sqrt{31}', '5', '\\sqrt{41}', '\\sqrt{61}', 'D');

 
 


insert into model_test_set(description_, problems_total) values('sat 2 test', 5);

insert into any_test_content(test_type, model_test_id, exercise_id, section, problem_no) values('SAT2', 1,1, 'section 1', 1);

insert into any_test_content(test_type, model_test_id, exercise_id, section, problem_no) values('SAT2',1,2, 'section 1', 2);

insert into any_test_content(test_type, model_test_id, exercise_id, section, problem_no) values('SAT2',1,3, 'section 1', 3);
 
insert into any_test_content(test_type, model_test_id, exercise_id, section, problem_no) values('SAT2',1,7, 'section 1', 4);

insert into any_test_content(test_type, model_test_id, exercise_id, section, problem_no) values('SAT2',1,8, 'section 1', 5);

/* the problem_no column will match the any_test_content problme_no column */
create table user_test_answer(
	 id int primary key auto_increment,
	 user_email varchar(50),
	 model_test_id int,
	 problem_no int,  
	 answer varchar(10),
	 comment varchar(100), 
	 foreign key(model_test_id) references model_test_set(id)
);

 
  
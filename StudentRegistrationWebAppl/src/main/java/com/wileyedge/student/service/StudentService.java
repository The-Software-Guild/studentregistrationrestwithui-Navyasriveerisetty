package com.wileyedge.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wileyedge.student.dao.StudentIDao;
import com.wileyedge.student.model.Student;

@Service
public class StudentService implements Iservice 
{

    @Autowired(required = true)
    @Qualifier(value="hiberstujpa")
    private StudentIDao studentDao;
    

	@Override
	public Student addStudent(Student student) {
		Student stu = studentDao.addStudent(student);
		return stu;
	}

	@Override
	public Student updateStudent(Student student) {
		Student stu = studentDao.updateStudent(student);
		return stu;
	}



	@Override
	public Student findById(int id) {
		Student student = studentDao.findById(id);
	
		return student;
	}
	
	@Override
	public List<Student> findAllStudents() {
		List<Student> students = studentDao.findAllStudents();
		return students;
	}

	@Override
	public void deleteStudent(int id) {
		studentDao.deleteStudent(id);

	}

	@Override
	public List<Student> findByName(String name) {
		
		return studentDao.findByName(name);
		
	}
}

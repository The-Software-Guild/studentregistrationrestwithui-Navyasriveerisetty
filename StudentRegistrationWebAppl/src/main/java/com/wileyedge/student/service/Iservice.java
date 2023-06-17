package com.wileyedge.student.service;

import java.util.List;
import com.wileyedge.student.model.Student;

public interface Iservice 
{
	public Student addStudent(Student student);
	public Student updateStudent(Student student);
	public Student findById(int id);
	public List<Student> findByName(String name);
	public List<Student> findAllStudents();
	public void deleteStudent(int id);
}
package com.wileyedge.student.dao;

import java.util.List;
import java.util.Optional;

import com.wileyedge.student.model.Student;

public interface StudentIDao 
{
    public Student addStudent(Student student);
    public Student updateStudent(Student student);
    public List<Student> findAllStudents();
    public Student findById(int id);
    public Student deleteStudent(int id);
	public List<Student> findByName(String name);
	
	
	
}
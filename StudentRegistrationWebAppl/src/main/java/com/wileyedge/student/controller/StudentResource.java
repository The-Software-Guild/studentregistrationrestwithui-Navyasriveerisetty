package com.wileyedge.student.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wileyedge.student.exception.StudentNotFoundException;
import com.wileyedge.student.model.Student;
import com.wileyedge.student.service.StudentService;

@RestController
//@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentResource {

	@Autowired
	private StudentService studentService;

	@GetMapping(path="/")
	public String showIndexPage() 
	{
		return "index";
	}
	
	@GetMapping(path="/students")
	public List<Student> getAllStudents() 
	{
		System.out.println("Inside getAllStudents() of StudentResource");
		List<Student> students = studentService.findAllStudents();
		return students;
	}

	@GetMapping(path="/students/{id}")
	public Student getStudent(@PathVariable int id) 
	{
		System.out.println("Inside getStudent of getAll");
		Student student = studentService.findById(id);
		if (student == null) 
		{
			throw new StudentNotFoundException("Student with id: " + id + " not found");
		}
		return student;
	}

	//@PostMapping(path="/students",consumes="application/json")
	@PostMapping(path="/students")
	public Student addStudent( @Valid @RequestBody Student student) 
	{
		System.out.println("Inside addStudent of StudentResource");
		Student addedStudent = studentService.addStudent(student);
		return addedStudent;
	}

	@DeleteMapping(path="/students/{id}")
	public void deleteStudent(@PathVariable int id) 
	{
		System.out.println("Inside deleteStudent of StudentResource");
		Student stu = studentService.findById(id);
		if(stu == null) 
		{
			throw new StudentNotFoundException("student with id: " + id + " not found!");
		}
		studentService.deleteStudent(id);
	}
	
	@PutMapping(path="/students/{id}")
	public Student editStudent(@PathVariable(value="id") Integer id, @Valid @RequestBody Student student) throws StudentNotFoundException 
	{
		Student editstud = studentService.findById(id);
		
		if(editstud == null) 
		{
			throw new StudentNotFoundException("student with id: " + id + " not found!");
		}
	
		editstud.setName(student.getName());
		editstud.setAge(student.getAge());
		editstud.setMobile(student.getMobile());
		editstud.setAddress(student.getAddress());
		studentService.updateStudent(student);
		return editstud;
		
	}
	
	@GetMapping(path = "/students/name/{name}")
	public List<Student> fetchStudentByName(@PathVariable String name) throws StudentNotFoundException 
	{
		List<Student> students = studentService.findByName(name);
		
		if(students == null) 
		{
			throw new StudentNotFoundException("student with name: " + name + " not found!");
		}
		
		return students;
	}
	
}

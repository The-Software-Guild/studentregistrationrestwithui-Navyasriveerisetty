package com.wileyedge.student.dao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.wileyedge.student.model.Student;


@Repository
@Profile("dev")
public class StudentMemoryRepository implements StudentIDao 
{
	
	private static List<Student> studentList = new ArrayList<>();
	
	static 
	{
		studentList.add(new Student("John", 22, "423678", "Sydney"));
		studentList.add(new Student("Bean", 23, "678901", "Melbourne"));
		
	}
	
	public StudentMemoryRepository() 
	{
		System.out.println("In student Memory Repository");
	}
	
	@Override
	public Student addStudent(Student student) 
	{
		studentList.add(student);
		return student;
	}

	@Override
	public Student updateStudent(Student student) 
	{
		boolean found = false;
		
		for (int i=0; i < studentList.size(); i++) 
		{
			var stu = studentList.get(i);
			
			if (stu.getId()== student.getId()) 
			{
				studentList.set(i, student);
				found = true;
				return student;
			}
		}
		
		if (found = false) 
		{
			System.out.println("No student with that id can be found");
					
		}
		return null;
			
	}

	@Override
	public List<Student> findAllStudents() 
	{
		
		return studentList;
	}

	@Override
	public Student findById(int id) 
	{
		Student stu=studentList.stream().filter(a -> a.getId() == id).findAny().orElse(null);
		return stu;
	}

	
	@Override
	public Student deleteStudent(int id) 
	{
		
		Iterator<Student> iter = studentList.iterator();
		while(iter.hasNext()) 
		{
			Student student = iter.next();
			if(student.getId() == id) 
			{
				iter.remove();
				return student;
			}
		}
		return null;
	}

	@Override
	public List<Student> findByName(String name) 
	{
		List<Student> studentsByName = new ArrayList<>();
		
		for(Student s : studentList) 
		{
			if(name.equals(s.getName())) 
			{
				studentsByName.add(s);
			}
		}
		return studentsByName;
	}

}


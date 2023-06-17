package com.wileyedge.student.dao;

import com.wileyedge.student.model.Student;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("studentJpaRepository")
public interface StudentJpaRepository extends JpaRepository<Student, Integer> 
{
    // You can define additional query methods specific to the Student entity if needed
    // For example:
     //public List<Student> findByName(String name);
     //List<Student> findByName(String name);
}
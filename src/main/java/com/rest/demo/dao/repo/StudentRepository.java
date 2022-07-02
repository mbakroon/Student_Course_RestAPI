package com.rest.demo.dao.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.demo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	
	//add a method to sort by last name
		//spring Data JPA will parse the method name
		//Looks for a specific format and pattern
		//Creates appropriate query ... behind the scenes
		//details www.luv2code.com/query-methods
		public List<Student> findBylastNameIgnoreCaseOrderByEmail(String last);
		
		public List<Student> findAllByOrderByFirstNameAsc();

		public List<Student> findAllByOrderByFirstNameDesc();
		
		
		@Query("SELECT s FROM Student s WHERE s.firstName LIKE %?1%")
		public List<Student> findAllLike(String name);
		
//		@Query("SELECT s.firstName, s.lastName , c.courses FROM Student s JOIN s.courses c")
//		public List<Student> findAllFirstLastNameJoinCourse();
//
	
//		@Query("SELECT s.lastName, s.firstName, c.courses FROM Student s JOIN s.courses c ")
//		public List<Student> getAllStudents();



}

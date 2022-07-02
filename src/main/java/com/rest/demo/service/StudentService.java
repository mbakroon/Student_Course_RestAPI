package com.rest.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.rest.demo.entity.Student;
import com.rest.demo.pojos.StudentCourseResponse;
import com.rest.demo.pojos.StudentRequest;

public interface StudentService {

	public List<Student> findAll();

	public List<Student> findAllLike(String string);

	public Student findById(int studentId);

	public Student save(Student student);

	public void deleteById(int studentId);

	public Student addCourseToStudent(StudentCourseResponse studentCourseResponse);

	public Student removeAllCourseFromStudent(StudentCourseResponse studentCourseResponse);
	
	public Student removeOneCourseFromStudent(StudentCourseResponse studentCourseResponse) ;


	public List<Student> getStudentCourse();

	public List<Student> findBylastNameIgnoreCaseOrderByEmail(String last);

//	public List<Student> findAllFirstLastNameJoinCourse();

	 String getStudentFirstAndLastName = " select "
	            + " s.firstName as firstName, "
	            + " s.lastName as lastName, "
	            + "	s.courses as courses "
	            + " from Student s ";

	
	@Query(value = getStudentFirstAndLastName)
	public List<Student> getFirstLastNameAndCourses();
	
	public List<Student> returnlist();
}

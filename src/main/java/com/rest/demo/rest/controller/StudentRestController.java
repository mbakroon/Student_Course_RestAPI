package com.rest.demo.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.entity.Student;
import com.rest.demo.error.StudentErrorResponse;
import com.rest.demo.error.StudentNotFoundException;
import com.rest.demo.pojos.StudentCourseResponse;
import com.rest.demo.pojos.StudentRequest;
import com.rest.demo.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	
	private StudentService studentService;

	@Autowired
	public StudentRestController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping("/addCourseToStudent")
	public Student addCourseToStudent(@RequestBody StudentCourseResponse studentCourseResponse) {
		
		return studentService.addCourseToStudent(studentCourseResponse);
		
	}
	
	@PostMapping("/removeAllCourseFromStudent")
	public Student removeAllCourseFromStudent(@RequestBody StudentCourseResponse studentCourseResponse) {
		
		
		return studentService.removeAllCourseFromStudent(studentCourseResponse);
	}
	
//	@PostMapping("/removeCourseFromStudent")
//	@Modifying      // to mark delete or update query
//	@Query("delete course_id from student s where s.id =:student_Id join s.course_id id where id = :course_Id")   
//	public void removeCourseFromStudent(@PathVariable int student_Id,@PathVariable int course_Id) {
//		
//	}
//	
	@GetMapping("/students")
	public List<Student> findAll(){
		return studentService.findAll();	
	}
	
	@GetMapping("/students/{studentId}")
	public Student findById(@PathVariable int studentId) {
		
		Student theStudent = studentService.findById(studentId);
		
		if(theStudent==null) {
			throw new StudentNotFoundException("the Student not found - id " + studentId);
		}
		
		return theStudent;
	}
	
	@GetMapping("/studentsLike/{partName}")
	public List<Student> findAllLike(@PathVariable String partName){
		return studentService.findAllLike(partName);
	}
	
	@PostMapping("/student")
	public Student addStudent(@RequestBody Student theStudent) {
		
		theStudent.setId(0);
		studentService.save(theStudent);
		
		return theStudent;
		
	}
	
	
	public boolean checkStudent(int studentID) {
		
		Optional<Student> theStudent = Optional.of(studentService.findById(studentID));
		if(theStudent.isPresent()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@PutMapping("/student")
	public Student updateStudent(@RequestBody Student theStudent) {
		
		if(checkStudent(theStudent.getId())) {
			return studentService.save(theStudent);
		}
		else {
			throw new RuntimeException("the Student not found - id: ");
		}
			
	
		
	}
	
	@DeleteMapping("delete/{StudentId}")
	public String deleteStudent(@PathVariable int StudentId) {
		
		Student theStudent = studentService.findById(StudentId);
		if( theStudent == null) {
			throw new StudentNotFoundException("The Student not found - id " + StudentId);
			
		}
		else {
			studentService.deleteById(StudentId);
		}
		
		return "The Student is deletet - id: " +StudentId;
	}
	
	@GetMapping("/getStudentCourse")
	public List<Student> getStudentCourse(){
		
		return studentService.getStudentCourse();
		
	}
	
	
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
		
		StudentErrorResponse error = new StudentErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	 }

	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
		
		StudentErrorResponse error = new StudentErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/orderByEmail/{email}")
	public List<Student> findBylastNameIgnoreCaseOrderByEmail(@PathVariable String email){
		return studentService.findBylastNameIgnoreCaseOrderByEmail(email);
	}
	
	@GetMapping("/getFirstLastNameAndCourses")
	public List<Student> getFirstLastNameAndCourses(){
		return studentService.getFirstLastNameAndCourses();
	}
	
	@GetMapping("/getTest")
	public List<Student> getTest(){
		return studentService.returnlist();
	}


}

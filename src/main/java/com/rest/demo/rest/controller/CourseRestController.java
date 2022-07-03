package com.rest.demo.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rest.demo.entity.Course;
import com.rest.demo.error.StudentErrorResponse;
import com.rest.demo.error.StudentNotFoundException;
import com.rest.demo.pojos.CourseRequest;
import com.rest.demo.service.CourseService;

@RestController
@RequestMapping("/api")
public class CourseRestController {
	
	private CourseService courseService;
	
	
	@Autowired
	public CourseRestController(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@GetMapping("/courses")
	public List<Course>findAll(){
		return courseService.findAll();
	}
	

	@GetMapping("/courses/findSorted/{field}")
	public List<Course> findAllSortedByField(@PathVariable String field){
		
		return courseService.findAllSortedByField(field);
	}
	
	@GetMapping("/courses/pagination/{offSet}/{pageSize}")
	public Page<Course> getCoursesWithSort(@PathVariable int offSet,@PathVariable int pageSize ){
		Page<Course> courseWithPagination = courseService.findCoursesWithPagination(offSet, pageSize);
		return courseWithPagination;
	}
	
	@GetMapping("/courses/pagination/{offSet}/{pageSize}/{field}")
	public Page<Course> getCoursesWithSort(@PathVariable int offSet,@PathVariable int pageSize,@PathVariable String field){
		Page<Course> courseWithPagination = courseService.findCoursesWithPagination(offSet, pageSize, field);
		return courseWithPagination;
	}
	
	@PostMapping("courses/addCourses")
	public Course addCourse(@RequestBody Course theCourse) {
		return 	courseService.addCourse(theCourse);

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
	
	@PostMapping("/addStudentToCourse")
	public Course addStudentToCourse(@RequestBody CourseRequest courseRequest) {
		
		return courseService.addStudentToCourse(courseRequest);
		
	}
	
	@PostMapping("/removeStudentFromCourse")
	public Course removeCourseFromStudent(@RequestBody CourseRequest courseRequest) {
		
		
		return courseService.removeStudentFromCourse(courseRequest);
	}
	
	

}

package com.rest.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rest.demo.entity.Course;
import com.rest.demo.pojos.CourseRequest;

public interface CourseService {
	
	public List<Course> findAllSortedByField(String field);
	
	public Page<Course> findCoursesWithPagination(int offset, int pageSize);
	
	public Page<Course> findCoursesWithPagination(int offset, int pageSize , String field);

	public Course addCourse(Course theCourse);

	public List<Course> findAll();
	
	public Course addStudentToCourse(CourseRequest courseRequest);
	
	public Course removeStudentFromCourse(CourseRequest courseRequest);
	
}

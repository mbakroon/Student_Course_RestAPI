package com.rest.demo.pojos;

import java.util.ArrayList;
import java.util.Set;

import com.rest.demo.entity.Course;

public class StudentRequest {
	
	//Student DI
	private int id;
		

	private Set<Course> courses;
	
	private ArrayList<Integer> courseId;


//	public StudentRequest(int id, Set<Course> courses) {
//		this.id = id;
//		this.courses = courses;
//	}
	
	public StudentRequest(int id, ArrayList<Integer>courseId) {
		this.id = id;
		this.courseId = courseId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public Set<Course> getCourses() {
		return courses;
	}


	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}


	public ArrayList<Integer> getCourseId() {
		return courseId;
	}


	public void setCourseId(ArrayList<Integer> courseId) {
		this.courseId = courseId;
	}




	
	

	

}

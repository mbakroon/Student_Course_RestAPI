package com.rest.demo.pojos;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class StudentCourseResponse {
	
	private int id;
	
	private ArrayList<Integer> courseId;
	
	//private String test;

	public StudentCourseResponse() {
		// TODO Auto-generated constructor stub
	}

	public StudentCourseResponse(int id, ArrayList<Integer> courseId) {
		this.id = id;
		this.courseId = courseId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Integer> getCourseId() {
		return courseId;
	}

	public void setCourseId(ArrayList<Integer> courseId) {
		this.courseId = courseId;
	}
	
	
	//with runing program , you will get error because the query Expected studentId and CourseName
	//the soultion to remove the String test from Constructor
	//cuse the setter/getter of test Argument you will got null variable in Postman 
//	{
//        "studentId": 2,
//        "courseName": "English",
//        "test": null <----
//    },
	//to hide it use JsonIgnorProperties
	
	
}
package com.rest.demo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class StudentCourseResponse {
	
	private int studentId;
	private String courseName;
	
	//private String test;

	public StudentCourseResponse() {
		// TODO Auto-generated constructor stub
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
	public StudentCourseResponse(int studentId, String courseName /*, String test*/) {
		this.studentId = studentId;
		this.courseName = courseName;
		//this.test = test;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

//	public String getTest() {
//		return test;
//	}
//
//	public void setTest(String test) {
//		this.test = test;
//	}
//	
	
	

}

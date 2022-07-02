package com.rest.demo.pojos;

import java.util.Set;

import com.rest.demo.entity.Student;

public class CourseRequest {
	
	//Course Id
	private int id;
	private Set<Student> listOfStudent;

	public CourseRequest() {
		// TODO Auto-generated constructor stub
	}

	public CourseRequest(int id, Set<Student> listOfStudent) {
		this.id = id;
		this.listOfStudent = listOfStudent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Student> getListOfStudent() {
		return listOfStudent;
	}

	public void setListOfStudent(Set<Student> listOfStudent) {
		this.listOfStudent = listOfStudent;
	}
	
	

}

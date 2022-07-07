package com.rest.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name", unique=true)
	private String name;

	@Column(name = "serial_number", unique=true)
	private int serialNumber;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinTable(name = "course_student",
			   joinColumns = @JoinColumn(name = "course_id"),
			   inverseJoinColumns = @JoinColumn(name = "student_id"))
	@JsonIgnore
	private Set<Student> students = new HashSet<>();

	public Course() {
	}

	
	public Course( String name, int serialNumber) {
		this.name = name;
		this.serialNumber = serialNumber;
	}
	
	public Course(int id, String name, int serialNumber, Set<Student> students) {
		this.id = id;
		this.name = name;
		this.serialNumber = serialNumber;
		this.students = students;
	}



	// add a convenience method

//	public void addStudent(Student theStudent) {
//		
//		this.students.add(theStudent);
//		
//		if (students == null) {
//			students = new HashSet<>();
//		}
//
//		students.add(theStudent);
//	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getSerialNumber() {
		return serialNumber;
	}



	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}



	public Set<Student> getStudents() {
		return students;
	}



	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	public void removeStudent(Set<Student> studentToRemove) {
		for (Student student : studentToRemove) {
			students.remove(student);
		
		}
		
	}

}

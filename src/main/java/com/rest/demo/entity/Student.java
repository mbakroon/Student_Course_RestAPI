package com.rest.demo.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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



@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="geb_dat")
	private LocalDate geb_datum;
	
	@Column(name="email")
	private String email; 
	
	@Column(name="tel")
	private int tel_number;
	
	@Column(name="address")
	private String address;
	

	
	@ManyToMany(fetch=FetchType.LAZY,
				cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						   CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="course_student",
			joinColumns = @JoinColumn(name="student_id"),
			inverseJoinColumns = @JoinColumn(name="course_id")
			)
	//@JsonIgnore
	private Set<Course> courses = new HashSet<>();
	

	public Student() {
	}

	

	public Student(String firstName, String lastName, LocalDate geb_datum, String email, int tel_number, String address,
			 Set<Course> courses) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.geb_datum = geb_datum;
		this.email = email;
		this.tel_number = tel_number;
		this.address = address;
		this.courses = courses;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getGeb_datum() {
		return geb_datum;
	}

	public void setGeb_datum(LocalDate geb_datum) {
		SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
		
			DateFor.format(geb_datum);

		this.geb_datum = geb_datum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTel_number() {
		return tel_number;
	}

	public void setTel_number(int tel_number) {
		this.tel_number = tel_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
	@Override
	public String toString() {
		return "Students [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", geb_datum=" + geb_datum
				+ ", email=" + email + ", tel_number=" + tel_number + ", address=" + address + "]";
	}


	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses =  courses;
	}

	public void removeCourses(Set<Course> coursesToRemove) {
		for (Course course : coursesToRemove) {
			courses.remove(course);
		
		}
		
	}
	
	public void removeCourse(int courseId) {
		courses.remove(courseId);
	}

}

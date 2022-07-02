package com.rest.demo.service;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.demo.dao.repo.CourseRepository;
import com.rest.demo.dao.repo.StudentRepository;
import com.rest.demo.entity.Course;
import com.rest.demo.entity.Student;
import com.rest.demo.pojos.StudentRequest;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private CourseRepository courseRepository;

	Set<Course> ListOfStudentCources;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

//	@PostConstruct
//	public void initDB() {
//		Set<Student> theStudents = new HashSet<Student>();
//		
//		
//		for(int i=0; i<10; i++) {
//			theStudents.add(new Student("FirstName "+i
//					,"LastName "+i 
//					,LocalDate.of(1991,01,i+1)
//					,"student "+i+"@outlook.com"
//					,12345+i
//					,"studentAddress "+i
//					,null));
//		}
//		studentRepository.saveAll(theStudents);
//	}

	@Override
	public List<Student> findAll() {

		return studentRepository.findAllByOrderByFirstNameDesc();
	}

	@Override
	public List<Student> findAllLike(String string) {

		return studentRepository.findAllLike(string);
	}

	@Override
	public Student findById(int studentId) {

		Optional<Student> result = studentRepository.findById(studentId);

		Student theStudent = null;
		if (result.isPresent()) {
			theStudent = result.get();
		} else {
			throw new RuntimeException("the Student not found - id " + studentId);
		}
		return theStudent;
	}

	@Override
	public Student save(Student student) {

		return studentRepository.save(student);

	}

	@Override
	public void deleteById(int studentId) {

		Optional<Student> result = studentRepository.findById(studentId);

		if (result.isPresent()) {
			studentRepository.deleteById(studentId);
		} else {
			throw new RuntimeException("the Student not found - id " + studentId);
		}

	}

//	public Student StudentWithCourse(StudentRequest studentRequest) {
//
//		Optional<Student> studentFromDb = studentRepository.findById(studentRequest.getId());
//		System.out.println("id: " + studentRequest.getId());
//		Student theStudent = null;
//
//		// check if Student exist in DB and get it
//		if (studentFromDb.isPresent())
//			theStudent = studentFromDb.get();
//
//		ListOfStudentCources = new HashSet<Course>();
//		for (Course course : studentRequest.getCourses()) {
//			System.out.println("course id :" + course.getId());
//			
//			Optional<Course> theCourse = courseRepository.findById(course.getId());
//
//			if (theCourse.isPresent())
//				System.out.println("cours is present: " + theCourse.isPresent());
//			ListOfStudentCources.add(theCourse.get());
//		}
//
//		return theStudent;
//	}
	
	public Student StudentWithCourse(StudentRequest studentRequest) {

		Optional<Student> studentFromDb = studentRepository.findById(studentRequest.getId());
		System.out.println("id: " + studentRequest.getId());
		Student theStudent = null;

		// check if Student exist in DB and get it
		if (studentFromDb.isPresent())
			theStudent = studentFromDb.get();

		ListOfStudentCources = new HashSet<Course>();
		for (int courseId : studentRequest.getCourseId()) {
			Optional<Course> course = courseRepository.findById(courseId);

		
			if (course.isPresent())
				System.out.println("cours is present: " + course.isPresent());
			ListOfStudentCources.add(course.get());
		}

		return theStudent;
	}

	@Override
	public Student addCourseToStudent(StudentRequest studentRequest) {

		Student theStudent = StudentWithCourse(studentRequest);

		if (theStudent != null) {
			theStudent.setCourses(ListOfStudentCources);

			return studentRepository.save(theStudent);
		}
		return theStudent;

	}

	@Override
	public Student removeCourseFromStudent(StudentRequest studentRequest) {

		Student theStudent = StudentWithCourse(studentRequest);

//		int studentId = studentRequest.getId();
//		int courseId = studentRequest.getId();

		// delete object with primary key
		// DELETE posts FROM posts INNER JOIN projects ON projects.project_id =
		// posts.project_id WHERE projects.client_id = :client_id
//		Query theQuery = entityManager.createQuery("delete FROM Student WHERE student_id =:studentId AND course_id=: courseId");
//		theQuery.setParameter("studentId", studentId).setParameter("courseId", courseId);
//		theQuery.executeUpdate();

		if (theStudent != null) {

			theStudent.removeCourses(ListOfStudentCources);

		}

		return studentRepository.save(theStudent);
	}

	@SuppressWarnings("unchecked")
	@Override
	// @Query("SELECT new com.rest.demo.pojos.StudentCourseResponse (s.id , c.name)
	// FROM Student s JOIN s.courses c")
	public List<Student> getStudentCourse() {

		TypedQuery<Student> query = (TypedQuery<Student>) entityManager.createQuery(
				"SELECT new com.rest.demo.pojos.StudentCourseResponse (s.id , c.name) FROM Student s JOIN s.courses c");

		List<Student> results = query.getResultList();

		return results;
	}

	@Override
	public List<Student> findBylastNameIgnoreCaseOrderByEmail(String last) {

		return studentRepository.findBylastNameIgnoreCaseOrderByEmail(last);
	}

	@Override
	public List<Student> getFirstLastNameAndCourses() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Student> getFirstLastNameAndCourses() {
//		// TODO Auto-generated method stub
//		return studentRepository.findAllFirstLastNameJoinCourse();
//	}

//	@Override
//	public List<Student> findAllFirstLastNameJoinCourse() {
//
//		return studentRepository.findAllFirstLastNameJoinCourse();
//	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Student> getFirstLastNameAndCourses() {
//		TypedQuery<Student> query = 
//				(TypedQuery<Student>) entityManager.createQuery(
//				"SELECT new com.rest.demo.pojos.FirstLastNameAndCourseRespose (s.firstName ,s.lastName , s.courses) FROM Student s");
//
//		List<Student> results = query.getResultList();
//
//		return results;
//
//	}
	
	
	
	public List<Student> returnlist(){
//		return studentRepository.getAllStudents();
		return studentRepository.findAll();
	}
	
	
	
	
	

}

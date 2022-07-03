package com.rest.demo.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rest.demo.dao.repo.CourseRepository;
import com.rest.demo.dao.repo.StudentRepository;
import com.rest.demo.entity.Course;
import com.rest.demo.entity.Student;
import com.rest.demo.pojos.StudentCourseResponse;

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

	public Student StudentWithCourse(StudentCourseResponse studentCourseResponse) {

		Student theStudent = null;

		Optional<Student> studentFromDb = studentRepository.findById(studentCourseResponse.getId());

		// check if Student exist in DB and get it
		if (studentFromDb.isPresent()) {
			theStudent = studentFromDb.get();
		}

		ListOfStudentCources = new HashSet<Course>();
		for (Course course : theStudent.getCourses()) {
			ListOfStudentCources.add(course);
		}

		for (int courseId : studentCourseResponse.getCourseId()) {
			Optional<Course> course = courseRepository.findById(courseId);

			if (course.isPresent()) {
				ListOfStudentCources.add(course.get());
			}
		}

		return studentFromDb.get();
	}

	@Override
	public Student addCourseToStudent(StudentCourseResponse studentCourseResponse) {
		System.out.println("Enter addCourseToStudent Method");

		Student theStudent = StudentWithCourse(studentCourseResponse);

		theStudent.setCourses(ListOfStudentCources);

		return studentRepository.save(theStudent);

	}

	@Override
	public Student removeAllCourseFromStudent(StudentCourseResponse studentCourseResponse) {

		Student theStudent = StudentWithCourse(studentCourseResponse);

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
	
	@Override
	public Student removeOneCourseFromStudent(StudentCourseResponse studentCourseResponse) {

		Student theStudent = StudentWithCourse(studentCourseResponse);
		Iterator<Course> courseId = ListOfStudentCources.iterator();

		if (theStudent != null) {
			
			while (courseId.hasNext()) {
				//theStudent.re
				
			}

		}

		return studentRepository.save(theStudent);
	}

	@SuppressWarnings("unchecked")
	@Override
	// @Query("SELECT new com.rest.demo.pojos.StudentCourseResponse (s.id , c.name)
	// FROM Student s JOIN s.courses c")
	public List<Student> getStudentCourse() {

		TypedQuery<Student> query = (TypedQuery<Student>) entityManager.createQuery(
				"SELECT new com.rest.demo.pojos.StudentCourseResponse(s.studentId , c.courseName) FROM Student s JOIN s.courses c");

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

	public List<Student> returnlist() {
//		return studentRepository.getAllStudents();
		return studentRepository.findAll();
	}

}

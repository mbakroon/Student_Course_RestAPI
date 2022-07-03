package com.rest.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.rest.demo.dao.repo.CourseRepository;
import com.rest.demo.dao.repo.StudentRepository;
import com.rest.demo.entity.Course;
import com.rest.demo.entity.Student;
import com.rest.demo.error.StudentNotFoundException;
import com.rest.demo.pojos.CourseRequest;

@Service
public class CourseServiceImpl implements CourseService {

	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	private Set<Student> listOfCoursesStudent;

	@Autowired
	private EntityManager entityManager;

//	@PostConstruct
//	public void initDB() {
//		Set<Course> theCourses = new HashSet<Course>();
//		
//		for(int i=0; i<100; i++) {
//			theCourses.add(new Course("course"+i, 5142+i));
//		}
//		courseRepository.saveAll(theCourses);
//	}

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public List<Course> findAllSortedByField(String field) {

		return courseRepository.findAll(Sort.by(Sort.Direction.ASC, field));
	}

	@Override
	public Page<Course> findCoursesWithPagination(int offset, int pageSize) {
		Page<Course> theCourses = courseRepository.findAll(PageRequest.of(offset, pageSize));
		return theCourses;
	}

	@Override
	public Page<Course> findCoursesWithPagination(int offset, int pageSize, String field) {
		Page<Course> theCourses = courseRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
		return theCourses;
	}

	@Override
	public Course addCourse(Course theCourse) {

		int tempSerialNumber = theCourse.getSerialNumber();
		Query theQuery = entityManager
				.createQuery("select c " + 
							 "FROM Course c "+ 
							 "WHERE c.serialNumber LIKE :tempSerialNumber");
		theQuery.setParameter("tempSerialNumber", tempSerialNumber);

		Course tempCourse = (Course) theQuery.getSingleResult();
//		System.out.println("Course: "+tempCourse.getName() + "serial: "+tempCourse.getSerialNumber());

		if (!(tempCourse.equals(theCourse))) {
			courseRepository.save(theCourse);
		} else {
			throw new StudentNotFoundException("The Course is allready in DB!");
		}
		return null;
	}
	

	@Override
	public List<Course> findAll() {
		return courseRepository.findAll();
	}
	
	public Course CourseWithStudent(CourseRequest courseRequest) {

		Optional<Course> courseFromDb = courseRepository.findById(courseRequest.getId());
		System.out.println("id: " + courseFromDb.get().getId());
		Course theCourse = null;

		// check if Student exist in DB and get it
		System.out.println(courseFromDb.isPresent());
		if (courseFromDb.isPresent())
			theCourse = courseFromDb.get();

		listOfCoursesStudent = new HashSet<Student>();
		for (Student student : courseRequest.getListOfStudent()) {
			System.out.println("Student id :" + student.getId());
			Optional<Student> theStudent = studentRepository.findById(student.getId());

			if (theStudent.isPresent())
				System.out.println("cours isss present: " + theStudent.isPresent());
			listOfCoursesStudent.add(theStudent.get());
		}

		return theCourse;
	}

	@Override
	public Course addStudentToCourse(CourseRequest courseRequest) {
		Course theCourse = CourseWithStudent(courseRequest);

		if (theCourse != null) {
			theCourse.setStudents(listOfCoursesStudent);

			return courseRepository.save(theCourse);
		}
		return theCourse;
	}



	@Override
	public Course removeStudentFromCourse(CourseRequest courseRequest) {
	
		Course theCourse = CourseWithStudent(courseRequest);

		if (theCourse != null) {

			theCourse.removeStudent(listOfCoursesStudent);

		}

		return courseRepository.save(theCourse);
	}

}

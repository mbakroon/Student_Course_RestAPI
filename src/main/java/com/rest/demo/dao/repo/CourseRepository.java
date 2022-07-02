package com.rest.demo.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.demo.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository< Course, Integer> {

	
//	public List<Course> findAllByOrderBySerialNumberDesc();
	
	


}

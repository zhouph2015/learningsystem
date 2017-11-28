package com.walmart.learningsystem.model;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class CourseRepositoryTest {

	
	@Test
	public void testRepositoryWithInputFile() {
        CourseRepository couseRepository = new CourseRepository("./src/courses.txt");
        
   
        System.out.println(couseRepository.getCourseById(33333));
	}
	
	@Test
	public void testRepositorySearchById() {
        CourseRepository couseRepository = new CourseRepository("./src/courses.txt");
        
   
        assertTrue(couseRepository.getCourseById(33333).getId() == 33333);
	}
	
	@Test
	public void testSearchBySubject() {
        CourseRepository courseRepository = new CourseRepository("./src/courses.txt");
        
   
        assertTrue( courseRepository.searchBySubject("CS").size() == 5  );
	}
	
	@Test
	public void testSearchByName() {
        CourseRepository courseRepository = new CourseRepository("./src/courses.txt");
        
   
        assertTrue( courseRepository.searchByName("Object").size() == 1);
        Set<Course> set = courseRepository.searchByName("Object");
        assertTrue( ((Course)set.toArray()[0]).getName().equals("Object Oriented Design"));
	}
	
	@Test
	public void testSearchByLength() {
		CourseRepository courseRepository = new CourseRepository(
				"./src/courses.txt");

		assertTrue(courseRepository.searchByLength(3).size() == 5);
	}





}

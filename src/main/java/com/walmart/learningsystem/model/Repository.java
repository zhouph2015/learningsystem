package com.walmart.learningsystem.model;

/**
 * Created by Peter Zhou 11/25/2017
 */

import java.util.List;
import java.util.Set;

public interface Repository {
	void add( Course course);
	void Edit(Course course);
	List<Course> list();
	Course getCourseById(int id);
	Set<Course> searchById(int id);
	Set<Course> searchByName(String name);
	Set<Course> searchByLength(int length);
	Set<Course> searchBySubject(String subject);
	boolean containsCourse(int id);

}

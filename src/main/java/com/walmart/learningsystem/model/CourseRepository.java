package com.walmart.learningsystem.model;

/**
 * Created by Peter Zhou 11/25/2017
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.walmart.learningsystem.exception.CourseProcessException;
import com.walmart.learningsystem.utils.CourseParseUtil;


public class CourseRepository implements Repository {
	private Map<Integer, Integer> idMap;
	private Map<Integer, Set<Integer>> lengthMap;
	private Map<String, Set<Integer>> subjectMap;
	ArrayList<Course> courseList = new ArrayList<Course>();

	public CourseRepository() {
		idMap = new HashMap<Integer, Integer>();
	    lengthMap = new HashMap<Integer, Set<Integer>>();
	    subjectMap = new HashMap<String, Set<Integer>>();
	    courseList = new ArrayList<Course>();
		
	}

	public CourseRepository(String inputFilePath) {
		this();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(inputFilePath));
			String st;
			while ((st = br.readLine()) != null) {
				Course course = CourseParseUtil.convertStringToCourse(st);
				if (!idMap.containsKey(course.getId())) {
					// update the list
					courseList.add(course);

					int courseIndex = courseList.size() - 1;
					// add to the idMap
					idMap.put(course.getId(), courseIndex);
					// update the lengthMap
					if (lengthMap.containsKey(course.getLength())) {
						Set<Integer> set = lengthMap.get(course.getLength());
						set.add(courseIndex);
					} else {
						Set<Integer> set = new HashSet<Integer>();
						set.add(courseIndex);
						lengthMap.put(course.getLength(), set);
					}

					// update the subject map
					if (subjectMap.containsKey(course.getSubject().toLowerCase())) {
						Set<Integer> set = subjectMap.get(course.getSubject().toLowerCase());
						set.add(courseIndex);
					} else {
						Set<Integer> set = new HashSet<Integer>();
						set.add(courseIndex);
						subjectMap.put(course.getSubject().toLowerCase(), set);
					}
				}
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (CourseProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	@Override
	public void add( Course course) {
		if (!idMap.containsKey(course.getId())) {
			// add to the list
			courseList.add(course);

			int courseIndex = courseList.size() - 1;
			// add to the idMap
			idMap.put(course.getId(), courseIndex);
			// add to the lengthMap
			if (lengthMap.containsKey(course.getLength())) {
				Set<Integer> set = lengthMap.get(course.getLength());
				set.add(courseIndex);
			} else {
				Set<Integer> set = new HashSet<Integer>();
				set.add(courseIndex);
				lengthMap.put(course.getLength(), set);
			}

			// add to the subject map
			if (subjectMap.containsKey(course.getSubject().toLowerCase())) {
				Set<Integer> set = subjectMap.get(course.getSubject().toLowerCase());
				set.add(courseIndex);
			} else {
				Set<Integer> set = new HashSet<Integer>();
				set.add(courseIndex);
				subjectMap.put(course.getSubject().toLowerCase(), set);
			}
		}
		
	}

	@Override
	public void Edit(Course course) {
		int courseIndex = idMap.get(course.getId());
		Course priorCourse = courseList.get(courseIndex);

		// update the course name
		if (!priorCourse.getName().equals(course.getName())) {
			priorCourse.setName(course.getName());
		}

		// update the course length in lengthMap
		if (priorCourse.getLength() != course.getLength()) {
			lengthMap.get(priorCourse.getLength()).remove(courseIndex);
			priorCourse.setLength(course.getLength());
			if (lengthMap.containsKey(course.getLength())) {
				Set<Integer> set = lengthMap.get(course.getLength());
				set.add(courseIndex);
			} else {
				Set<Integer> set = new HashSet<Integer>();
				set.add(courseIndex);
				lengthMap.put(course.getLength(), set);
			}
		}

		// update the course subject in subjectMap
		if (!priorCourse.getSubject().equalsIgnoreCase(course.getSubject())) {
			// update the subject map
			subjectMap.get(priorCourse.getSubject().toLowerCase()).remove(courseIndex);
			priorCourse.setSubject(course.getSubject());

			if (subjectMap.containsKey(course.getSubject().toLowerCase())) {
				Set<Integer> set = subjectMap.get(course.getSubject().toLowerCase());
				set.add(courseIndex);
			} else {
				Set<Integer> set = new HashSet<Integer>();
				set.add(courseIndex);
				subjectMap.put(course.getSubject().toLowerCase(), set);
			}
		}
	}

	@Override
	public List<Course> list() {
		return courseList;
	}

	@Override
	public Course getCourseById(int id) {
		if(idMap.containsKey(id)){
			return courseList.get(idMap.get(id));
		}
		return null;
	}
	
	@Override
	public Set<Course> searchById(int id) {
		Set<Course> set = new HashSet<Course>();
		if(idMap.containsKey(id)){
			set.add(courseList.get(idMap.get(id)));
		}
		return set;
	}



	@Override
	public Set<Course> searchByName(String name) {
	       Set<Course> set = new HashSet<Course>();
		   for(int i = 0; i < courseList.size(); i++){
			   Course course = courseList.get(i);
			   if(course.getName().toLowerCase().contains(name.toLowerCase())){
				   set.add(course);
			   }
		   }
		
			return set;
	}


	@Override
	public Set<Course> searchByLength(int length) {
		  Set<Course> set = new HashSet<Course>();
		  
           if(lengthMap.containsKey(length)){
        	   Set<Integer> indexSet = lengthMap.get(length);
        	   for(Integer index : indexSet){
        		   set.add(courseList.get(index));
        	   }
        	   
           } 
           
           return set;
	}


	@Override
	public Set<Course> searchBySubject(String subject) {
	
		Set<Course> set = new HashSet<Course>();
		  
        if(subjectMap.containsKey(subject.toLowerCase())){
     	   Set<Integer> indexSet = subjectMap.get(subject.toLowerCase());
     	   for(Integer index : indexSet){
     		   set.add(courseList.get(index));
     	   }
        } 
        return set;
	}

	@Override
	public boolean containsCourse(int id) {
		if (idMap.containsKey(id)){
			return true;
		} else {
			return false;
		}
	}
}

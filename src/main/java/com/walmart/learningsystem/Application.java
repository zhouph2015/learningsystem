package com.walmart.learningsystem;

/**
 * Created by Peter Zhou 11/25/2017
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.walmart.learningsystem.model.Course;
import com.walmart.learningsystem.model.CourseRepository;
import com.walmart.learningsystem.ui.UiInput;
import com.walmart.learningsystem.ui.SearchQuery;
import com.walmart.learningsystem.validator.CourseValidator;

public class Application {
	private static String name;
	private static CourseRepository courseRepository = new CourseRepository("./src/courses.txt");
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static CourseValidator courseValidator = new CourseValidator();
	
	public static void main(String[] args) throws IOException {
		
		UiInput.showWelcomeMessage();
		name = getUserName();
		showtAllCourses();
		courseOperation();
		showFavoriteList();
	}
	
	
	public static String getUserName() throws IOException{
		System.out.print("Please Enter Your User Name(only letter and number without space): ");
		String input = reader.readLine();
		while(!UiInput.validName(input)){
			System.out.print("\nThe input name: \"" + input +"\" is not a good name (only letter and number without space), Please input User Name again:");
			input = reader.readLine();
		}
		System.out.println("\nWelcome, you have sucessfully login our system");
		return input;
	}

	public static void showtAllCourses(){
		
		List<Course> list = courseRepository.list();
		System.out.println("\nPlease Find All Courses In Our Latest Learning System:");
		for(int i = 0; i < list.size(); i++ ){
			System.out.print(i + 1 + ": ");
			System.out.println(list.get(i));
		}
	}
	
	private static void listAllCourses(){
		System.out.println("\nYou have selected to list all courses in our learning system");
		showtAllCourses();
	}
	
	public static void courseOperation() throws IOException {
		boolean flag = true;
		int option = UiInput.selectMenu();

		while (flag) {
			courseOperationProcess(option);
			System.out.print("\nWould you please continue to perform another operation(Yes or No):");
			String input = reader.readLine();
			while( ! input.equalsIgnoreCase("Yes") && ! input.equalsIgnoreCase("No")){
				System.out.print("\nInvalid input, Would you please continue to perform another operation(Yes or No):");
				input = reader.readLine();
			}
			
			if (input.equalsIgnoreCase("Yes")) {
				option = UiInput.selectMenu();
			} else {
				flag = false;
			}
		}
	}
	
	private static void courseOperationProcess( int option) throws IOException{
		switch(option){
		case 1: 
			addCourse();
			break;
		case 2: 
			editCourse();
			break;
		case 3:
			listAllCourses();
			break;
		case 4:
			searchCourse();
			break;
		default:
			break;
		}
	}
	
	private static int addCourseID() throws IOException {
		int id = 0;
		System.out.print("\nPlease Enter A Course ID (only 5 digits length will be accepted):");
		try {
			String input = reader.readLine();
			id = Integer.parseInt(input);
			while (!courseValidator.isValidID(id)) {
				System.out.print("\nThe input is an invalid ID ((only 5 digits length will be accepted), Please Enter again:");
				input = reader.readLine();
				id = Integer.parseInt(input);
			}
		} catch (NumberFormatException e) {
			System.out.println("\nCatch an ID input format exception, please input ID again");
			id = addCourseID();
		} catch(IOException e){
			throw e;
		}
		return id;
	}
	
	private static String addCourseName() throws IOException {
		System.out.print("\nPlease Enter A Course Name (only letter, number and space will be accepted):");
		String input = reader.readLine();
		while (!courseValidator.isValidName(input)) {
			System.out.print("\nThe input is an invalid Name (only letter, number and space will be accepted), Please Enter again:");
			input = reader.readLine();
		}
		return input;
	}
	
	private static int addCourseLength() throws IOException {
		System.out.print("Please Enter A Course Length in hours(only 1~3 digits will be accepted):");
		int length = 0;
		try {
			String input = reader.readLine();
			length = Integer.parseInt(input);
			while (!courseValidator.isValidLength(length)) {
				System.out.print("The input is an invalid Length hours (only 1~3 digits will be accepted), Please Enter again:");
				input = reader.readLine();
				length = Integer.parseInt(input);
			}
		} catch (NumberFormatException e) {
			System.out.println("Catch a course length input format exception, please try again");
			length = addCourseLength();
		} catch (Exception e) {
			throw e;
		}
		return length;
	}
	
	private static String addCourseSubject() throws IOException {
		System.out.print("\nPlease Enter A Course Subject (only letter will be accepted):");
		String input = reader.readLine();
		while (!courseValidator.isValidSubject(input)) {
			System.out.print("\nThe input is an invalid Subject(only letter will be accepted), Please Enter again:");
			input = reader.readLine();
		}
		return input;
	}
	
	private static int selectCourseID() throws IOException {
		System.out.print("Please Enter A Course ID to edit:");
		int id = 0;
		try {
			String input = reader.readLine();
			id = Integer.parseInt(input);
			while (!courseValidator.isValidID(id)) {
				System.out.print("The Id is an invalid ID, Please Enter again:");
				input = reader.readLine();
				id = Integer.parseInt(input);
			}
		} catch (NumberFormatException e) {
			System.out.println("Catch an ID input format exception, please try again");
			id = selectCourseID();
		} catch (IOException e) {
			throw e;
		}
		return id;
	}
	
	
	private static void  addCourse() throws IOException{
		  System.out.println("\nYou have selected to add a new course");
	      int id = addCourseID();
	      String name = addCourseName();
	      int length = addCourseLength();
	      String subject = addCourseSubject();
	      Course course = new Course(id, name, length, subject);
	      courseRepository.add(course);
	      System.out.println("\nYou have sucessfully added a new course to our learning sytem");
	}
	
	private static void editCourse() throws IOException {
		System.out.println("\nYou have selected to edit a course");
		int id = selectCourseID();
		while (!courseRepository.containsCourse(id)) {
			System.out.println("\nOur Learning System Does Not Contain This Course, Please Select Another Course ID");
			id = selectCourseID();
		}
        System.out.println("\nYou have selected the following course to edit:");
        System.out.println(courseRepository.searchById(id));
		Course tempCourse = new Course(courseRepository.getCourseById(id));

		int fieldOption = selectEditField();
		switch (fieldOption) {
		case 1:
			String courseName = addCourseName();
			tempCourse.setName(courseName);
			break;
		case 2:
			int courseLength = addCourseLength();
			tempCourse.setLength(courseLength);
			break;
		case 3:
			String courseCubject = addCourseSubject();
			tempCourse.setSubject(courseCubject);
			break;
		default:
			break;
		}
		courseRepository.Edit(tempCourse);
		System.out.println("You have successfully edit a course in our system");
	}
	

	@SuppressWarnings("unchecked")
	private static void searchCourse() throws IOException {
		System.out.println("\nYou have selected to search course");
		HashMap<String, HashSet<Course>> courseSetMap = new HashMap<String, HashSet<Course>>();
		Set<Course> searchResults = new HashSet<Course>();
		SearchQuery query = new SearchQuery();
	
		boolean flag = true;

		int searchFieldOption = UiInput.selectSearchFieldOption();

		while (flag) {
			switch (searchFieldOption) {
			case 1:
				int id = addCourseID();
				query.setId(id);
				courseSetMap.put("ID",(HashSet<Course>) courseRepository.searchById(id));
				break;
			case 2:
				String name = addCourseName();
				query.setName(name);
				courseSetMap.put("NAME",(HashSet<Course>) courseRepository.searchByName(name));
				break;
			case 3:
				int length = addCourseLength();
				query.setLength(length);
				courseSetMap.put("LENGTH", (HashSet<Course>) courseRepository.searchByLength(length));
				break;
			case 4:
				String subject = addCourseSubject();
				query.setSubject(subject);
				courseSetMap.put("SUBJECT", (HashSet<Course>) courseRepository.searchBySubject(subject));
				break;
			case 5:
				flag = false;
			default:
				flag = false;
				break;
			}
			if (flag) {
				System.out.println("\nCurrent Search Query: " + query.toString());
				searchFieldOption = UiInput.selectSearchFieldOption();
			}
		}

		ArrayList<Entry<String, HashSet<Course>>> array = new ArrayList(
				courseSetMap.entrySet());
		// Combine the search results
		if (array.size() == 1) {
			searchResults.addAll(array.get(0).getValue());
		} else if (array.size() > 1){
			searchResults.addAll(array.get(0).getValue());
			for (int i = 1; i < array.size(); i++) {
				searchResults.retainAll(array.get(i).getValue());
			}
		}

		if (searchResults.isEmpty()) {
			System.out.println("\nNo Course is found with this query: " + query.toString());
		} else {
			System.out.println("\nFind the following course with this query: " + query.toString());
			for (Course course : searchResults) {
				System.out.println(course);
			}
		}
	}
	
	private static int selectEditField() throws IOException{
		int fieldOption = 0;
		UiInput.showFieldOption();
		try {
			String input = reader.readLine();
			fieldOption = Integer.parseInt(input);
			while (!UiInput.isValidFieldOption(fieldOption)) {
				System.out.print("\nThe selection field is not valid option (only 1, 2, 3 will be accepted), please enter again");
				UiInput.showFieldOption();
				input = reader.readLine();
				fieldOption = Integer.parseInt(input);
			}
		} catch (NumberFormatException e) {
            System.out.println("\nCatch a field input format exception, please select option again");
            fieldOption = selectEditField();
		} catch(IOException e){
			throw e;
		}
		return fieldOption;
	}
	
	
	public static void showFavoriteList() throws IOException{
		System.out.println("\nYou need add a list to course to your favorite list:");
		List<Integer> idList = addToFavoriteList();
		int totalCreidt = 0;
		int numberCourse = 0;
		
		System.out.println("\nYou have added following course to your favorite list:");
		for(Integer id: idList ){
			if(! courseValidator.isValidID(id)){
				System.out.println("This id:" + id +" is not a valid in our learning system");
			} else if(courseRepository.containsCourse(id)){
				Course course = courseRepository.getCourseById(id);
				System.out.println(course);
				totalCreidt += course.getLength();
				numberCourse += 1;
			} else {
				System.out.println("Our Learning system does not contains course with this id: " + id);
			}
		}
		System.out.println();
		System.out.println(name + " has signed up for " + numberCourse + " courses with " + totalCreidt + " credits");
		System.out.println("****************************************");
		System.out.println("*        Bye Bye, welcome back         *");
		System.out.println("****************************************");
	}
	
	
	private static List<Integer> addToFavoriteList() throws IOException {
		List<Integer> list = new ArrayList<Integer>();
		System.out.print("Please add a list of course id (5 digits id with comma separated) to your favorite list:");
		String input = reader.readLine();

		while (!UiInput.validInputOfIDList(input)) {
			System.out.print("Please add a list of course id (comma separated) to your favorite list:");
			input = reader.readLine();
		}
		String[] array = input.split(",");
		for (String element: array){
			list.add(Integer.parseInt(element));
		}
		return list;
	}
}

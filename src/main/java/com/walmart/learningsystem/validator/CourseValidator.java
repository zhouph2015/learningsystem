package com.walmart.learningsystem.validator;

/**
 * Created by Peter Zhou 11/25/2017
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseValidator {
	
	public CourseValidator(){
		
	}
	
	public boolean isValidID(int id){
		if(id < 10000 || id >99999){
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isValidName(String name){
		Pattern p = Pattern.compile("^[A-Za-z0-9 ]+$");
		Matcher m = p.matcher(name);
		return m.matches();
	}
	
	
	public boolean isValidLength(int length){
		if(length < 1 || length >999){
			return false;
		} else {
			return true;
		}
	}
	
	
	public boolean isValidSubject(String subject){
		Pattern p = Pattern.compile("^[A-Za-z]+$");
		Matcher m = p.matcher(subject.trim());
		return m.matches();
	}
}

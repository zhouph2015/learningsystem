package com.walmart.learningsystem.exception;

/**
 * Created by Peter Zhou 11/25/2017
 */

public class CourseProcessException extends Exception{
	private String str;
	
	public CourseProcessException(String str){
		this.str = str;
	}
	
	public String toString(){
		return ("This is CourseParseException at line: "+ str) ;
	}

}

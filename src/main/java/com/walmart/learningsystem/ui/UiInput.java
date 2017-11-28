package com.walmart.learningsystem.ui;

/**
 * Created by Peter Zhou 11/25/2017
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UiInput {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void showWelcomeMessage(){
		System.out.println("***********************************************");
		System.out.println("*    Welcome To Our Course Learning System    *");
		System.out.println("***********************************************");
	}

	public static void showFieldOption(){
		System.out.println("\nPlease see the following field option for editing:");
		System.out.println("1) name");
		System.out.println("2) length hours");
		System.out.println("3) Subject");
		System.out.print("Please select a field 1, 2 , 3 to edit:");
	}

	
	public static boolean validName(String name){
		Pattern p = Pattern.compile("[A-Za-z0-9_]+");
		Matcher m = p.matcher(name.trim());
		return m.matches();
		
	}
	
	public static boolean validInputOfIDList(String input) {
		Pattern p = Pattern.compile("[0-9, /,]+");
		Matcher m = p.matcher(input.trim());
		return m.matches();
	}

	
	public static boolean isValidFieldOption(int option) {
		if (option >= 1 && option <= 3) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int selectSearchFieldOption() throws IOException{
		int fieldOption = 0;
		showFieldOptionToSearch();
		try {
			String input = reader.readLine();
			fieldOption = Integer.parseInt(input);
			while (!validFieldOptionToSearch(fieldOption)) {
				System.out.println("The selection field for searching is not valid (only 1,2,3,4,5 will be accepted), please select again");
				showFieldOptionToSearch();
				input = reader.readLine();
				fieldOption = Integer.parseInt(input);
			}
		} catch (NumberFormatException e) {
            System.out.println("Catch a field input format exception, please select option again");
            fieldOption = selectSearchFieldOption();
		} catch(IOException e){
			throw e;
		}
		return fieldOption;
	}
	
	public static int selectMenu() throws IOException {
		int option = 0;
		showMenu();
		try {
			String input = reader.readLine();
			option = Integer.parseInt(input);
			while (!isValidOption(option)) {
				System.out.println("\nThe selection operation is not valid, please enter again");
				showMenu();
				input = reader.readLine();
				option = Integer.parseInt(input);
			}
		} catch (NumberFormatException e) {
            System.out.println("\nCatch a opertion option input format exception, please select option again");
            option = selectMenu();
		} catch(IOException e){
			throw e;
		}
		return option;
	}

	private static void showFieldOptionToSearch(){
		System.out.println("\nPlease see the following field 1, 2, 3, 4, 5 for adding search query");
		System.out.println("1) Adding an Id to search query");
		System.out.println("2) Adding a Name to search query");
		System.out.println("3) Adding a Length hours to search query");
		System.out.println("4) Adding a Subject to search query");
		System.out.println("5) Exit adding query, and continue to perform searching process");
		System.out.print("Please select a field option 1, 2, 3, 4, 5 for adding search query:");
	}
	
	private static void showMenu() {
		System.out.println("\nPlease find the following 4 course operations in our system:");
		System.out.println("1) Add a new course to our system");
		System.out.println("2) Edit a existing course in our system");
		System.out.println("3) List all couses in our system");
		System.out.println("4) Search courses in our system");
		System.out.print("Please Enter 1, 2, 3 or 4 for your operation if your like:");
	}
	
	private static boolean validFieldOptionToSearch( int option){
		if( option < 1 || option >5){
			return false;
		} else {
			return true;
		}
	}

	private static boolean isValidOption(int option) {
		if (option >= 1 && option <= 4) {
			return true;
		} else {
			return false;
		}
	}
}

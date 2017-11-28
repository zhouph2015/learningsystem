package com.walmart.learningsystem.utils;

/**
 * Created by Peter Zhou 11/25/2017
 */

import com.walmart.learningsystem.exception.CourseProcessException;
import com.walmart.learningsystem.model.Course;


public class CourseParseUtil {

	public static Course convertStringToCourse(String line)
			throws CourseProcessException {
		try {
			int id = Integer.parseInt(line.substring(0, 6).trim());
			int index = getIndexOfFirstDigit(line.substring(6));
			String name = line.substring(6, 6 + index).trim();

			String[] array = line.substring(6 + index).split(" ");

			if (array.length == 2) {
				int length = Integer.parseInt(array[0].trim());
				String subject = array[1].trim();
				return new Course(id, name, length, subject);
			}

		} catch (Exception e) {
			throw new CourseProcessException(line);
		}
		return null;
	}

	private static int getIndexOfFirstDigit(String string) {
		for (int i = 0; i < string.length(); i++) {
			if (Character.isDigit(string.charAt(i))) {
				return i;
			}
		}
		return -1;
	}
}

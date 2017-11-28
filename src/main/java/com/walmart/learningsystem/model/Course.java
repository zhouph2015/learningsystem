package com.walmart.learningsystem.model;
/**
 * Created by Peter Zhou 11/25/2017
 */
public class Course {
	private int id;
	private String name;
	private int length;
	private String subject;
	
	public Course(Course course){
		this.id = course.getId();
		this.name = course.getName();
		this.length = course.getLength();
		this.subject = course.getSubject();
	}
	
	public Course(int id, String name, int length, String subject){
		this.id = id;
		this.name = name;
		this.length = length;
		this.subject = subject;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Override
	public String toString() {
		return "Course ID:" + id + ", Name:" + name + ", Length:" + length
				+ ", Subject:" + subject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Course other = (Course) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}
}

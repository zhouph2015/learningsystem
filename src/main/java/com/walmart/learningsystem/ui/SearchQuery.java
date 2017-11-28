package com.walmart.learningsystem.ui;

/**
 * Created by Peter Zhou 11/25/2017
 */

public class SearchQuery {
	int id;
	String name;
	int length;
	String subject;
	
	public SearchQuery(){
		super();
		this.id = 0;
		this.name = "";
		this.length = 0;
		this.subject = "";
	}

	public SearchQuery(int id, String name, int length, String subject) {
		super();
		this.id = id;
		this.name = name;
		this.length = length;
		this.subject = subject;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

		String query = "SearchQuery [";
		if (id == 0) {
			query += "id=, ";
		} else {
			query += "id=" + id + ", ";
		}
		query += "name=" + name + ", ";
		if (length == 0) {
			query += "length=, ";
		} else {
			query += "length=" + length + ", ";
		}
		query += "subject=" + subject + "]";
		return query;
	}

}

//package com.spring.angular.models;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//
////@Entity
//public class Employee {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	private String firstName;
//	private String lastName;
//	private String emailId;
//	
//	/*
//	 * 
//	 * constructor
//	 */
//	
//	public Employee() {
//		super();
//	}
//	public Employee(String firtsName, String lastName, String emailId) {
//		super();
//		this.firstName = firtsName;
//		this.lastName = lastName;
//		this.emailId = emailId;
//	}
//	/*
//	 * getter and setter
//	 */
//	public Long getId() {
//		return id;
//	}
//	
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//	public String getEmailId() {
//		return emailId;
//	}
//	public void setEmailId(String emailId) {
//		this.emailId = emailId;
//	}
//	
//}

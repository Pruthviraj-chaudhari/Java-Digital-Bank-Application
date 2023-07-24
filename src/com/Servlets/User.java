package com.Servlets;

public class User {

	public static int id;
	public static String name;
	public static String username;
	public static String password;
	public static String city;
	public static String mobile;
	public static int balance;
	public static String accType;
	public static int accNo;
	
	
	public static int getAccNo() {
		return accNo;
	}
	public static void setAccNo(int accNo) {
		User.accNo = accNo;
	}
	public static int getBalance() {
		return balance;
	}
	public static void setBalance(int balance) {
		User.balance = balance;
	}
	public static String getAccType() {
		return accType;
	}
	public static void setAccType(String accType) {
		User.accType = accType;
	}
	
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		User.id = id;
	}
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		User.name = name;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		User.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		User.password = password;
	}
	public static String getCity() {
		return city;
	}
	public static void setCity(String city) {
		User.city = city;
	}
	public static String getMobile() {
		return mobile;
	}
	public static void setMobile(String mobile) {
		User.mobile = mobile;
	}
	
}

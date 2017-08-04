package com.niit.model;

public class Error {
private int code;
private String message;
public Error(int code, String message){
	super();//To call the super class constructor.Here the super class is object.
	this.code = code;
	this.message = message;
}
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
}

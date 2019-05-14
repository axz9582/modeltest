package com.amitymathacademy.model;

public class Email {

	String from;
	String to;
	String body;
	String subject;
	String cc;
	String bcc;
	String cref="allowedhere";
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	
	public String getCref() {
		return cref;
	}
	public void setCref(String cref) {
		this.cref = cref;
	}
	public String toString (){
		return subject+" from:"+from+" to:"+to+" body:"+body;
	}

}

/*
 * 
 * alter table event modify column data varchar(200);
 * 
 * */

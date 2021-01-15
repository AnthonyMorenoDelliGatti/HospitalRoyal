package client.model;

import java.util.ArrayList;

import javax.mail.Part;

public class Email {

	private String to, subject, user;
	private Part content;
	private String date;
	private Boolean isRead;

	public Email(String to, String subject, String user, Part content, String date, boolean isRead) {
		this.to = to;
		this.subject = subject;
		this.user = user;
		this.content = content;
		this.date = date;
		this.isRead = isRead;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Part getContent() {
		return content;
	}

	public void setContent(Part content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

}

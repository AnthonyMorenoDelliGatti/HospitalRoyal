package client.model;

import java.util.ArrayList;

import javax.mail.Part;
/**
 * 
 * @authors Anthony Moreno Delli Gatti
 *			Francisco Manuel Rodriguez Martin
 *			Juan Salguero Ibarrola
 *			Nicolas Rosa Hinojosa
 *			Gonzalo Ruiz de Mier Mora
 *
 *	date 13/01/2021
 *
 *	@version 1.0
 *
 *	description: class that control the data of the mail
 */
public class Email {

	private String to, subject, user;
	private Part content;
	private String date;
	private Boolean isRead;
/**
 * class' constructor
 * @param to the name to who the email is send
 * @param subject subject of the mail
 * @param user the name of the user sending the mail
 * @param content the content of the mail
 * @param date date it was send 
 * @param isRead variable to check if the mail was read
 */
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

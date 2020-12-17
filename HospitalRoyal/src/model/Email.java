package model;

public class Email {

	private String to, subject, user;
	private String text;
	private String fecha;
	private Boolean isRead;

	/**
	 * 
	 * @param to      - poner un objeto usuario o algo que haga referencia
	 * @param subject
	 * @param user    - poner un objeto usuario o algo que haga referencia
	 */
	public Email(String to, String subject, String user, String text, String fecha, boolean isRead) {
		this.to = to;
		this.subject = subject;
		this.user = user;
		this.text = text;
		this.fecha = fecha;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

}

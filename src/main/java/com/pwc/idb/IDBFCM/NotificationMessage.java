package com.pwc.idb.IDBFCM;

public class NotificationMessage {
	private String title;
	private String body;
    private String operation;
	
	public NotificationMessage(String title, String body) {
		this.title = title;
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public NotificationMessage(String title, String body, String operation) {
		super();
		this.title = title;
		this.body = body;
		this.operation = operation;
	}

}

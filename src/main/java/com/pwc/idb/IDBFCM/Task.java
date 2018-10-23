package com.pwc.idb.IDBFCM;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Task")
public class Task {
	@Id
	private String id;
	private String takName;
	private User user;

	public Task(String takName, User user) {
		super();
		this.takName = takName;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTakName() {
		return takName;
	}

	public void setTakName(String takName) {
		this.takName = takName;
	}

}

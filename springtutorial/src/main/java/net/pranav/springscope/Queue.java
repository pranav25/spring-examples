package net.pranav.springscope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Queue {
	private Message message;
	private String id;
	private String name;
	@Autowired
	public void setMessage(Message message)
	{
		this.message=message;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Message getMessage() {
		return message;
	}
	

}

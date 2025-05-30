package in.ineuron.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RabbitmqQueue {

	private long messages;

	private String name;

	public long getMessages() {
		return messages;
	}

	public void setMessages(long messages) {
		this.messages = messages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDirty() {
		return messages > 0;
	}

}


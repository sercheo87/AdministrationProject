package com.project.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseMessage {
	private Object data;
	private List<Message> messages;

	public ResponseMessage() {
		super();
		this.messages = new ArrayList<Message>();
	}

	public Object getData() {
		return this.data;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public Map<String, Object> getResponse() {
		Map<String, Object> ret = new HashMap<String, Object>();
		if (this.messages.size() > 0) {
			ret.put("messages", this.messages);
		}
		ret.put("data", this.data);
		return ret;
	}

	public Map<String, List<Message>> getResponseMessage() {
		Map<String, List<Message>> ret = new HashMap<String, List<Message>>();
		ret.put("messages", this.messages);
		return ret;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}

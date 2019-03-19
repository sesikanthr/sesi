package com.cisco.cdr.api.payload;

public class CDRCountDTO {
	
	private String messageType;
	private Long count;
	
	public CDRCountDTO(String messageType, Long count) {
		this.messageType = messageType;
		this.count = count;
	}
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MessageCountDTO [messageType=");
		builder.append(messageType);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}

}

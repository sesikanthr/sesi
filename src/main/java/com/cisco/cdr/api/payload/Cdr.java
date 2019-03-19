package com.cisco.cdr.api.payload;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cdrs")
public class Cdr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdrId;
	private Long simId;
	private String dataType;
	private Long operatorId;
	private Long acctId;
	private Long byteUpLink;
	private Long byteDownLink;
	private Date recordOpenTime;
	private Date recordCloseTime;
	// ProcessingSTATTUS - SUCESS, Error, Failure, Warning
	private String status;
	private Date dateAdded;
		
	
	public Long getCdrId() {
		return cdrId;
	}
	public void setCdrId(Long cdrId) {
		this.cdrId = cdrId;
	}
	public Long getSimId() {
		return simId;
	}
	public void setSimId(Long simId) {
		this.simId = simId;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public Long getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	public Long getAcctId() {
		return acctId;
	}
	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}
	public Long getByteUpLink() {
		return byteUpLink;
	}
	public void setByteUpLink(Long byteUpLink) {
		this.byteUpLink = byteUpLink;
	}
	public Long getByteDownLink() {
		return byteDownLink;
	}
	public void setByteDownLink(Long byteDownLink) {
		this.byteDownLink = byteDownLink;
	}
	public Date getRecordOpenTime() {
		return recordOpenTime;
	}
	public void setRecordOpenTime(Date recordOpenTime) {
		this.recordOpenTime = recordOpenTime;
	}
	public Date getRecordCloseTime() {
		return recordCloseTime;
	}
	public void setRecordCloseTime(Date recordCloseTime) {
		this.recordCloseTime = recordCloseTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cdr [cdrId=");
		builder.append(cdrId);
		builder.append(", simId=");
		builder.append(simId);
		builder.append(", dataType=");
		builder.append(dataType);
		builder.append(", operatorId=");
		builder.append(operatorId);
		builder.append(", acctId=");
		builder.append(acctId);
		builder.append(", byteUpLink=");
		builder.append(byteUpLink);
		builder.append(", byteDownLink=");
		builder.append(byteDownLink);
		builder.append(", recordOpenTime=");
		builder.append(recordOpenTime);
		builder.append(", recordCloseTime=");
		builder.append(recordCloseTime);
		builder.append(", status=");
		builder.append(status);
		builder.append(", dateAdded=");
		builder.append(dateAdded);
		builder.append("]");
		return builder.toString();
	}
}

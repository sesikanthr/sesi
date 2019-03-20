/**
 * 
 */
package com.cisco.cdr.api.payload;

/**
 * @author dedadhic
 *
 */
public class AcctDataUsageDTO {
	
	private Long acctId;
	
	private String acctIdStr;
	
	private Long byteUpLink;
	
	private Long byteDownLink;
	
	public AcctDataUsageDTO(Long acctId, Long byteUpLink,Long byteDownLink ) {
		this.acctId = acctId;
		this.byteUpLink = byteUpLink;
		this.byteDownLink = byteDownLink;
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

	public String getAcctIdStr() {
		return Long.toString(getAcctId());
	}

	public void setAcctIdStr(String acctIdStr) {
		this.acctIdStr = acctIdStr;
	}
}

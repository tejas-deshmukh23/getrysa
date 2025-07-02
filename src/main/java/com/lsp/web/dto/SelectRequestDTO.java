package com.lsp.web.dto;

public class SelectRequestDTO {
	
	private String transactionId;
    private String bppId;
    private String bppUri;
    private String providerId;
    private String itemId;
    private String formId;
    private String submissionId;
    private String status;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getBppId() {
		return bppId;
	}
	public void setBppId(String bppId) {
		this.bppId = bppId;
	}
	public String getBppUri() {
		return bppUri;
	}
	public void setBppUri(String bppUri) {
		this.bppUri = bppUri;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getSubmissionId() {
		return submissionId;
	}
	public void setSubmissionId(String submissionId) {
		this.submissionId = submissionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}

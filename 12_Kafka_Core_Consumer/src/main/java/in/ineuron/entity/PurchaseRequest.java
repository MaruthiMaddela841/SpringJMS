package in.ineuron.entity;

import java.util.UUID;

public class PurchaseRequest {
	
	
	private UUID requestID;
	private String requestNumber;
	private int amount;
	private String currency;
	public PurchaseRequest() {
		super();
	}
	public PurchaseRequest(UUID requestID, String requestNumber, int amount, String currency) {
		super();
		this.requestID = requestID;
		this.requestNumber = requestNumber;
		this.amount = amount;
		this.currency = currency;
	}
	public UUID getRequestID() {
		return requestID;
	}
	public void setRequestID(UUID requestID) {
		this.requestID = requestID;
	}
	public String getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "PurchaseRequest [requestID=" + requestID + ", requestNumber=" + requestNumber + ", amount=" + amount
				+ ", currency=" + currency + "]";
	}
	

}

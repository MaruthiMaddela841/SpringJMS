package in.ineuron.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

public class PaymentRequest {
	
	private int amount;
	private String currency;
	private String bankAccountNumber;
	private String notes;
	private LocalDate paymentDate;
	public PaymentRequest() {
		super();
	}
	public PaymentRequest(int amount, String currency, String bankAccountNumber, String notes, LocalDate paymentDate) {
		super();
		this.amount = amount;
		this.currency = currency;
		this.bankAccountNumber = bankAccountNumber;
		this.notes = notes;
		this.paymentDate = paymentDate;
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
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	@Override
	public String toString() {
		return "PaymentRequest [amount=" + amount + ", currency=" + currency + ", bankAccountNumber="
				+ bankAccountNumber + ", notes=" + notes + ", paymentDate=" + paymentDate + "]";
	}
	
	public String calcuateHash() throws NoSuchAlgorithmException {
		String data=amount+"//"+currency+"//"+bankAccountNumber;
		MessageDigest digest=MessageDigest.getInstance("SHA-256");
		byte[] hashBytes=digest.digest(data.getBytes());
		StringBuilder hashBuilder=new StringBuilder();
		for(byte b: hashBytes) {
			hashBuilder.append(String.format("%02x", b));
		}
		return hashBuilder.toString();
	}

}

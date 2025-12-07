package hn.shoppingcart.shoppingcart_payments.model;

public class CardPayment extends Payment {
	private String cardNumber;

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
}

package hn.shoppingcart.shoppingcart_payments.dto.payment;

import lombok.Data;

@Data
public final class PaymentRequestDto {

	private final int paymentId;
	
	private final int orderId;

	private final String paymentMethodDescription;
}

package hn.shoppingcart.shoppingcart_payments.dto.payment;

import java.util.Date;
import java.util.List;

import hn.shoppingcart.shoppingcart_payments.dto.order.OrderDetailDto;
import hn.shoppingcart.shoppingcart_payments.model.Payment;
import lombok.Data;

@Data
public final class PaymentResponseDto {
	private final int id;
	private final int orderId;
	private final int clientId;
	private final List<OrderDetailDto> orderDetails;
	private final String paymentMethodDescription;
	private final Date date;
	private final double subtotal;
	private final double tax;
	private final double total;
	private final String paymentStatusDescription;

	public PaymentResponseDto(Payment payment) {
		this.id = payment.getId();
		this.orderId = payment.getOrderId();
		this.clientId = payment.getClientId();
		this.orderDetails = payment.getOrderDetail().stream().map(OrderDetailDto::new).toList();
		this.paymentMethodDescription = payment.getPaymentMethod().getDescription();
		this.date = payment.getDate();
		this.subtotal = payment.getSubtotal();
		this.tax = payment.getTax();
		this.total = payment.getTotal();
		this.paymentStatusDescription = payment.getPaymentStatus().getDescription();
	}
}

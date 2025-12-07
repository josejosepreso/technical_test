package hn.shoppingcart.shoppingcart_payments.mapper;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hn.shoppingcart.shoppingcart_payments.Configuration;
import hn.shoppingcart.shoppingcart_payments.dto.order.OrderSummaryDto;
import hn.shoppingcart.shoppingcart_payments.dto.payment.PaymentRequestDto;
import hn.shoppingcart.shoppingcart_payments.model.CardPayment;
import hn.shoppingcart.shoppingcart_payments.model.CashPayment;
import hn.shoppingcart.shoppingcart_payments.model.Payment;
import hn.shoppingcart.shoppingcart_payments.model.PaymentStatus;
import hn.shoppingcart.shoppingcart_payments.util.OrderServiceClient;

@Component
public class PaymentMapper {

	@Autowired
	private OrderServiceClient orderServiceClient;

	public Payment toEntity(PaymentRequestDto dto) throws Exception {
		return switch(dto.getPaymentMethodDescription()) {
			case "CARD" -> this.toCardPaymentEntity(dto);
			case "CASH" -> this.toCashPaymentEntity(dto);
			default -> throw new Exception();
		};
	}

	private CardPayment toCardPaymentEntity(PaymentRequestDto paymentRequestDto) throws Exception {
		CardPayment cardPayment = new CardPayment();
		this.mapCommon(paymentRequestDto, cardPayment);

		cardPayment.setCardNumber(paymentRequestDto.getCardNumber());

		return cardPayment;
	}

	private CashPayment toCashPaymentEntity(PaymentRequestDto paymentRequestDto) throws Exception {
		CashPayment cashPayment = new CashPayment();
		this.mapCommon(paymentRequestDto, cashPayment);

		return cashPayment;
	}

	private void mapCommon(PaymentRequestDto paymentRequestDto, Payment payment) throws Exception {
		final OrderSummaryDto orderSummary = this.orderServiceClient.getOrderById(paymentRequestDto.getOrderId())
			.orElseThrow(() -> new Exception(String.format("Order width id %s doesn't exist.", paymentRequestDto.getOrderId())));

		if (!orderSummary.getStatus().equals("PENDING")) {
			throw new Exception("Order not in \"PENDING\" status.");
		}

		final double subtotal = orderSummary.getOrderDetails()
			.stream()
			.mapToDouble(orderDetails -> orderDetails.getQuantity() * orderDetails.getUnitPrice())
			.sum();
		final double tax = subtotal * Configuration.TAX_PERCENTAGE;
		final double total = subtotal + tax;

		payment.setId(paymentRequestDto.getPaymentId());
		payment.setOrderId(paymentRequestDto.getOrderId());
		payment.setClientId(orderSummary.getClientId());
		payment.setDate(new Date());
		payment.setSubtotal(subtotal);
		payment.setTax(tax);
		payment.setTotal(total);
		payment.setPaymentStatus(PaymentStatus.PENDING.name());
	}
}

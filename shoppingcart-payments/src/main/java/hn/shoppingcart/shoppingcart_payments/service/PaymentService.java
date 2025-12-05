package hn.shoppingcart.shoppingcart_payments.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.shoppingcart.shoppingcart_payments.Configuration;
import hn.shoppingcart.shoppingcart_payments.dto.OrderSummaryDto;
import hn.shoppingcart.shoppingcart_payments.dto.PaymentRequestDto;
import hn.shoppingcart.shoppingcart_payments.dto.PaymentResponseDto;
import hn.shoppingcart.shoppingcart_payments.model.OrderDetail;
import hn.shoppingcart.shoppingcart_payments.model.Payment;
import hn.shoppingcart.shoppingcart_payments.model.PaymentMethod;

@Service
public class PaymentService {

	@Autowired
	private OrderServiceClient orderServiceClient;

	private List<Payment> payments;

	private List<PaymentMethod> paymentMethods;

	public PaymentService() {
		this.payments = new ArrayList<>();

		this.paymentMethods = List.of(
			new PaymentMethod(1, "CARD"),
			new PaymentMethod(2, "CASH")
		);
	}

	public List<PaymentResponseDto> getAll() {
		return this.payments.stream().map(PaymentResponseDto::new).toList();
	}

	public PaymentResponseDto create(PaymentRequestDto paymentRequestDto) throws Exception {
		// assuming paymentId is unique
		final boolean paymentExistsById = this.payments.stream().anyMatch(p -> p.getId() == paymentRequestDto.getPaymentId());

		if (paymentExistsById) {
			throw new Exception(String.format("Payment with id %s already exists.", paymentRequestDto.getPaymentId()));
		}

		final Optional<OrderSummaryDto> orderSummary = this.orderServiceClient.getOrderById(paymentRequestDto.getOrderId());

		if (orderSummary.isEmpty()) {
			throw new Exception(String.format("Order width id %s doesn't exist.", paymentRequestDto.getOrderId()));
		}

		// assuming paymentMethodDescription is unique
		final Optional<PaymentMethod> paymentMethod = this.paymentMethods.stream()
			.filter(pMethod -> pMethod.getDescription().equals(paymentRequestDto.getPaymentMethodDescription()))
			.findFirst();

		if (paymentMethod.isEmpty()) {
			throw new IllegalArgumentException("Invalid payment method.");
		}

		//
		final double subtotal = orderSummary.get()
			.getOrderDetails()
			.stream()
			.mapToDouble(orderDetails -> orderDetails.getQuantity() * orderDetails.getUnitPrice())
			.sum();
		final double tax = subtotal * Configuration.TAX_PERCENTAGE;
		final double total = subtotal + tax;

		final List<OrderDetail> orderDetails = orderSummary.get().getOrderDetails()
			.stream()
			.map(oDetailDto -> new OrderDetail(oDetailDto.getProductId(), oDetailDto.getQuantity(), oDetailDto.getUnitPrice()))
			.toList();

		final Payment payment = new Payment();
		payment.setId(paymentRequestDto.getPaymentId());
		payment.setOrderId(paymentRequestDto.getOrderId());
		payment.setOrderDetail(orderDetails);
		payment.setPaymentMethod(paymentMethod.get());
		payment.setDate(new Date());
		payment.setSubtotal(subtotal);
		payment.setTax(tax);
		payment.setTotal(total);

		this.payments.add(payment);

		return new PaymentResponseDto(payment);
	}
}

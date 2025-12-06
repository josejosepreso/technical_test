package hn.shoppingcart.shoppingcart_payments.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.shoppingcart.shoppingcart_payments.Configuration;
import hn.shoppingcart.shoppingcart_payments.dto.order.OrderSummaryDto;
import hn.shoppingcart.shoppingcart_payments.dto.payment.PaymentRequestDto;
import hn.shoppingcart.shoppingcart_payments.dto.payment.PaymentResponseDto;
import hn.shoppingcart.shoppingcart_payments.model.OrderDetail;
import hn.shoppingcart.shoppingcart_payments.model.Payment;
import hn.shoppingcart.shoppingcart_payments.model.PaymentMethod;
import hn.shoppingcart.shoppingcart_payments.model.PaymentStatus;
import hn.shoppingcart.shoppingcart_payments.util.OrderServiceClient;

@Service
public class PaymentService {

	@Autowired
	private OrderServiceClient orderServiceClient;

	private List<Payment> payments;

	private List<PaymentMethod> paymentMethods;

	private List<PaymentStatus> paymentStatus;

	public PaymentService() {
		this.payments = new ArrayList<>();

		this.paymentMethods = List.of(
			new PaymentMethod(1, "CARD"),
			new PaymentMethod(2, "CASH")
		);

		this.paymentStatus = List.of(
			new PaymentStatus(1, "PENDING"),
			new PaymentStatus(2, "PAID"),
			new PaymentStatus(3, "CANCELLED")
		);
	}

	public List<PaymentResponseDto> getAll() {
		return this.payments.stream().map(PaymentResponseDto::new).toList();
	}

	public PaymentResponseDto create(PaymentRequestDto paymentRequestDto) throws Exception {
		// assuming paymentId is unique
		final boolean paymentExistsById = this.payments.stream()
			.anyMatch(p -> p.getId() == paymentRequestDto.getPaymentId());
		//
		if (paymentExistsById) {
			throw new Exception(String.format("Payment with id %s already exists.", paymentRequestDto.getPaymentId()));
		}

		final OrderSummaryDto orderSummary = this.orderServiceClient.getOrderById(paymentRequestDto.getOrderId())
			.orElseThrow(() -> new Exception(String.format("Order width id %s doesn't exist.", paymentRequestDto.getOrderId())));

		if (!orderSummary.getStatus().equals("PENDING")) {
			throw new Exception("Order not in \"PENDING\" status.");
		}

		//
		final double subtotal = orderSummary.getOrderDetails()
			.stream()
			.mapToDouble(orderDetails -> orderDetails.getQuantity() * orderDetails.getUnitPrice())
			.sum();
		final double tax = subtotal * Configuration.TAX_PERCENTAGE;
		final double total = subtotal + tax;

		final List<OrderDetail> orderDetails = orderSummary.getOrderDetails()
			.stream()
			.map(oDetailDto -> new OrderDetail(oDetailDto.getProductId(), oDetailDto.getQuantity(), oDetailDto.getUnitPrice()))
			.toList();

		// assuming paymentMethodDescription is unique
		final PaymentMethod paymentMethod = this.paymentMethods.stream()
			.filter(pMethod -> pMethod.getDescription().equals(paymentRequestDto.getPaymentMethodDescription()))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Invalid payment method."));

		final PaymentStatus paymentStatus = this.paymentStatus.stream()
			.filter(pStatus -> pStatus.getDescription().equals(paymentMethod.getDescription().equals("CASH") ? "PENDING" : "PAID"))
			.findFirst()
			.orElseThrow(() -> new Exception("Impossible event."));

		final Payment payment = new Payment();
		payment.setId(paymentRequestDto.getPaymentId());
		payment.setOrderId(paymentRequestDto.getOrderId());
		payment.setClientId(orderSummary.getClientId());
		payment.setOrderDetail(orderDetails);
		payment.setPaymentMethod(paymentMethod);
		payment.setDate(new Date());
		payment.setSubtotal(subtotal);
		payment.setTax(tax);
		payment.setTotal(total);
		payment.setPaymentStatus(paymentStatus);

		if (!paymentStatus.getDescription().equals("PENDING")) {
			this.orderServiceClient.confirmOrder(paymentRequestDto.getOrderId());
		}

		this.payments.add(payment);

		return new PaymentResponseDto(payment);
	}
}
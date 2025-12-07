package hn.shoppingcart.shoppingcart_payments.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.shoppingcart.shoppingcart_payments.dto.payment.PaymentCancelRequestDto;
import hn.shoppingcart.shoppingcart_payments.dto.payment.CashPaymentConfirmRequestDto;
import hn.shoppingcart.shoppingcart_payments.dto.payment.PaymentRequestDto;
import hn.shoppingcart.shoppingcart_payments.mapper.PaymentMapper;
import hn.shoppingcart.shoppingcart_payments.model.CardPayment;
import hn.shoppingcart.shoppingcart_payments.model.CashPayment;
import hn.shoppingcart.shoppingcart_payments.model.Payment;
import hn.shoppingcart.shoppingcart_payments.model.PaymentStatus;
import hn.shoppingcart.shoppingcart_payments.util.OrderServiceClient;

@Service
public class PaymentService {

	@Autowired
	private PaymentMapper paymentMapper;

	@Autowired
	private OrderServiceClient orderServiceClient;

	private List<Payment> payments;

	public PaymentService() {
		this.payments = new ArrayList<>();
	}

	public List<Payment> getAll() {
		return this.payments;
	}

	private void processPayment(Payment payment) throws Exception {
		if (payment instanceof CardPayment) {
			this.orderServiceClient.confirmOrder(payment.getOrderId());
			payment.setPaymentStatus(PaymentStatus.PAID.name());
			return;
		}

		if (payment instanceof CashPayment) {
			this.orderServiceClient.confirmOrder(payment.getOrderId());
			return;
		}

		throw new IllegalArgumentException("Impossible event.");
	}

	public Payment create(PaymentRequestDto paymentRequestDto) throws Exception {
		final int paymentId = paymentRequestDto.getPaymentId();

		if (paymentId < 1) {
			throw new Exception(String.format("Invalid payment id: %s", paymentId));
		}

		// assuming paymentId is unique
		final boolean paymentExistsById = this.payments.stream()
			.anyMatch(p -> p.getId() == paymentId);
		//
		if (paymentExistsById) {
			throw new Exception(String.format("Payment with id %s already exists.", paymentId));
		}

		final Payment payment = this.paymentMapper.toEntity(paymentRequestDto);

		this.processPayment(payment);

		this.payments.add(payment);

		return payment;
	}

	public CashPayment confirmCashPayment(CashPaymentConfirmRequestDto confirmCashPaymentRequestDto) throws Exception {
		final int paymentId = confirmCashPaymentRequestDto.getPaymentId();

		final Payment payment = this.payments.stream()
			.filter(p -> p.getId() == paymentId)
			.findFirst()
			.orElseThrow(() -> new Exception(String.format("Payment with id %s doesn't exist.", paymentId)));

		if (!(payment instanceof CashPayment)) {
			throw new Exception("Not a cash payment!");
		}

		if (!payment.getPaymentStatus().equals(PaymentStatus.PENDING.name())) {
			throw new Exception("Payment not in \"PENDING\" status.");
		}

		payment.setPaymentStatus(PaymentStatus.PAID.name());

		return (CashPayment) payment;
	}

	public Payment cancelPayment(PaymentCancelRequestDto cancelPaymentRequestDto) throws Exception {
		final int paymentId = cancelPaymentRequestDto.getPaymentId();

		final Payment payment = this.payments.stream()
			.filter(p -> p.getId() == paymentId)
			.findFirst()
			.orElseThrow(() -> new Exception(String.format("Payment with id %s doesn't exist.", paymentId)));

		if (!payment.getPaymentStatus().equals(PaymentStatus.PENDING.name())) {
			throw new Exception("Payment not in \"PENDING\" status.");
		}

		payment.setPaymentStatus(PaymentStatus.CANCELLED.name());

		return payment;
	}
}
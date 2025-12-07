package hn.shoppingcart.shoppingcart_payments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.shoppingcart.shoppingcart_payments.dto.payment.PaymentRequestDto;
import hn.shoppingcart.shoppingcart_payments.dto.payment.CashPaymentConfirmRequestDto;
import hn.shoppingcart.shoppingcart_payments.dto.payment.PaymentCancelRequestDto;
import hn.shoppingcart.shoppingcart_payments.model.Payment;
import hn.shoppingcart.shoppingcart_payments.service.PaymentService;
import hn.shoppingcart.shoppingcart_payments.util.ErrorResponse;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/all")
	public List<Payment> getAll() {
		return this.paymentService.getAll();
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody PaymentRequestDto dto) {
		try { return ResponseEntity.ok(this.paymentService.create(dto)); }
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
		}
	}

	@PostMapping("/confirmCashPayment")
	public ResponseEntity<?> confirmCashPayment(@RequestBody CashPaymentConfirmRequestDto dto) {
		try { return ResponseEntity.ok(this.paymentService.confirmCashPayment(dto)); }
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
		}
	}

	@PostMapping("/cancel")
	public ResponseEntity<?> cancelPayment(@RequestBody PaymentCancelRequestDto dto) {
		try { return ResponseEntity.ok(this.paymentService.cancelPayment(dto)); }
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
		}
	}
}

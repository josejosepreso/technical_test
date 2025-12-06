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

import hn.shoppingcart.shoppingcart_payments.dto.PaymentRequestDto;
import hn.shoppingcart.shoppingcart_payments.dto.PaymentResponseDto;
import hn.shoppingcart.shoppingcart_payments.service.PaymentService;
import hn.shoppingcart.shoppingcart_payments.util.ErrorResponse;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/all")
	public List<PaymentResponseDto> getAll() {
		return this.paymentService.getAll();
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody PaymentRequestDto dto) {
		try {
			return ResponseEntity.ok(this.paymentService.create(dto));
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
		}
	}
}

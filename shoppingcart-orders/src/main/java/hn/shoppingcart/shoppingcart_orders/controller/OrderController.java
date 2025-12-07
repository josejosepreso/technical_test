package hn.shoppingcart.shoppingcart_orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.shoppingcart.shoppingcart_orders.dto.order.OrderCancelRequestDto;
import hn.shoppingcart.shoppingcart_orders.dto.order.OrderConfirmRequestDto;
import hn.shoppingcart.shoppingcart_orders.dto.order.OrderRequestDto;
import hn.shoppingcart.shoppingcart_orders.dto.order.OrderResponseDto;
import hn.shoppingcart.shoppingcart_orders.service.OrderService;
import hn.shoppingcart.shoppingcart_orders.util.ErrorResponse;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/all")
	public List<OrderResponseDto> getAll() {
		return this.orderService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getByIdDto(@PathVariable int id) {
		try {
			return ResponseEntity.ok(this.orderService.getByIdDto(id));
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
		}
	}

	@GetMapping("/{id}/summary")
	public ResponseEntity<?> getByIdSummary(@PathVariable int id) {
		try {
			return ResponseEntity.ok(this.orderService.getByIdSummary(id));
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
		}
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody OrderRequestDto dto) {
		try {
			return ResponseEntity.ok(this.orderService.create(dto));
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
		}
	}

	@PostMapping("/confirm")
	public ResponseEntity<?> confirm(@RequestBody OrderConfirmRequestDto dto) {
		try {
			return ResponseEntity.ok(this.orderService.confirm(dto));
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
		}
	}

	@PostMapping("/cancel")
	public ResponseEntity<?> cancel(@RequestBody OrderCancelRequestDto dto) {
		try {
			return ResponseEntity.ok(this.orderService.cancel(dto));
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
		}
	}
}

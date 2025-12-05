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

import hn.shoppingcart.shoppingcart_orders.dto.OrderRequestDto;
import hn.shoppingcart.shoppingcart_orders.dto.OrderResponseDto;
import hn.shoppingcart.shoppingcart_orders.dto.OrderSummaryResponseDto;
import hn.shoppingcart.shoppingcart_orders.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/all")
	public List<OrderResponseDto> getAll() {
		return this.orderService.getAll();
	}

	@GetMapping("/{id}")
	public OrderResponseDto getByIdDto(@PathVariable int id) {
		return this.orderService.getByIdDto(id).orElse(null);
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody OrderRequestDto dto) {
		try {
			return ResponseEntity.ok(this.orderService.create(dto));
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(String.format("{\"ok\": false, \"msg\": \"%s\"}", e.getMessage()));
		}
	}

	@GetMapping("/{id}/summary")
	public OrderSummaryResponseDto getByIdSummary(@PathVariable int id) {
		return this.orderService.getByIdSummary(id).orElse(null);
	}
}

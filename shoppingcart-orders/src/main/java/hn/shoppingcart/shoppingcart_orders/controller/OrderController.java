package hn.shoppingcart.shoppingcart_orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.shoppingcart.shoppingcart_orders.dto.OrderRequestDto;
import hn.shoppingcart.shoppingcart_orders.model.Order;
import hn.shoppingcart.shoppingcart_orders.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/all")
	public List<Order> getAll() {
		return this.orderService.getAll();
	}

	@GetMapping("/{id}")
	public Order getById(@PathVariable int id) {
		return this.orderService.getById(id).orElse(null);
	}

	@PostMapping("/create")
	public Order create(@RequestBody OrderRequestDto dto) {
		return this.orderService.create(dto);
	}
}

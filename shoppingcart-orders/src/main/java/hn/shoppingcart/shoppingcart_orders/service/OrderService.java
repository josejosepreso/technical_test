package hn.shoppingcart.shoppingcart_orders.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.shoppingcart.shoppingcart_orders.OrderStatus;
import hn.shoppingcart.shoppingcart_orders.dto.OrderDetailRequestDto;
import hn.shoppingcart.shoppingcart_orders.dto.OrderRequestDto;
import hn.shoppingcart.shoppingcart_orders.model.Client;
import hn.shoppingcart.shoppingcart_orders.model.Order;
import hn.shoppingcart.shoppingcart_orders.model.OrderDetail;

@RestController
@RequestMapping("/orders")
public class OrderService {

	private List<Order> orders;

	private List<OrderDetail> orderDetails;

	private List<Client> clients;

	public OrderService() {
		this.orders = new ArrayList<>();
		this.orderDetails = new ArrayList<>();

		this.clients = List.of(
			new Client(1, "Jose", "Bautista", "jose@gmail.com", "11223344", new Date())
		);
	}

	public List<Order> getAll() {
		return this.orders;
	}

	public Optional<Order> getById(int id) {
		final List<Order> orders = this.orders.stream()
			.filter(order -> order.getId() == id)
			.toList();

		if (orders.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(orders.get(0));
	}

	public Order create(OrderRequestDto dto) {
		//
		final int id = dto.getId();

		if (this.getById(id).isPresent()) return null;

		//
		final List<Client> clientsMatch = this.clients.stream()
			.filter(client -> client.getId() == dto.getClientId())
			.toList();

		if (clientsMatch.isEmpty()) return null;

		//
		final Order order = new Order();
		order.setId(id);
		order.setClient(clientsMatch.get(0));
		order.setDate(new Date());
		order.setStatus(OrderStatus.PENDING);

		//
		List<OrderDetailRequestDto> products = dto.getOrderDetails();

		final OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder(order);
		orderDetail.setProducts(products);

		//
		this.orderDetails.add(orderDetail);
		this.orders.add(order);

		return order;
	}
}

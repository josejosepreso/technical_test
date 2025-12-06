package hn.shoppingcart.shoppingcart_orders.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.shoppingcart.shoppingcart_orders.dto.order.OrderDetailRequestDto;
import hn.shoppingcart.shoppingcart_orders.dto.order.OrderRequestDto;
import hn.shoppingcart.shoppingcart_orders.dto.order.OrderResponseDto;
import hn.shoppingcart.shoppingcart_orders.dto.order.OrderSummaryResponseDto;
import hn.shoppingcart.shoppingcart_orders.dto.product.ProductPriceResponseDto;
import hn.shoppingcart.shoppingcart_orders.model.Client;
import hn.shoppingcart.shoppingcart_orders.model.Order;
import hn.shoppingcart.shoppingcart_orders.model.OrderDetail;
import hn.shoppingcart.shoppingcart_orders.model.OrderStatus;
import hn.shoppingcart.shoppingcart_orders.util.ProductServiceClient;

@RestController
@RequestMapping("/orders")
public class OrderService {

	@Autowired
	private ProductServiceClient productServiceClient;

	private List<Order> orders;

	private List<OrderStatus> orderStatus;

	private List<Client> clients;

	public OrderService() {
		this.orders = new ArrayList<>();

		this.orderStatus = List.of(
			new OrderStatus(1, "PENDING"),
			new OrderStatus(2, "PAID"),
			new OrderStatus(3, "CANCELLED")
		);

		this.clients = List.of(
			new Client(1, "Jose", "Bautista", "jose@gmail.com", "11223344", new Date()),
			new Client(2, "Roberto", "Cubas", "roberto@gmail.com", "33445566", new Date())
		);
	}

	public List<OrderResponseDto> getAll() {
		return this.orders.stream().map(OrderResponseDto::new).toList();
	}

	public Optional<Order> getById(int id) {
		// assuming orderId is unique
		return this.orders.stream()
			.filter(order -> order.getId() == id)
			.findFirst();
	}

	public OrderSummaryResponseDto getByIdSummary(int id) throws Exception {
		return this.getById(id)
			.map(OrderSummaryResponseDto::new)
			.orElseThrow(() -> new Exception(String.format("Order with id %s doesn't exist.", id)));
	}

	public OrderResponseDto getByIdDto(int id) throws Exception {
		return this.getById(id)
			.map(OrderResponseDto::new)
			.orElseThrow(() -> new Exception(String.format("Order with id %s doesn't exist.", id)));
	}

	public OrderResponseDto create(OrderRequestDto dto) throws Exception {
		final int id = dto.getId();

		if (this.getById(id).isPresent()) {
			throw new Exception(String.format("Order width id %s already exists.", id));
		}

		// assuming clientId is unique
		final Client client = this.clients.stream()
			.filter(c -> c.getId() == dto.getClientId())
			.findFirst()
			.orElseThrow(() -> new Exception(String.format("Client with id %s doesn't exists.", dto.getClientId())));

		final Optional<OrderStatus> orderStatus = this.orderStatus.stream()
			.filter(oStatus -> oStatus.getDescription().equals("PENDING"))
			.findFirst();
		//
		final Order order = new Order();
		order.setId(id);
		order.setClient(client);
		order.setDate(new Date());
		order.setStatus(orderStatus.get());

		final List<OrderDetail> orderDetails = this.getOrderDetails(order, dto.getOrderDetails());
		order.setOrderDetails(orderDetails);

		//
		this.orders.add(order);

		return new OrderResponseDto(order);
	}

	private List<OrderDetail> getOrderDetails(Order order, List<OrderDetailRequestDto> dtos) throws Exception {
		final List<Integer> productsIds = dtos.stream()
			.map(OrderDetailRequestDto::getProductId)
			.toList();

		final List<ProductPriceResponseDto> pricing = this.productServiceClient.fetchProductsPricing(productsIds);

		List<OrderDetail> orderDetails = new ArrayList<>();

		for (OrderDetailRequestDto orderDetailRequestDto : dtos) {
			int productId = orderDetailRequestDto.getProductId();

			ProductPriceResponseDto productPriceDto = pricing.stream()
				.filter(p -> p.getProductId() == productId)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Product width id %s doesn't exist.", productId)));

			orderDetails.add(new OrderDetail(order, productId, productPriceDto.getUnitPrice(), orderDetailRequestDto.getQuantity()));
		}

		return orderDetails;
	}
}

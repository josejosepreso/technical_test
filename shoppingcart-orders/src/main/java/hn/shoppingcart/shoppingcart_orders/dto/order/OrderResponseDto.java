package hn.shoppingcart.shoppingcart_orders.dto.order;

import java.util.Date;
import java.util.List;

import hn.shoppingcart.shoppingcart_orders.dto.ClientDto;
import hn.shoppingcart.shoppingcart_orders.model.Client;
import hn.shoppingcart.shoppingcart_orders.model.Order;
import lombok.Data;

@Data
public final class OrderResponseDto {
	private final int id;

	private final ClientDto client;

	private final Date date;

	private final String orderStatusDescription;

	private final List<OrderDetailResponseDto> orderDetails;

	public OrderResponseDto(Order order) {
		this.id = order.getId();
		this.date = order.getDate();
		this.orderStatusDescription = order.getStatus().getDescription();
		this.orderDetails = order.getOrderDetails().stream()
			.map(OrderDetailResponseDto::new)
			.toList();

		final Client client = order.getClient();

		this.client = new ClientDto(client.getId(), client.getFirstName() + " " + client.getLastName());
	}
}

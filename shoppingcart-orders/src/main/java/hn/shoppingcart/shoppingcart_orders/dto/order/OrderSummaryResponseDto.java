package hn.shoppingcart.shoppingcart_orders.dto.order;

import java.util.List;

import hn.shoppingcart.shoppingcart_orders.model.Order;
import lombok.Data;

@Data
public final class OrderSummaryResponseDto {
	private final int orderId;
	private final int clientId;
	private final List<OrderDetailResponseDto> orderDetails;

	public OrderSummaryResponseDto(Order order) {
		this.orderId = order.getId();
		this.clientId = order.getClient().getId();
		this.orderDetails = order.getOrderDetails().stream().map(OrderDetailResponseDto::new).toList();
	}
}

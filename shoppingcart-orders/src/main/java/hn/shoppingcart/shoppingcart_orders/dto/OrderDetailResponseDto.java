package hn.shoppingcart.shoppingcart_orders.dto;

import hn.shoppingcart.shoppingcart_orders.model.OrderDetail;
import lombok.Data;

@Data
public final class OrderDetailResponseDto {
	private final int productId;
	private final double unitPrice;
	private final int quantity;

	public OrderDetailResponseDto(OrderDetail orderDetail) {
		this.productId = orderDetail.getProductId();
		this.unitPrice = orderDetail.getUnitPrice();
		this.quantity = orderDetail.getQuantity();
	}
}

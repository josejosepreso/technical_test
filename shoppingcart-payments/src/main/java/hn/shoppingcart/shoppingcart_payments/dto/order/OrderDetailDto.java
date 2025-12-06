package hn.shoppingcart.shoppingcart_payments.dto.order;

import hn.shoppingcart.shoppingcart_payments.model.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class OrderDetailDto {
	private int productId;
	private int quantity;
	private double unitPrice;

	public OrderDetailDto(OrderDetail orderDetail) {
		this.productId = orderDetail.getProductId();
		this.quantity = orderDetail.getQuantity();
		this.unitPrice = orderDetail.getUnitPrice();
	}
}

package hn.shoppingcart.shoppingcart_orders.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDetailRequestDto {

	private final int productId;

	private final int quantity;
}

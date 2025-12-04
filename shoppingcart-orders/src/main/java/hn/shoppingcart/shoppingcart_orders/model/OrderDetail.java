package hn.shoppingcart.shoppingcart_orders.model;

import java.util.List;

import hn.shoppingcart.shoppingcart_orders.dto.OrderDetailRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
	
	private Order order;

	private List<OrderDetailRequestDto> products;
}

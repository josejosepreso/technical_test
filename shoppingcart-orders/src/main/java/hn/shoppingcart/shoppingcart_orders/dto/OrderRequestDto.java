package hn.shoppingcart.shoppingcart_orders.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderRequestDto {

	private final int id;

	private final int clientId;

	private final List<OrderDetailRequestDto> orderDetails;
}

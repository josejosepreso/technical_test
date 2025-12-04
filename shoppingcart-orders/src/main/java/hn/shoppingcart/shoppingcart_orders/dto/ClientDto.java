package hn.shoppingcart.shoppingcart_orders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientDto {
	private final int clientId;

	private final String fullName;
}

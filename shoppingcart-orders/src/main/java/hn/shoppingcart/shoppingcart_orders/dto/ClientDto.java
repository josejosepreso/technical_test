package hn.shoppingcart.shoppingcart_orders.dto;

import hn.shoppingcart.shoppingcart_orders.model.Client;
import lombok.Data;

@Data
public final class ClientDto {

	private final int clientId;

	private final String fullName;

	public ClientDto(Client client) {
		this.clientId = client.getId();
		this.fullName = client.getFirstName() + " " + client.getLastName();
	}
}

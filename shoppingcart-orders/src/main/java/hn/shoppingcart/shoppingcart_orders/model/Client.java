package hn.shoppingcart.shoppingcart_orders.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

	private int id;
	
	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;

	private Date birthdate;
}

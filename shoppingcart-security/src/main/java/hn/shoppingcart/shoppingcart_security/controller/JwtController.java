package hn.shoppingcart.shoppingcart_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.shoppingcart.shoppingcart_security.ErrorResponse;
import hn.shoppingcart.shoppingcart_security.dto.AuthRequestDto;
import hn.shoppingcart.shoppingcart_security.dto.AuthResponseDto;
import hn.shoppingcart.shoppingcart_security.dto.ValidateJwtRequestDto;
import hn.shoppingcart.shoppingcart_security.dto.ValidateJwtResponseDto;
import hn.shoppingcart.shoppingcart_security.service.JwtService;

@RestController
@RequestMapping("/api/security")
public class JwtController {

	@Autowired
	private JwtService jwtService;

	@GetMapping("/health")
	public ResponseEntity<?> health() {
		return ResponseEntity.ok("Ok.");
	}

	@PostMapping("/auth")
	public ResponseEntity<?> auth(@RequestBody AuthRequestDto authRequestDto) {
		try {
			return ResponseEntity.ok(new AuthResponseDto(this.jwtService.generateToken(authRequestDto)));
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(e.getMessage()));
		}
	}

	@PostMapping("/validateAuth")
	public ResponseEntity<?> validateAuth(@RequestBody ValidateJwtRequestDto dto) {
		return ResponseEntity.ok(new ValidateJwtResponseDto(this.jwtService.authorize(dto)));
	}
}

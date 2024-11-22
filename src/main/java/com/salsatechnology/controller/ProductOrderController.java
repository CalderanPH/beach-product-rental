package com.salsatechnology.controller;

import com.salsatechnology.dto.ProductOrderByUsernameDTO;
import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class ProductOrderController {
	
	private final ProductOrderService productOrderService;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void createOrder(@RequestBody @Valid ProductOrderDTO productOrderDTO) {
		productOrderService.createOrder(productOrderDTO);
	}

	@GetMapping
	public List<ProductOrderByUsernameDTO> findProductOrderByUsername(@RequestParam String username) {
		return productOrderService.findProductOrderByUsername(username);
	}
}

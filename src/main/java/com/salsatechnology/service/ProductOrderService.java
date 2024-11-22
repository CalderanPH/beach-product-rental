package com.salsatechnology.service;

import com.salsatechnology.assembler.ProductOrderAssembler;
import com.salsatechnology.dto.ProductOrderByUsernameDTO;
import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.exception.NotFoundException;
import com.salsatechnology.model.ProductOrder;
import com.salsatechnology.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;

import static com.salsatechnology.exception.ExceptionConstants.PRODUCT_ORDER_NOT_FOUND_FOR_THIS_USERNAME;


@Transactional
@Service
@RequiredArgsConstructor
public class ProductOrderService {

	private final ProductOrderRepository productOrderRepository;
	private final ProductOrderAssembler productOrderAssembler;
	
	@Transactional
	public void createOrder(ProductOrderDTO productOrderDTO) {
		ProductOrder productOrder = productOrderAssembler.toEntity(productOrderDTO);
		productOrderRepository.save(productOrder);
	}

	public List<ProductOrderByUsernameDTO> findProductOrderByUsername(String username) {
		List<ProductOrder> productOrders = productOrderRepository.findProductOrderByUserNameIgnoreCase(username);
		if (CollectionUtils.isEmpty(productOrders)) {
			throw new NotFoundException(PRODUCT_ORDER_NOT_FOUND_FOR_THIS_USERNAME);
		}
		return productOrderAssembler.toModel(productOrders);
	}
}

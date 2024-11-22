package com.salsatechnology.assembler;

import com.salsatechnology.dto.ProductOrderByUsernameDTO;
import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.factory.ProductFactory;
import com.salsatechnology.factory.ProductStrategy;
import com.salsatechnology.model.ProductOrder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductOrderAssembler {

    public ProductOrder toEntity(ProductOrderDTO productOrderDTO) {
        ProductOrder productOrder = new ProductOrder();

        ProductStrategy product = ProductFactory.getProductFactory(productOrderDTO);
        ProductOrderCalculator productOrderCalculator = new ProductOrderCalculator(productOrderDTO, product);

        productOrder.setUserName(productOrderDTO.getUserName());
        productOrder.setProductType(productOrderDTO.getProductType());
        productOrder.setTimeHour(productOrderDTO.getTimeHour());
        productOrder.setProductValue(productOrderCalculator.getProductValue());
        productOrder.setProductTotal(productOrderCalculator.getTotalValue());
        productOrder.setUserAmount(productOrderCalculator.getUserAmount());

        return productOrder;
    }

    public List<ProductOrderByUsernameDTO> toModel(List<ProductOrder> productOrderByUsername) {

        return productOrderByUsername.stream()
                .map(productOrder -> {
                    ProductOrderByUsernameDTO productOrderByUsernameDTO = new ProductOrderByUsernameDTO();
                    productOrderByUsernameDTO.setProductType(productOrder.getProductType());
                    productOrderByUsernameDTO.setTimeHour(productOrder.getTimeHour());
                    productOrderByUsernameDTO.setProductTotal(productOrder.getProductTotal());
                    productOrderByUsernameDTO.setProductValue(productOrder.getProductValue());
                    productOrderByUsernameDTO.setUserAmount(productOrder.getUserAmount());

                    return productOrderByUsernameDTO;
                })
                .collect(Collectors.toList());
    }
}

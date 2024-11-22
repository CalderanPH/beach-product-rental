package com.salsatechnology.factory;

import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.exception.NotFoundException;
import com.salsatechnology.model.ProductType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.EnumMap;
import java.util.function.Supplier;

import static com.salsatechnology.exception.ExceptionConstants.PRODUCT_TYPE_NOT_FOUND;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductFactory {

    private static final EnumMap<ProductType, Supplier<ProductStrategy>> STRATEGIES = new EnumMap<>(ProductType.class);

    static {
        register(ProductType.SURFBOARD, SurfboardStrategy::new);
        register(ProductType.BEACH_CHAIR, BeachChairStrategy::new);
        register(ProductType.SUNSHADE, SunshadeStrategy::new);
        register(ProductType.SAND_BOARD, SandBoardStrategy::new);
        register(ProductType.BEACH_TABLE, BeachTableStrategy::new);
    }

    private static void register(ProductType productType, Supplier<ProductStrategy> strategySupplier) {
        STRATEGIES.put(productType, strategySupplier);
    }

    public static ProductStrategy getProductFactory(ProductOrderDTO productOrderDTO) {
        ProductType productType = productOrderDTO.getProductType();

        Supplier<ProductStrategy> strategy = STRATEGIES.get(productType);
        if (strategy == null) {
            throw new NotFoundException(PRODUCT_TYPE_NOT_FOUND);
        }
        return strategy.get();
    }

}

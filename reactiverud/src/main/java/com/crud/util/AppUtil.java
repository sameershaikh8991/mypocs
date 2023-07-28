package com.crud.util;

import com.crud.dto.ProductDto;
import com.crud.model.Product;
import org.springframework.beans.BeanUtils;

public class AppUtil {

    public static ProductDto entityToDto(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product , productDto);
        return  productDto;
    }

    public static Product DtoToEntity(ProductDto productDto){
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        return product;
    }
}

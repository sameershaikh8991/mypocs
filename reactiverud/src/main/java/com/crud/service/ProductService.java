package com.crud.service;




import com.crud.dto.ProductDto;
import com.crud.exception.NotFoundException;
import com.crud.model.Product;
import com.crud.repo.ProductRepo;
import com.crud.service.internal.ProductInternalservice;
import com.crud.util.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepo productRepo;


    @Autowired
    private  ProductInternalservice productInternalservice;


    // ALL GET API


    // map use here to get data from Model format To Dto format
    public Flux<Product> getAllProduct(){
        return productInternalservice.getAllProduct();
    }

        public Mono<com.crud.responses.AppResponse> getProduct(String id){
        return productInternalservice.findProductByPublicId(id)
                .map(product -> {
                            ProductDto productResposeDto  = AppUtil.entityToDto(product);
                            return new com.crud.responses.AppResponse(HttpStatus.OK.value(),
                            "organization.retrieved.successfully",
                            "organization.retrieved.successfully",
                                    productResposeDto,
                            null);
                        }
                );
    }



    public  Flux<ProductDto> getProductInRange(double min,double max){
        return  productRepo.findByPriceBetween(Range.closed(min,max));
    }

    // ALL POST API
    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
        return productDtoMono.map(AppUtil::DtoToEntity)
                .flatMap(productRepo::insert)
                .map(AppUtil::entityToDto);
    }


    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono,String id){
      return productRepo.findById(id)
                .flatMap(p -> productDtoMono.map(AppUtil::DtoToEntity)
                .doOnNext(i ->i.setId(id)))
                .flatMap(productRepo::save)
                .map(AppUtil::entityToDto);
    }


    public Mono<Void> deleteProduct(String id){
        return  productRepo.deleteById(id);
    }
}

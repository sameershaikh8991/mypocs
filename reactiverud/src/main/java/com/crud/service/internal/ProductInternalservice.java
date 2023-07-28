package com.crud.service.internal;

import com.crud.exception.NotFoundException;
import com.crud.model.Product;
import com.crud.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductInternalservice {

@Autowired
    private   ProductRepo productRepo;


    public Flux<Product> getAllProduct(){
        return productRepo.findAll();
    }

    public Mono<Product> findProductByPublicId(String id){
        return productRepo.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("product not found")));
    }


}

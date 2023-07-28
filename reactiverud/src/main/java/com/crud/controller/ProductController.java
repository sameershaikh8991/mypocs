package com.crud.controller;



import com.crud.dto.ProductDto;
import com.crud.model.Product;
import com.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductService service;



    @GetMapping("/allproduct")
    public Flux<Product> allProduct(){
        return  service.getAllProduct();

    }

    @GetMapping("/allproduct/{id}")
    public  Mono<ResponseEntity<com.crud.responses.AppResponse>> getbyId(@PathVariable String id){
        return  service.getProduct(id).map(ResponseEntity::ok);

    }


    @GetMapping("/allproduct/range")
    public Flux<ProductDto> getProductRange(@RequestParam double min,@RequestParam double max){
        return  service.getProductInRange(min,max);

    }

    /*
    POST MAPPING CONTROLLER
     */

    @PostMapping("/addproduct")
    public  Mono<ProductDto> addProduct(@RequestBody Mono<ProductDto> productDtoMono){
        return  service.saveProduct(productDtoMono);
    }

    @PutMapping("/update")
    public  Mono<ProductDto> Update(@RequestBody Mono<ProductDto> productDtoMono,@RequestParam String id){
        return  service.updateProduct(productDtoMono,id);
    }

    /*
     Delete controller
     */

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public Mono<Void> delete(@RequestParam String id){
        return service.deleteProduct(id);
    }


}

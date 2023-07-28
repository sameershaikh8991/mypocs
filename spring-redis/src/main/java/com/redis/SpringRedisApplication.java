package com.redis;

import com.redis.model.Product;
import com.redis.repo.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication

@RestController
@RequestMapping("/product")
@EnableCaching
public class SpringRedisApplication {

	@Autowired
	private ProductDto productDto;
	@PostMapping
	public Product save(@RequestBody Product product){
		return productDto.save(product);
	}

	@GetMapping
	public List<Product> getAll(){
		return productDto.findAll();
	}

	@GetMapping("/{id}")
	@Cacheable(key = "#id",value = "Product")
	public Product getById(@PathVariable int id){
		return productDto.findProductById(id);
	}


	@DeleteMapping("/{id}")
	public String  deleteById(@PathVariable int id){
		return productDto.deleteProduct(id);
	}






	public static void main(String[] args) {
		SpringApplication.run(SpringRedisApplication.class, args);
	}

}

package com.crud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication
public class ReactiverudApplication {


	public static void main(String[] args) {
		SpringApplication.run(ReactiverudApplication.class, args);

		System.out.println("hello world.......");



		Flux.just(1,2,3,4)
				.filter(i -> i>2)
				.subscribe(System.out::println);


		// concat flux

		Flux<String> a = Flux.just("a", "b", "c");
//				.delayElements(Duration.ofMillis(50));


		Flux<String> b = Flux.just("x", "y", "z");

		Flux<String> concat = Flux.concat(a, b);
		concat.map(String::toUpperCase)
				.subscribe(System.out::println);


		System.out.println("-------------MERGE----------------");
		Flux.merge(a,b).map(String::toUpperCase)
				.subscribe(System.out::println);

		Integer[] integers = {1, 3, 5};
		Flux.fromArray(integers)
				.subscribe(System.out::println);


		System.out.println("-------------ZIP----------------");
		Flux.zip(a,b,(f,s) ->f+s)
				.subscribe(System.out::println);

		System.out.println("-------------ZIP WITH----------------");
		a.zipWith(b,(f,s)-> f+s)
				.retry(2)
				.subscribe(System.out::println);


		Flux<Integer> f = Flux.fromIterable(Arrays.asList(1, 2, 3, 4, 5));
		f.flatMap( x -> Mono.just(x).subscribeOn(Schedulers.parallel()))
				.subscribe(System.out::println);


		System.out.println("------------- .as()----------------");

		Mono<String> monoa = Mono.just("Hello");
		Flux<String> flux = monoa.as(Flux::from);

		Class<? extends Flux> aClass = flux.getClass();



		System.out.println("------------- .retry()----------------");

		Mono<Integer> just = Mono.just(1);


		Disposable subscribe = Flux.just(1, 2, 3, 4)
				.retry(2)
				.subscribe(System.out::println);


	}

}

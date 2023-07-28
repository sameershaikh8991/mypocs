package com.crud.sample;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sample {

    public static void main(String[] args) {

        String[][] dataArray = new String[][]{{"a", "b"},
                {"c", "d"}, {"e", "f"}, {"g", "h"}};


        // flatmap over Stream of Stream To single list of stream....

        List<String> listString = Arrays.stream(dataArray)
                .flatMap(x -> Arrays.stream(x))
                .collect(Collectors.toList());

        System.out.println(listString);

        List<Integer> number = Arrays.asList(1,2,3,4);

        Flux<Integer> fluxString = Flux.fromIterable(number).map(n -> n*n);
        System.out.println(fluxString);



       // Flux method ....

        Flux.fromArray(new String[]{"sameer","sahakh"}).map(String::toUpperCase)
                .subscribe(System.out::println);

        Mono.just("sameer").subscribe(System.out::println);
    }

}

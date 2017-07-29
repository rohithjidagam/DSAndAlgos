package com.uh.streams;

import static java.util.stream.Collectors.*;
import java.util.Arrays;
import java.util.List;

public class Sample {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        integers.forEach(e -> System.out.print(e));
        System.out.println();
        // integers.forEach(System.out::print);

        // integers.stream().map(String::valueOf).forEach(System.out::println);

        // integers.stream().map(e -> e.toString()).forEach(System.out::print);

        System.out.println(integers.stream().reduce(0, (total, e) -> Integer.sum(total, e)));

        System.out.println(integers.stream().map(String::valueOf).reduce("", (total, e) -> total.concat(e)));

        System.out.println(integers.stream().map(String::valueOf).reduce("", String::concat));

        Integer reduce = integers
                .stream()
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .reduce(0, Integer::sum);
        System.out.println(reduce);
        
        Double reduce2 = integers
                .stream()
                .filter(e -> e % 2 == 0)
                .mapToDouble(e -> e * 2.0)
                .sum();
        System.out.println(reduce2);
        
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5);
        
         integers
                .stream()
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .collect(toList());
        
        
    }

}

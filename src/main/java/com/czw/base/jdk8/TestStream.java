package com.czw.base.jdk8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Ref:http://ifeve.com/stream/
 *
 * @author ZeviChen , 2017/6/6 15:03
 */
public class TestStream {


    @Test
    public void test() {
        System.out.println("-----------------------------------------");

        List<Integer> list = Lists.newArrayList(3, 23, 4, 3, 43, 16, null);
        System.out.println("notNull count : " + list.stream().filter(num -> num != null).count());
        System.out.println("distinct count : " + list.stream().distinct().filter(num -> num != null).count());

        System.out.println("convert : " + list.stream().filter(num -> num != null).distinct());

        System.out.println("-----------------------------------------");


        Stream<Integer> intStream = Stream.of(1, 2, 3, 5);
        Stream<String> strStream = Stream.of("test");
        Stream<Double> longStream = Stream.generate(() -> Math.random());
        Stream<Double> randLongStream = Stream.generate(Math::random);

        System.out.println("-----------------------------------------");

        Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);

        System.out.println("-----------------------------------------");

        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is : " + nums.stream().filter(num -> num != null).
                distinct().mapToInt(num -> num * 2).
                peek(System.out::println).skip(2).limit(4).sum());

        System.out.println("-----------------------------------------");

        List<Integer> numsWithoutNull = nums.stream().filter(num -> num != null).
                collect(Collectors.toList());

        List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("ints sum is:" + ints.stream().reduce((sum, item) -> sum + item).get());

        System.out.println("-----------------------------------------");

        System.out.println(ints.stream().allMatch(item -> item < 5));
        ints.stream().max(Comparator.naturalOrder()).ifPresent(System.out::println);



    }
}

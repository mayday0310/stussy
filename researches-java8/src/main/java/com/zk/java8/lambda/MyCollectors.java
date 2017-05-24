package com.zk.java8.lambda;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by zk_chs on 16/8/2.
 */
public class MyCollectors {
    private static <T, K, V> Collector<T, ?, Map<K, V>> toMap(Function<T, K> f1, Function<T, V> f2) {
        return new ForceToMapCollector<>(f1, f2);
    }

    private static void duplicateKeyException (){
        Emp[] emps = IntStream.range(0, 10).mapToObj(x -> new Emp(x % 3, "name" + x))
                .toArray(Emp[]::new);
        Map<Integer, String> map = Stream.of(emps).collect(Collectors.toMap(Emp::getId, Emp::getName));
        System.out.println(map);
    }

    private static void nullPointerException (){
        Emp[] emps = IntStream.range(0, 10).mapToObj(x -> new Emp(x, x % 5 == 0 ? null : "name" + x))
                .toArray(Emp[]::new);
        Map<Integer, String> map = Stream.of(emps).collect(Collectors.toMap(Emp::getId, Emp::getName));
        System.out.println(map);
    }

    private static void noException (){
        Emp[] emps = IntStream.range(0, 10).mapToObj(x -> new Emp(x % 3, x % 5 == 0 ? null : "name" + x))
                .toArray(Emp[]::new);
        Map<Integer, String> map = Stream.of(emps).collect(MyCollectors.toMap(Emp::getId, Emp::getName));
        System.out.println(map);
    }

    public static void main(String[] args) {
        duplicateKeyException();
    }
}

class ForceToMapCollector<T, K, V> implements Collector<T, Map<K, V>, Map<K, V>> {

    private Function<? super T, ? extends K> keyMapper;

    private Function<? super T, ? extends V> valueMapper;

    ForceToMapCollector(Function<? super T, ? extends K> keyMapper,
                        Function<? super T, ? extends V> valueMapper) {
        super();
        this.keyMapper = keyMapper;
        this.valueMapper = valueMapper;
    }

    @Override
    public BiConsumer<Map<K, V>, T> accumulator() {
        return (map, element) -> map.put(keyMapper.apply(element), valueMapper.apply(element));
    }

    @Override
    public Supplier<Map<K, V>> supplier() {
        return HashMap::new;
    }

    @Override
    public BinaryOperator<Map<K, V>> combiner() {
        return null;
    }

    @Override
    public Function<Map<K, V>, Map<K, V>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
}

class Emp {
    private Integer id;
    private String name;
    Emp(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
        System.out.println(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {return "id: " + id + ", name: " + name;}
}
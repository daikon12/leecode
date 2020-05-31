package java8.lambdaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by daikai on 2020/5/31.
 */
public class StreamApiTest {

    public static void main(String[] args){

        List<String> stringCollection = init();

        // filter
        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
        System.out.println(stringCollection);

        //sort
        stringCollection
                .stream()
                .sorted()
                .filter(s -> s.startsWith("a"))
                .forEach(System.out::println);
        System.out.println(stringCollection);

        //map
        stringCollection
                .stream()
                .map((x) -> x + "MMMMMM" )
                .map(String::toUpperCase)
                .sorted((a,b) -> a.compareTo(b))
                .forEach(System.out::println);
        System.out.println(stringCollection);

        // match
        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));
        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));
        System.out.println(noneStartsWithZ);    //true


        // count
        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();
        System.out.println(startsWithB);    // 3

        // reduce
        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted((a,b) -> b.compareTo(a))
                        .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);
        System.out.println(stringCollection);

        // 并行计算
        long t1 = System.nanoTime();
        stringCollection.parallelStream().sorted().count();
        System.out.println(System.nanoTime() - t1);

        // 并行计算
        long t0 = System.nanoTime();
        stringCollection.stream().sorted().count();
        System.out.println(System.nanoTime() - t0);



    }

    private static List<String> init() {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        return stringCollection;
    }
}

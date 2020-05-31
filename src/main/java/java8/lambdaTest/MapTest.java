package java8.lambdaTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daikai on 2020/5/31.
 */
public class MapTest {
    public static void main(String[] args){

        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.print(id + "->"+ val + "\t"));

        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3));             // val33

        map.computeIfPresent(9, (num, val) -> null);
        System.out.println(map.containsKey(9));     // false

        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println(map.containsKey(23) + "->" + map.get(23));    // true

        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.containsKey(3) + "->" + map.get(3));            // val33

        map.forEach((id, val) -> System.out.print(id + "->"+ val + "\t"));
        map.remove(3, "val3");
        System.out.println(map.get(3));             // val33
        map.remove(3, "val33");
        System.out.println(map.get(3));

        System.out.println(map.getOrDefault(42, "not found"));

        map.forEach((id, val) -> System.out.print(id + "->"+ val + "\t"));
        System.out.println();
        // 不存在则插入，否则追加
        map.merge(9, "val", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));             // val9

        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));
    }
}

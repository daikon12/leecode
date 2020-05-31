package java8.lambdaTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by daikai on 2020/5/23.
 */
public class helloLambda {

    public static void main(String [] args){

        //1. 有参数有返回
        // 1.7匿名内部类
        Myinterface myinterface1 = new Myinterface() {
            @Override
            public int sum(int a, int b) {
                return a + b;
            }
        };


        /*
        1.形参中数据类型可以自我推断
        2.当方法体只有一句，可以省略{}，如果是return，直接省略return
         */
        Myinterface myinterface2 = (int a,int b) -> {
            return a + b;
        };

        // 高级写法
        Myinterface myinterface3 = (a ,b) -> a + b;


        System.out.println(myinterface1.sum(2,4));
        System.out.println(myinterface2.sum(2,4));
        System.out.println(myinterface3.sum(2,4));


        // 2.有参数无返回
        PrintTest printTest1 = new PrintTest() {
            @Override
            public void show(String str) {
                System.out.println(str);
            }
        };

        // 当只有一个参数时：
        PrintTest printTest2 = str -> System.out.println(str);

        printTest1.show("old");
        printTest2.show("new8");


        // 3. 对于不返回的无参数写法，格式如下：
//        PrintTest printTest3 = () - {};

        // 4. final 作用域
        int sub = 9;
        Myinterface myinterface4 = (a, b) -> {
            // 访问局部变量sub，默认sub是final变量
            System.out.println(sub);
            // 下面语句会报错：
//            sub = 10;
            return a + b;
        };

        myinterface4.sum(1,5);

        // 5.构造方法中的引用
        // 构造方法中的lambda
        PersonFactory personFactory1 = new PersonFactory() {
            @Override
            public Person createPerson(String firstName, String lastName) {
                return new Person(firstName, lastName);
            }
        };

        PersonFactory personFactory2 = (firstName, lastName) -> new Person(firstName, lastName);

        // 构造方法引用，要求两个方法的参数列表完全一致
        PersonFactory personFactory3 = Person::new;

        System.out.println(personFactory1.createPerson("1", "11"));
        System.out.println(personFactory2.createPerson("1", "11"));
        System.out.println(personFactory3.createPerson("1", "11"));

        // 6.静态方法引用
        IntParse intParse1 = new IntParse() {
            @Override
            public int parse(String num) {
                return Integer.parseInt(num);
            }
        };

        IntParse intParse2 = num -> Integer.parseInt(num);
        IntParse intParse3 = Integer::parseInt;

        System.out.println(intParse1.parse("123"));
        System.out.println(intParse2.parse("123"));
        System.out.println(intParse3.parse("123"));


        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        // 升序
        Collections.sort(names, (a, b) -> a.compareTo(b));
        System.out.println("names:" + names);

        // predicate接口
        Predicate<String> predicate = (s) -> s.length() > 0;
        System.out.println(predicate.test("foo"));            // true
        System.out.println(predicate.negate().test("foo"));     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        System.out.println(nonNull.test(false));

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
        System.out.println(isEmpty.test("sa"));

    }
}

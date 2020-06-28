package mulThread;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by daikai on 2020/6/7.
 */
public class JOLTest {

    public static void main(String[] args) throws InterruptedException {

        // 无锁 001
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(ClassLayout.parseInstance(atomicInteger).toPrintable());
        System.out.println("初始值："+atomicInteger.get());

        Thread.sleep(4000L);
        // 偏向锁 101
        AtomicInteger atomicInteger1 = new AtomicInteger();
        System.out.println(ClassLayout.parseInstance(atomicInteger1).toPrintable());

        // 默认先用偏向锁 101
        AtomicInteger atomicInteger2 = new AtomicInteger();
        synchronized (atomicInteger2){
            System.out.println(ClassLayout.parseInstance(atomicInteger2).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(atomicInteger2).toPrintable());

    }

}

package medium;

/**
 * Created by daikai on 2020/4/19.
 */
public class Test {
    public static Test t1 = new Test();
    {
        System.out.print("blockA");
    }
    static
    {
        System.out.print("blockB");
    }
    public static void main(String[] args)
    {
        System.out.print("blockC");
        Test t2 = new Test();
        System.out.print("blockD");
    }
}

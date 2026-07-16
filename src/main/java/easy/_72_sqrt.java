package easy;

/**
 * @author: kanedai
 * @date: 2024/11/24
 * 给定一个非负整数 x ，计算并返回 x 的平方根，即实现 int sqrt(int x) 函数。
 * 正数的平方根有两个，只输出其中的正数平方根。
 * 如果平方根不是整数，输出只保留整数的部分，小数部分将被舍去。
 */
public class _72_sqrt {
    public static void main(String[] args) {
        int num = 2147395599;
        long start1 = System.currentTimeMillis();
//        System.out.println(myBinarySearch(num));
        long end1 = System.currentTimeMillis();
        System.out.println("time1:" + (end1 - start1));

        long start2 = System.currentTimeMillis();
        System.out.println(newton(num));
        long end2 = System.currentTimeMillis();
        System.out.println("time2:" + (end2 - start2));
    }

    /**
     * n --> logN
     * 21亿时运算超时
     * @param num
     * @return
     */
    private static Integer myBinarySearch(int num) {
        int index = -1, l = 0, r = num;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (mid * mid <= num) {
                index = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }

        }
        return index;
    }

    /**
     * 牛顿迭代求均值
     * 均值比两数更靠近平方根。 12的平方根 2 4 6
     * @param num
     * @return
     */
    private static Integer newton(int num) {
        if(num <= 1) {
            return num;
        }
        return (int)newtonSqrt(num/2, num);
    }

    private static double newtonSqrt(double i, int num) {
        double mid = (i + num/i) / 2;
        if(i == mid) {
            return mid;
        } else {
            return newtonSqrt(mid, num);
        }
    }

}
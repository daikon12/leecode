package easy;

/**
 * @author: kanedai
 * @date: 2026/7/4
 */
public class _72_pingfanggen {

    // 二分法实现
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int l = 0, r = x;
        int index = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // 使用long避免整数溢出
            long square = (long) mid * mid;
             if (square <= x) {
                l = mid + 1;
                index = mid;
            } else {
                r = mid - 1;
            }
        }
        return index;

    }

    public static void main(String[] args) {
        int i = new _72_pingfanggen().mySqrt(2147395599);
        System.out.println(i);
    }
}
package easy;

/**
 * @author: kanedai
 * @date: 2026/7/15
 * 你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。
 *
 * 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
 */
public class _441_arrangeCoins {
    public static void main(String[] args) {
        System.out.println(arrangeCoins(5));
        System.out.println(arrangeCoins(8));
    }

    // 迭代 n要放完才能停止
    public static int arrangeCoins(int n) {
        int i = 1;
        while (n >= i) {
            n -= i;
            i++;
        }
        return i - 1;
    }

    /**
     * 二分法
     * @param n
     * @return
     */
    public static int arrangeCoins_1(int n) {
        int l = 0, r = n;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            long square = (long) mid * (mid + 1) / 2;
            if (square <= n) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;

    }
}
package easy;

import java.util.Arrays;

/**
 * @author: kanedai
 * @date: 2024/11/24
 * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * 注意可能存在负数
 */
public class _628_maximumProduct {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 7, 2, 3, 4, 5, 6};

        long start1 = System.currentTimeMillis();
        System.out.println(mySortType(nums));
        long end1 = System.currentTimeMillis();
        System.out.println("time1:" + (end1 - start1));

        long start2 = System.currentTimeMillis();
        System.out.println(maxMin(nums));
        long end2 = System.currentTimeMillis();
        System.out.println("time2:" + (end2 - start2));
    }

    /**
     * 算法复杂度取决于排序, N * logN
     *
     * @param nums
     * @return
     */
    private static Integer mySortType(int[] nums) {
        Arrays.sort(nums);
        Arrays.stream(nums).forEach(num -> System.out.println(num));
        // 排序后, 只需计算计算 最大三个数乘积(全正) 或者 最小两个数 * 最大一个数 (有负数)
        int length = nums.length;
        return Math.max(nums[length - 1] * nums[length - 2] * nums[length - 3],
                nums[length - 1] * nums[0] * nums[1]);
        // 只需查到这五个数即可, 无需排序。
    }

    /**
     * 线性扫描获取上述5个数
     *
     * @param nums
     * @return
     */
    private static Integer maxMin(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            if (current <= min1) {
                min2 = min1;
                min1 = current;
            } else if (current <= min2) {
                min2 = current;
            }

            if (current >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = current;
            } else if (current >= max2) {
                max3 = max2;
                max2 = current;
            } else if (current >= max3) {
                max3 = current;
            }
        }

        return Math.max(max1 * max2 * max3, max1 * min2 * min1);
    }
}
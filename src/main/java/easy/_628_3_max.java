package easy;

import java.util.Arrays;

/**
 * @author: kanedai
 * @date: 2026/7/6
 */
public class _628_3_max {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(new _628_3_max().maxProduct_1(nums));
    }

    /**
     * 628. 三个数的最大乘积
     * 给定一个整型数组，找出最大的乘积
     * 17ms 击败24.8%
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[nums.length - 1], nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]);

    }
//

    /**
     * 628. 三个数的最大乘积
     * 给定一个整型数组，找出最大的乘积
     * 2ms 击败99%
     *
     * @param nums
     * @return
     */
    public static int maxProduct_1(int[] nums) {
        int min_1 = Integer.MAX_VALUE, min_2 = Integer.MAX_VALUE;
        int max_1 = Integer.MIN_VALUE, max_2 = Integer.MIN_VALUE, max_3 = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
             int c = nums[i];
             if(c <= min_1) {
                 min_2 = min_1;
                 min_1 = c;
             } else if (c <= min_2) {
                 min_2 = c;
             }

             if(c >= max_1) {
                 max_3 = max_2;
                 max_2 = max_1;
                 max_1 = c;
             } else if (c >= max_2) {
                 max_3 = max_2;
                 max_2 = c;
             } else if (c >= max_3) {
                 max_3 = c;

             }
        }
        return Math.max(min_1 * min_2 * max_1, max_1 * max_2 * max_3);

    }
}
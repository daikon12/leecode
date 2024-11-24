package easy;

import java.util.Arrays;

/**
 * @author: kanedai
 * @date: 2024/11/24
 * 给你一个整数数组 nums ，请计算数组的 中心下标 。
 * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
 */
public class _12_array_center {

    public static void main(String[] args) {

        int[] nums = new int[]{1, 7, 3, 6, 5, 6};
        System.out.println(pivotIndex(nums));
    }

    public static int pivotIndex(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int suml = 0;
        int i = 0;
        for (; i < nums.length; i++) {
            // suml = sumr
            // suml + sumr + current = sum
            // 2 * suml + current = sum
            if (2 * suml + nums[i] == sum) {
                return i;
            }
            suml += nums[i];
        }
        return -1;

    }
}
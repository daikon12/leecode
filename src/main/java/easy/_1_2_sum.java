package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: kanedai
 * @date: 2026/7/7
 */
public class _1_2_sum {
    public static void main(String[] args) {
        int[] nums = {3,2, 4};
        int target = 9;
        System.out.println(java.util.Arrays.toString(twoSum(nums, target)));
    }

    /**
     * 用map进行存储
     * 无需有序数组
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum_1(int[] nums, int target) {
        // nums[i],i
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

/**
 * 用双指针进行实现 需要是有序数组
 * 时间复杂度 O(n)
 * 空间复杂度 O(1)
 * @param nums
 * @param target
 * @return
 */
    public static int[] twoSum(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum == target) {
                return new int[]{l, r};
            } else if (sum < target) {
                l++;
            } else {
                r--;
            }
        }
        return new int[]{};
    }
}
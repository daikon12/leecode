package easy;

import java.util.Arrays;

/**
 * @author: kanedai
 * @date: 2026/7/1
 */
public class _12_zhongxinxiabiao {

    public int pivotIndex_1(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
            if (total == sum) {
                return i;
            }
            sum -= nums[i];
        }
        return -1;
    }

    public int pivotIndex(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int totalLeft = 0;
        for (int i = 0; i < nums.length; i++) {
            if (2 * totalLeft == sum - nums[i]) {
                return i;
            } else {
                totalLeft += nums[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        _12_zhongxinxiabiao solution = new _12_zhongxinxiabiao();
        int[] nums = {1, 7, 3, 6, 5, 6};
        System.out.println(solution.pivotIndex(nums));
    }
}
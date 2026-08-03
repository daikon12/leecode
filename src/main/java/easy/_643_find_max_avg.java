package easy;

/**
 * @author: kanedai
 * @date: 2026/7/23
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * <p>
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 * 任何误差小于 10-5 的答案都将被视为正确答案。
 */
public class _643_find_max_avg {
    public double findMaxAverage(int[] nums, int k) {
        if (nums.length < k) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int sumMax = sum;
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i - k] + nums[i];
            sumMax = Math.max(sumMax, sum);
        }
        return (double) sumMax / k;
    }

    public double findMaxAverage_v1(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int sumMax = sum;
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i - k] + nums[i];
            sumMax = Math.max(sumMax, sum);
        }
        return (double) sumMax / k;
    }

    public static void main(String[] args) {
        System.out.println(new _643_find_max_avg().findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
    }
}
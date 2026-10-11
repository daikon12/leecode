package easy;

/**
 * @author: kanedai
 * @date: 2026/8/3
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 * 两种实现都是 时间 复杂度 O(n)
 *
 */
public class _674_longest_CIS {
    /**
     * 双指针跳跃法
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 1;
            int j = i + 1;
            while (j < nums.length && nums[j] > nums[j - 1]) {
                count++;
                j++;
            }
            i = j - 1;
            res = Math.max(res, count);
        }
        return res;
    }

    /**
     * 贪心法起点重置法
     * @param nums
     * @return
     */
    public int findLengthOfLCIS1(int[] nums) {
        int max = 0;
        if(nums.length <= 1) {
            return nums.length;
        }
        int start = 0;
        for(int i =1; i < nums.length; i++) {
            if(nums[i] <= nums[i-1]) {
                start = i;
            }
            max = Math.max(max, i - start + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1, 9, 0,2,3, 5, 4, 7};
        System.out.println(new _674_longest_CIS().findLengthOfLCIS(nums));
        System.out.println(new _674_longest_CIS().findLengthOfLCIS1(nums));
    }

}
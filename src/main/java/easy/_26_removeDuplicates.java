package easy;

/**
 * @author: kanedai
 * @date: 2026/7/1
 * 有序数组去重个数
 */
public class _26_removeDuplicates {

    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            // 相等的时候 i不变，j+1，不
            // 相等的时候 i和j都向后移，把不相等的数复制到 i+1 位置
            // 最后 i的位置以及以前都是去重后的数
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
    public static void main(String[] args) {
        _26_removeDuplicates solution = new _26_removeDuplicates();
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int k = solution.removeDuplicates(nums);
        System.out.println(k);
        System.out.println(java.util.Arrays.toString(nums));
    }

}
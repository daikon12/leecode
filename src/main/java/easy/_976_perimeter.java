package easy;

/**
 * @author: kanedai
 * @date: 2026/9/10
 */
public class _976_perimeter {
    public int largestPerimeter(int[] nums) {
        for(int i = nums.length - 1; i >= 2; i--) {

            if(nums[i-1] + nums[i-2] > nums[i]) {
                return nums[i-1] + nums[i-2] + nums[i];
            }
        }
        return 0;
    }

}
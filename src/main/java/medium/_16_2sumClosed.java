package medium;

import java.util.Arrays;

/**
 * Created by daikai on 2020/5/16.
 */
public class _16_2sumClosed {
    public static void main(String[] args) {

        int [] nums = {-1,2,1,-4};
        System.out.println(threeSumClosest(nums, 1));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int res = nums[0] + nums[1] + nums[2];

        for (int start = 0; start < nums.length - 2; start++) {

            int L = start + 1;
            int R = nums.length - 1;
            while (L < R) {
                int temp = nums[start] + nums[L] + nums[R];
                if (Math.abs(temp - target) < Math.abs(res - target)){
                    res = temp;
                }
                if(temp < target) L++;
                else if(temp > target) R--;
                else return target;
            }
        }
        return res;
    }
}

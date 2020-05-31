package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by daikai on 2020/5/16.
 */
public class _15_3sum0 {

    public static void main(String [] args){

        int[] nums = {-1, 0, 1, 2, -1, -4};

        Arrays.sort(nums);

        showArray(nums);

        System.out.println(threeSum(nums));
    }

    private static void showArray(int[] num) {
        for(int i : num){
            System.out.print(i + "  ");
        }
    }


    public static int[] sortNums(int [] num){

        if (num.length < 2) return num;

        int mid = num.length / 2;
        int [] left = Arrays.copyOfRange(num, 0, mid);
        int [] right = Arrays.copyOfRange(num, mid, num.length);
        return merge(sortNums(left), sortNums(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int [] res = new int [left.length + right.length];

        for(int i =0, j =0, index = 0; index < res.length; index++){
            if(i >= left.length){
                res[index] = right[j++];
            }
            else if(j >= right.length){
                res[index] = left[i++];
            }
            else if(left[i] < right[j]){
                res[index] = left[i++];
            }else {
                res[index] = right[j++];
            }
        }

        return res;
    }


    /*

    特判，对于数组长度 nn，如果数组为 nullnull 或者数组长度小于 33，返回 [][]。
对数组进行排序。
遍历排序后数组：
若 nums[i]>0nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 00，直接返回结果。
对于重复元素：跳过，避免出现重复解
令左指针 L=i+1L=i+1，右指针 R=n-1R=n−1，当 L<RL<R 时，执行循环：
当 nums[i]+nums[L]+nums[R]==0nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,RL,R 移到下一位置，寻找新的解
若和大于 00，说明 nums[R]nums[R] 太大，RR 左移
若和小于 00，说明 nums[L]nums[L] 太小，LL 右移
22 ms  43.8MB
     */
    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        if(nums.length<3)
            return res;

        Arrays.sort(nums);

        int len = nums.length;
        for(int start = 0; start < len; start++){

            if(nums[start] > 0) return res;
            if(start > 0 && (nums[start] == nums[start -1])) continue;

            int L = start + 1;
            int R = nums.length - 1;
            while (L < R){
                int sum = nums[start] + nums[L] + nums[R];
                if(sum == 0){
                    List <Integer> found = new ArrayList<>();
                    found.add(nums[start]);
                    found.add(nums[L]);
                    found.add(nums[R]);
                    res.add(found);

                    while(L < R && (nums[L+1] == nums[L])) L++;
                    while(L < R && (nums[R-1] == nums[R])) R--;

                    L++;
                    R--;
                } else if(sum > 0) R--;
                else L++;
            }
        }

        return res;
    }
}

package easy;

import java.util.Arrays;

/**
 * @author: kanedai
 * @date: 2026/7/23
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 *
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 */
public class _88_sort_merge {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m -1;
        int j = n -1;
        int p = m + n - 1;
        while(i>=0 && j>=0) {
            if(nums1[i] >= nums2[j]) {
                nums1[p--] = nums1[i--];
            } else {
                nums1[p--] = nums2[j--];
            }
        }
        while(j >= 0) {
            nums1[p--] = nums2[j--];
        }

    }
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        new _88_sort_merge().merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));

    }
}
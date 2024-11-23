package easy;

/**
 * @author: kanedai
 * @date: 2024/11/20
 * 去除有序数组重的重复数据, 返回去重后的数组大小\
 * 双指针算法
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 
 * 返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 */
public class _26_RemoveDuplicate {

    public static void main(String[] args) {
        int[] data = new int[]{0, 0, 1, 2, 2, 3, 3, 4, 5, 5};
        System.out.println(removeDuplicate(data));
    }

    private static int removeDuplicate(int[] data) {
        if (data == null) {
            return 0;
        }
        int length = data.length;
        if (length <= 1) {
            return length;
        }
        // 慢指针
        int i = 0;
        //快指针
        for (int j = 1; j < length; j++) {
            if (data[i] != data[j]) {
                i++;
                data[i] = data[j];
            }
        }
        return i + 1;
    }
}
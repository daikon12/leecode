package medium.tree;

import java.util.ArrayList;
import java.util.List;

// 二叉搜索树的中序遍历结果是递增的
public class offer_54_kthLargest {

//    public static List<Integer> list = new ArrayList<>();
    static int count;
    static int result;
    public static void main(String[] args) {

        System.out.println(kthLargest(TreeDemo.init(), 4));
        kthLargest1(TreeDemo.init(), 4);
        System.out.println(result);
    }

    public static int kthLargest(TreeDemo.TreeNode root, int k){

        List<Integer> list = new ArrayList<>();
        middleFS(root, list);
        System.out.println(list);
        return list.get(list.size() - k);
    }

    private static void middleFS(TreeDemo.TreeNode root, List<Integer> list) {
        if(root == null) return;
        middleFS(root.left, list);
        list.add(root.val);
        middleFS(root.right, list);
    }

    // 不保存完整的结果， 遍历到第k大后便停止
    public static int kthLargest1(TreeDemo.TreeNode root, int k){

        count = k;
        middleFS1(root);
        return result;
    }

    private static void middleFS1(TreeDemo.TreeNode root) {
        if(root != null){
            middleFS1(root.right);

            if(count == 1){
                result = root.val;
                count -- ;
                return;
            }
            count--;

            middleFS1(root.left);
        }

    }
}

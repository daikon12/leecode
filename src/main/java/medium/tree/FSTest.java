package medium.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class FSTest {

    static List<Integer> list = new ArrayList<>();
    static List<List<Integer>> list1= new ArrayList<>();

    public static void main(String[] args) {

        TreeDemo.TreeNode root = TreeDemo.init();
//        dfs(root);
//        bfs(root);
        cengci(root);
        System.out.println(list1);

    }

    // 层次遍历
    // 每层返回一个数组，[[1], [6, 9], [5, 3], [4]]
    private static void cengci(TreeDemo.TreeNode root) {
        Queue<TreeDemo.TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int n = queue.size();
            for(int i = 0; i < n; i++){
                TreeDemo.TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            list1.add(list);
        }
    }


    // 广度优先遍历
    // 返回一个数组 [1, 6, 9, 5, 3, 4]
    public static void bfs(TreeDemo.TreeNode root) {
        Queue<TreeDemo.TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeDemo.TreeNode node = queue.poll();
            list.add(node.val);
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
    }

    // 深度优先遍历
    // 返回一个数组 [1, 6, 5, 4, 3, 9]
    private static void dfs(TreeDemo.TreeNode root) {

        if(root != null){
            list.add(root.val);
        } else {
            return;
        }
        dfs(root.left);
        dfs(root.right);
    }

}

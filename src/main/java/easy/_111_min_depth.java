package easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: kanedai
 * @date: 2026/7/27
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 最低空间（瘦高树）/ 最快命中浅叶子 → 选 BFS（② 或 ③）。时间 O(N), 空间O(w) W 为树的最大宽度
 * 最低空间（矮胖树）/ 代码最干净 → 选 DFS（①）。 时间 O(N),空间 O(H) H 为树高，递归调用栈深度 = 树高。最坏 O(N)（链状树），平衡树 O(logN)
 */
public class _111_min_depth {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        int depth;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 递归-深度优先
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (root.left != null) {
            min = Math.min(minDepth(root.left), min);
        }
        if (root.right != null) {
            min = Math.min(minDepth(root.right), min);
        }
        return min + 1;
    }

    /**
     * 迭代-广度优先
     * 分层BFS
     *
     *
     * @param root
     * @return
     */
    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) { // 遍历当前层的节点
                TreeNode node = queue.poll();
                // 判断是否是叶子节点
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        root.depth = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null && node.right == null) {
                return node.depth;
            }
            if (node.left != null) {
                node.left.depth = node.depth + 1;
                queue.offer(node.left);
            }
            if (node.right != null) {
                node.right.depth = node.depth + 1;
                queue.offer(node.right);
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        _111_min_depth md = new _111_min_depth();
        TreeNode root = md.new TreeNode(3);
        root.left = md.new TreeNode(9);
        root.right = md.new TreeNode(20);
        root.right.left = md.new TreeNode(15);
        root.right.right = md.new TreeNode(7);
        System.out.println(md.minDepth(root));
        System.out.println(md.minDepth1(root));
        System.out.println(md.minDepth2(root));
    }
}
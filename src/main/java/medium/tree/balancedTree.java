package medium.tree;

public class balancedTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static TreeNode init() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(9);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(3);
//        TreeNode node6 = new TreeNode(2);
        TreeNode node7 = new TreeNode(4);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
//        node4.left = node6;
        node4.right = node7;

        return node1;
    }

    static boolean isBalanced = true;

    public static void main(String[] args) {
        TreeNode node = init();
        System.out.println(isBalanced(node));
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        heigh(root);

        return isBalanced;
    }

    public static int heigh(TreeNode node) {

        // 变量剪枝
        if (!isBalanced)
            return 0;

        if (node == null) return 0;

        // 所有节点都进行平衡性判断，如果有一个节点不平衡，变量就会为false
        int left = heigh(node.left);
        int right = heigh(node.right);

        if (Math.abs(left - right) > 1)
            isBalanced = false;

        return Math.max(left , right) + 1;

    }
}

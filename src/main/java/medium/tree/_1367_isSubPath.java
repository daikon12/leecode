package medium.tree;

public class _1367_isSubPath {

    public static void main(String[] args) {

        TreeDemo.TreeNode tree = TreeDemo.init();
        TreeDemo.ListNode list = TreeDemo.initList();

        System.out.println(isSubPath(list, tree));

        while(list !=null ){
            System.out.print(list.val+ "  ");
            list = list.next;
        }

    }

    private static boolean isSubPath(TreeDemo.ListNode list, TreeDemo.TreeNode tree) {

        if (list == null) return true;
        if (tree == null) return false;

        // //先判断当前的节点，如果不对，再看左子树和右子树
        return isSub(list, tree) || isSubPath(list, tree.left) || isSubPath(list, tree.right);

    }

    private static boolean isSub(TreeDemo.ListNode list, TreeDemo.TreeNode tree) {
        if(list == null) return true;
        if(tree == null) return false;

        if(list.val != tree.val) return false;
        return isSub(list.next, tree.left) || isSub(list.next, tree.right);

    }
}

package medium;

/**
 * Created by daikai on 2020/4/1.
 */
public class _2_addTwoNumbers {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }

    public static void main(String[] args) {

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int remain = 0;

        // while 条件，要都为null 且上次也没有进位才结束
        while (l1 != null || l2 != null || remain != 0) {
            int l1Value = (l1 == null) ? 0 : l1.val;
            int l2Value = (l2 == null) ? 0 : l2.val;

            int sum = l1Value + l2Value + remain;
            ListNode temp = new ListNode(sum % 10);
            remain = sum / 10;

            // 最精彩的地方，是取一个cursor来遍历添加，而 root 头节点不变
            cursor.next = temp;
            cursor = temp;

            // 此处要多一个 null 判断，否在会出现空指针问题
            l1 = (l1 == null)? null : l1.next;
            l2 = (l2 == null)? null : l2.next;
        }

        // 此处注意取的是next
        return root.next;
    }
}

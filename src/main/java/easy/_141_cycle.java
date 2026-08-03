package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: kanedai
 * @date: 2026/7/16
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
 * 评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）
 */
public class _141_cycle {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 迭代法 时间 O(n) 空间 O(n)
     * @param head
     * @return
     */
    public boolean hasCycle_1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            // set.add(current) 添加失败 返回false，否则返回true
            if (set.contains(current)) {
                return true;
            }
            set.add(current);
            current = current.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head = getDemoData();
        System.out.println(new _141_cycle().hasCycle(head));
    }

    /**
     * 双指针 时间 O(n) 空间 O(1)
     * @param head
     * @return
     */
    private boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if(slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    private static ListNode getDemoData() {
        ListNode head5 = new ListNode(5, null);
        ListNode head4 = new ListNode(4, head5);
        ListNode head3 = new ListNode(3, head4);
        ListNode head2 = new ListNode(2, head3);
        ListNode head1 = new ListNode(1, head2);
//        head5.next = head2;
        return head1;
    }
}
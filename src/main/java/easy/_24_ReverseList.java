package easy;

import java.util.Objects;

/**
 * @author: kanedai
 * @date: 2024/11/18
 * 206 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class _24_ReverseList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode getDemoData() {
        ListNode head5 = new ListNode(5, null);
        ListNode head4 = new ListNode(4, head5);
        ListNode head3 = new ListNode(3, head4);
        ListNode head2 = new ListNode(2, head3);
        ListNode head1 = new ListNode(1, head2);
        return head1;
    }

    public static void main(String[] args) {
        ListNode node = getDemoData();
        ListNode reverseNode = recursion(node);
        System.out.println(reverseNode.val);
    }

    /**
     * 用迭代的方式进行实现
     * 用 prev,next双指针完成相关标记
     * 迭代重复某一过程，每次处理结果作为下一次处理的初始值,类似与状态，每次处理完都会改变状态
     *
     * @param node
     * @return
     */
    private static ListNode reverse(ListNode head) {
        ListNode prev = null, next, current;
        current = head;
        while (Objects.nonNull(current)) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    /**
     * 递归实现
     *
     * @param node
     * @return
     */
    private static ListNode recursion(ListNode node) {
           if(node == null || node.next == null) {
               return node;
           }
           ListNode last = recursion(node.next);
           // 调换顺序 并去掉原始箭头方向
           node.next.next = node;
           node.next = null;
           return last;
    }
}
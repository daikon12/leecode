package medium.common;

import medium._2_addTwoNumbers;

import java.util.ArrayList;
import java.util.List;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static ListNode init(List<Integer> list) {
        ListNode root = new ListNode(0);
        ListNode node = root;
        int len = list.size();
        for (int i = 0; i < len; i++) {
            node.next = new ListNode(list.get(i));
            node = node.next;
        }
        return root.next;
    }

    public static void printListNode(ListNode node){
        while(node != null){
            System.out.print(node.val + "  ");
            node= node.next;
        }
    }
}
package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daikai on 2020/5/24.
 */
public class _143_reorderList {

    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    private static ListNode initNodes() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        return listNode1;
    }

    public static void main(String [] args){
        ListNode root = initNodes();
        reorderList(root);

    }

    public static void show(ListNode root){
        while(root != null) {
            System.out.print(root.val + " -> ");
            root = root.next;
        }
    }

    public static void reorderList(ListNode head) {

        List<ListNode> nodes = new ArrayList<>();
        ListNode node = head;

        if(head == null)
            return;
        while(node != null) {
            nodes.add(node);
            node = node.next;
        }

        int i = 0;
        int j = nodes.size() - 1;

        while(i < j){
            nodes.get(i++).next = nodes.get(j);
            if(i == j)
                break;
            nodes.get(j--).next = nodes.get(i);
        }

        nodes.get(i).next = null;
        show(nodes.get(0));



    }


}

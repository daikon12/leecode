package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daikai on 2020/5/24.
 */
public class _142_detectCycle {

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
            next = null;
        }
    }


    public static void main(String [] args){
        ListNode root = initNodes();
        detectCycle(root);

    }

    public static ListNode detectCycle(ListNode head) {

        Map<ListNode, Integer> nodeScaned = new HashMap<>();
        ListNode node = head;
        int num = 0;
        while (node != null){
//            System.out.println(node.val);
            if(nodeScaned.containsKey(node)){
//                System.out.println(node.val);
                System.out.println("tail connects to node index " + nodeScaned.get(node).toString());
                return node;
            } else{
                nodeScaned.put(node, num++);
                node = node.next;
            }
        }

        System.out.println("no cycle");
        return null;
    }

    private static ListNode initNodes() {
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(0);
        ListNode listNode4 = new ListNode(-4);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
//        listNode4.next = listNode2;

        return listNode1;
    }
}

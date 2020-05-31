package medium;

/**
 * Created by daikai on 2020/5/16.
 */
public class _19_removeNthFromEnd {

    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    public static void main(String[] args){
        ListNode root = init();
        showListNode(root);
        System.out.println();
        ListNode res = removeNthFromEnd(root, 1);
        showListNode(res);
    }

    private static void showListNode(ListNode root) {
        ListNode temp = root;
        while(temp != null){
            System.out.print(temp.val+ " -> ");
            temp = temp.next;
        }
    }

    public static ListNode init(){
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

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        if(head == null || head.next == null)
            return null;
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode L = root;
        ListNode R = root;

        for(int i = 0; i < n ; i++){
            R = R.next;
        }


        while(R.next != null){
            L = L.next;
            R= R.next;
        }

        L.next = L.next.next;
        //有可能删除head
        return root.next;
    }
}

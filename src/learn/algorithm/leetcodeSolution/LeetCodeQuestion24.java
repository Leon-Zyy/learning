package learn.algorithm.leetcodeSolution;

/**
 * @Description leetCode问题
 * @Author Leon.Zhao
 * @Date 2020/3/10 16:25
 * @Version 1.0
 */
public class LeetCodeQuestion24 {

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * <p>
     *  
     * <p>
     * 示例:
     * <p>
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prevNode = dummy;

        while (head != null && head.next != null) {

            // Nodes to be swapped
            ListNode a = head;
            ListNode b = head.next;

            // Swapping
            prevNode.next = b;
            a.next = b.next;
            b.next = a;

            // Reinitializing the head and prevNode for next swap
            prevNode = a;
            head = a.next; // jump
        }

        // Return the new head node.
        return dummy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode dummyHead = new ListNode(1);

        ListNode prev = dummyHead;
        for (int i = 2; i <= 4; i++) {
            prev.next = new ListNode(i);
            prev = prev.next;
        }

        ListNode handelNode = swapPairs(dummyHead);
    }

}

package learn.algorithm.leetcodeSolution;

import java.util.List;

/**
 * @Description leetCode问题
 * @Author Leon.Zhao
 * @Date 2020/3/10 16:25
 * @Version 1.0
 */
public class LeetCodeQuestion206 {

    /**
     * LetCode 206. 倒置单链表 - 常规
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * LetCode 203. 倒置单链表 - 递归
     * @param head
     * @return
     */
    public static ListNode reverseListByRecursive(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode p = reverseListByRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    /**
     * LetCode 206. 倒置单链表 内部类
     */
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
        for (int i = 2; i <= 6; i++) {
            prev.next = new ListNode(i);
            prev = prev.next;
        }

        ListNode handelNode = reverseListByRecursive(dummyHead);
    }
}

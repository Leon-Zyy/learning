package learn.solution;

/**
 * @Description leetCode问题
 * @Author Leon.Zhao
 * @Date 2020/3/10 16:25
 * @Version 1.0
 */
public class LeetCodeQuestion203 {

    /**
     * LetCode 203. 移除链表元素 - 常规
     */
    public static ListNode normalRemoveElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode prev = dummyNode;
        while (prev.next != null) {
            if (prev.next.val == val)
                prev.next = prev.next.next;
            else
                prev = prev.next;
        }

        return dummyNode.next;
    }

    /**
     * LetCode 203. 移除链表元素 - 递归算法
     */
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    /**
     * LetCode 203. 移除链表元素 内部类
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

        ListNode handelNode = normalRemoveElements(dummyHead, 6);
    }
}

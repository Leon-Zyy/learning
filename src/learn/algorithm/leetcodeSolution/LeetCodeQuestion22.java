package learn.algorithm.leetcodeSolution;

/**
 * @Description leetCode问题
 * @Author Leon.Zhao
 * @Date 2020/3/10 16:25
 * @Version 1.0
 */
public class LeetCodeQuestion22 {

    /**
     * leetCode 算法 22 链表中倒数第k个节点
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
     * 示例:
     * 给定一个链表: 1->2->3->4->5, 和 k = 2.
     * <p>
     * 解题关键：
     * 1.先移动F(firstNode)K个步长，此时F与Sec(secNode)的距离即为K
     * 2.共同移动 F Sec, 当F==NULL时，即已到链表尾部，那么Sec所在的位置即为倒数第K个结点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode firstN = head;
        ListNode secN = head;
        for (int i = 0; i < k; i++) {
            if (firstN.next == null) {
                throw new RuntimeException("链表元素个数小于 K");
            }
            firstN = firstN.next;
        }
        while (firstN != null) {
            firstN = firstN.next;
            secN = secN.next;
        }

        return secN;
    }

    /**
     * LetCode 22. 链表 内部类
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode() {
        }
    }
}

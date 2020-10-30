package learn.algorithm.leetcodeSolution;

/**
 * @Description leetCode 876. 链表的中间结点
 * @Author Leon.Zhao
 * @Date 2020/3/23 10:48
 * @Version 1.0
 */
public class LeetCodeQuestion876 {

    /**
     * leetCode 876. 链表的中间结点
     * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
     * 如果有两个中间结点，则返回第二个中间结点。
     * 示例 1：
     * <p>
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
     * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
     */
    public ListNode middleNode(ListNode head) {
        ListNode[] resNode = new ListNode[100];
        int count = 0;
        while (head != null) {
            resNode[count++] = head;
            head = head.next;
        }
        return resNode[count / 2];
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

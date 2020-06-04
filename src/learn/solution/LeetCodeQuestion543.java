package learn.solution;

/**
 * @Description leetCode问题
 * @Author Leon.Zhao
 * @Date 2020/3/10 16:25
 * @Version 1.0
 */
public class LeetCodeQuestion543 {

    /**
     * leetCode 543 二叉树的直径
     * 给定一棵二叉树，你需要计算它的直径长度。
     * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
     * 这条路径可能穿过根结点。
     * 示例 :
     * 给定二叉树
     * <p>
     * -----------1
     * ----------/ \
     * ---------2   3
     * --------/ \
     * -------4   5
     * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
     * <p>
     * 注意：两结点之间的路径长度是以它们之间边的数目表示。
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }

    /**
     * leetCode 543 定义全局变量
     */
    int ans;

    /**
     * leetCode 543 递归计算路径
     *
     * @param treeNode
     * @return
     */
    public int depth(TreeNode treeNode) {
        if (treeNode == null) return 0;

        int left = depth(treeNode.left);
        int right = depth(treeNode.right);

        ans = Math.max(ans, left + right + 1);
        return Math.max(left, right) + 1;
    }

    /**
     * leetCode 543 内部类
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

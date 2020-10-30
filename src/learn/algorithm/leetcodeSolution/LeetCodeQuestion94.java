package learn.algorithm.leetcodeSolution;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description leetCode 144. 二叉树中序遍历
 * @Author Leon.Zhao
 * @Date 2020/3/23 10:48
 * @Version 1.0
 */
public class LeetCodeQuestion94 {

    /**
     * 二叉树中序遍历 - 非递归
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stk = new LinkedList<TreeNode>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    /**
     * 二叉树中序遍历 - 递归
     * @param root
     * @return
     */
    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = null;

        for (int i = 1; i <= 10; i++) {
            treeNode = add(treeNode, (int) (1+Math.random()*10));
        }

        List<Integer> output = inorderTraversal(treeNode);
        System.out.println(output);
    }

    /**
     * 构造二叉树
     * @param node
     * @param e
     * @return
     */
    private static TreeNode add(TreeNode node, Integer e) {
        if (node == null) {
            return new TreeNode(e);
        }

        if (e.compareTo(node.val) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.val) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }
}

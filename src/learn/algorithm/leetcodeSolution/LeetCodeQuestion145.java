package learn.algorithm.leetcodeSolution;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description leetCode 144. 二叉树后序遍历
 * @Author Leon.Zhao
 * @Date 2020/3/23 10:48
 * @Version 1.0
 */
public class LeetCodeQuestion145 {

    /**
     * 二叉树后序遍历 - 非递归
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    /**
     * 二叉树后序遍历 - 递归
     * @param root
     * @return
     */
    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        inorder(root.right, res);
        res.add(root.val);
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

        List<Integer> output = postorderTraversal(treeNode);
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

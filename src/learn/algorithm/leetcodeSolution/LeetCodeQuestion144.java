package learn.algorithm.leetcodeSolution;

import java.util.*;

/**
 * @Description leetCode 144. 二叉树前序遍历
 * @Author Leon.Zhao
 * @Date 2020/3/23 10:48
 * @Version 1.0
 */
public class LeetCodeQuestion144 {

    /**
     * 二叉树前序遍历 - 非递归
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> output = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.add(node.val);

            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return output;
    }

    /**
     * 二叉树前序遍历 - 递归
     * @param root
     * @return
     */
    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        inorder(root.left, res);
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

        List<Integer> output = preorderTraversal(treeNode);
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

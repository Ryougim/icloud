package tree;

import java.util.LinkedList;

public class TreeTraveral {
    public static void main(String[] args) {
        //构建一个二叉树
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        TreeNode<?> node = createBinaryTree(list);
        System.out.print(node.toString());
        //先序遍历（根->左->右）
        preorderTraversal(node);
    }

    private static void preorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getData());
        preorderTraversal(node.getLeftNode());
        preorderTraversal(node.getRightNode());
    }

    private static <T extends Number> TreeNode createBinaryTree(LinkedList<? extends Number> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        TreeNode<T> node = null;
        T data = (T) list.removeFirst();
        LinkedList listCopy = new LinkedList<>();
        listCopy.addAll(list);
        if (data != null) {
            node = new TreeNode<>(data);
            node.setLeftNode(createBinaryTree(listCopy));
            node.setRightNode(createBinaryTree(listCopy));
        }
        return node;
    }
}

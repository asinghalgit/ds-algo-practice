import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Integer[] array =
                new Integer[]{50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        Node root = constructTree(array);
        /*display(root);
        System.out.println(size(root));
        System.out.println(sum(root));
        System.out.println(max(root));
        System.out.println(height(root));
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();
        displayNodesByLevel(root);
        iterativeTraversal(root);
        System.out.println(find(root, 87));
        List<Integer> path = new ArrayList<>();
        find(root, 70, path);
        System.out.println(path);
        printNodesByLevel(root, 4);*/
    }

    public static void printNodesByLevel(Node node, int targetLevel) {
        if (node == null)
            return;
        printNodesByLevel(node, targetLevel, 0);
    }

    private static void printNodesByLevel(Node node, int targetLevel, int currentLevel)
    {
        if(node == null)
            return;
        else if(currentLevel == targetLevel)
        {
            System.out.print(node.data + " ");
            return;
        }
        printNodesByLevel(node.left, targetLevel, currentLevel+1);
        printNodesByLevel(node.right, targetLevel, currentLevel+1);
    }

    public static boolean find(Node node, int data) {
        if (node == null)
            return false;
        else if (node.data == data)
            return true;
        else if (find(node.left, data))
            return true;
        else if (find(node.right, data))
            return true;
        else
            return false;
    }

    public static boolean find(Node node, int data, List<Integer> path) {
        if (node == null)
            return false;
        else if (node.data == data) {
            path.add(node.data);
            return true;
        }

        boolean isLeft = find(node.left, data, path);
        if (isLeft) {
            path.add(node.data);
            return true;
        }

        boolean isRight = find(node.right, data, path);
        if (isRight) {
            path.add(node.data);
            return true;
        }
        return false;
    }


    public static void iterativeTraversal(Node node) {
        if (node == null)
            return;
        Stack<Pair> stack = new Stack<>();
        Pair rootPair = new Pair(node, 1);
        stack.push(rootPair);
        String pre = "";
        String in = "";
        String post = "";
        while (!stack.isEmpty()) {
            Pair top = stack.peek();
            if (top.state == 1) {
                pre = pre + top.node.data + " ";
                if (top.node.left != null) {
                    Pair newPair = new Pair(top.node.left, 1);
                    stack.push(newPair);
                }
                top.state++;
            } else if (top.state == 2) {
                in = in + top.node.data + " ";
                if (top.node.right != null) {
                    Pair newPair = new Pair(top.node.right, 1);
                    stack.push(newPair);
                }
                top.state++;
            } else if (top.state == 3) {
                post = post + top.node.data + " ";
                stack.pop();
            }
        }
        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);
    }

    public static void displayNodesByLevel(Node node) {
        if (node == null)
            return;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int index = 0; index < size; index++) {
                Node temp = queue.remove();
                System.out.print(temp.data + " ");
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
            System.out.println();
        }
    }

    public static void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    public static void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public static void preOrder(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public static int height(Node node) {
        if (node == null)
            return -1;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static int max(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;
        int leftMax = max(node.left);
        int rightMax = max(node.right);
        return Math.max(node.data, Math.max(leftMax, rightMax));
    }

    public static int sum(Node node) {
        if (node == null)
            return 0;
        int leftSum = sum(node.left);
        int rightSum = sum(node.right);
        return leftSum + node.data + rightSum;
    }


    public static int size(Node node) {
        if (node == null)
            return 0;
        int leftSize = size(node.left);
        int rightSize = size(node.right);
        return leftSize + 1 + rightSize;
    }

    public static void display(Node node) {
        if (node == null)
            return;
        String str = "";
        str = str + (node.left == null ? "." : node.left.data);
        str = str + "<-" + node.data + "->";
        str = str + (node.right == null ? "." : node.right.data);
        System.out.println(str);
        display(node.left);
        display(node.right);
    }

    public static Node constructTree(Integer[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        Stack<Pair> stack = new Stack<>();
        Node root = new Node(array[0], null, null);
        Pair rootPair = new Pair(root, 1);
        stack.push(rootPair);

        for (int i = 1; i < array.length; i++) {
            while (!stack.isEmpty() && stack.peek().state == 3) {
                stack.pop();
            }
            Pair top = stack.peek();
            if (top.state == 1) {
                if (array[i] != null) {
                    Node newNode = new Node(array[i], null, null);
                    Pair newPair = new Pair(newNode, 1);
                    top.node.left = newNode;
                    top.state++;
                    stack.push(newPair);
                } else {
                    top.state++;
                }

            } else if (top.state == 2) {
                if (array[i] != null) {
                    Node newNode = new Node(array[i], null, null);
                    Pair newPair = new Pair(newNode, 1);
                    top.node.right = newNode;
                    top.state++;
                    stack.push(newPair);
                } else {
                    top.state++;
                }
            }
        }

        return root;
    }

}

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

class Pair {
    Node node;
    int state;

    public Pair(Node node, int state) {
        this.node = node;
        this.state = state;
    }
}
package Tree;

import java.util.ArrayList;
import java.util.List;

class Node {
    char data;
    Node firstChild;
    Node nextBrother;

    public Node(char data) {
        this.data = data;
    }
}

public class CreateCSTree {
    public static void main(String[] args) {
        char[] data = {'z', 'a', 'c', 'g', 'j', 'b', 'e', 'f', 'k', 'l', 'd', 'h', 'i', 'm', 'n'};
        int[] degree = {4, 2, 1, 0, 2, 1, 1, 2, 0, 1, 0, 0, 0, 0, 0};
        Node root = createCSTreeDegree(data, degree, degree.length);
        traverse(root);
    }

    public static Node createCSTreeDegree(char[] data, int[] degree, int n) {
        List<Node> list = new ArrayList<>();
        for (char d : data) {
            list.add(new Node(d));
        }
        int k = 0;
        for (int i = 0; i < n; i++) {
            int d = degree[i];
            if (d > 0) {
                k++;
                list.get(i).firstChild = list.get(k);
                for (int j = 2; j <= d; j++) {
                    list.get(k).nextBrother = list.get(k + 1);
                    k++;
                }
            }
        }
        return list.get(0);
    }

    public static void traverse(Node node) {
        if (node == null) {
            return;
        }
        traverse(node.firstChild);
        System.out.print(node.data);
        traverse(node.nextBrother);
    }
}
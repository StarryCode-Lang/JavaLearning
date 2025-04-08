package HashMap.frb230915_v02;

import java.util.HashMap;

//链表
public class LinkList {
    private Node root;  //不存数据，保存链表的头节点
    private int size = 0;

    public LinkList() {
        root = new Node();
    }

    //添加
    public void add(Object key, Object value) {
        Node node = new Node(key, value);
        Node head = root.next;
        if (head == null) {
            root.next = node;
        } else {
            Node current = root.next;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        size++;
    }

    //取数据
    public Node get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node current = root.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public int size() {
        return size;
    }
}

class Node {
    public Object key;
    public Object value;
    public Node next;

    public Node(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Node() {

    }
}

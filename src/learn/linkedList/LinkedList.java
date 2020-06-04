package learn.linkedList;

/**
 * @Description 链表
 * @Author Leon.Zhao
 * @Date 2020/2/15 16:16
 * @Version 1.0
 */
public class LinkedList<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(){
            this(null, null);
        }
    };

    private Node dummyHead;
    int size;

    public LinkedList(){
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(E e){
        add(0, e);
    }

    public void addLast(E e){
        add(size, e);
    }

    /**
     * 不常使用 练习使用
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index. ");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;
        prev.next = new Node(e, prev);

        size++;
    }

    /**
     * 常使用 练习使用
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index. ");

        Node curNode = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }

        return curNode.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size - 1);
    }

    public void set(int index, E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index. ");

        Node curNode = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }

        curNode.e = e;
    }

    public boolean contains(E e){
        Node curNode = dummyHead.next;
        while (curNode != null) {
            if (curNode.e == e) {
                return true;
            }
            curNode = curNode.next;
        }

        return false;
    }

    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index. ");

        Node preNode = dummyHead.next;
        for (int i = 0; i < index; i++) {
            preNode = preNode.next;
        }

        Node retNode = preNode.next;
        preNode.next = retNode.next;
        retNode.next = null;
        size--;
        return retNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node curNode = dummyHead.next;
        while (curNode != null) {
            res.append(curNode + "->");
            curNode = curNode.next;
        }
        res.append("NULL");

        return res.toString();
    }
}

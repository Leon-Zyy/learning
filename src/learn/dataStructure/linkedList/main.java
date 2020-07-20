package learn.dataStructure.linkedList;

/**
 * @Description TODO
 * @Author Leon.Zhao
 * @Date 2020/2/15 17:03
 * @Version 1.0
 */
public class main {

    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);
    }
}

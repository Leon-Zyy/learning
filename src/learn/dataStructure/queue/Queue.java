package learn.dataStructure.queue;

/**
 * @Description 队列
 * @Author Leon.Zhao
 * @Date 2020/2/14 15:57
 * @Version 1.0
 */
public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}

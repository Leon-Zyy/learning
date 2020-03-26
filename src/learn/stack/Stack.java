package learn.stack;

/**
 * @Description 栈
 * @Author Leon.Zhao
 * @Date 2020/2/15 18:13
 * @Version 1.0
 */
public interface Stack<E> {

    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}

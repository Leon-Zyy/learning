package learn.queue;

import java.util.Random;

/**
 * @Description 队列测试main
 * @Author Leon.Zhao
 * @Date 2020/2/15 14:21
 * @Version 1.0
 */
public class queueMain {

    private static double testQueue(Queue<Integer> queue, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));

        }
        for (int i = 0; i < opCount; i++) {
            queue.dequeue();

        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 10000000;
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double loopQueueTime = testQueue(loopQueue, opCount);
        System.out.println("LoopQueueTime消耗时间为 : " + loopQueueTime);

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double arrayQueueTime = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueueTime消耗时间为 : " + arrayQueueTime);
    }
}

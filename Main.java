/**
 * A linked list implementation of a queue.
 *
 * @author (32465092 MSM Dhlamini)
 * @version (2023-10-19)
 */
public class Main {
    public static void main(String[] args) {
        QueueAsMyLinkedList<Integer> list2 = new QueueAsMyLinkedList<>();
        list2.enqueue(10);
        list2.enqueue(2);
        list2.enqueue(6);
        list2.enqueue(4);
        list2.enqueue(9);

        QueueAsMyLinkedList<Integer> sortedList2 = sortQueue(list2);

        System.out.println(sortedList2);
    }

    public static QueueAsMyLinkedList<Integer> sortQueue(QueueAsMyLinkedList<Integer> pl) {
        QueueAsMyLinkedList<Integer> sortedQueue = new QueueAsMyLinkedList<>();

        while (pl.peek() != null) {
            int min = findMin(pl);
            sortedQueue.enqueue(min);
        }

        return sortedQueue;
    }

    public static int findMin(QueueAsMyLinkedList<Integer> pl) {
        int min = pl.dequeue();
        int size = 1; // Start with size 1

        while (pl.peek() != null) {
            int current = pl.dequeue();
            if (current < min) {
                pl.enqueue(min);
                min = current;
            } else {
                pl.enqueue(current);
            }
            size++;
        }

        // Enqueue the minimum value back to the queue
        pl.enqueue(min);

        // Move size-1 elements from the front to the rear
        for (int i = 0; i < size - 1; i++) {
            pl.enqueue(pl.dequeue());
        }

        return min;
    }
}


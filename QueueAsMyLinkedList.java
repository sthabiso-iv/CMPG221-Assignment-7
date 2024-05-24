public class QueueAsMyLinkedList<E> {
    MyLinkedList<E> theQueue;

    public QueueAsMyLinkedList() {
        theQueue = new MyLinkedList<>();
    }

    public void enqueue(E newElement) {
        theQueue.append(newElement);
    }

    public E dequeue() {
        E temp = null;
        boolean isDone = false;
        temp = theQueue.getFirst();
        if (temp != null) {
            isDone = theQueue.delete(temp);
        }
        if (isDone) {
            return temp;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return theQueue.toString();
    }

    // Recursive method to sort the queue
    public static QueueAsMyLinkedList method(QueueAsMyLinkedList pl, int x) {
        if (x <= 1) {
            return pl; // Base case: a single element or an empty list is already sorted.
        }

        // Split the input queue into two sub-queues.
        QueueAsMyLinkedList leftQueue = new QueueAsMyLinkedList();
        QueueAsMyLinkedList rightQueue = new QueueAsMyLinkedList();

        int mid = x / 2;
        for (int i = 0; i < mid; i++) {
            leftQueue.enqueue(pl.dequeue());
        }
        for (int i = mid; i < x; i++) {
            rightQueue.enqueue(pl.dequeue());
        }

        // Recursively sort the two sub-queues.
        leftQueue = method(leftQueue, mid);
        rightQueue = method(rightQueue, x - mid);

        // Merge the two sorted sub-queues.
        QueueAsMyLinkedList mergedQueue = merge(leftQueue, rightQueue);

        return mergedQueue;
    }

    // Helper method to merge two sorted queues
    private static QueueAsMyLinkedList merge(QueueAsMyLinkedList left, QueueAsMyLinkedList right) {
        QueueAsMyLinkedList mergedQueue = new QueueAsMyLinkedList();

        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.theQueue.getFirst().compareTo(right.theQueue.getFirst()) < 0) {
                mergedQueue.enqueue(left.dequeue());
            } else {
                mergedQueue.enqueue(right.dequeue());
            }
        }

        // Add remaining elements from both queues, if any.
        while (!left.isEmpty()) {
            mergedQueue.enqueue(left.dequeue());
        }
        while (!right.isEmpty()) {
            mergedQueue.enqueue(right.dequeue());
        }

        return mergedQueue;
    }

    public static void main(String[] args) {
        QueueAsMyLinkedList list1 = new QueueAsMyLinkedList();
        list1.enqueue(3);
        list1.enqueue(5);
        list1.enqueue(8);
        list1.enqueue(7);
        list1.enqueue(1);

        QueueAsMyLinkedList sortedList = method(list1, 5);
        System.out.println(sortedList); // Display [1, 3, 5, 7, 8]
    }
}

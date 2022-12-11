/**
 * Priority Queue interface
 *
 * @param <T>
 */
@SuppressWarnings("unused")
public interface PriorityQueueInterface<T> {

    /**
     * Inserts the specified element into this priority queue.
     *
     * @param item the item to add to the priority queue
     */
    void add(T item);


    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of the queue
     */
    T peek();


    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of the queue
     */
    T getMax();
}

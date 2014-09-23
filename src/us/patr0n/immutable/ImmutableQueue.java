package us.patr0n.immutable;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The Queue class represents an immutable first-in-first-out (FIFO) queue of
 * objects.
 *
 * @param<E>
 */
public final class ImmutableQueue<E>  implements Iterable<E>{
    private ImmutableStack<E> outcoming;
    private ImmutableStack<E> incoming;
    private int size;

    public ImmutableQueue() {
        this(new ImmutableStack<E>(), new ImmutableStack<E>(), 0);
    }

    @Override
    public Iterator<E> iterator() {
        return new ImmutableQueueIterator<E>(this);
    }

    private ImmutableQueue(ImmutableStack<E> incoming, ImmutableStack<E> outcoming, int size) {
        this.incoming = incoming;
        this.outcoming = outcoming;
        this.size = size;
    }

    private void flip() {
        for (; incoming.size() > 0; incoming = incoming.pop()) {
            outcoming = outcoming.push(incoming.peek());
        }
    }

    /**
     * Return the queue that adds item into the tail of this queue without
     * modifying this queue,
     *
     * <pre>
     * e.g.
     * When this queue represents the queue(2,1,2,2,6) and we enqueue the value 4 into this queue,
     * this method returns a new queue(2,1,2,2,6,4)
     * and this object still represents the queue(2,1,2,2,6).
     * </pre>
     *
     * If the element e is null, throws IllegalArgumentException.
     *
     * @param e
     * @return
     * @throws IllegalArgumentException
     */
    public ImmutableQueue<E> enqueue(E e) {
        //System.out.print(incoming);
        return new ImmutableQueue<E>(
                incoming.push(e),
                outcoming,
                size + 1
        );
    }

    /**
     * Return the queue that removes the object at the head of this queue
     * without modifying this queue.
     *
     * <pre>
     * e.g.
     * When this queue represents the queue(7,1,3,3,5,1),
     * this method returns a new queue(1,3,3,5,1)
     * and this object still represents the queue(7,1,3,3,5,1).
     * </pre>
     *
     * If this queue is empty, throws java.util.NoSuchElementException.
     *
     * @returns
     * @throws java.util.NoSuchElementException
     */
    public ImmutableQueue<E> dequeue() {
        if (outcoming.size() == 0) {
            flip();
        }
        if (outcoming.size() == 0) {
            throw new NoSuchElementException();
        }
        return new ImmutableQueue<E>(
                incoming,
                outcoming.pop(),
                size - 1
        );
    }

    /**
     * Looks at the object which is the head of this queue without removing it
     * from the queue.
     *
     * <pre>
     * e.g.
     * When this queue represents the queue(7,1,3,3,5,1),
     * this mothod returns 7 and this object still represents the queue(7,1,3,3,5,1)
     * </pre>
     *
     * If the queue is empty, throws java.util.NoSuchElementException
     *
     * @return
     * @throws java.util.NoSuchElementException
     */
    public E peek() {
        if (outcoming.size() == 0) {
            flip();
        }
        if (outcoming.size() == 0) {
            throw new NoSuchElementException();
        }
        return outcoming.peek();
    }

    /**
     * Return the number of objects in this queue.
     *
     * @return
     */
    public int size() {
        return size;
    }
}

final class ImmutableQueueIterator<E> implements Iterator<E> {
    private ImmutableQueue<E> queue;

    public ImmutableQueueIterator(ImmutableQueue<E> queue) {
        this.queue = queue;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E next() {
        try {
            return queue.peek();
        } finally {
            queue = queue.dequeue();
        }
    }

    @Override
    public boolean hasNext() {
        return queue.size() > 0;
    }
}

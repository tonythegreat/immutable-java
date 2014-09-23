package us.patr0n.immutable;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by patr0nus on 9/23/14.
 */
public final class ImmutableStack<E> implements Iterable<E> {
    private ImmutableStack<E> base;
    private E top;
    private int size;
    public ImmutableStack() {
        base = null;
        top = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ImmutableStackIterator<E>(this);
    }

    /**
    * @throws IllegalArgumentException
    */
    public ImmutableStack<E> push(E e) {
        if (e == null) {
            throw new IllegalArgumentException();
        }
        ImmutableStack<E> newStack = new ImmutableStack<E>();
        newStack.base = this;
        newStack.top = e;
        newStack.size = size + 1;
        return newStack;
    }
    public ImmutableStack<E> pop() {
        if (base == null) {
            throw new NoSuchElementException();
        }
        return base;
    }
    public E peek() {
        if (top == null) {
            throw new NoSuchElementException();
        }
        return top;
    }

    public int size() {
        return size;
    }
}


final class ImmutableStackIterator<E> implements Iterator<E> {
    private ImmutableStack<E> stack;
    public ImmutableStackIterator(ImmutableStack<E> stack) {
        this.stack = stack;
    }
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E next() {
        try {
            return stack.peek();
        }
        finally {
            stack = stack.pop();
        }
    }

    @Override
    public boolean hasNext() {
        return stack.size() > 0;
    }
}
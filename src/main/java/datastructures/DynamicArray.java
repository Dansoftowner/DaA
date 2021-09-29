package datastructures;

import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T> {

    private final int increase;
    private Object[] arrayImpl;
    private int size;

    public DynamicArray() {
        this(10);
    }

    public DynamicArray(int initialSize) {
        this(initialSize, 10);
    }

    public DynamicArray(int initialSize, int increase) {
        this.arrayImpl = new Object[initialSize];
        this.increase = increase;
    }

    public void insert(T item) {
        if (isArrayFull())
            expandArray();
        arrayImpl[size] = item;
        size++;
    }

    public void removeAt(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();
        shrinkArray(index);
    }

    public int indexOf(T item) {
        for (int i = 0; i < arrayImpl.length; i++) {
            if (arrayImpl[i] != null && arrayImpl[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T item) {
        for (var element : arrayImpl)
            if (element != null && element.equals(item))
                return true;
        return false;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();
        return (T) arrayImpl[index];
    }

    public int getSize() {
        return size;
    }

    private boolean isArrayFull() {
        return arrayImpl.length == size;
    }

    private void expandArray() {
        var newArray = new Object[arrayImpl.length * increase];
        for (int i = 0; i < arrayImpl.length; i++)
            newArray[i] = arrayImpl[i];
        arrayImpl = newArray;
    }

    private void shrinkArray(int indexToRemove) {
        var newArray = new Object[arrayImpl.length - 1];
        int k = 0;
        for (int i = 0; i < indexToRemove; i++)
            newArray[k++] = arrayImpl[i];
        for (int i = indexToRemove + 1; i < arrayImpl.length; i++)
            newArray[k++] = arrayImpl[i];
        arrayImpl = newArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                return (T) arrayImpl[i++];
            }
        };
    }
}

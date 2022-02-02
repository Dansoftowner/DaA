package datastructures;

import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T> {

    private static final int INCREASE = 2;

    private Object[] arrayImpl;
    private int size;

    public DynamicArray() {
        this(10);
    }

    public DynamicArray(int initialSize) {
        this.arrayImpl = new Object[initialSize];
    }

    public void insert(T item) {
        if (isArrayFull())
            expandArray();
        arrayImpl[size++] = item;
    }

    public void insertAt(int index, T value) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();
        if (isArrayFull())
            expandArray();
        for (int i = size +1; i > index; i--)
            arrayImpl[i] = arrayImpl[i - 1];
        arrayImpl[index] = value;
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

    public void reverse() {
        for (int i = 0; i < size / 2; i++) {
            int reflectIndex = getLastIndex() -i;
            var temp = arrayImpl[i];
            arrayImpl[i] = arrayImpl[reflectIndex];
            arrayImpl[reflectIndex] = temp;
        }
    }

    public int getSize() {
        return size;
    }

    public int getLastIndex() {
        return size -1;
    }

    private boolean isArrayFull() {
        return arrayImpl.length == size;
    }

    private void expandArray() {
        var newArray = new Object[Math.max(1, arrayImpl.length) * INCREASE];
        for (int i = 0; i < arrayImpl.length; i++)
            newArray[i] = arrayImpl[i];
        arrayImpl = newArray;
    }

    private void shrinkArray(int indexToRemove) {
        for (int i = indexToRemove; i < size; i++)
            arrayImpl[i] = arrayImpl[i + 1];
        size--;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
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

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (T e : this)
            sb.append(e).append(", ");
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}

package Country;

import java.util.Iterator;

public class ArrayIterator implements Iterator {
    private Object[] arr;
    private int size;
    private int index = 0;

    public ArrayIterator(Object[] arr, int size){
        this.arr = arr;
        this.size = size;
    }

    public Object[] getArr(){
        return arr;
    }

    @Override
    public boolean hasNext() {
        return index <size;
    }

    @Override
    public Object next() {
        if (!hasNext())
            throw new IndexOutOfBoundsException("out of boundaries");
        index++;
        return arr[index -1];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }
}

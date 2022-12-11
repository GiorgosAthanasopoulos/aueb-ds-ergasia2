import java.util.Arrays;

@SuppressWarnings("unchecked, unused")
public class ArrayList<T> {
    Object[] data;
    private static final int SIZE_INCR = 10;
    private int size = 0;

    public ArrayList() {
        data = new Object[SIZE_INCR];
    }

    public void add(T val) {
        checkSize(size + 1);
        data[size++] = val;
    }

    public void add(int index, T val) {
        if (size < 0 || size >= data.length) throw new IllegalArgumentException();
        checkSize(size + 1);
        System.arraycopy(data, index, data, index+1, size-index);
        data[index] = val;
        size++;
    }

    public void remove(T val) {
        if (val == null) {
            for (int i = 0; i < size; i++)
                if (data[i] == null)
                    removeInternal(i);
        } else
            for (int i=0; i<size; i++)
                if (val.equals(data[i]))
                    removeInternal(i);
    }

    private void removeInternal(int index) {
        int tmp = size - index - 1;
        if (tmp > 0)
            System.arraycopy(data, index+1, data, index, tmp);
        data[--size] = null;
    }

    public T remove(int index) {
        if (index < 0 || index >= data.length) throw new IllegalArgumentException();
        T res = (T) data[index];
        int tmp = size - index - 1;
        if (tmp > 0)
            System.arraycopy(data, index+1, data, index, tmp);
        data[--size] = null;
        return res;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(getClass().getName() + "@" + hashCode() + ": ");
        for (int i=0; i<size; i++) {
            string.append(data[i]);
            if (i != size - 1)
                string.append(", ");
        }
        return string.toString();
    }

    private void checkSize(int size) {
        if (size < 0) throw  new IllegalArgumentException();
        if (size - data.length > 0)
            grow(size);
    }

    private void grow(int size) {
        int oldSize = data.length;
        int newSize = oldSize + oldSize >> 1;
        if (newSize - size < 0)
            newSize = size;
        data = Arrays.copyOf(data, newSize);
    }
}

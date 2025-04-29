import java.util.Arrays;
public class IntList {
    private int[] arr;
    private int size;
    IntList() {
        arr = new int[1];
        size = 0;
    }
    public static IntList singleton(int a) {
        var x = new IntList();
        x.add(a);
        return x;
    }
    
    public int size() {
        return size;
    }

    public void inc(int pos) {
        arr[pos]++;
    }
    public void add(int n) {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, (size * 3 + 1) / 2);
        }
        arr[size++] = n;
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            s.append(Integer.toString(arr[i]) + " ");
        }
        s.delete(s.length() - 1, s.length());
        return s.toString();
    }
}
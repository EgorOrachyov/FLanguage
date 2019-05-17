package Utils;

public class Carriage<T> {

    private int current = 0;
    private final T[] buffer;


    public Carriage(T[] source) {
        buffer = source;
    }

    public boolean end() {
        return (current >= buffer.length);
    }

    public void advance() {
        current += 1;
    }

    public void setIndex(int index) {
        current = index;
    }

    public T current() {
        return buffer[current];
    }

    public void offsetBack() {
        current -= 1;
    }

    public int index() {
        return current;
    }

    public String subString(int l, int r) {
        if (l < r && r < buffer.length) {
            StringBuilder string = new StringBuilder(r - l);
            for (int i = l; i < r; i++) {
                string.append(buffer[i]);
            }
            return string.toString();
        }

        return "";
    }

}

package Lexing;

public class Carriage {

    private int current = 0;
    private final char[] buffer;

    public Carriage(String source) {
        buffer = source.toCharArray();
    }

    public Carriage(char[] source) {
        buffer = source;
    }

    public boolean end() {
        return (current >= buffer.length);
    }

    public void advance() {
        current += 1;
    }

    public char current() {
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

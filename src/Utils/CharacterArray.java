package Utils;

public class CharacterArray {

    /**
     * Convert string to Character[] array (for prefix tree)
     * @param source String source
     * @return Array of characters, which represents the string s
     */
    public static Character[] convert(String source) {
        Character[] result = new Character[source.length()];
        for (int i = 0; i < source.length(); i++) {
            result[i] = source.charAt(i);
        }
        return result;
    }

}

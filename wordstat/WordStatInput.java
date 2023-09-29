import java.util.*;
import java.io.*;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
public class WordStatInput{
    private static final int BUFFER_SIZE = 1024;
    private static LinkedHashMap<String, Integer> data;

    private static boolean isPartOfWord(char cur) {
        return Character.getType(cur) == Character.LOWERCASE_LETTER || Character.getType(cur) == Character.DASH_PUNCTUATION || cur == '\'';
    }

    private static void addWord(StringBuilder builder) {
        String name = builder.toString();
        if (name.isEmpty()) return;
        Integer val = data.get(name);
        data.put(name, ((val == null) ? 0 : val) + 1);
        builder.setLength(0);
    }

    private static void parse(char[] buffer, int size, StringBuilder builder) {
        for (int i = 0; i < size; ++i) {
            char cur = Character.toLowerCase(buffer[i]);
            if (!isPartOfWord(cur)) {
                addWord(builder);
            } else {
                builder.append(cur);
            }
        }
    }

    public static void main(String[] args) {
        data = new LinkedHashMap<String, Integer>();
        StringBuilder result = new StringBuilder();
        try (FileReader reader = new FileReader(new File(args[0]), StandardCharsets.UTF_8)) {
            char[] buffer = new char[BUFFER_SIZE];
            StringBuilder builder = new StringBuilder();
            int size = 0;
            while ((size = reader.read(buffer)) >= 0) {
                parse(buffer, size, builder);
                //System.err.println(buffer);
            }
            addWord(builder);
            
            for (var x : data.entrySet()) {
                result.append(x.getKey().toString() + " " + x.getValue().toString() + "\n");
            }
        } catch(FileNotFoundException e) {
            System.err.println("Error! Cannot open file " + args[0]);
            return;
        } catch(IOException e) {
            System.err.println("...");
            return;
        }
        try (var writer = new FileWriter(new File(args[1]), StandardCharsets.UTF_8)) {
            writer.append(result.toString());
        } catch(IOException e) {
            System.err.println("Cannot open file " + args[1]);
        }
    }
}
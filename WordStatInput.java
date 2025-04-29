import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
public class WordStatInput{
    private static LinkedHashMap<String, Integer> data;
    
    private static void addWord(String name) {
        name = name.toLowerCase();
        if (name.isEmpty()) return;
        Integer val = data.get(name);
        data.put(name, ((val == null) ? 0 : val) + 1);
    }
    
    private static boolean delim(int c) {
        return c != '\'' &&
         Character.getType(Character.toLowerCase(c)) != Character.DASH_PUNCTUATION && 
         Character.getType(Character.toLowerCase(c)) != Character.LOWERCASE_LETTER;
    }
    
    public static void main(String[] args) {
        data = new LinkedHashMap<String, Integer>();
        try {
            var scan = new MyScanner(new FileReader(new File(args[0]), StandardCharsets.UTF_8),
                                    WordStatInput::delim);
            while (scan.hasNext()) {
                String s = scan.next();
                System.out.println(s);
                addWord(s);
            }
        } catch(FileNotFoundException e) {
            System.err.println("Error! Cannot open file " + args[0]);
            return;
        } catch(IOException e) {
            System.err.println("...");
            return;
        }
        try (var writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(args[1]),
                "UTF-8"
                ))) {
            for (var x : data.entrySet()) {
                writer.write(x.getKey() + " " + x.getValue().toString() + "\n");
            }
            writer.close();
        } catch(IOException e) {
            System.err.println("Cannot open file " + args[1]);
        } 
    }
}
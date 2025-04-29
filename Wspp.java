import java.io.*;
import java.util.*;

public class Wspp{
    public static void addWord(LinkedHashMap<String, IntList> d, String word, int cnt) {
        var kv = d.get(word);
        if (kv != null) {
            kv.add(cnt);
            return;
        }
        IntList list = new IntList();
        list.add(cnt);
        d.put(word, list);
    }
    private static boolean delim(int c) {
        c = Character.toLowerCase(c);
        return c != '\'' &&
        Character.getType(c) != Character.LOWERCASE_LETTER && 
        Character.getType(c) != Character.DASH_PUNCTUATION;
    }
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("ERROR");
            return;
        }
        LinkedHashMap<String, IntList> data = new LinkedHashMap<>();
        try (var scan = new MyScanner(new File(args[0]), Wspp::delim)) {
            int cnt = 0;
            while (scan.hasNext()) {
                ++cnt;
                addWord(data, scan.next().toLowerCase(), cnt);
            }
        } catch(IOException e) {
            System.out.println(e.toString());
        }
        try (var writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(args[1]),
                "UTF-8"
                ))) {
            for (var x : data.entrySet()) {
                var list = x.getValue();
                writer.write(x.getKey() + " " + String.valueOf(list.size()) + " " + list.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
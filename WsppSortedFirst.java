import java.io.*;
import java.util.*;

public class WsppSortedFirst {
    
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
        Map<String, IntList> data = new TreeMap<>();
        try (var scan = new MyScanner(new File(args[0]), WsppSortedFirst::delim)) {
            int cur = 0;
            while (scan.hasNextLine()) {
                Set<String> curSet = new HashSet<>();
                while (scan.hasNextInLine()) {
                    ++cur;
                    String word = scan.next().toLowerCase();
                    var list = data.getOrDefault(word, IntList.singleton(0));
                    list.inc(0);
                    if(!curSet.contains(word)) {
                        curSet.add(word);
                        list.add(cur);
                    }
                    data.put(word, list);
                }
                scan.nextLine();
            }
        } catch(IOException e) {
            System.out.println(e.toString());
        }
        try (var writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(args[1]),
                "UTF-8"
                ))) {
            for (var x : data.entrySet()) {
                writer.write(x.getKey() + " " + x.getValue().toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}



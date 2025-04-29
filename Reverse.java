import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class Reverse {
    private static int[] realloc(int[] cur, int size) {
        int[] temp = new int[size * 2];
        System.arraycopy(cur, 0, temp, 0, size);
        return temp;
    }
    
    private static void print(ArrayList<int[]> lines) {
        for (int i = lines.size() - 1; i >= 0; --i) {
            int[] cur = lines.get(i);
            for (int j = cur.length - 1; j >= 0; --j) {
                System.out.print(cur[j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    public static boolean delim(int c) {return Character.isWhitespace(c);}
    public static void main(String args[]) {
        try {
            ArrayList<int[]> lines = new ArrayList<>();
            int[] arr = new int[1];
            int cnt = 1;
            boolean firstLine = true;
            var scan = new MyScanner((InputStream)System.in, Reverse::delim);
            while (scan.hasNextLine()) {
                cnt = 0;
                while (scan.hasNextInLine()) {
                    String curToken = scan.next();
                    if (arr.length == cnt) {
                        arr = realloc(arr, cnt);
                    }
                    arr[cnt++] = Integer.parseInt(curToken);
                }
                scan.nextLine();
                int[] temp = new int[cnt];
                System.arraycopy(arr, 0, temp, 0, cnt);
                lines.add(temp);
                
            }
            //System.err.println("ENDED!"); 
            //lines.removeLast();
            print(lines);
        } catch(IOException e) {
            System.out.println("Error");
        }
    }
}

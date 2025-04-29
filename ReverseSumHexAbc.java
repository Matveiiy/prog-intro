import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ReverseSumHexAbc {
    
    public static String toAbc(int n) {
        boolean flag = n < 0;
        n = Math.abs(n);
        String s = Integer.toString(n);
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            b.append((char)(s.charAt(i) - '0' + 'a'));
        }
        if (flag) return "-" + b.toString();
        return b.toString();
    }
    private static void print(ArrayList<int[]> lines) {
        for (int i = 0; i < lines.size(); ++i) {
            int[] cur = lines.get(i);
            for (int j = 0; j < cur.length; ++j) {
                System.out.print(toAbc(cur[j]));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    private static int toDec(String str) {
        int sign = 1, spos = 0;
        //str == 0
        if (str.charAt(0) == '-') {
            sign = -1;
            spos = 1;
        }
        if (str.length() > spos + 1 && (str.charAt(spos + 1) == 'x' || str.charAt(spos + 1) == 'X')) {
            return sign * Integer.parseUnsignedInt(str.substring(spos + 2), 16);
        }
        StringBuilder s = new StringBuilder(str);
        for (int i = spos; i < str.length(); ++i) {
            s.setCharAt(i, (char)(Character.toLowerCase(s.charAt(i)) - 'a' + '0'));
        }
        return sign * Integer.parseUnsignedInt(s.substring(spos).toString());
    }
    private static boolean delim(int c) {
        return Character.isWhitespace(c);
    }
    
    private static ArrayList<int[]> parse() {
        try {
            var scan = new MyScanner((InputStream)System.in, ReverseSumHexAbc::delim);
            int[] cur = new int[1];
            ArrayList<int[]> lines = new ArrayList<int[]>(1);
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                var scanLine = new MyScanner(s, ReverseSumHexAbc::delim);
                int cntCur = 0;
                while (scanLine.hasNext()) {
                    if (cntCur == cur.length) {
                        cur = Arrays.copyOf(cur, cntCur * 2);
                    }
                    String temp = scanLine.next();
                    cur[cntCur++] += toDec(temp);
                }
                int curPrefix = 0;
                int[] temp = new int[cntCur]; 
                for (int i = 0; i < cntCur; ++i) {
                    curPrefix += cur[i];
                    temp[i] = curPrefix;
                }
                lines.add(temp);
            }
            return lines;
        }  catch(IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }
    public static void main(String[] args) {
        print(parse());
    }
}
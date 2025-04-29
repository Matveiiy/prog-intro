import java.util.Arrays;
import java.util.Scanner;
import java.io.Console;
import java.util.ArrayList;

public class ReverseSumHex {
    static int START_SIZE = 1;
    private static void print(ArrayList<int[]> lines) {
        for (int i = 0; i < lines.size(); ++i) {
            int[] cur = lines.get(i);
            for (int j = 0; j < cur.length; ++j) {
                System.out.print(Integer.toHexString(cur[j]));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    private static ArrayList<int[]> parse() {
        Scanner scan = new Scanner(System.in);
        int[] cur = new int[START_SIZE];
        ArrayList<int[]> lines = new ArrayList<int[]>(1);
        while (scan.hasNextLine()) {
            Scanner scanLine = new Scanner(scan.nextLine());
            int cntCur = 0;
            while (scanLine.hasNext()) {
                if (cntCur == cur.length) cur = Arrays.copyOf(cur, cntCur * 2);
                cur[cntCur++] += Integer.parseUnsignedInt(scanLine.next(), 16);// (int)scanLine.nextLong(16);
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
    }
    public static void main(String[] args) {
        print(parse());
    }
}
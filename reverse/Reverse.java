/*
________00000000000___________000000000000_________
______00000000_____00000___000000_____0000000______
____0000000_____________000______________00000_____
___0000000_______________0_________________0000____
__000000____________________________________0000___
__00000_____________________________________ 0000__
_00000______________________________________00000__
_00000_____________________________________000000__
__000000_________________________________0000000___
___0000000______________________________0000000____
_____000000____________________________000000______
_______000000________________________000000________
__________00000_____________________0000___________
_____________0000_________________0000_____________
_______________0000_____________000________________
_________________000_________000___________________
_________________ __000_____00_____________________
______________________00__00_______________________
________________________00_________________________
*/

import java.util.Scanner;
import java.util.Arrays;
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
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        ArrayList<int[]> lines = new ArrayList<>();
        int[] arr = new int[1];
        int cnt = 1;
        while (scan.hasNextLine()) {
            String curLine = scan.nextLine();
            Scanner scanLine = new Scanner(curLine);
            cnt = 0;
            while (scanLine.hasNextInt()) {
                if (arr.length == cnt) {
                    arr = realloc(arr, cnt);
                }
                arr[cnt++] = scanLine.nextInt();
            }
            int[] temp = new int[cnt];
            System.arraycopy(arr, 0, temp, 0, cnt);
            lines.add(temp);
        }
        print(lines);
    }
}

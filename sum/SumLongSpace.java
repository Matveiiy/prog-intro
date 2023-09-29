public class SumLongSpace {
    private static boolean isWhitespace(char c) {
        return Character.SPACE_SEPARATOR == Character.getType(c);
    }

    public static long parse(String arg) {
        long ans = 0;
        int l = 0;
        for (int r = 0; r <= arg.length(); ++r) {
            if (!isWhitespace(arg.charAt(r)) && r != arg.length()) {
                continue;
            } 
            if (!isWhitespace(arg.charAt(l))) {
                ans += Long.parseLong(arg.substring(l, r));
            } 
            l = r + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        long ans = 0;
        for (String s : args) {
            ans += parse(s);
        }
        System.out.println(ans);
    } 
} 
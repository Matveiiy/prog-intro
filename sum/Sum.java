public class Sum {
    public static int ans = 0;
    private static int parsePart(String s, int start) {
        int sum = 0, neg = 1;
        while (start < s.length() && 
            !Character.isDigit(s.charAt(start)) &&
            s.charAt(start) != '+' &&
            s.charAt(start) != '-') start++;
        if (start >= s.length()) return start;
        if (!Character.isDigit(s.charAt(start))) {
            if (s.charAt(start) == '-') neg = -1;
            start++;
        }
        while (start < s.length() && Character.isDigit(s.charAt(start))) {
            sum *= 10;
            sum += Character.getNumericValue(s.charAt(start++));
        }
        ans += sum * neg;
        return start;
    }
    public static void parse(String s) {
        if (s.isEmpty()) return;
        int cur = 0;
        while (cur < s.length()) 
            cur = parsePart(s, cur);
    }
    public static void main(String[] args) {
        ans = 0;
        for (int i = 0; i < args.length; ++i) 
            parse(args[i]);
        System.out.println(ans);
    } 
} 
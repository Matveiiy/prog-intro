public class Sum {
	
    public static long parse(String arg) {
        int ans = 0;
        int l = 0;
        for (int r = 0; r <= arg.length(); ++r) {
            if (r != arg.length() && !Character.isWhitespace(arg.charAt(r))) {
                continue;
            } 
            if (l != r) {
                ans += Integer.parseInt(arg.substring(l, r));
            } 
	    l = r + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int ans = 0;
        for (String s : args) {
            ans += parse(s);
        }
        System.out.println(ans);
    } 
} 

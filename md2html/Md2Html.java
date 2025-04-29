package md2html;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class Md2Html{
    
    public static int add(String line, int pos, StringBuilder res, String end, String pattern) {
        res.append("<").append(pattern).append(">");
        int npos = parseTill(line, pos, res, end);
        res.append("</").append(pattern).append(">");
        return npos;
    }

    public static int parseSuperCode(String line, int pos, StringBuilder res) {
        while (pos < line.length() && !line.startsWith("```", pos)) {
            res.append(line.charAt(pos++));
        }
        return pos + 2;
    } 
    
    public static int parseTill(String line, int start, StringBuilder res, String end) {
        for (int i = start; i < line.length(); ++i) {
            if (line.charAt(i) == '\\') {
                res.append(line.charAt(i + 1));
                i++;
                continue;
            }
            if (!end.equals("**") && line.startsWith("**", i)) {
                i = add(line, i + 2, res, "**", "strong");
                continue;
            }
            if (!end.equals("--") && line.startsWith("--", i)) {
                i = add(line, i + 2, res, "--", "s");
                continue;
            }
            if (!end.equals("__") && line.startsWith("__", i)) {
                i = add(line, i + 2, res, "__", "strong");
                continue;
            }
            if (line.startsWith("```", i)) {
                res.append("<pre>");
                i = parseSuperCode(line, i + 3, res);
                res.append("</pre>");
                continue;
            }
            if (line.startsWith(end, i)) {
                return i + end.length() - 1; 
            }
            if (line.charAt(i) == '`')  {
                i = add(line, i + 1, res, "`", "code");
                continue;
            }
            if ((line.charAt(i) == '*' || line.charAt(i) == '_') && i + 1 < line.length()) {
                if (!Character.isWhitespace(line.charAt(i + 1))) {
                    i = add(line, i + 1, res, String.valueOf(line.charAt(i)), "em");
                    continue;
                }
            }
            switch (line.charAt(i)) {
                case '<' -> res.append("&lt;");
                case '>' -> res.append("&gt;");
                case '&' -> res.append("&amp;");
                default -> res.append(line.charAt(i));
            }
        }
        return line.length();
    }
    
    public static void parsePart(String line, StringBuilder res) {
        int j = 0;
        while (j < Math.min(7, line.length()) && line.charAt(j) == '#') {
            ++j;
        }
        // ####$ | ###[space]*
        if (j != 0 && (j >= line.length() || Character.isWhitespace(line.charAt(j)))) {
            add(line, j + 1, res, "\\", "h" + String.valueOf(j));
        }
        else {
            add(line, 0, res, "\\", "p");
        }
        res.append('\n');    
    }
    
    public static void parse(MyScanner scan, StringBuilder res) throws IOException {
        String line = scan.nextLine();
        if (line.isBlank()) return;
        StringBuilder s = new StringBuilder(line);
        
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            if (line.isBlank()) {
                break;
            }
            s.append('\n');
            s.append(line);
        }
        parsePart(s.toString(), res);
    }
    public static boolean sep(int c) {
        return Character.isWhitespace(c);
    }
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Error! Expected 2 arguments");
            return;
        }
        StringBuilder res = new StringBuilder();
        try (MyScanner scan = new MyScanner(new FileReader(args[0], StandardCharsets.UTF_8), Md2Html::sep)) {
            while (scan.hasNextLine()) {
                parse(scan, res);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error! Input file not found");
            return;
        } catch (IOException e) {
            System.err.println("Error while trying to read the file!" + e.toString());
            return;
        }
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(args[1]))) {
            writer.append(res.toString());
        } catch (IOException e) {
            System.err.println("Error while trying to write in file!");
            return;
        }
    }
}
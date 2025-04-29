
import java.util.*;
import java.io.*;
public class MyScanner implements Closeable {
    
    public interface ScannerSeparator {
        boolean isDelim(int ch);
    }
    
    private ScannerSeparator delimiter;
    private Reader reader;
    private char[] buffer;
    private int bufferSize = 0;
    private int curPos = 0;
    private int linePos = 0;
    private int curChar, lastReadChar;
    private StringBuilder line;
    private int savedLines;
    

    MyScanner(Reader rd, int sz, ScannerSeparator sep) throws IOException {
        reader = rd;
        buffer = new char[sz];
        savedLines = 0;
        line = new StringBuilder();
        delimiter = sep;
        curChar = readNext();
    }

    MyScanner(Reader r, ScannerSeparator sep) throws IOException {
        this(r, 512, sep); 
    }

    MyScanner(String s, ScannerSeparator sep) throws IOException {
        this(new StringReader(s), Math.max(1, s.length()), sep);
    }

    MyScanner(File f, ScannerSeparator sep) throws IOException {
        this(new FileReader(f), sep);
    }

    MyScanner(InputStream s, ScannerSeparator sep) throws IOException {
        this(new InputStreamReader(s), sep);
    }

    public void close() throws IOException {
        reader.close();
    }

    public String next() throws IOException {
        if (!hasNext()) {
            throw new InputMismatchException("Expected token");
        }
        StringBuilder s = new StringBuilder();
        while (!delimiter.isDelim(curChar)) {
            s.append((char)curChar);
            curChar = readNext();
        }
        return s.toString();
    }

    public String nextLine() throws IOException {
        if (!hasNextLine()) {
            throw new InputMismatchException("Expected line");
        }
        if (savedLines > 0) {
            savedLines--;
            return "\n";
        }
        String res = line.toString();
        linePos = 0;
        line = line.delete(0, line.length());
        curChar = lastReadChar;
        return res;
    }
    public boolean hasNextLine() throws IOException {
        if (savedLines > 0 || line.length() > 0) {
            return true;
        }
        if (lastReadChar == -1) {
            return false;
        }
        
        while (!checkNextLine(lastReadChar, peek()) && lastReadChar != -1) {
            line.append((char)lastReadChar);
            lastReadChar = read();
        }
        
        if (lastReadChar == -1) {
            return false;
        }
        line.append('\n');
        lastReadChar = read();
        curChar = readNext();
        return true;
    }

    public boolean hasNext() throws IOException {
        while (!hasNextInLine() && curChar != -1) {
            savedLines++;
            curChar = readNext();
        }
        return curChar != -1;
    }

    public boolean hasNextInLine() throws IOException {
        while (!checkNextLine(curChar, peekNext()) && curChar != -1) {
            if (!delimiter.isDelim(curChar)) {
                return true;
            }
            curChar = readNext();
        }
        return false;
    }
    
    private static boolean checkNextLine(int cur, int nxt) {
        if (cur == '\r' && nxt == '\n') {
            return false;
        }
        return cur == '\r' || cur == '\n';
    }

    private int readNext() throws IOException {
        if (linePos < line.length()) {
            return line.charAt(linePos++);
        }
        return read();
    }

    private int peekNext() throws IOException {
        if (linePos < line.length()) {
            return line.charAt(linePos);
        }
        return peek();
    }
    
    private int read() throws IOException {
        lastReadChar = peek();
        curPos++;
        return lastReadChar;
    }

    private int peek() throws IOException {
        if (bufferSize == -1) {
            return -1;
        } 
        if (curPos < bufferSize) {
            return buffer[curPos];
        }
        curPos = 0;
        bufferSize = reader.read(buffer);
        return peek();
    }
}

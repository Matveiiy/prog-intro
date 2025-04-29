package markup;
import java.util.*;

public class Emphasis extends TextElement {
    public Emphasis(List<TextElement> elements) {
        super(elements, "*", "*", "[i]", "[/i]");
    }
}

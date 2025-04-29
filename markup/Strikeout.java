package markup;
import java.util.*;

public class Strikeout extends TextElement {
    public Strikeout(List<TextElement> elements) {
        super(elements, "~", "~", "[s]", "[/s]");
    }
}

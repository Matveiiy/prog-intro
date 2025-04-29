package markup;
import java.util.*;

public class Strong extends TextElement {
    public Strong(List<TextElement> elements) {
        super(elements, "__", "__", "[b]", "[/b]");
    }
}

package markup;
import java.util.*;
public class ListContainer extends MarkupContainer {
    List<ListItem> elements;
    String start, end;
        
    ListContainer(List<ListItem> elements, String start, String end) {
        this.start = start;
        this.end = end;
        this.elements = elements;
    }

    public void toBBCode(StringBuilder res) {
        res.append(start);
        for (var x : elements) {
            x.toBBCode(res);
        }
        res.append(end);
    }
}

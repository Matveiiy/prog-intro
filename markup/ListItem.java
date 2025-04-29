package markup;
import java.util.*;

public class ListItem implements BBCodeElement {
    List<MarkupContainer> list;

    public ListItem(List<MarkupContainer> list) {
        this.list = list;
    }

    @Override
    public void toBBCode(StringBuilder res) {
        res.append("[*]");
        for (var x : list) {
            x.toBBCode(res);
        }
    }
}

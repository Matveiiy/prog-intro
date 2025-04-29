package markup;
import java.util.List;

public class UnorderedList extends ListContainer {
    public UnorderedList(List<ListItem> elements) {
        super(elements, "[list]", "[/list]");
    }
}

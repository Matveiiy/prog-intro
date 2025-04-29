package markup;
import java.util.*;

public class OrderedList extends ListContainer {
    public OrderedList(List<ListItem> elements) {
        super(elements, "[list=1]", "[/list]");
    }    
}

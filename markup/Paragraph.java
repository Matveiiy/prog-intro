package markup;
import java.util.*;
public class Paragraph extends MarkupContainer implements MarkupElement, BBCodeElement {

    List<TextElement> elements;

    public Paragraph(List<TextElement> elements) {
        this.elements = elements;
    }

    @Override
    public void toMarkdown(StringBuilder res) {
        for (var x : elements) {
            x.toMarkdown(res);
        }
    } 

    @Override
    public void toBBCode(StringBuilder res) {
        for (var x : elements) {
            x.toBBCode(res);
        }
    }
}

package markup;
import java.util.*;

import javax.print.DocFlavor.STRING;

public abstract class TextElement implements MarkupElement, BBCodeElement {
    String start1, end1, start2, end2;
    List<TextElement> elements;
    public TextElement(List<TextElement> elements, String start1, String end1, String start2, String end2) {
        this.start1 = start1;
        this.start2 = start2;
        this.end1 = end1;
        this.end2 = end2;
        this.elements = elements;
    }
    
    public void toBBCode(StringBuilder res) {
        res.append(start2);
        for (var x : elements) {
            x.toBBCode(res);
        }
        res.append(end2);
    }
   
    public void toMarkdown(StringBuilder res) {
        res.append(start1);
        for (var x : elements) {
            x.toMarkdown(res);
        }
        res.append(end1);
    }
     
}

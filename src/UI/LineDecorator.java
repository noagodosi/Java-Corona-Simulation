package UI;

import Country.Settlement;
import java.awt.*;

public class LineDecorator {

    public volatile Color colorLine;
    public Settlement s1;
    public Settlement s2;

    public LineDecorator(Settlement s1, Settlement s2) {
        int red=(((s1.getRamzorColor().getColor().getRed())+(s2.getRamzorColor().getColor().getRed()))/2);
        int blue= (((s1.getRamzorColor().getColor().getBlue())+(s2.getRamzorColor().getColor().getBlue()))/2);
        int green= (((s1.getRamzorColor().getColor().getGreen())+(s2.getRamzorColor().getColor().getGreen()))/2);
        this.colorLine = new Color(red,green,blue);
        this.s1=s1;
        this.s2=s2;
    }

    public void setColor(Graphics g) {
        g.setColor(colorLine);
    }
}





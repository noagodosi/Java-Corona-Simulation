package UI;

import Country.Settlement;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class PanelDrawing extends JPanel {
    protected static Graphics2D g2;
    protected final List<Shape> shapes;
    public volatile Settlement[] settlements;

    public PanelDrawing(mainFrame Frame){
        this.setBackground(Color.WHITE);
        Frame.add(this,BorderLayout.CENTER);
        shapes = new ArrayList<>();
        settlements=Frame.loadMap.map.getSettlement();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;

        for (Settlement settlement : settlements) {
            if (settlement.getConnectedSettlements() != null) {
                for (Settlement s : settlement.getConnectedSettlements()) {
                    LineDecorator colorShape = new LineDecorator(s, settlement);
                    colorShape.setColor(g);
                    g2.drawLine(s.getLocation().getPosition().getX() + 50, s.getLocation().getPosition().getY() + 30, settlement.getLocation().getPosition().getX() + 50, settlement.getLocation().getPosition().getY() + 30);

                    // colorLine=new Color((s.getRamzorColor().getColor().getRGB()+settlement.getRamzorColor().getColor().getRGB())/2);
                    //g2.setColor(colorLine);
                    // g2.drawLine(s.getLocation().getPosition().getX()+50,s.getLocation().getPosition().getY()+30,settlement.getLocation().getPosition().getX()+50,settlement.getLocation().getPosition().getY()+30);

                }
            }
            g2.setColor(settlement.getRamzorColor().getColor());
            Shape shape = new Rectangle2D.Double(settlement.getLocation().getPosition().getX(), settlement.getLocation().getPosition().getY(), settlement.getLocation().getSize().getWidth(), settlement.getLocation().getSize().getHeight());
            shapes.add(shape);
            g2.fill(shape);
            g2.setColor(Color.BLACK);
            g2.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
            g2.drawString(settlement.getName(), (settlement.getLocation().getPosition().getX()+(settlement.getLocation().getSize().getWidth()/4)), (settlement.getLocation().getPosition().getY()+(settlement.getLocation().getSize().getHeight())/2));
        }
    }

    public List<Shape> shapeList(){
        return shapes;
    }
}
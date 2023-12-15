package fr.uga.miage.m1.polygons.gui.shapes;

import edu.uga.singleshape.CubePanel;
import fr.uga.miage.m1.polygons.gui.persistence.Visitable;
import fr.uga.miage.m1.polygons.gui.persistence.Visitor;

import java.awt.*;

public class Cube extends AbstractSimpleShape implements SimpleShape, Visitable {

    private int size;

    public Cube(int size, int mX,int mY){
        this.size = size;
        super.setX(mX);
        super.setY(mY);
    }

    public void draw(Graphics2D g2) {
        CubePanel c = new CubePanel(size,super.getX(),super.getY());
        c.paintComponent(g2);
    }

    public void drawWithBorder(Graphics2D g2) {
        draw(g2);
    }

    public void accept(Visitor visitor) { visitor.visit(this);  }

    public boolean contains(int x, int y) {

        int mySize = (int)(this.size * 0.8);
        int delta = this.size - mySize;
        int centerX = (int)(super.getX() - delta / 2.0);
        int centerY = (int)(super.getY() - delta / 2.0);

        return x >= centerX-25 && x <= centerX+25 && y >= centerY-25 && y <= centerY+25;
    }
}

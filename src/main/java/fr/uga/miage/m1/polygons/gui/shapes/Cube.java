package fr.uga.miage.m1.polygons.gui.shapes;

import edu.uga.singleshape.CubePanel;
import fr.uga.miage.m1.polygons.gui.persistence.Visitable;
import fr.uga.miage.m1.polygons.gui.persistence.Visitor;

import java.awt.*;

public class Cube implements SimpleShape, Visitable {

    private int mX;
    private int mY;

    private int size;

    public Cube(int size, int mX,int mY){
        this.size = size;
        this.mX = mX;
        this.mY = mY;
    }

    public void draw(Graphics2D g2) {
        CubePanel c = new CubePanel(50,mX,mY);
        c.paintComponent(g2);
    }

    public void accept(Visitor visitor) { visitor.visit(this);  }

    public void setX(int x) {this.mX = x; }

    public void setY(int y) { this.mY = y; }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public boolean contains(int x, int y) {

        int mySize = (int)(this.size * 0.8);
        int delta = this.size - mySize;
        int centerX = (int)(this.mX - delta / 2.0);
        int centerY = (int)(this.mY - delta / 2.0);

        return x >= centerX-25 && x <= centerX+25 && y >= centerY-25 && y <= centerY+25;
    }
}

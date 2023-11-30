package fr.uga.miage.m1.polygons.gui.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import fr.uga.miage.m1.polygons.gui.persistence.Visitable;
import fr.uga.miage.m1.polygons.gui.persistence.Visitor;

public class Circle implements SimpleShape, Visitable {

    private int mX;

    private int mY;

    public Circle(int x, int y) {
        mX = x - 25;
        mY = y - 25;
    }

    public void draw(Graphics2D g2) {
        int mX50 = mX + 50;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0xFF585E));
        g2.fill(new Ellipse2D.Double(mX, mY, 50, 50));
        g2.draw(new Ellipse2D.Double(mX, mY, 50, 50));
    }

    @Override
    public int getX() {
        return this.mX;
    }

    @Override
    public int getY() {
        return this.mY;
    }

    @Override
    public void setX(int x) {
        this.mX = x;
    }

    @Override
    public void setY(int y) {
        this.mY = y;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public boolean contains(int x, int y) {
        return new Ellipse2D.Double(mX, mY, 50, 50).contains(x, y);
    }
}

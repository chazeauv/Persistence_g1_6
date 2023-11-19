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
        GradientPaint gradient = new GradientPaint(mX, mY, Color.RED, mX50, mY, Color.WHITE);
        g2.setPaint(gradient);
        g2.fill(new Ellipse2D.Double(mX, mY, 50, 50));
        BasicStroke wideStroke = new BasicStroke(2.0f);
        g2.setColor(Color.black);
        g2.setStroke(wideStroke);
        g2.draw(new Ellipse2D.Double(mX, mY, 50, 50));
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getX() {
        return mX;
    }

    public void setX(int x) {
        mX = x;
    }

    public int getY() {
        return mY;
    }

    public void setY(int y) {
        mY = y;
    }

    public boolean contains(int x, int y) {
        return new Ellipse2D.Double(mX, mY, 50, 50).contains(x, y);
    }
}

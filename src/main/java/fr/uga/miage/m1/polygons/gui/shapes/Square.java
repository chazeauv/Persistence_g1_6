
package fr.uga.miage.m1.polygons.gui.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import fr.uga.miage.m1.polygons.gui.persistence.Visitable;
import fr.uga.miage.m1.polygons.gui.persistence.Visitor;

/**
 * This class implements the square <tt>SimpleShape</tt> extension.
 * It simply provides a <tt>draw()</tt> that paints a square.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class Square implements SimpleShape, Visitable {

    int mX;

    int mY;

    public Square(int x, int y) {
        mX = x - 25;
        mY = y - 25;
    }

    public void draw(Graphics2D g2) {
        int mX50 = mX + 50;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(mX, mY, Color.BLUE, mX50, mY, Color.WHITE);
        g2.setPaint(gradient);
        g2.fill(new Rectangle2D.Double(mX, mY, 50, 50));
        BasicStroke wideStroke = new BasicStroke(2.0f);
        g2.setColor(Color.black);
        g2.setStroke(wideStroke);
        g2.draw(new Rectangle2D.Double(mX, mY, 50, 50));
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

    public boolean contains(int x, int y) { return new Rectangle2D.Double(mX, mY, 50, 50).contains(x, y); }
}

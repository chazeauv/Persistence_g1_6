
package fr.uga.miage.m1.polygons.gui.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

import fr.uga.miage.m1.polygons.gui.persistence.Visitable;
import fr.uga.miage.m1.polygons.gui.persistence.Visitor;

/**
 * This inner class implements the triangle <tt>SimpleShape</tt> service.
 * It simply provides a <tt>draw()</tt> that paints a triangle.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class Triangle implements SimpleShape, Visitable {

    int mX;

    int mY;

    private GeneralPath polygon;

    public Triangle(int x, int y) {
        mX = x - 25;
        mY = y - 25;
    }

    public void draw(Graphics2D g2) {
        int mX50 = mX + 50;
        int mX25 = mX + 25;

        int[] xcoords = { mX + 25, mX, mX + 50 };
        int[] ycoords = { mY, mY + 50, mY + 50 };

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0x00E499));

        polygon = new GeneralPath(Path2D.WIND_EVEN_ODD, xcoords.length);
        polygon.moveTo(mX25, mY);
        for (int i = 0; i < xcoords.length; i++) {
            polygon.lineTo(xcoords[i], ycoords[i]);
        }
        polygon.closePath();
        g2.fill(polygon);
        g2.draw(polygon);
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

        int mX25 = mX+25;
        polygon.moveTo(mX25, mY);

        return polygon.contains(x, y);
    }
}

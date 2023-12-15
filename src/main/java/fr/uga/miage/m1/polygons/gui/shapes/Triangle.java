
package fr.uga.miage.m1.polygons.gui.shapes;

import fr.uga.miage.m1.polygons.gui.persistence.Visitable;
import fr.uga.miage.m1.polygons.gui.persistence.Visitor;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

/**
 * This inner class implements the triangle <tt>SimpleShape</tt> service.
 * It simply provides a <tt>draw()</tt> that paints a triangle.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class Triangle extends AbstractSimpleShape implements SimpleShape, Visitable {

    private GeneralPath polygon;

    public Triangle(int x, int y) {
        super.setX(x - 25);
        super.setY(y - 25);
    }

    public void draw(Graphics2D g2) {
        int mX25 = super.getX() + 25;

        int[] xcoords = { super.getX() + 25, super.getX(), super.getX() + 50 };
        int[] ycoords = { super.getY(), super.getY() + 50, super.getY() + 50 };

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0x00E499));

        polygon = new GeneralPath(Path2D.WIND_EVEN_ODD, xcoords.length);
        polygon.moveTo(mX25, super.getY());
        for (int i = 0; i < xcoords.length; i++) {
            polygon.lineTo(xcoords[i], ycoords[i]);
        }
        polygon.closePath();
        g2.fill(polygon);
        g2.draw(polygon);
    }

    public void drawWithBorder(Graphics2D g2) {
        int mX25 = super.getX() + 25;

        int[] xcoords = { super.getX() + 25, super.getX(), super.getX() + 50 };
        int[] ycoords = { super.getY(), super.getY() + 50, super.getY() + 50 };

        polygon = new GeneralPath(Path2D.WIND_EVEN_ODD, xcoords.length);
        polygon.moveTo(mX25, super.getY());
        for (int i = 0; i < xcoords.length; i++) {
            polygon.lineTo(xcoords[i], ycoords[i]);
        }
        polygon.closePath();
        g2.setColor(new Color(0x00E499));
        g2.fill(polygon);
        BasicStroke bs = new BasicStroke(2.0f);
        g2.setColor(Color.BLACK);
        g2.setStroke(bs);
        g2.draw(polygon);
    }

    public GeneralPath getPolygon() {
        return this.polygon;
    }

    public void setPolygon(GeneralPath polygon) {
        this.polygon = polygon;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }



    public boolean contains(int x, int y) {

        int mX25 = super.getX()+25;
        polygon.moveTo(mX25, super.getY());

        return polygon.contains(x, y);
    }
}

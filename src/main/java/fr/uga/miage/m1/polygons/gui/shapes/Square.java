
package fr.uga.miage.m1.polygons.gui.shapes;

import fr.uga.miage.m1.polygons.gui.persistence.Visitable;
import fr.uga.miage.m1.polygons.gui.persistence.Visitor;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * This class implements the square <tt>SimpleShape</tt> extension.
 * It simply provides a <tt>draw()</tt> that paints a square.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class Square extends AbstractSimpleShape implements SimpleShape, Visitable {

    public Square(int x, int y) {
        super.setX(x - 25);
        super.setY(y - 25);
    }

    public void draw(Graphics2D g2) {

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0x5831FF));
        g2.fill(new Rectangle2D.Double(super.getX(), super.getY(), 50, 50));
        g2.draw(new Rectangle2D.Double(super.getX(), super.getY(), 50, 50));
    }

    public void drawWithBorder(Graphics2D g2) {
        g2.setColor(new Color(0x5831FF));
        g2.fill(new Rectangle2D.Double(super.getX(), super.getY(), 50, 50));
        BasicStroke bs = new BasicStroke(2.0f);
        g2.setColor(Color.BLACK);
        g2.setStroke(bs);
        g2.draw(new Rectangle2D.Double(super.getX(), super.getY(), 50, 50));
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }



    public boolean contains(int x, int y) { return new Rectangle2D.Double(super.getX(), super.getY(), 50, 50).contains(x, y); }
}

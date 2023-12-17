package fr.uga.miage.m1.polygons.gui.shapes;

import java.awt.Graphics2D;

/**
 * This interface defines the <tt>SimpleShape</tt> extension. This extension
 * is used to draw shapes.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public interface SimpleShape {

    void draw(Graphics2D g2);

    void drawWithBorder(Graphics2D g2);

    int getX();

    int getY();

    int getX25();

    int getY25();

    void setX(int x);

    void setY(int y);

    boolean contains(int x, int y);
}

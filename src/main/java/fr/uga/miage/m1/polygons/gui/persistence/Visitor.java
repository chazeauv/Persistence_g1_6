package fr.uga.miage.m1.polygons.gui.persistence;

import fr.uga.miage.m1.polygons.gui.shapes.Circle;
import fr.uga.miage.m1.polygons.gui.shapes.Square;
import fr.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * You must define a method for each type of Visitable.
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public interface Visitor {

    public void visit(Circle circle);

    public void visit(Square square);

    public void visit(Triangle triangle);
}

package fr.uga.miage.m1.polygons.gui.persistence;

import fr.uga.miage.m1.polygons.gui.shapes.Circle;
import fr.uga.miage.m1.polygons.gui.shapes.Square;
import fr.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLVisitor implements Visitor {

    private String representation = null;

    static final String XY = "</x><y>";
    static final String YSHAPE = "</y></shape>";

    public XMLVisitor() {
    }

    @Override
    public void visit(Circle circle) {

        this.representation = "<shape><type>circle</type><x>" + circle.getX() + XY + circle.getY() + YSHAPE;
    }

    @Override
    public void visit(Square square) {

        this.representation = "<shape><type>square</type><x>" + square.getX() + XY + square.getY() + YSHAPE;
    }

    @Override
    public void visit(Triangle triangle) {

        this.representation = "<shape><type>triangle</type><x>" + triangle.getX() + XY + triangle.getY() + YSHAPE;
    }

    /**
     * @return the representation in JSon example for a Triangle:
     *
     *         <pre>
     * {@code
     *  <shape>
     *    <type>triangle</type>
     *    <x>-25</x>
     *    <y>-25</y>
     *  </shape>
     * }
     * </pre>
     */
    public String getRepresentation() {
        return representation;
    }
}

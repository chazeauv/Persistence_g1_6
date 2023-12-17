package fr.uga.miage.m1.polygons.gui.persistence;

import fr.uga.miage.m1.polygons.gui.shapes.*;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLVisitor implements Visitor {

    private String representation = null;

    static final String XY = "</x><y>";
    static final String YSHAPE = "</y></shape>";

    @Override
    public void visit(Circle circle) {

        this.representation = "<shape><type>circle</type><x>" + circle.getX25() + XY + circle.getY25() + YSHAPE;
    }

    @Override
    public void visit(Square square) {

        this.representation = "<shape><type>square</type><x>" + square.getX25() + XY + square.getY25() + YSHAPE;
    }

    @Override
    public void visit(Triangle triangle) {

        this.representation = "<shape><type>triangle</type><x>" + triangle.getX25() + XY + triangle.getY25() + YSHAPE;
    }

    @Override
    public void visit(Cube cube) {

        this.representation = "<shape><type>cube</type><x>" + cube.getX25() + XY + cube.getY25() + YSHAPE;
    }

    @Override
    public void visit(Group group) {
        String shapeType = null;

        StringBuilder sb = new StringBuilder();
        sb.append("<group>");

        for (SimpleShape shape : group.getShapes()) {
            shapeType = shape.getClass().getSimpleName().toLowerCase();

            switch(shapeType) {
                case "circle":
                    sb.append("<shape><type>circle</type><x>").append(shape.getX25()).append(XY).append(shape.getY25()).append(YSHAPE);
                    break;
                case "square":
                    sb.append("<shape><type>square</type><x>").append(shape.getX25()).append(XY).append(shape.getY25()).append(YSHAPE);
                    break;
                case "triangle":
                    sb.append("<shape><type>triangle</type><x>").append(shape.getX25()).append(XY).append(shape.getY25()).append(YSHAPE);
                    break;
                case "cube":
                    sb.append("<shape><type>cube</type><x>").append(shape.getX25()).append(XY).append(shape.getY25()).append(YSHAPE);
                    break;
                default:
                    break;
            }

        }

        sb.append("</group>");
        this.representation = sb.toString();
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

    @Override
    public String getRepresentation() {
        return representation;
    }
}

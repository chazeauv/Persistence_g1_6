package fr.uga.miage.m1.polygons.gui.persistence;

import fr.uga.miage.m1.polygons.gui.shapes.*;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {

    private String representation = null;
    static final String XY = ",\n\t\t\"y\": ";
    static final String NT = "\n\t}";
    static final String NT2 = "\n\t\t}";

    @Override
    public void visit(Circle circle) {
        
        this.representation = "{\n\t\t\"type\": \"circle\",\n\t\t\"x\": " + circle.getX25() + XY + circle.getY25() + NT;
    }

    @Override
    public void visit(Square square) {

        this.representation = "{\n\t\t\"type\": \"square\",\n\t\t\"x\": " + square.getX25() + XY + square.getY25() + NT;
    }

    @Override
    public void visit(Triangle triangle) {

        this.representation = "{\n\t\t\"type\": \"triangle\",\n\t\t\"x\": " + triangle.getX25() + XY + triangle.getY25() + NT;
    }

    public void visit(Cube cube) {

        this.representation = "{\n\t\t\"type\": \"cube\",\n\t\t\"x\": " + cube.getX25() + XY + cube.getY25() + NT;
    }

    @Override
    public void visit(Group group) {
        String shapeType = null;

        StringBuilder sb = new StringBuilder();
        sb.append("{\n\t\"group\": [");

        for (SimpleShape shape : group.getShapes()) {
            shapeType = shape.getClass().getSimpleName().toLowerCase();

            switch(shapeType) {
                case "circle":
                    sb.append("\n\t\t{\n\t\t\"type\": \"circle\",\n\t\t\"x\": ").append(shape.getX25()).append(XY).append(shape.getY25()).append(NT2);
                    break;
                case "square":
                    sb.append("\n\t\t{\n\t\t\"type\": \"square\",\n\t\t\"x\": ").append(shape.getX25()).append(XY).append(shape.getY25()).append(NT2);
                    break;
                case "triangle":
                    sb.append("\n\t\t{\n\t\t\"type\": \"triangle\",\n\t\t\"x\": ").append(shape.getX25()).append(XY).append(shape.getY25()).append(NT2);
                    break;
                case "cube":
                    sb.append("\n\t\t{\n\t\t\"type\": \"cube\",\n\t\t\"x\": ").append(shape.getX25()).append(XY).append(shape.getY25()).append(NT2);
                    break;
                default:
                    break;
            }
        }

        sb.append("\n\t]\n}");
        this.representation = sb.toString();
    }

    /**
     * @return the representation in JSon example for a Circle
     *
     *         <pre>
     * {@code
     *  {
     *     "shape": {
     *     	  "type": "circle",
     *        "x": -25,
     *        "y": -25
     *     }
     *  }
     * }
     *         </pre>
     */

    @Override
    public String getRepresentation() {
        return representation;
    }
}

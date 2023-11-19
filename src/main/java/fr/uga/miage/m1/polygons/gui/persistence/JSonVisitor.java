package fr.uga.miage.m1.polygons.gui.persistence;

import fr.uga.miage.m1.polygons.gui.shapes.Circle;
import fr.uga.miage.m1.polygons.gui.shapes.Cube;
import fr.uga.miage.m1.polygons.gui.shapes.Square;
import fr.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {

    private String representation = null;
    static final String XY = ",\n\t\t\"y\": ";
    static final String NT = "\n\t}";

    @Override
    public void visit(Circle circle) {
        
        this.representation = "{\n\t\t\"type\": \"circle\",\n\t\t\"x\": " + circle.getX() + XY + circle.getY() + NT;
    }

    @Override
    public void visit(Square square) {

        this.representation = "{\n\t\t\"type\": \"square\",\n\t\t\"x\": " + square.getX() + XY + square.getY() + NT;
    }

    @Override
    public void visit(Triangle triangle) {

        this.representation = "{\n\t\t\"type\": \"triangle\",\n\t\t\"x\": " + triangle.getX() + XY + triangle.getY() + NT;
    }

    public void visit(Cube cube) {

        this.representation = "{\n\t\t\"type\": \"cube\",\n\t\t\"x\": " + cube.getX() + XY + cube.getY() + NT;
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
    public String getRepresentation() {
        return representation;
    }
}

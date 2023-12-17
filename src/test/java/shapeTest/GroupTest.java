package shapeTest;

import fr.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import fr.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import fr.uga.miage.m1.polygons.gui.shapes.Circle;
import fr.uga.miage.m1.polygons.gui.shapes.Group;
import fr.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import fr.uga.miage.m1.polygons.gui.shapes.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupTest {

    //Tests unitaires de la classe Group
    @Test
    @DisplayName("Test addShape")
    void testAddShape() {
        //given
        Group group = new Group();
        SimpleShape shape = new Circle(0, 0);
        //when
        group.addShape(shape);
        //then
        Assertions.assertTrue(group.getShapes().contains(shape));
    }

    @Test
    @DisplayName("Test fonction removeShape")
    void testRemoveShape() {
        //given
        Group group = new Group();
        SimpleShape shape = new Circle(0, 0);
        //when
        group.addShape(shape);
        group.removeShape(shape);
        //then
        Assertions.assertTrue(!group.getShapes().contains(shape));
    }

    @Test
    @DisplayName("Test fonction setShapes")
    void testSetShapes() {
        //given
        Group group = new Group();
        SimpleShape shape = new Circle(0, 0);
        ArrayList<SimpleShape> shapes = new ArrayList<>();
        shapes.add(shape);
        //when
        group.setShapes(shapes);
        //then
        Assertions.assertTrue(group.getShapes().contains(shape));
    }

    @Test
    @DisplayName("Test fonction getShapes")
    void testGetShapes() {
        //given
        Group group = new Group();
        SimpleShape shape = new Circle(0, 0);
        ArrayList<SimpleShape> shapes = new ArrayList<>();
        shapes.add(shape);
        group.setShapes(shapes);
        //when
        List<SimpleShape> shapes2 = group.getShapes();
        //then
        Assertions.assertTrue(shapes2.contains(shape));
    }

    @Test
    @DisplayName("Test fonction visit JSON")
    void testVisitGroupJSON() {
        // Créez une instance de JSonVisitor
        JSonVisitor visitor = new JSonVisitor();

        // Créez une instance de Group
        Group group = new Group();

        // Ajoutez quelques formes au groupe
        group.addShape(new Circle(30, 30));
        group.addShape(new Square(40, 40));

        // Appellez la méthode visit
        visitor.visit(group);

        // Vérifiez que getRepresentation renvoie la représentation correcte
        List<SimpleShape> shapes = group.getShapes();
        int[][] coords = new int[shapes.size()][2];
        for (SimpleShape shape : shapes) {
            coords[shapes.indexOf(shape)][0] = shape.getX25();
            coords[shapes.indexOf(shape)][1] = shape.getY25();
        }

        String expectedRepresentation = "{\n\t\"group\": [\n\t\t{\n\t\t\t\"type\": \"circle\",\n\t\t\t\"x\": " + coords[0][0] + ",\n\t\t\"y\": " + coords[0][1] + "\n\t}\n\t\t{\n\t\t\t\"type\": \"square\",\n\t\t\t\"x\": " + coords[1][0] + ",\n\t\t\"y\": " + coords[1][1] + "\n\t}\n\t]\n}";
        assertEquals(expectedRepresentation, visitor.getRepresentation());
    }

    @Test
    @DisplayName("Test fonction visit XML")
    void testVisitGroupXML() {
        // Créez une instance de JSonVisitor
        XMLVisitor visitor = new XMLVisitor();

        // Créez une instance de Group
        Group group = new Group();

        // Ajoutez quelques formes au groupe
        group.addShape(new Circle(30, 30));
        group.addShape(new Square(40, 40));

        // Appellez la méthode visit
        visitor.visit(group);

        // Vérifiez que getRepresentation renvoie la représentation correcte
        List<SimpleShape> shapes = group.getShapes();
        int[][] coords = new int[shapes.size()][2];
        for (SimpleShape shape : shapes) {
            coords[shapes.indexOf(shape)][0] = shape.getX25();
            coords[shapes.indexOf(shape)][1] = shape.getY25();
        }

        String expectedRepresentation = "<group><shape><type>circle</type><x>" + coords[0][0] + "</x><y>" + coords[0][1] + "</y></shape><shape><type>square</type><x>" + coords[1][0] + "</x><y>" + coords[1][1] + "</y></shape></group>";
        assertEquals(expectedRepresentation, visitor.getRepresentation());
    }
}

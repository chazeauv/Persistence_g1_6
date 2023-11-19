package shapeTest;

import edu.uga.singleshape.CubePanel;
import fr.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import fr.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import fr.uga.miage.m1.polygons.gui.shapes.Cube;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CubeTest {
    @Test
    @DisplayName("Test XMLvisitor sur Cube")
    void testVisitorXML() {

        //given
        Cube cube = new Cube(50, 0, 0);
        XMLVisitor xmlVisitor = new XMLVisitor();
        //when
        cube.accept(xmlVisitor);
        //then
        assertEquals("<shape><type>cube</type><x>"+cube.getX()+"</x><y>"+cube.getY()+"</y></shape>", xmlVisitor.getRepresentation());
    }

    @Test
    @DisplayName("Test JSONvisitor sur Cube")
    void testVisitorJSON() {

        final String XY = ",\n\t\t\"y\": ";
        final String NT = "\n\t}";

        //given
        Cube cube = new Cube(50, 0, 0);
        JSonVisitor jsonVisitor = new JSonVisitor();
        //when
        cube.accept(jsonVisitor);
        //then
        assertEquals("{\n\t\t\"type\": \"cube\",\n\t\t\"x\": " + cube.getX() + XY + cube.getY() + NT, jsonVisitor.getRepresentation());
    }
}

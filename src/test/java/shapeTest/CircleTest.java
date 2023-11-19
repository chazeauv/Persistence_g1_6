package shapeTest;

import fr.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import fr.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import fr.uga.miage.m1.polygons.gui.shapes.Circle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.awt.geom.Ellipse2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CircleTest {
    @Test
    @DisplayName("Test XMLvisitor sur Circle")
    void testVisitorXML() {

        //given
        Circle circle = new Circle(0, 0);
        XMLVisitor xmlVisitor = new XMLVisitor();
        //when
        circle.accept(xmlVisitor);
        //then
        assertEquals("<shape><type>circle</type><x>"+circle.getX()+"</x><y>"+circle.getY()+"</y></shape>", xmlVisitor.getRepresentation());
    }

    @Test
    @DisplayName("Test JSONvisitor sur Circle")
    void testVisitorJSON() {

        final String XY = ",\n\t\t\"y\": ";
        final String NT = "\n\t}";

        //given
        Circle circle = new Circle(0, 0);
        JSonVisitor jsonVisitor = new JSonVisitor();
        //when
        circle.accept(jsonVisitor);
        //then
        assertEquals("{\n\t\t\"type\": \"circle\",\n\t\t\"x\": " + circle.getX() + XY + circle.getY() + NT, jsonVisitor.getRepresentation());
    }

    @Mock
    Graphics2D g;
    @Test
    @DisplayName("Test draw sur Circle")
    void testDraw() {

        //given
        Circle circle = new Circle(0, 0);
        g = mock(Graphics2D.class);
        //when
        circle.draw(g);
        //then
        verify(g).setColor(Color.black);
        verify(g).setStroke(new BasicStroke(2.0f));
        verify(g).draw(new Ellipse2D.Double(circle.getX(), circle.getY(), 50, 50));
    }

}

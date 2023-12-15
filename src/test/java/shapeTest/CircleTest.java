package shapeTest;

import fr.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import fr.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import fr.uga.miage.m1.polygons.gui.shapes.Circle;
import fr.uga.miage.m1.polygons.gui.shapes.Circle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CircleTest {
    @Test
    @DisplayName("Test XMLvisitor sur Circle (+ getters)")
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
    @DisplayName("Test JSONvisitor sur Circle (+ getters)")
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
    @DisplayName("Test draw sur Circle (+ getters)")
    void testDraw() {

        //given
        Circle circle = new Circle(0, 0);
        g = mock(Graphics2D.class);
        //when
        circle.draw(g);
        //then
        verify(g).setColor(new Color(0xFF585E));
        verify(g).draw(new Ellipse2D.Double(circle.getX(), circle.getY(), 50, 50));
    }

    @Test
    @DisplayName("Test drawWithBorder sur Circle")
    void testDrawWithBorder() {
        //given
        g = mock(Graphics2D.class);
        Circle circle = new Circle(30, 30);

        //when
        circle.drawWithBorder(g);

        //then
        verify(g, times(1)).setColor(new Color(0xFF585E));
        verify(g, times(1)).fill(any(Ellipse2D.Double.class));
        verify(g, times(1)).setStroke(new BasicStroke(2.0f));
        verify(g, times(1)).setColor(Color.BLACK);
        verify(g, times(1)).draw(any(Ellipse2D.Double.class));
    }

    @Test
    @DisplayName("Test setX sur Circle")
    void testSetX() {
        //given
        Circle circle = new Circle(0, 0);
        //when
        circle.setX(50);
        //then
        assertEquals(50, circle.getX());
    }

    @Test
    @DisplayName("Test setY sur Circle")
    void testSetY() {
        //given
        Circle circle = new Circle(0, 0);
        //when
        circle.setY(50);
        //then
        assertEquals(50, circle.getY());
    }

    @Test
    @DisplayName("Test getX sur Circle")
    void testContains() {
        //given
        Circle circle = new Circle(50, 30);

        //then
        assertTrue(circle.contains(50, 30), "Should return true for a point inside the circle");
        assertFalse(circle.contains(100, 100), "Should return false for a point outside the circle");
    }

}

package shapeTest;

import fr.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import fr.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import fr.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.mockito.Mock;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TriangleTest {

    @Test
    @DisplayName("Test XMLvisitor sur triangle (+ getters)")
    void testVisitorXML() {

        //given
        Triangle triangle = new Triangle(0, 0);
        XMLVisitor xmlVisitor = new XMLVisitor();
        //when
        triangle.accept(xmlVisitor);
        //then
        assertEquals("<shape><type>triangle</type><x>"+triangle.getX25()+"</x><y>"+triangle.getY25()+"</y></shape>", xmlVisitor.getRepresentation());
    }

    @Test
    @DisplayName("Test JSONvisitor sur Triangle (+ getters)")
    void testVisitorJSON() {

        final String XY = ",\n\t\t\"y\": ";
        final String NT = "\n\t}";

        //given
        Triangle triangle = new Triangle(0, 0);
        JSonVisitor jsonVisitor = new JSonVisitor();
        //when
        triangle.accept(jsonVisitor);
        //then
        assertEquals("{\n\t\t\"type\": \"triangle\",\n\t\t\"x\": " + triangle.getX25() + XY + triangle.getY25() + NT, jsonVisitor.getRepresentation());
    }

    @Mock
    Graphics2D g;

    @Test
    @DisplayName("Test draw sur Triangle")
    void testDraw() {

        //given
        Triangle triangle = new Triangle(0, 0);

        g = mock(Graphics2D.class);
        //when
        triangle.draw(g);
        //then
        verify(g).setColor(new Color(0x00E499));
    }

    @Test
    void testDrawWithBorder() {
        //given
        g = mock(Graphics2D.class);
        Triangle triangle = new Triangle(30, 30);

        //when
        triangle.drawWithBorder(g);

        //then
        verify(g, times(1)).setColor(new Color(0x00E499));
        verify(g, times(1)).fill(any(GeneralPath.class));
        verify(g, times(1)).setStroke(new BasicStroke(2.0f));
        verify(g, times(1)).setColor(Color.BLACK);
        verify(g, times(1)).draw(any(GeneralPath.class));
    }

    @Test
    @DisplayName("Test setX sur Triangle")
    void testSetX() {
        //given
        Triangle triangle = new Triangle(0, 0);
        //when
        triangle.setX(10);
        //then
        assertEquals(10, triangle.getX());
    }

    @Test
    @DisplayName("Test setY sur Triangle")
    void testSetY() {
        //given
        Triangle triangle = new Triangle(0, 0);
        //when
        triangle.setY(10);
        //then
        assertEquals(10, triangle.getY());
    }

    @Test
    @DisplayName("Test setPolygon sur Triangle")
    void testSetPolygon() {
        //given
        Triangle triangle = new Triangle(0, 0);
        //when
        triangle.setPolygon(new GeneralPath(Path2D.WIND_EVEN_ODD, 2));
        //then
        assertNotNull(triangle.getPolygon());
    }

    @Test
    @DisplayName("Test getPolygon sur Triangle")
    void testGetPolygon() {
        //given
        Triangle triangle = new Triangle(0, 0);
        //when
        triangle.setPolygon(new GeneralPath(Path2D.WIND_EVEN_ODD, 2));
        //then
        assertNotNull(triangle.getPolygon());
    }

    @Test
    @DisplayName("Test contains sur Triangle")
    void testContains() {
        //given
        Triangle triangle = new Triangle(50, 30);
        triangle.setPolygon(new GeneralPath(Path2D.WIND_EVEN_ODD, 2));

        //then
        //assertTrue(triangle.contains(50, 30), "Should return true for a point inside the triangle");
        assertFalse(triangle.contains(100, 100), "Should return false for a point outside the triangle");
    }

}

package shapeTest;

import fr.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import fr.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import fr.uga.miage.m1.polygons.gui.shapes.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SquareTest {

    @Test
    @DisplayName("Test XMLvisitor sur Square (+ getters)")
    void testVisitorXML() {

        //given
        Square square = new Square(0, 0);
        XMLVisitor xmlVisitor = new XMLVisitor();
        //when
        square.accept(xmlVisitor);
        //then
        assertEquals("<shape><type>square</type><x>"+square.getX()+"</x><y>"+square.getY()+"</y></shape>", xmlVisitor.getRepresentation());
    }

    @Test
    @DisplayName("Test JSONvisitor sur Square (+ getters)")
    void testVisitorJSON() {

        final String XY = ",\n\t\t\"y\": ";
        final String NT = "\n\t}";

        //given
        Square square = new Square(0, 0);
        JSonVisitor jsonVisitor = new JSonVisitor();
        //when
        square.accept(jsonVisitor);
        //then
        assertEquals("{\n\t\t\"type\": \"square\",\n\t\t\"x\": " + square.getX() + XY + square.getY() + NT, jsonVisitor.getRepresentation());
    }

    @Mock
    Graphics2D g;

    @Test
    @DisplayName("Test draw sur Square (+ getters)")
    void testDraw() {

        //given
        Square square = new Square(0, 0);
        g = mock(Graphics2D.class);
        //when
        square.draw(g);
        //then
        verify(g).setColor(new Color(0x5831FF));
        verify(g).draw(new Rectangle2D.Double(square.getX(), square.getY(), 50, 50));
    }

    @Test
    @DisplayName("Test drawWithBorder sur Square")
    void testDrawWithBorder() {
        //given
        g = mock(Graphics2D.class);
        Square square = new Square(30, 30);

        //when
        square.drawWithBorder(g);

        //then
        verify(g, times(1)).setColor(new Color(0x5831FF));
        verify(g, times(1)).fill(new Rectangle2D.Double(5, 5, 50, 50));
        verify(g, times(1)).setStroke(new BasicStroke(2.0f));
        verify(g, times(1)).setColor(Color.BLACK);
        verify(g, times(1)).draw(new Rectangle2D.Double(5, 5, 50, 50));
    }

    //Tests setters
    @Test
    @DisplayName("Test setX sur Square (+ getters)")
    void testSetX() {
        //given
        Square square = new Square(0, 0);
        //when
        square.setX(10);
        //then
        assertEquals(10, square.getX());
    }

    @Test
    @DisplayName("Test setY sur Square (+ getters)")
    void testSetY() {
        //given
        Square square = new Square(0, 0);
        //when
        square.setY(10);
        //then
        assertEquals(10, square.getY());
    }

    @Test
    @DisplayName("Test sur le contains de Square")
    void testContains() {
        //given
        Square square = new Square(50, 30);

        //then
        assertTrue(square.contains(51, 36), "Should return true for a point inside the square");
        assertFalse(square.contains(100, 100), "Should return false for a point outside the square");
    }
}
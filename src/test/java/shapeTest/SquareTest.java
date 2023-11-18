package shapeTest;

import fr.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import fr.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import fr.uga.miage.m1.polygons.gui.shapes.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SquareTest {

    @Test
    @DisplayName("Test XMLvisitor sur Square")
    public void testVisitorXML() {

        //given
        Square square = new Square(0, 0);
        XMLVisitor xmlVisitor = new XMLVisitor();
        //when
        square.accept(xmlVisitor);
        //then
        assertEquals("<shape><type>square</type><x>"+square.getX()+"</x><y>"+square.getY()+"</y></shape>", xmlVisitor.getRepresentation());
    }

    @Test
    @DisplayName("Test JSONvisitor sur Square")
    public void testVisitorJSON() {

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
    @DisplayName("Test draw sur Square")
    public void testDraw() {

        //given
        Square square = new Square(0, 0);
        g = mock(Graphics2D.class);
        //when
        square.draw(g);
        //then
        verify(g).setColor(Color.black);
        verify(g).setStroke(new BasicStroke(2.0f));
        verify(g).draw(new Rectangle2D.Double(square.getX(), square.getY(), 50, 50));
    }
}
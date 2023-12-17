package shapeTest;

import fr.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import fr.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import fr.uga.miage.m1.polygons.gui.shapes.Cube;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CubeTest {
    @Test
    @DisplayName("Test XMLvisitor sur Cube (+ getters)")
    void testVisitorXML() {

        //given
        Cube cube = new Cube(50, 0, 0);
        XMLVisitor xmlVisitor = new XMLVisitor();
        //when
        cube.accept(xmlVisitor);
        //then
        assertEquals("<shape><type>cube</type><x>"+cube.getX25()+"</x><y>"+cube.getY25()+"</y></shape>", xmlVisitor.getRepresentation());
    }

    @Test
    @DisplayName("Test JSONvisitor sur Cube (+ getters)")
    void testVisitorJSON() {

        final String XY = ",\n\t\t\"y\": ";
        final String NT = "\n\t}";

        //given
        Cube cube = new Cube(50, 0, 0);
        JSonVisitor jsonVisitor = new JSonVisitor();
        //when
        cube.accept(jsonVisitor);
        //then
        assertEquals("{\n\t\t\"type\": \"cube\",\n\t\t\"x\": " + cube.getX25() + XY + cube.getY25() + NT, jsonVisitor.getRepresentation());
    }

    @Test
    @DisplayName("Test setX sur Cube")
    void testSetX() {
        //given
        Cube cube = new Cube(50, 0, 0);
        //when
        cube.setX(100);
        //then
        assertEquals(100, cube.getX());
    }

    @Test
    @DisplayName("Test setY sur Cube")
    void testSetY() {
        //given
        Cube cube = new Cube(50, 0, 0);
        //when
        cube.setY(100);
        //then
        assertEquals(100, cube.getY());
    }

    /*@Mock
    Graphics2D g;
    @Test
    @DisplayName("Test draw sur Cube")

    void testDraw() {
        //given
        Cube cube = new Cube(50, 0, 0);
        g = mock(Graphics2D.class);
        //when
        cube.draw(g);
        //then
        verify(g).setColor(new Color(0x5831FF));
    }*/

    @Mock
    Graphics2D g;
    @Test
    @DisplayName("Test draw sur Cube")
    void testDraw() {
        //given
        g = mock(Graphics2D.class);
        Cube cube = new Cube(50, 30, 30);

        //when
        when(g.create()).thenReturn(mock(Graphics.class));
        cube.draw(g);

        //then
        verify(g).setStroke(new BasicStroke(2.0F));
        verify(g, times(12)).drawLine(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    @DisplayName("Test drawWithBorder sur Cube")
    void testDrawWithBorder() {
        //given
        g = mock(Graphics2D.class);
        Cube cube = new Cube(50, 30, 30);

        //when
        when(g.create()).thenReturn(mock(Graphics.class));
        cube.drawWithBorder(g);

        //then
        verify(g).setStroke(new BasicStroke(2.0F));
        verify(g, times(12)).drawLine(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    void testContains() {
        //given
        Cube cube = new Cube(50, 30, 30);

        //then
        assertTrue(cube.contains(35, 35), "Should return true for a point inside the cube");
        assertFalse(cube.contains(100, 100), "Should return false for a point outside the cube");
    }
}

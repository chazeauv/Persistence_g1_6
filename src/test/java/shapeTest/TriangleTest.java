package shapeTest;

import fr.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import fr.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import fr.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TriangleTest {

    @Test
    @DisplayName("Test XMLvisitor sur triangle")
    public void testVisitorXML() {

        //given
        Triangle triangle = new Triangle(0, 0);
        XMLVisitor xmlVisitor = new XMLVisitor();
        //when
        triangle.accept(xmlVisitor);
        //then
        assertEquals("<shape><type>triangle</type><x>"+triangle.getX()+"</x><y>"+triangle.getX()+"</y></shape>", xmlVisitor.getRepresentation());
    }

    @Test
    @DisplayName("Test JSONvisitor sur Triangle")
    public void testVisitorJSON() {

        final String XY = ",\n\t\t\"y\": ";
        final String NT = "\n\t}";

        //given
        Triangle triangle = new Triangle(0, 0);
        JSonVisitor jsonVisitor = new JSonVisitor();
        //when
        triangle.accept(jsonVisitor);
        //then
        assertEquals("{\n\t\t\"type\": \"triangle\",\n\t\t\"x\": " + triangle.getX() + XY + triangle.getY() + NT, jsonVisitor.getRepresentation());
    }

    @Mock
    Graphics2D g;
    @Test
    @DisplayName("Test draw sur Triangle")
    public void testDraw() {

        //given
        Triangle triangle = new Triangle(0, 0);
        int[] xcoords = {triangle.getX() + 25, triangle.getX(), triangle.getX() + 50 };
        int[] ycoords = {triangle.getY(), triangle.getY() + 50, triangle.getY() + 50 };

        /*GeneralPath polygon = new GeneralPath(Path2D.WIND_EVEN_ODD, xcoords.length);
        polygon.moveTo(triangle.getX()+25, triangle.getY());
        for (int i = 0; i < xcoords.length; i++) {
            polygon.lineTo(xcoords[i], ycoords[i]);
        }
        polygon.closePath();*/

        g = mock(Graphics2D.class);
        //when
        triangle.draw(g);
        //then
        verify(g).setColor(Color.black);
        verify(g).setStroke(new BasicStroke(2.0f));
        //verify(g).draw(new Rectangle2D.Double(mX, mY, 50, 50));
    }
}

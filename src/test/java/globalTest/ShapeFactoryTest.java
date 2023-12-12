import fr.uga.miage.m1.polygons.gui.ShapeFactory;
import fr.uga.miage.m1.polygons.gui.shapes.Circle;
import fr.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import fr.uga.miage.m1.polygons.gui.shapes.Square;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class ShapeFactoryTest {
    /*@Test
    @DisplayName("Test fonction createShapeFromStr")
    public void testCreateShapeFromStr() {
        //given
        ShapeFactory factory = ShapeFactory.getInstance();
        //when
        SimpleShape shape = factory.createShapeFromStr("rectangle", 10, 20);
        //then
        assertTrue(shape instanceof Square);
        assertEquals(10, shape.getX());
        assertEquals(20, shape.getY());
    }

    @Test
    @DisplayName("Test fonction createShapeFromShapes")
    public void testCreateShapeFromShapes() {
        //given
        ShapeFactory factory = ShapeFactory.getInstance();
        //when
        SimpleShape shape = factory.createShapeFromShapes(ShapeFactory.Shapes.CIRCLE, 30, 40);
        //then
        assertTrue(shape instanceof Circle);
        assertEquals(30, shape.getX());
        assertEquals(40, shape.getY());
    }*/

    @Test
    public void testCreateShapeFromStr() {
        ShapeFactory factory = ShapeFactory.getInstance();
        assertNotNull(factory.createShapeFromStr("rectangle", 10, 10));
        assertNotNull(factory.createShapeFromStr("circle", 10, 10));
        assertNotNull(factory.createShapeFromStr("triangle", 10, 10));
        assertNotNull(factory.createShapeFromStr("cube", 10, 10));
        assertNull(factory.createShapeFromStr("unknown", 10, 10));
    }

    @Test
    public void testCreateShapeFromShapes() {
        ShapeFactory factory = ShapeFactory.getInstance();
        assertNotNull(factory.createShapeFromShapes(ShapeFactory.Shapes.SQUARE, 10, 10));
        assertNotNull(factory.createShapeFromShapes(ShapeFactory.Shapes.CIRCLE, 10, 10));
        assertNotNull(factory.createShapeFromShapes(ShapeFactory.Shapes.TRIANGLE, 10, 10));
        assertNotNull(factory.createShapeFromShapes(ShapeFactory.Shapes.CUBE, 10, 10));
    }

    @Test
    @DisplayName("Test fonction getInstance")
    public void testGetInstance() {
        //given
        ShapeFactory factory = ShapeFactory.getInstance();
        //when
        ShapeFactory factory2 = ShapeFactory.getInstance();
        //then
        assertSame(factory, factory2);
    }
}


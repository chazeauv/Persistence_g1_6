package globalTest;

import fr.uga.miage.m1.polygons.gui.ShapeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

class ShapeFactoryTest {
    /*@Test
    @DisplayName("Test fonction createShapeFromStr")
    void testCreateShapeFromStr() {
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
    void testCreateShapeFromShapes() {
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
    void testCreateShapeFromStr() {
        ShapeFactory factory = ShapeFactory.getInstance();
        Assertions.assertNotNull(factory.createShapeFromStr("square", 10, 10));
        Assertions.assertNotNull(factory.createShapeFromStr("circle", 10, 10));
        Assertions.assertNotNull(factory.createShapeFromStr("triangle", 10, 10));
        Assertions.assertNotNull(factory.createShapeFromStr("cube", 10, 10));
        Assertions.assertNull(factory.createShapeFromStr("unknown", 10, 10));
    }

    @Test
    void testCreateShapeFromShapes() {
        ShapeFactory factory = ShapeFactory.getInstance();
        Assertions.assertNotNull(factory.createShapeFromShapes(ShapeFactory.Shapes.SQUARE, 10, 10));
        Assertions.assertNotNull(factory.createShapeFromShapes(ShapeFactory.Shapes.CIRCLE, 10, 10));
        Assertions.assertNotNull(factory.createShapeFromShapes(ShapeFactory.Shapes.TRIANGLE, 10, 10));
        Assertions.assertNotNull(factory.createShapeFromShapes(ShapeFactory.Shapes.CUBE, 10, 10));
    }

    @Test
    @DisplayName("Test fonction getInstance")
    void testGetInstance() {
        //given
        ShapeFactory factory = ShapeFactory.getInstance();
        //when
        ShapeFactory factory2 = ShapeFactory.getInstance();
        //then
        Assertions.assertSame(factory, factory2);
    }
}


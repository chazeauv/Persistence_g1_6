package fr.uga.miage.m1.polygons.gui;

import fr.uga.miage.m1.polygons.gui.shapes.Circle;
import fr.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import fr.uga.miage.m1.polygons.gui.shapes.Square;
import fr.uga.miage.m1.polygons.gui.shapes.Triangle;

import java.awt.*;

public class ShapeFactory {

    public enum Shapes {

        SQUARE, TRIANGLE, CIRCLE, CUBE
    }
    private static ShapeFactory singleton;

    private ShapeFactory() {
    }

    public static ShapeFactory getInstance(){
        if (singleton == null) {
            singleton = new ShapeFactory();
        }
        return singleton;
    }

    public SimpleShape createShapeFromStr(String shapeType, int x, int y) {
        SimpleShape shape = null;
        switch (shapeType) {
            case "rectangle":
                shape = new Square(x, y);
                break;
            case "circle":
                shape = new Circle(x, y);
                break;
            case "triangle":
                shape = new Triangle(x, y);
                break;
            default:
                break;
        }
        return shape;
    }

    public SimpleShape createShapeFromShapes(Shapes shapeType, int x, int y) {
        SimpleShape shape = null;
        switch (shapeType) {
            case SQUARE:
                shape = new Square(x, y);
                break;
            case CIRCLE:
                shape = new Circle(x, y);
                break;
            case TRIANGLE:
                shape = new Triangle(x, y);
                break;
            default:
                break;
        }
        return shape;
    }
}

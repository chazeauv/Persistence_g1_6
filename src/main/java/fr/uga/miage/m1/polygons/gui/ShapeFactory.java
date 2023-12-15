package fr.uga.miage.m1.polygons.gui;

import fr.uga.miage.m1.polygons.gui.shapes.*;

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

    public static SimpleShape createShapeFromStr(String shapeType, int x, int y) {
        SimpleShape shape = null;
        switch (shapeType) {
            case "square":
                shape = new Square(x, y);
                break;
            case "circle":
                shape = new Circle(x, y);
                break;
            case "triangle":
                shape = new Triangle(x, y);
                break;
            case "cube":
                shape = new Cube(50, x, y);
                break;
            default:
                break;
        }
        return shape;
    }

    public static SimpleShape createShapeFromShapes(Shapes shapeType, int x, int y) {
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
            case CUBE:
                shape = new Cube(50, x, y);
                break;
            default:
                break;
        }
        return shape;
    }
}

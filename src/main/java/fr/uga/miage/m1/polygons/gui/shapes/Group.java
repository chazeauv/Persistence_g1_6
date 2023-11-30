package fr.uga.miage.m1.polygons.gui.shapes;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private ArrayList<SimpleShape> shapes;

    public Group() {}
    public Group(List<SimpleShape> shapes) {
        this.shapes = new ArrayList<>(shapes);
    }

    public ArrayList<SimpleShape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<SimpleShape> shapes) {
        this.shapes = shapes;
    }

    public void addShape(SimpleShape shape) {
        shapes.add(shape);
    }

    public void removeShape(SimpleShape shape) {
        shapes.remove(shape);
    }

    public boolean contains(int x1, int y1, int x2, int y2) {

        int shapeX;
        int shapeY;

        for (SimpleShape shape : shapes) {
            shapeX = shape.getX();
            shapeY = shape.getY();

            if(x1 > x2) {
                if(shapeX >= x2 && shapeX <= x1) {
                    if(y1 > y2) {
                        if(shapeY >= y2 && shapeY <= y1) {
                            return true;
                        }
                    } else {
                        if(shapeY >= y1 && shapeY <= y2) {
                            return true;
                        }
                    }
                }
            } else {
                if(shapeX >= x1 && shapeX <= x2) {
                    if(y1 > y2) {
                        if(shapeY >= y2 && shapeY <= y1) {
                            return true;
                        }
                    } else {
                        if(shapeY >= y1 && shapeY <= y2) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}

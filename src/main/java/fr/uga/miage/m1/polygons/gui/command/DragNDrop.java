package fr.uga.miage.m1.polygons.gui.command;

import fr.uga.miage.m1.polygons.gui.JDrawingFrame;
import fr.uga.miage.m1.polygons.gui.shapes.SimpleShape;

public class DragNDrop implements Command{

    private final JDrawingFrame drawing;

    private SimpleShape shape;

    private final int evtX;
    private final int evtY;

    private int newX;
    private int newY;

    public DragNDrop(JDrawingFrame drawing, SimpleShape shape) {

        this.drawing = drawing;
        this.shape = shape;
        evtX = shape.getX();
        evtY = shape.getY();
    }

    public void execute() {

        drawing.moveShape(shape, shape.getX() + newX, shape.getY() + newY);
    }

    public void undo() {

        drawing.moveShape(shape, evtX, evtY);
    }

    public void setNewX(int x) {
        newX = x;
    }

    public void setNewY(int y) {
        newY = y;
    }
}

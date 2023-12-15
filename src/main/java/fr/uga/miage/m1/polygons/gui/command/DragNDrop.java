package fr.uga.miage.m1.polygons.gui.command;

import fr.uga.miage.m1.polygons.gui.JDrawingFrame;
import fr.uga.miage.m1.polygons.gui.shapes.Group;
import fr.uga.miage.m1.polygons.gui.shapes.SimpleShape;

public class DragNDrop implements Command{

    private final JDrawingFrame drawing;

    private SimpleShape shape;

    private Group group = null;

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

    public boolean execute() {

        if(group != null) {
            drawing.moveGroup(group, shape,shape.getX() + newX, shape.getY() + newY);
        } else {
            drawing.moveShape(shape, shape.getX() + newX, shape.getY() + newY);
        }

        this.drawing.paintComponents(this.drawing.getGraphics());

        return true;
    }

    public boolean undo() {

        drawing.moveShape(shape, evtX, evtY);
        return true;
    }

    public SimpleShape getShape() {
        return shape;
    }

    public void setNewX(int x) {
        newX = x;
    }

    public void setNewY(int y) {
        newY = y;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}

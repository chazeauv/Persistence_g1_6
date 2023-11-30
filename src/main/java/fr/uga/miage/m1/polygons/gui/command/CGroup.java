package fr.uga.miage.m1.polygons.gui.command;

import fr.uga.miage.m1.polygons.gui.JDrawingFrame;
import fr.uga.miage.m1.polygons.gui.shapes.Group;
import fr.uga.miage.m1.polygons.gui.shapes.SimpleShape;

import java.util.List;

public class CGroup implements Command {

    private final JDrawingFrame drawing;

    private Group group;

    private int x1, y1, x2, y2;

    public CGroup(JDrawingFrame drawing) {

        this.drawing = drawing;
        this.group = new Group(drawing.getShapes());
    }

    public CGroup(JDrawingFrame drawing, int x1, int y1) {
        this.drawing = drawing;
        this.x1 = x1;
        this.y1 = y1;
        this.group = new Group(drawing.getShapes());
    }

    @Override
    public void execute() { drawing.instantiateGroup(group, x1, y1, x2, y2); }

    @Override
    public void undo() { }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}

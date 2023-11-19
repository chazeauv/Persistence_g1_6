package fr.uga.miage.m1.polygons.gui.command;

import fr.uga.miage.m1.polygons.gui.JDrawingFrame;

public class Drag implements Command{

    private final JDrawingFrame drawing;
    private final int evtX;
    private final int evtY;

    public Drag(JDrawingFrame drawing, int x, int y) {

        this.drawing = drawing;
        evtX = x;
        evtY = y;
    }

    @Override
    public void execute() { drawing.drag(evtX, evtY); }

    @Override
    public void undo() { drawing.undoDrag(); }
}

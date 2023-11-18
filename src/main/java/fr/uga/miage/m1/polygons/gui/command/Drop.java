package fr.uga.miage.m1.polygons.gui.command;

import fr.uga.miage.m1.polygons.gui.JDrawingFrame;

public class Drop implements Command{

    private final JDrawingFrame drawing;
    private final int evtX;
    private final int evtY;

    public Drop(JDrawingFrame drawing, int x, int y) {

        this.drawing = drawing;
        evtX = x;
        evtY = y;
    }

    @Override
    public void execute() { drawing.drop(evtX, evtY); }

    @Override
    public void undo() { drawing.undoDrop(); }
}

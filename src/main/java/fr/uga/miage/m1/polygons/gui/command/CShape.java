package fr.uga.miage.m1.polygons.gui.command;

import fr.uga.miage.m1.polygons.gui.JDrawingFrame;
import fr.uga.miage.m1.polygons.gui.shapes.SimpleShape;

public class CShape implements Command{

        private final JDrawingFrame drawing;

        private int x;

        private int y;

        public CShape(JDrawingFrame drawing, int x, int y) {

                this.drawing = drawing;
                this.x = x;
                this.y = y;
        }

        @Override
        public void execute() { drawing.instantiateShape(x, y); }

        @Override
        public void undo() { drawing.undoShape(); }
}

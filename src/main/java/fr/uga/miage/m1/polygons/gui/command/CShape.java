package fr.uga.miage.m1.polygons.gui.command;

import fr.uga.miage.m1.polygons.gui.JDrawingFrame;
import fr.uga.miage.m1.polygons.gui.shapes.SimpleShape;

public class CShape implements Command{

        private final JDrawingFrame drawing;

        private SimpleShape shape;

        private int x;

        private int y;

        public CShape(JDrawingFrame drawing, SimpleShape shape, int x, int y) {

                this.drawing = drawing;
                this.shape = shape;
                this.x = x;
                this.y = y;
        }

        @Override
        public void execute() { drawing.instantiateShape(shape); }

        @Override
        public void undo() { drawing.undoShape(); }
}

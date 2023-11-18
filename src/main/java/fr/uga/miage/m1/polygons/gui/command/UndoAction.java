package fr.uga.miage.m1.polygons.gui.command;

import fr.uga.miage.m1.polygons.gui.JDrawingFrame;

public class UndoAction implements Command{

        private final JDrawingFrame drawing;

        public UndoAction(JDrawingFrame drawing) {
            this.drawing = drawing;
        }

        @Override
        public void execute() {
            drawing.undoShape();
        }

        @Override
        public void undo() {
            //drawing.undoAction();
        }
}

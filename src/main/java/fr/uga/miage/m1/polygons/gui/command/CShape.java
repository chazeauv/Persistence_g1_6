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
        public boolean execute() {
                drawing.instantiateShape(shape);
                return true;
        }

        @Override
        public boolean undo() {
                drawing.undoShape();
                return true;
        }

        public JDrawingFrame getDrawing() {
                return drawing;
        }

        public SimpleShape getShape() {
                return shape;
        }

        public void setShape(SimpleShape shape) {
                this.shape = shape;
        }

        public int getX() {
                return x;
        }

        public void setX(int x) {
                this.x = x;
        }

        public int getY() {
                return y;
        }

        public void setY(int y) {
                this.y = y;
        }

}

package fr.uga.miage.m1.polygons.gui.shapes;

public abstract class AbstractSimpleShape implements SimpleShape {

    private int mX;
    private int mY;

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public void setX(int mX) {
        this.mX=mX;
    }

    public void setY(int mY) {
        this.mY=mY;
    }
}

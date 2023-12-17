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

    public int getX25() {
        return mX + 25;
    }

    public int getY25() {
        return mY + 25;
    }

    public void setX(int mX) {
        this.mX=mX;
    }

    public void setY(int mY) {
        this.mY=mY;
    }
}

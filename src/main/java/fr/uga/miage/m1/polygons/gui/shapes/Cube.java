package fr.uga.miage.m1.polygons.gui.shapes;

import edu.uga.singleshape.CubePanel;

import java.awt.*;

public class Cube implements SimpleShape{

    private int mX;
    private int mY;
    public Cube(int mX,int mY){
        this.mX = mX;
        this.mY = mY;
    }
    @Override
    public void draw(Graphics2D g2) {
        CubePanel c = new CubePanel(100,mX,mY);
        c.paintComponent(g2);
    }

    @Override
    public int getX() {
        return mX;
    }
    @Override
    public int getY() {
        return mY;
    }
    @Override
    public boolean contains(int x, int y) {
        return false;
    }
}

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package fr.uga.miage.m1.polygons.gui.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import fr.uga.miage.m1.polygons.gui.persistence.Visitable;
import fr.uga.miage.m1.polygons.gui.persistence.Visitor;

/**
 * This class implements the square <tt>SimpleShape</tt> extension.
 * It simply provides a <tt>draw()</tt> that paints a square.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class Square implements SimpleShape, Visitable {

    int mX;

    int mY;

    public Square(int x, int y) {
        mX = x - 25;
        mY = y - 25;
    }

    /**
     * Implements the <tt>SimpleShape.draw()</tt> method for painting
     * the shape.
     * @param g2 The graphics object used for painting.
     */
    public void draw(Graphics2D g2) {
        int mX50 = mX + 50;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(mX, mY, Color.BLUE, mX50, mY, Color.WHITE);
        g2.setPaint(gradient);
        g2.fill(new Rectangle2D.Double(mX, mY, 50, 50));
        BasicStroke wideStroke = new BasicStroke(2.0f);
        g2.setColor(Color.black);
        g2.setStroke(wideStroke);
        g2.draw(new Rectangle2D.Double(mX, mY, 50, 50));
    }

    /**
     * Implements the <tt>SimpleShape.accept()</tt> method for the visitor
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Implements the <tt>SimpleShape.getX()</tt> method for the visitor
     * @return int
     */
    public int getX() {
        return mX;
    }

    /**
     * Implements the <tt>SimpleShape.getY()</tt> method for the visitor
     * @return int
     */
    public int getY() {
        return mY;
    }

    /**
     * Implements the <tt>SimpleShape.setX()</tt> method for the visitor
     * @param x
     */
    public void setX(int x) {
        mX = x;
    }

    /**
     * Implements the <tt>SimpleShape.setY()</tt> method for the visitor
     * @param y
     */
    public void setY(int y) {
        mY = y;
    }

    public boolean contains(int x, int y) { return new Rectangle2D.Double(mX, mY, 50, 50).contains(x, y); }
}

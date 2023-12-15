package fr.uga.miage.m1.polygons.gui.shapes;

import fr.uga.miage.m1.polygons.gui.persistence.Visitable;
import fr.uga.miage.m1.polygons.gui.persistence.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Group implements Visitable {

    private ArrayList<SimpleShape> shapes;

    public Group() {this.shapes = new ArrayList<>();}

    public List<SimpleShape> getShapes() {
        return shapes;
    }

    public void setShapes(List<SimpleShape> shapes) {
        this.shapes = (ArrayList<SimpleShape>) shapes;
    }

    public void addShape(SimpleShape shape) {
        shapes.add(shape);
    }

    public void removeShape(SimpleShape shape) {
        shapes.remove(shape);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

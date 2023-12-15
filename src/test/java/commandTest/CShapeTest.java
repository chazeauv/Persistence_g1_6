package commandTest;

import fr.uga.miage.m1.polygons.gui.Client;
import fr.uga.miage.m1.polygons.gui.command.CShape;
import fr.uga.miage.m1.polygons.gui.command.CommandControl;
import fr.uga.miage.m1.polygons.gui.shapes.Circle;
import fr.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

class CShapeTest {

    @Test
    @DisplayName("Test constructeur")
    void testConstructeur() {
        //given
        CommandControl commandControl = new CommandControl();
        Client cli = new Client("Polygons");
        SimpleShape circle = new Circle(0, 0);
        CShape command;
        //when
        command = new CShape(cli.getFrame(), circle, circle.getX(), circle.getY());
        //then
        Assertions.assertEquals(command.getShape(), circle);
    }
}

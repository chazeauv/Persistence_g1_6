import fr.uga.miage.m1.polygons.gui.JDrawingFrame;
import fr.uga.miage.m1.polygons.gui.shapes.Circle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JDrawingFrameTests {
/*
    @Test
    @DisplayName("Test addShapeToTable")
    public void addShapeToTable(){
        //given
        JDrawingFrame frame = new JDrawingFrame("JDrawTest");
        ArrayList<String[]> mShapeTest = new ArrayList<String[]>();

        String[] shapeTest = {"square", "0", "0"};
        mShapeTest.add(shapeTest);

        //when
        frame.addShapeToTable("square", 0, 0);
        //then
        String mShapeTestStr = "";
        String frameStr = "";

        for(String[] mSTElt : mShapeTest){
            mShapeTestStr += mSTElt[0] + mSTElt[1] + mSTElt[2];
        }

        for(String[] frameElt : frame.getShapes()){
            frameStr += frameElt[0] + frameElt[1] + frameElt[2];
        }

        assertEquals(mShapeTestStr, frameStr);
    }*/

/*    @Test
    @DisplayName("Test mouseClicked")
    public void testMouseClicked() {
        // Créer un objet `JDrawingFrame`
        JDrawingFrame frame = new JDrawingFrame("JDrawTest");

        // Sélectionner la forme "CIRCLE" dans le menu déroulant
        Circle circle = new Circle(0, 0);
        frame.setSelected(CIRCLE);

        // Ajouter la fenêtre au gestionnaire d'événements du panneau de dessin
        JPanel panel = frame.getPanel();
        panel.addMouseListener(frame);

        // Simuler un clic de souris sur le panneau de dessin
        panel.dispatchEvent(new MouseEvent(panel, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 1, false, MouseEvent.BUTTON1));

        // Vérifier qu'un cercle a été ajouté au tableau de formes
        ArrayList<String[]> shapes = frame.getShapes();
        assertEquals(1, shapes.size());
    }*/

}

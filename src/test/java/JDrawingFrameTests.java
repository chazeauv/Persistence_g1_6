import fr.uga.miage.m1.polygons.gui.Client;
import fr.uga.miage.m1.polygons.gui.JDrawingFrame;
import fr.uga.miage.m1.polygons.gui.ShapeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class JDrawingFrameTests {

    private JDrawingFrame frame;

    @BeforeEach
    void init() {
        Client client = new Client("Test");
        frame = client.getFrame();
    }

    @Test
    @DisplayName("Test sur la création de la frame")
    void testJDrawingFrame() {
        assertNotNull(frame);
    }

    @Test
    @DisplayName("Test sur le titre de la frame")
    public void testJDrawingFrameTitle() {
        assertEquals("Test", frame.getTitle());
    }


}

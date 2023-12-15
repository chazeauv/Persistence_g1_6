package globalTest;

import fr.uga.miage.m1.polygons.gui.Client;
import fr.uga.miage.m1.polygons.gui.JDrawingFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JDrawingFrameTests {

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
    void testJDrawingFrameTitle() {
        assertEquals("Test", frame.getTitle());
    }


}

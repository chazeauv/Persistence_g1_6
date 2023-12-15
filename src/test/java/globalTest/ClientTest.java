package globalTest;

import fr.uga.miage.m1.polygons.gui.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.MouseEvent;

import static org.mockito.Mockito.*;

class ClientTest {

    private Client client;
    private MouseEvent mouseEvent;

    @BeforeEach
    void setUp() {
        client = new Client("Test");
        mouseEvent = Mockito.mock(MouseEvent.class);
    }

    @Test
    @DisplayName("Test mouseClicked")
    void testMouseClicked() {
        //when
        when(mouseEvent.getX()).thenReturn(10);
        when(mouseEvent.getY()).thenReturn(10);

        client.mouseClicked(mouseEvent);

        //then
        verify(mouseEvent, times(1)).getX();
        verify(mouseEvent, times(1)).getY();
    }

    @Test
    @DisplayName("Test mousePressed")
    void testMousePressed() {
        //when
        when(mouseEvent.getX()).thenReturn(10);
        when(mouseEvent.getY()).thenReturn(10);

        client.mousePressed(mouseEvent);

        //then
        verify(mouseEvent, times(1)).getX();
        verify(mouseEvent, times(1)).getY();
    }

    @Test
    @DisplayName("Test mouseReleased")
    void testMouseReleased() {
        //when
        client.mouseReleased(mouseEvent);

        //then
        verify(mouseEvent, never()).getX();
        verify(mouseEvent, never()).getY();
    }

    /*@Test
    @DisplayName("Test mouseExited")
    void testMouseExited() {
        //when
        client.mouseExited(mouseEvent);

        //then
        assertTrue(" ".equals(client.getFrame().getLabel()));
    }*/

    @Test
    @DisplayName("Test mouseDragged")
    void testMouseDragged() {
        //when
        when(mouseEvent.getX()).thenReturn(10);
        when(mouseEvent.getY()).thenReturn(10);

        client.mouseDragged(mouseEvent);

        //then
        verify(mouseEvent, times(1)).getX();
        verify(mouseEvent, times(1)).getY();
    }
}

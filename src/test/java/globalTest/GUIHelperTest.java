import fr.uga.miage.m1.polygons.gui.Client;
import fr.uga.miage.m1.polygons.gui.GUIHelper;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

class GUIHelperTest {
    @Test
    void testShowOnFrame() {
        //given
        Client cli = new Client("test");
        //when
        GUIHelper.showOnFrame(cli);
        //then
        assertTrue(cli.getFrame().isVisible());
    }
}


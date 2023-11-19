package commandTest;

import fr.uga.miage.m1.polygons.gui.Client;
import fr.uga.miage.m1.polygons.gui.JDrawingFrame;
import fr.uga.miage.m1.polygons.gui.command.CShape;
import fr.uga.miage.m1.polygons.gui.command.CommandControl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandControlTest {

    @Test
    @DisplayName("Test addCommand")
    void testAddCommand() {

        //given
        CommandControl commandControl = new CommandControl();
        Client cli = new Client("Polygons");
        CShape command = new CShape(cli.getFrame(), null, 0, 0);
        //when
        commandControl.addCommand(command);
        //then
        assert(commandControl.getCommands().contains(command));
    }

    @Test
    @DisplayName("Test removeCommand")
    void testRemoveCommand() {

        //given
        CommandControl commandControl = new CommandControl();
        Client cli = new Client("Polygons");
        CShape command = new CShape(cli.getFrame(), null, 0, 0);
        //when
        commandControl.addCommand(command);
        commandControl.removeCommand(command);
        //then
        assert(!commandControl.getCommands().contains(command));
    }

    //@Mock
    CShape cShape;
    @Test
    @DisplayName("Test executeCommands")
    void testExecuteCommands() {

        //given
        CommandControl commandControl = new CommandControl();
        Client cli = new Client("Polygons");
        cShape = mock(CShape.class);
        //when
        commandControl.addCommand(cShape);
        commandControl.executeCommands();
        //then
        verify(cShape).execute();
    }
}

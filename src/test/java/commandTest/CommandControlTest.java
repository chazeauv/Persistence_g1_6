package commandTest;

import fr.uga.miage.m1.polygons.gui.JDrawingFrame;
import fr.uga.miage.m1.polygons.gui.command.CommandControl;
import fr.uga.miage.m1.polygons.gui.command.UndoAction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandControlTest {

    @Test
    @DisplayName("Test addCommand")
    public void testAddCommand() {

        //given
        CommandControl commandControl = new CommandControl();

        JDrawingFrame drawing = new JDrawingFrame("TestAddCommand");
        UndoAction undoAction = new UndoAction(drawing);
        //when
        commandControl.addCommand(undoAction);
        //then
        assert(commandControl.getCommands().contains(undoAction));
    }

    @Test
    @DisplayName("Test removeCommand")
    public void testRemoveCommand() {

        //given
        CommandControl commandControl = new CommandControl();

        JDrawingFrame drawing = new JDrawingFrame("TestRemoveCommand");
        UndoAction undoAction = new UndoAction(drawing);
        //when
        commandControl.addCommand(undoAction);
        commandControl.removeCommand(undoAction);
        //then
        assert(!commandControl.getCommands().contains(undoAction));
    }

    @Mock
    UndoAction undoAction;
    @Test
    @DisplayName("Test executeCommands")
    void testExecuteCommands() {

        //given
        CommandControl commandControl = new CommandControl();

        JDrawingFrame drawing = new JDrawingFrame("TestExecuteCommands");
        undoAction = mock(UndoAction.class);
        //when
        commandControl.addCommand(undoAction);
        commandControl.executeCommands();
        //then
        verify(undoAction).execute();
    }
}

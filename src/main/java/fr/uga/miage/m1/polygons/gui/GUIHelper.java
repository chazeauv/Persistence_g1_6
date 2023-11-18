package fr.uga.miage.m1.polygons.gui;

import fr.uga.miage.m1.polygons.gui.command.*;

import java.awt.event.*;
import javax.swing.*;

/**
 *  @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class GUIHelper {

    private static CommandControl commandControl;
    GUIHelper() {
    }

    public static void showOnFrame(String frameName){

        JDrawingFrame frame = new JDrawingFrame(frameName);

        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "undo");
        frame.getRootPane().getActionMap().put("undo", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                commandControl = frame.getCommandControl();

                commandControl.addCommands();
                commandControl.undoCommands();
                //TODO : del last command from history
            }
        });

        //add command to commandhistory when mouseclicked
        /*frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(MouseEvent.MOUSE_CLICKED, 0), "mouseclicked");
        frame.getRootPane().getActionMap().put("mouseclicked", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = new AddShapeToTable(frame, "circle", 10, 10);
                commandHistory = new CommandHistory();
                commandHistory.addCommandsHistory(command);
            }
        });*/


        WindowAdapter wa = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(wa);
        frame.pack();
        frame.setVisible(true);
    }
}

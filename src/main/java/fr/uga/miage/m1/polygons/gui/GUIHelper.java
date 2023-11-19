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

    public static void showOnFrame(Client cli){

        WindowAdapter wa = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        cli.getFrame().addWindowListener(wa);
        cli.getFrame().pack();
        cli.getFrame().setVisible(true);
    }
}

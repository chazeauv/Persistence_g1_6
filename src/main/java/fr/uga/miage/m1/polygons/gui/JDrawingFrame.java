package fr.uga.miage.m1.polygons.gui;

import fr.uga.miage.m1.polygons.gui.command.*;
import fr.uga.miage.m1.polygons.gui.shapes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JDrawingFrame extends JFrame {

    private transient SimpleShape shape;

    private transient SimpleShape draggedShape;

    private transient List<SimpleShape> mShapes = new ArrayList<>();

    private static final long serialVersionUID = 1L;

    private JToolBar mToolBar;

    private ShapeFactory.Shapes mSelected;

    private JPanel mPanel;

    private JLabel mLabel;

    private Point mLastPressed;

    private static final Logger LOGGER = Logger.getLogger(JDrawingFrame.class.getName());

    private transient Command command;

    private final transient ActionListener mReusableActionListener = new ShapeActionListener();

    private final transient ActionListener mExportActionListener = new ExportActionListener();

    /**
     * Tracks buttons to manage the background.
     */
    private Map<ShapeFactory.Shapes, JButton> mButtons = new EnumMap<>(ShapeFactory.Shapes.class);

    /**
     * Default constructor that populates the main window.
     * @param frameName
     */
    public JDrawingFrame(String frameName, Client cli) {

        super(frameName);

        // Instantiates components
        mToolBar = new JToolBar("Toolbar");
        mPanel = new JPanel();
        mPanel.setBackground(Color.WHITE);
        mPanel.setLayout(null);
        mPanel.setMinimumSize(new Dimension(400, 400));
        mPanel.addMouseListener(cli);
        mPanel.addMouseMotionListener(cli);
        mLabel = new JLabel(" ", SwingConstants.LEFT);

        mPanel.setFocusable(true);
        mPanel.requestFocusInWindow();

        // Fills the panel
        setLayout(new BorderLayout());
        add(mToolBar, BorderLayout.NORTH);
        add(mPanel, BorderLayout.CENTER);
        add(mLabel, BorderLayout.SOUTH);

        // Add shapes in the menu
        addShape(ShapeFactory.Shapes.SQUARE, new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/square.png"));
        addShape(ShapeFactory.Shapes.TRIANGLE, new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/triangle.png"));
        addShape(ShapeFactory.Shapes.CIRCLE, new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/circle.png"));
        addShape(ShapeFactory.Shapes.CUBE, new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/underc.png"));
        addButton("EXPORT", new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/underc.png"));

        // Sets the frame initial size
        setPreferredSize(new Dimension(400, 400));
    }

    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     * @param shape The name of the injected <tt>SimpleShape</tt>.
     * @param icon The icon associated with the injected <tt>SimpleShape</tt>.
     */
    private void addShape(ShapeFactory.Shapes shape, ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        mButtons.put(shape, button);
        button.setActionCommand(shape.toString());
        button.addActionListener(mReusableActionListener);
        if (mSelected == null) {
            button.doClick();
        }
        mToolBar.add(button);
        mToolBar.validate();
        repaint();
    }

    /**
     * Add a button to the toolbar.
     * @param name The name of the button.
     * @param icon The icon associated with the button.
     */
    private void addButton(String name, ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        button.setActionCommand(name);
        button.addActionListener(mExportActionListener);
        mToolBar.add(button);
        mToolBar.validate();
        repaint();
    }

    /**
     * Exports the shapes into a file (XML or Json).
     */
    public void export() {

        String fileType = "";
        StringBuilder export = new StringBuilder();

        LogRecord warnRec = new LogRecord(Level.WARNING, "Warning");

        // Ouvre une fenêtre pour choisir le type de fichier
        fileType = javax.swing.JOptionPane.showInputDialog("Quel type de fichier voulez-vous exporter ? (XML ou JSON)");

        if ("json".equalsIgnoreCase(fileType)) {
            export.append("{\n\"shapes\": [\n");

            for (int i = 0; i < mShapes.size(); i++) {
                shape = mShapes.get(i);
                String shapeName = shape.getClass().getSimpleName().toLowerCase();
                export.append("\t{\n\t\t\"type\": \"" + shapeName + "\",\n\t\t\"x\": " + shape.getX() + ",\n\t\t\"y\": " + shape.getY() + "\n\t}");
                if (i < mShapes.size() - 1) {
                    export.append(",\n");
                }
            }

            export.append("\n]\n}");

        } else if ("xml".equalsIgnoreCase(fileType)) {
            export.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root>\n<shapes>\n");

            for (int i = 0; i < mShapes.size(); i++) {
                shape = mShapes.get(i);
                String shapeName = shape.getClass().getSimpleName();
                export.append("\t<shape>\n\t\t<type>" + shapeName + "</type>\n\t\t<x>" + shape.getX() + "</x>\n\t\t<y>" + shape.getY() + "</y>\n\t</shape>");
                if (i < mShapes.size() - 1) {
                    export.append("\n");
                }
            }

            export.append("\n</shapes>\n</root>");
        } else {
            warnRec.setMessage("Format de fichier non supporté");
            LOGGER.log(warnRec);
            return;
        }

        // Ouvre une fenêtre pour choisir le nom du fichier
        String fileName = javax.swing.JOptionPane.showInputDialog("Quel nom voulez-vous donner au fichier ?");

        // Ouvre un explorateur de fichier pour choisir le dossier de destination
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Enregistrer le fichier");
        fileChooser.showSaveDialog(null);

        File selectedFile = fileChooser.getSelectedFile();
        String filePath = selectedFile.getAbsolutePath();

        // Créer le fichier
        File file = new File(filePath + "/" + fileName + "." + fileType);

        // Écrit dans le fichier
        try (java.io.PrintWriter output = new java.io.PrintWriter(file)) {
            output.print(export);
        } catch (Exception e) {
            warnRec.setMessage("Erreur lors de l'écriture du fichier");
            LOGGER.log(warnRec);
        }

        //Afficher que le fichier a bien été enregistré
        javax.swing.JOptionPane.showMessageDialog(null, "Le fichier a bien été enregistré");

    }


    //Getters
    public List<SimpleShape> getShapes() {
        return mShapes;
    }

    public ShapeFactory.Shapes getSelected() {
        return mSelected;
    }

    public JPanel getPanel() {
        return mPanel;
    }

    public JLabel getLabel() {
        return mLabel;
    }

    public SimpleShape getSimpleShape() {
        return shape;
    }

    public Point getLastPressed() {
        return mLastPressed;
    }

    public SimpleShape getDraggedShape() {
        return draggedShape;
    }

    public Command getCommand() {
        return command;
    }

    //Setters
    public void setShapes(List<SimpleShape> shapes) {
        this.mShapes = shapes;
    }

    public void setSimpleShape(SimpleShape shape) {
        this.shape = shape;
    }

    public void setSelected(ShapeFactory.Shapes selected) {
        this.mSelected = selected;
    }

    public void setPanel(JPanel panel) {
        this.mPanel = panel;
    }

    public void setLastPressed(Point p) {
        this.mLastPressed = p;
    }

    public void setDraggedShape(SimpleShape shape) {
        this.draggedShape = shape;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    //Graphique
    public void instantiateShape(SimpleShape shp){

        shp.draw((Graphics2D) mPanel.getGraphics());
        addShapeToTable(shp);
    }

    public void drawShape(SimpleShape shape){
        Graphics2D g2 = (Graphics2D) mPanel.getGraphics();
        shape.draw(g2);
        addShapeToTable(shape);
    }
    public void addShapeToTable(SimpleShape shape) {
        this.mShapes.add(shape);
    }

    public void moveShape(SimpleShape shape, int x, int y) {
        shape.setX(x);
        shape.setY(y);
        paintComponents(this.getGraphics());
    }

    //Debug
    private void showShapes(List<SimpleShape> shapes) {
        for (SimpleShape shp : shapes) {
            LogRecord infoShapes = new LogRecord(Level.INFO, shp.getClass().getSimpleName() + " : " + shp.getX() + ", " + shp.getY());
            LOGGER.log(infoShapes);
        }
    }

    public void undoShape() {

        if (!mShapes.isEmpty()) {
            mShapes.remove(mShapes.size() - 1);
            paintComponents(this.getGraphics());
        }
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        for (SimpleShape mShape : mShapes) {
            mShape.draw((Graphics2D) mPanel.getGraphics());
        }
    }

    /**
     * Simple action listener for shape tool bar buttons that sets
     * the drawing frame's currently selected shape when receiving
     * an action event.
     */
    private class ShapeActionListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            // Itère sur tous les boutons
            for (Map.Entry<ShapeFactory.Shapes, JButton> buttonEntry: mButtons.entrySet()) {
                ShapeFactory.Shapes selectedShape = buttonEntry.getKey();
                JButton btn = mButtons.get(selectedShape);
                if (evt.getActionCommand().equals(selectedShape.toString())) {
                    btn.setBorderPainted(true);
                    mSelected = selectedShape;
                } else {
                    btn.setBorderPainted(false);
                }
                btn.repaint();
            }
        }
    }

    /**
     * Simple action listener for export button.
     */
    private class ExportActionListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            
            if(evt.getActionCommand().equals("EXPORT")) {
                export();
            }
        }
    }
}

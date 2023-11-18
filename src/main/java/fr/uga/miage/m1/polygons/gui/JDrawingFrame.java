package fr.uga.miage.m1.polygons.gui;

import fr.uga.miage.m1.polygons.gui.command.*;
import fr.uga.miage.m1.polygons.gui.shapes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import edu.uga.singleshape.CubePanel;

/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JDrawingFrame extends JFrame implements MouseListener, MouseMotionListener {

    private enum Shapes {

        SQUARE, TRIANGLE, CIRCLE, CUBE
    }

    private static final String CIRCLE = "circle";
    private static final String TRIANGLE = "triangle";
    private static final String SQUARE = "square";

    private static final String CUBE = "cube";

    private transient SimpleShape shape;
    private transient List<SimpleShape> mShapes = new ArrayList<SimpleShape>();

    private transient List<List<SimpleShape>> mShapesHistory = new ArrayList<List<SimpleShape>>();

    private static final long serialVersionUID = 1L;

    private JToolBar mToolBar;

    private Shapes mSelected;

    private JPanel mPanel;

    private JLabel mLabel;

    private static final Logger LOGGER = Logger.getLogger(JDrawingFrame.class.getName());
    private LogRecord warnRec = new LogRecord(Level.WARNING, "Warning");

    private String shapeMovedType = "";

    private transient CommandControl commandControl = new CommandControl();

    private Command  command;

    private final transient ActionListener mReusableActionListener = new ShapeActionListener();

    private final transient ActionListener mExportActionListener = new ExportActionListener();

    /**
     * Tracks buttons to manage the background.
     */
    private EnumMap<Shapes, JButton> mButtons = new EnumMap<>(Shapes.class);

    /**
     * Default constructor that populates the main window.
     * @param frameName
     */
    public JDrawingFrame(String frameName) {
        super(frameName);
        // Instantiates components
        mToolBar = new JToolBar("Toolbar");
        mPanel = new JPanel();
        mPanel.setBackground(Color.WHITE);
        mPanel.setLayout(null);
        mPanel.setMinimumSize(new Dimension(400, 400));
        mPanel.addMouseListener(this);
        mPanel.addMouseMotionListener(this);
        mLabel = new JLabel(" ", SwingConstants.LEFT);
        // Fills the panel
        setLayout(new BorderLayout());
        add(mToolBar, BorderLayout.NORTH);
        add(mPanel, BorderLayout.CENTER);
        add(mLabel, BorderLayout.SOUTH);
        // Add shapes in the menu
        addShape(Shapes.SQUARE, new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/square.png"));
        addShape(Shapes.TRIANGLE, new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/triangle.png"));
        addShape(Shapes.CIRCLE, new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/circle.png"));
        addShape(Shapes.CUBE, new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/underc.png"));
        addButton("EXPORT", new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/underc.png"));
        setPreferredSize(new Dimension(400, 400));
    }

    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     * @param shape The name of the injected <tt>SimpleShape</tt>.
     * @param icon The icon associated with the injected <tt>SimpleShape</tt>.
     */
    private void addShape(Shapes shape, ImageIcon icon) {
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

        // Ouvre une fenêtre pour choisir le type de fichier
        fileType = javax.swing.JOptionPane.showInputDialog("Quel type de fichier voulez-vous exporter ? (XML ou JSON)");

        if ("json".equalsIgnoreCase(fileType)) {
            export.append("{\n\"shapes\": [\n");

            for (int i = 0; i < mShapes.size(); i++) {
                SimpleShape shape = mShapes.get(i);
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
                SimpleShape shape = mShapes.get(i);
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


    /**
     * Implements method for the <tt>MouseListener</tt> interface to
     * draw the selected shape into the drawing canvas.
     * @param evt The associated mouse event.
     */
    public void mouseClicked(MouseEvent evt) {

        int evtX = evt.getX();
        int evtY = evt.getY();

        if (mPanel.contains(evtX, evtY)) {
            command = new CShape(this, evtX, evtY);
            commandControl.addCommand(command);
            commandControl.executeCommands();
        }
    }

    public void instantiateShape(int evtX, int evtY){
        if (mPanel.contains(evtX, evtY)) {

            switch(mSelected) {
                case CIRCLE:
                    shape = new Circle(evtX, evtY);
                    drawShape(shape);
                    break;
                case TRIANGLE:
                    shape = new Triangle(evtX, evtY);
                    drawShape(shape);
                    break;
                case SQUARE:
                    shape = new Square(evtX, evtY);
                    drawShape(shape);
                    break;
                case CUBE:
                    shape = new Cube(evtX, evtY);
                    drawShape(shape);
                    break;
                default:
                    warnRec.setMessage("No shape found");
                    LOGGER.log(warnRec);
            }
            if("".equals(shapeMovedType)) addShapesStateToHistory();
        }
    }

    public void drawShape(SimpleShape shape){
        Graphics2D g2 = (Graphics2D) mPanel.getGraphics();
        shape.draw(g2);
        addShapeToTable(shape);
    }
    public void addShapeToTable(SimpleShape shape) {
        shapeMovedType = "";
        this.mShapes.add(shape);
    }

    private void addShapesStateToHistory() {

        List<SimpleShape> mShapesCopy = new ArrayList<SimpleShape>(this.mShapes);
        this.mShapesHistory.add(mShapesCopy);
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
     */
    public void mouseEntered(MouseEvent evt) {
        //TODO
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
     */
    public void mouseExited(MouseEvent evt) {
        mLabel.setText(" ");
        mLabel.repaint();
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to initiate
     * shape dragging.
     * @param evt The associated mouse event.
     */
    public void mousePressed(MouseEvent evt) {
        //TODO
    }

    private void showShapes(List<SimpleShape> shapes) {
        for (SimpleShape shape : shapes) {
            LogRecord infoShapes = new LogRecord(Level.INFO, shape.getClass().getSimpleName() + " : " + shape.getX() + ", " + shape.getY());
            LOGGER.log(infoShapes);
        }
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     * @param evt The associated mouse event.
     */
    public void mouseReleased(MouseEvent evt) {
        int x = evt.getX();
        int y = evt.getY();

        if (!"".equals(shapeMovedType) && mPanel.contains(x, y)) {
            command = new Drop(this, x, y);
            commandControl.addCommand(command);
            commandControl.executeCommands();
        }
    }

    public void drop(int x, int y){
        switch(shapeMovedType) {
            case CIRCLE:
                shape = new Circle(x, y);
                drawShape(shape);
                break;
            case TRIANGLE:
                shape = new Triangle(x, y);
                drawShape(shape);
                break;
            case SQUARE:
                shape = new Square(x, y);
                drawShape(shape);
                break;
            default:
                warnRec.setMessage("No shape found");
                LOGGER.log(warnRec);
        }
        if(!"".equals(shapeMovedType)) addShapesStateToHistory();
    }

    public void undoDrop(){
        mShapes.remove(mShapes.size()-1);
        paintComponents25(this.getGraphics());
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     * @param evt The associated mouse event.
     */
    public void mouseDragged(MouseEvent evt) {
        int x = evt.getX();
        int y = evt.getY();

        if("".equals(shapeMovedType)){
            command = new Drag(this, x, y);
            commandControl.addCommand(command);
            commandControl.executeCommands();
        }
    }

    public void drag(int x, int y){
        for (SimpleShape shape : mShapes) {
            if (shape.contains(x, y)) {
                mShapes.remove(shape);
                shapeMovedType = shape.getClass().getSimpleName().toLowerCase();
                paintComponents25(this.getGraphics());
                break;
            }
        }
    }

    public void undoDrag(){
        setShapes(mShapesHistory.get(mShapesHistory.size()-1));
        paintComponents25(this.getGraphics());
    }

    /**
     * Implements an empty method for the <tt>MouseMotionListener</tt>
     * interface.
     * @param evt The associated mouse event.
     */
    public void mouseMoved(MouseEvent evt) {
        modifyLabel(evt);
    }

    private void modifyLabel(MouseEvent evt) {
        mLabel.setText("(" + evt.getX() + "," + evt.getY() + ")");
    }


    public void undoShape() {

        if (!mShapes.isEmpty()) {
            mShapes.remove(mShapes.size() - 1);
            paintComponents25(this.getGraphics());
        }
    }


    //Getters
    public List<SimpleShape> getShapes() {
        return mShapes;
    }

    public Shapes getSelected() {
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

    public CommandControl getCommandControl() {
        return commandControl;
    }

    public Command getCommand() {
        return command;
    }

    //Setters
    public void setShapes(List<SimpleShape> shapes) {
        this.mShapes = shapes;
    }

    public void setSelected(Shapes selected) {
        this.mSelected = selected;
    }

    public void setPanel(JPanel panel) {
        this.mPanel = panel;
    }

    public void paintComponents25(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) mPanel.getGraphics();
        for (SimpleShape shape : mShapes) {
            String className = "";
            className = shape.getClass().getSimpleName().toLowerCase();
            switch (className) {
                case CIRCLE:
                    new Circle(shape.getX()+25,shape.getY()+25).draw(g2);
                    break;
                case TRIANGLE:
                    new Triangle(shape.getX()+25,shape.getY()+25).draw(g2);
                    break;
                case SQUARE:
                    new Square(shape.getX()+25,shape.getY()+25).draw(g2);
                    break;
                case CUBE:
                    new Cube(shape.getX(),shape.getY()).draw(g2);
                    break;
                default:
                    warnRec.setMessage("No shape found");
                    LOGGER.log(warnRec);
            }
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
            Iterator<Shapes> keys = mButtons.keySet().iterator();
            while (keys.hasNext()) {
                Shapes shape = keys.next();
                JButton btn = mButtons.get(shape);
                if (evt.getActionCommand().equals(shape.toString())) {
                    btn.setBorderPainted(true);
                    mSelected = shape;
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

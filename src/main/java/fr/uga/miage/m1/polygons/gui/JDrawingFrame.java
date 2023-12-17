package fr.uga.miage.m1.polygons.gui;

import fr.uga.miage.m1.polygons.gui.command.Command;
import fr.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import fr.uga.miage.m1.polygons.gui.persistence.Visitor;
import fr.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import fr.uga.miage.m1.polygons.gui.shapes.*;
import importExport.Export;
import importExport.ImportXML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;
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

    private static final String EXPORT = "EXPORT";

    private static final String IMPORT = "IMPORT";

    private static final String GROUP = "GROUP";

    private boolean modeGroup = false;

    private transient SimpleShape shape;

    private transient SimpleShape draggedShape = null;

    private transient List<SimpleShape> mShapes = new ArrayList<>();

    private transient List<Group> mGroups = new ArrayList<>();

    private static final long serialVersionUID = 1L;

    private JToolBar mToolBar;

    private JPanel mGroupsMenu;

    private ShapeFactory.Shapes mSelected;

    private transient Group mSelectedGroup;

    private Map<String, JButton> actionButtons = new HashMap<>();

    private JPanel mPanel;

    private JLabel mLabel;

    private Point mLastPressed;

    private static final Logger LOGGER = Logger.getLogger(JDrawingFrame.class.getName());

    private transient Command command;

    private final transient ActionListener mReusableActionListener = new ShapeActionListener();

    private final transient ActionListener mCustomActionListener = new CustomActionListener();

    private final transient ActionListener mGroupsButtonsListener = new GroupsButtonsListener();

    private static final String SQUARE = "square";

    private static final String CIRCLE = "circle";

    private static final String TRIANGLE = "triangle";

    private static final String CUBE = "cube";

    /**
     * Tracks buttons to manage the background.
     */
    private Map<ShapeFactory.Shapes, JButton> mButtons = new EnumMap<>(ShapeFactory.Shapes.class);

    /**
     * Default constructor that populates the main window.
     * @param frameName name of the frame
     * @param cli the client
     */
    public JDrawingFrame(String frameName, Client cli) {

        super(frameName);

        // Instantiates components
        mToolBar = new JToolBar("Toolbar");
        mToolBar.setBackground(Color.WHITE);
        mPanel = new JPanel();
        mPanel.setBackground(Color.WHITE);
        mPanel.setLayout(null);
        mPanel.setMinimumSize(new Dimension(400, 400));
        mPanel.setBorder(new MatteBorder(0, 0, 1, 0, new Color(204,204,204)));
        mPanel.addMouseListener(cli);
        mPanel.addMouseMotionListener(cli);
        mLabel = new JLabel(" ", SwingConstants.LEFT);
        mLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        mLabel.setOpaque(true);
        mLabel.setBackground(new Color(234,241,251));

        mPanel.setFocusable(true);
        mPanel.requestFocusInWindow();

        // Fills the panel
        setLayout(new BorderLayout());
        add(mToolBar, BorderLayout.NORTH);
        add(mLabel, BorderLayout.SOUTH);
        add(mPanel, BorderLayout.CENTER);

        // Add shapes in the menu
        addShape(SQUARE, ShapeFactory.Shapes.SQUARE, new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/square.png"));
        addShape(TRIANGLE, ShapeFactory.Shapes.TRIANGLE, new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/triangle.png"));
        addShape(CIRCLE, ShapeFactory.Shapes.CIRCLE, new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/circle.png"));
        addShape(CUBE, ShapeFactory.Shapes.CUBE, new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/cube.png"));
        mToolBar.add(Box.createHorizontalGlue()); // pour décaler le bouton "export" à droite
        addButton(GROUP, new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/groups.png"));
        addButton(EXPORT, new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/export.png"));
        addButton(IMPORT, new ImageIcon("src/main/java/fr/uga/miage/m1/polygons/gui/images/import.png"));

        // Sets the frame initial size
        setPreferredSize(new Dimension(720, 480));

        // Création du menu latéral
        mGroupsMenu = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        mGroupsMenu.setBackground(new Color(234,241,251));
        mGroupsMenu.setPreferredSize(new Dimension(101, 480));
        mGroupsMenu.setBorder(new MatteBorder(0, 0, 0, 1, new Color(204,204,204)));

        // Ajout du menu latéral à la JFrame
        add(mGroupsMenu, BorderLayout.WEST);
    }

    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     * @param shape The name of the injected <tt>SimpleShape</tt>.
     * @param icon The icon associated with the injected <tt>SimpleShape</tt>.
     */
    private void addShape(String text, ShapeFactory.Shapes shape, ImageIcon icon) {
        JButton button = new JButton(text, icon);
        button.setBorderPainted(false);
        button.setBackground(Color.WHITE);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
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
        JButton button = new JButton(name.toLowerCase(),icon);
        button.setBackground(Color.WHITE);
        button.setBorderPainted(false);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        actionButtons.put(name, button);
        button.setActionCommand(name);
        button.addActionListener(mCustomActionListener);
        mToolBar.add(button);
        mToolBar.validate();
        repaint();
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

    public boolean getModeGroup() {
        return modeGroup;
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

    public void setModeGroup(boolean modeGroup) {
        this.modeGroup = modeGroup;
    }

    //Graphique
    public void instantiateShape(SimpleShape shp){

        shp.draw((Graphics2D) mPanel.getGraphics());
        addShapeToTable(shp);
    }

    public boolean instantiateGroup(Group group, int x1, int y1, int x2, int y2){

            fillShapes(group, x1, y1, x2, y2);

            cleanGroup(group);

            return instantiateGroup(group);
    }

    public boolean instantiateGroup(Group group){

            if(group.getShapes().isEmpty()){
                return false;
            }

            mGroups.add(group);

            int mGroupsSize = mGroups.size();

            JButton button = new JButton("Group" + mGroupsSize);
            if(mGroupsSize % 2 == 0)
                button.setBackground(new Color(245, 249, 253));
            else
                button.setBackground(Color.WHITE);

            button.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
            button.setPreferredSize(new Dimension(100, 20));
            button.addActionListener(mGroupsButtonsListener);
            mGroupsMenu.add(button);


            return true;
    }

    public void fillShapes(Group group, int x1, int y1, int x2, int y2) {
        int shpX;
        int shpY;

        for (SimpleShape shp : this.mShapes) {
            shpX = shp.getX();
            shpY = shp.getY();

            if ((x1 > x2 && shpX >= x2 && shpX <= x1)
                    || (shpX >= x1 && shpX <= x2) && ((y1 > y2 && shpY >= y2 && shpY <= y1)
                    || (shpY >= y1 && shpY <= y2))) {
                    group.addShape(shp);

            }
        }
    }


    public void cleanGroup(Group group) {

        List<SimpleShape> groupShapes;
        ArrayList<SimpleShape> newShapes = new ArrayList<>(group.getShapes());

        for (SimpleShape shp : group.getShapes()) {
            for (Group grp : mGroups) {
                groupShapes = grp.getShapes();
                for (SimpleShape groupShape : groupShapes) {
                    if (groupShape.equals(shp)) {
                        newShapes.remove(shp);
                    }
                }
            }
        }

        group.setShapes(newShapes);
    }

    public Group isShapeInAGroup(SimpleShape shape) {
        for (Group grp : mGroups) {
            for (SimpleShape shp : grp.getShapes()) {
                if (shp.equals(shape)) {
                    return grp;
                }
            }
        }
        return null;
    }

    public boolean isShapeInSelectedGroup(SimpleShape shape, Group group) {
        if(group != null){
            for (SimpleShape shp : group.getShapes()) {
                if (shp.equals(shape)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void moveGroup(Group group, SimpleShape shape, int x, int y) {

        int decalageX =  shape.getX() - x;
        int decalageY = shape.getY() - y ;

        for (SimpleShape shp : group.getShapes()) {
            if(!shp.equals(shape)){
                moveShape(shp, shp.getX() - decalageX, shp.getY() - decalageY);
            }
        }

        moveShape(shape, x, y);

    }

    public boolean undoGroup(Group group) {

        if (!mGroups.isEmpty()) {
            mGroups.remove(group);
            mGroupsMenu.remove(mGroupsMenu.getComponentCount() - 1);
            paintComponents(this.getGraphics());
            return true;
        }

        return false;
    }

    public void addShapeToTable(SimpleShape shape) {
        this.mShapes.add(shape);
    }

    public void moveShape(SimpleShape shape, int x, int y) {
        shape.setX(x);
        shape.setY(y);
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
            if (isShapeInSelectedGroup(mShape, mSelectedGroup) && !(mShape instanceof Cube)) {
                mShape.drawWithBorder((Graphics2D) mPanel.getGraphics());
            } else {
                mShape.draw((Graphics2D) mPanel.getGraphics());
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
            for (Map.Entry<ShapeFactory.Shapes, JButton> buttonEntry: mButtons.entrySet()) {
                ShapeFactory.Shapes selectedShape = buttonEntry.getKey();
                JButton btn = mButtons.get(selectedShape);
                if (evt.getActionCommand().equals(selectedShape.toString())) {
                    btn.setBackground(new Color(194,231,255));
                    mSelected = selectedShape;
                } else {
                    btn.setBackground(Color.WHITE);
                }
                btn.repaint();
            }
        }
    }

    private class CustomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {

            JButton btn;

            if (evt.getActionCommand().equals(EXPORT)) {
                Export.export(mShapes, mGroups);
                btn = actionButtons.get(EXPORT);
                btn.setBackground(new Color(194, 231, 255));
            } else if (evt.getActionCommand().equals(GROUP)) {
                setModeGroup(!getModeGroup());
                btn = actionButtons.get(GROUP);
                btn.setBackground(getModeGroup() ? new Color(194, 231, 255) : Color.WHITE);
            } else if(evt.getActionCommand().equals(IMPORT)) {
                btn = actionButtons.get(IMPORT);
                btn.setBackground(new Color(194, 231, 255));
                ImportXML.parseShapes(ImportXML.importXML(), mShapes, JDrawingFrame.this);
            } else{
                btn = actionButtons.get(EXPORT);
                btn.setBackground(Color.WHITE);
                btn = actionButtons.get(GROUP);
                btn.setBackground(Color.WHITE);
                btn = actionButtons.get(IMPORT);
                btn.setBackground(Color.WHITE);
            }
        }

        /*private void parseShapes(String xmlString) {
            List<List<SimpleShape>> groupes = new ArrayList<>();
            try {
                Document doc = parseXmlString(xmlString);

                assert doc != null;
                NodeList shapeNodes = doc.getElementsByTagName("shape");
                for (int i = 0; i < shapeNodes.getLength(); i++) {
                    Node shapeNode = shapeNodes.item(i);
                    shape = parseShapeNode(shapeNode);
                    if(shape != null) instantiateShape(shape);
                }

                NodeList groupNodes = doc.getElementsByTagName("group");
                for (int i = 0; i < groupNodes.getLength(); i++) {
                    Node groupNode = groupNodes.item(i);
                    List<SimpleShape> shapes = parseGroupNode(groupNode);
                    groupes.add(shapes);
                }

                for (List<SimpleShape> groupe : groupes) {
                    Group g = new Group();
                    for (SimpleShape shp : groupe) {
                        g.addShape(shp);
                    }
                    instantiateGroup(g);
                }

            } catch (Exception e) {
                LogRecord warnRec = new LogRecord(Level.WARNING, "Erreur lors du parsing du fichier");
                LOGGER.log(warnRec);
            }
        }

        private List<SimpleShape> parseGroupNode(Node groupNode) {
            List<SimpleShape> shapes = new ArrayList<>();
            SimpleShape existingShape = null;
            if (groupNode.getNodeType() == Node.ELEMENT_NODE) {
                Element groupElement = (Element) groupNode;
                NodeList shapeNodes = groupElement.getElementsByTagName("shape");
                for (int j = 0; j < shapeNodes.getLength(); j++) {
                    Node shapeNode = shapeNodes.item(j);
                    shape = parseShapeNode(shapeNode);
                    if(shape != null) existingShape = shapeAlreadyExists(shape, mShapes);
                    if (existingShape != null) {
                        shapes.add(existingShape);
                    }
                }
            }
            return shapes;
        }

        private SimpleShape parseShapeNode(Node shapeNode) {
            if (shapeNode.getNodeType() == Node.ELEMENT_NODE) {
                Element shapeElement = (Element) shapeNode;
                String type = shapeElement.getElementsByTagName("type").item(0).getTextContent();
                int x = Integer.parseInt(shapeElement.getElementsByTagName("x").item(0).getTextContent());
                int y = Integer.parseInt(shapeElement.getElementsByTagName("y").item(0).getTextContent());
                return ShapeFactory.createShapeFromStr(type, x, y);
            }
            return null;
        }

        private Document parseXmlString(String xmlString) {
            try{
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                dbFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(new InputSource(new StringReader(xmlString)));
                doc.getDocumentElement().normalize();
                return doc;
            } catch (Exception e) {
                LogRecord warnRec = new LogRecord(Level.WARNING, "Erreur lors du parsing du fichier");
                LOGGER.log(warnRec);
                return null;
            }
        }

        private SimpleShape shapeAlreadyExists(SimpleShape shape, List<SimpleShape> mShps) {
            for (SimpleShape s : mShps) {
                //compare les coordonnées et le type des shapes
                if (s.getX() == shape.getX() && s.getY() == shape.getY() && shape.getClass().isAssignableFrom(s.getClass())) {
                    return s;
                }
            }
            return null;
        }*/
    }

    private class GroupsButtonsListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {

            //On boucle sur les boutons de MGroupsMenu
            for (Component component: mGroupsMenu.getComponents()) {

                if(component instanceof JButton) {
                    JButton btn = (JButton) component;

                    if (evt.getActionCommand().equals(btn.getText())) {
                        component.setBackground(new Color(194,231,255));
                        mSelectedGroup = mGroups.get(mGroupsMenu.getComponentZOrder(component));
                        JDrawingFrame.this.paintComponents(JDrawingFrame.this.getGraphics());
                    } else {
                        component.setBackground(Color.WHITE);
                    }
                }
            }

        }
    }
}

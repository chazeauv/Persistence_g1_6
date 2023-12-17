package fr.uga.miage.m1.polygons.gui.importexport;

import fr.uga.miage.m1.polygons.gui.JDrawingFrame;
import fr.uga.miage.m1.polygons.gui.ShapeFactory;
import fr.uga.miage.m1.polygons.gui.shapes.Group;
import fr.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ImportXML {

    private static final Logger LOGGER = Logger.getLogger(JDrawingFrame.class.getName());
    static SimpleShape shape;

    public static String importXML() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                dbFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(selectedFile);
                doc.getDocumentElement().normalize();
                return nodeToString(doc.getDocumentElement());
            } catch (Exception e) {
                LogRecord warnRec = new LogRecord(Level.WARNING, "Erreur lors de l'import du fichier");
                LOGGER.log(warnRec);
                return null;
            }
        } else {
            return null;
        }
    }

    private static String nodeToString(Node node) {
        StringBuilder result = new StringBuilder();
        if (node.getNodeType() == Node.TEXT_NODE) {
            result.append(node.getNodeValue());
        } else if (node.getNodeType() != Node.DOCUMENT_NODE) {
            result.append("<").append(node.getNodeName());
            if (node.getAttributes() != null && node.getAttributes().getLength() > 0) {
                for (int i = 0; i < node.getAttributes().getLength(); i++) {
                    result.append(" ").append(node.getAttributes().item(i).getNodeName())
                            .append("=").append("\"")
                            .append(node.getAttributes().item(i).getNodeValue())
                            .append("\"");
                }
            }
            result.append(">");
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node currentNode = nodeList.item(i);
                result.append(nodeToString(currentNode));
            }
            result.append("</").append(node.getNodeName()).append(">");
        }
        return result.toString();
    }

    public static void parseShapes(String xmlString, List<SimpleShape> mShps, JDrawingFrame frame) {
        List<List<SimpleShape>> groupes = new ArrayList<>();
        try {
            Document doc = parseXmlString(xmlString);

            assert doc != null;
            NodeList shapeNodes = doc.getElementsByTagName("shape");
            for (int i = 0; i < shapeNodes.getLength(); i++) {
                Node shapeNode = shapeNodes.item(i);
                shape = parseShapeNode(shapeNode);


                /* ATTENTION ____________________________________________________________________
                 * Changer l'appel d'instantiateShape par vos fonctions d'instanciation du shape
                 */
                if(shape != null) frame.instantiateShape(shape);
            }

            NodeList groupNodes = doc.getElementsByTagName("group");
            for (int i = 0; i < groupNodes.getLength(); i++) {
                Node groupNode = groupNodes.item(i);
                List<SimpleShape> shapes = parseGroupNode(groupNode, mShps);
                groupes.add(shapes);
            }

            for (List<SimpleShape> groupe : groupes) {
                Group g = new Group();
                for (SimpleShape shp : groupe) {
                    g.addShape(shp);
                }

                /* ATTENTION ____________________________________________________________________
                 * Changer l'appel d'instantiateGroup par vos fonctions d'instanciation du group
                 */
                frame.instantiateGroup(g);
            }

        } catch (Exception e) {
            LogRecord warnRec = new LogRecord(Level.WARNING, "Erreur lors du parsing du fichier");
            LOGGER.log(warnRec);
        }
    }

    private static List<SimpleShape> parseGroupNode(Node groupNode, List<SimpleShape> mShps) {
        List<SimpleShape> shapes = new ArrayList<>();
        SimpleShape existingShape = null;
        if (groupNode.getNodeType() == Node.ELEMENT_NODE) {
            Element groupElement = (Element) groupNode;
            NodeList shapeNodes = groupElement.getElementsByTagName("shape");
            for (int j = 0; j < shapeNodes.getLength(); j++) {
                Node shapeNode = shapeNodes.item(j);
                shape = parseShapeNode(shapeNode);
                if(shape != null) existingShape = shapeAlreadyExists(shape, mShps);
                if (existingShape != null) {
                    shapes.add(existingShape);
                }
            }
        }
        return shapes;
    }

    private static SimpleShape parseShapeNode(Node shapeNode) {
        if (shapeNode.getNodeType() == Node.ELEMENT_NODE) {
            Element shapeElement = (Element) shapeNode;
            String type = shapeElement.getElementsByTagName("type").item(0).getTextContent();
            int x = Integer.parseInt(shapeElement.getElementsByTagName("x").item(0).getTextContent());
            int y = Integer.parseInt(shapeElement.getElementsByTagName("y").item(0).getTextContent());
            return ShapeFactory.createShapeFromStr(type, x, y);
        }
        return null;
    }

    private static Document parseXmlString(String xmlString) {
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

    private static SimpleShape shapeAlreadyExists(SimpleShape shape, List<SimpleShape> mShps) {
        for (SimpleShape s : mShps) {
            //compare les coordonnées et le type des shapes
            if (s.getX() == shape.getX() && s.getY() == shape.getY() && shape.getClass().isAssignableFrom(s.getClass())) {
                return s;
            }
        }
        return null;
    }
}

package fr.uga.miage.m1.polygons.gui.importexport;


import fr.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import fr.uga.miage.m1.polygons.gui.persistence.Visitor;
import fr.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import fr.uga.miage.m1.polygons.gui.shapes.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Export {

    private Export(){}

    private static final Logger LOGGER = Logger.getLogger(Export.class.getName());

    public static void export(List<SimpleShape> mShps, List<Group> mGrps) {
        String fileType = getFileType();

        if (fileType.isEmpty()) {
            handleInvalidFileType();
            return;
        }

        StringBuilder export = new StringBuilder();
        boolean isJSON = fileType.equalsIgnoreCase("json");

        prepareExportHeader(export, isJSON);

        generateShapeRepresentations(export, isJSON, mShps, mGrps);

        saveFile(export, fileType);

        showMessageFileSaved();
    }

    private static String getFileType() {
        String result = javax.swing.JOptionPane.showInputDialog("Quel type de fichier voulez-vous exporter ? (XML ou JSON)");
        return result != null ? result.toLowerCase() : "";
    }

    private static void handleInvalidFileType() {
        LogRecord warnRec = new LogRecord(Level.WARNING, "Le type de fichier n'est pas valide");
        LOGGER.log(warnRec);
    }

    private static void prepareExportHeader(StringBuilder export, boolean isJSON) {
        if (isJSON) {
            export.append("{\n\"shapes\": [\n");
        } else {
            export.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<shapes>");
        }
    }

    private static void generateShapeRepresentations(StringBuilder export, boolean isJSON, List<SimpleShape> mShps, List<Group> mGrps) {
        List<SimpleShape> shapesAlreadyInVisited = new ArrayList<>();

        //on boucle sur les groupes
        for (Group group : mGrps) {
            Visitor visitor = isJSON ? new JSonVisitor() : new XMLVisitor();

            group.accept(visitor);

            export.append(visitor.getRepresentation());

            shapesAlreadyInVisited.addAll(group.getShapes());
        }

        for (int i = 0; i < mShps.size(); i++) {
            SimpleShape shp = mShps.get(i);
            Visitor visitor = isJSON ? new JSonVisitor() : new XMLVisitor();

            visitShapes(mShps, shp, visitor, export, isJSON, shapesAlreadyInVisited, i);
        }


        export.append(isJSON ? "\n]\n}" : "\n</shapes>");
    }

    private static void visitShapes(List<SimpleShape> mShps, SimpleShape shp, Visitor visitor, StringBuilder export, boolean isJSON, List<SimpleShape> shapesAlreadyInVisited, int i){
        if(!shapesAlreadyInVisited.contains(shp)) {
            if (shp instanceof Square) {
                Square square = (Square) shp;
                square.accept(visitor);
            } else if (shp instanceof Triangle) {
                Triangle triangle = (Triangle) shp;
                triangle.accept(visitor);
            } else if (shp instanceof Circle) {
                Circle circle = (Circle) shp;
                circle.accept(visitor);
            } else if (shp instanceof Cube) {
                Cube cube = (Cube) shp;
                cube.accept(visitor);
            } else {
                LogRecord warnRec = new LogRecord(Level.WARNING, "Shape non reconnue");
                LOGGER.log(warnRec);
            }
            export.append(visitor.getRepresentation());

            if (i < mShps.size() - 1 && shapesAlreadyInVisited.size() < mShps.size() - 1) {
                export.append(isJSON ? ",\n" : "");
            }
            shapesAlreadyInVisited.add(shp);
        }
    }

    private static void saveFile(StringBuilder export, String fileType) {
        String fileName = javax.swing.JOptionPane.showInputDialog("Quel nom voulez-vous donner au fichier ?");
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Enregistrer le fichier");
        fileChooser.showSaveDialog(null);

        File selectedFile = fileChooser.getSelectedFile();
        String filePath = selectedFile.getAbsolutePath();
        Path path = Paths.get(filePath).resolve(fileName + "." + fileType);
        File file = path.toFile();

        try (java.io.PrintWriter output = new java.io.PrintWriter(file)) {
            output.print(export);
        } catch (Exception e) {
            LogRecord warnRec = new LogRecord(Level.WARNING, "Erreur lors de l'écriture du fichier");
            LOGGER.log(warnRec);
        }
    }

    private static void showMessageFileSaved() {
        javax.swing.JOptionPane.showMessageDialog(null, "Le fichier a bien ete enregistre");
    }
}

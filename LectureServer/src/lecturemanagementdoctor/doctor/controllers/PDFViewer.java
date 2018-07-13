/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.controllers;

import java.io.File;
import java.net.MalformedURLException;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
//import org.icepdf.ri.common.ComponentKeyBinding;
//import org.icepdf.ri.common.SwingController;
//import org.icepdf.ri.common.SwingViewBuilder;
import javafx.embed.swing.SwingNode;
import javafx.scene.Node;

/**
 *
 * @author Saad Alenany
 */
public class PDFViewer {

    public JPanel viewerComponentPanel;

//    public static Node showPDF(File sFiles) {
//
//        String filePath = sFiles.getAbsolutePath();
//
//        // build a controller
//        SwingController controller = new SwingController();
//
//        // Build a SwingViewFactory configured with the controller
//        SwingViewBuilder factory = new SwingViewBuilder(controller);
//
//        // Use the factory to build a JPanel that is pre-configured
//        //with a complete, active Viewer UI.
//        JPanel viewerComponentPanel = factory.buildViewerPanel();
//
//        // add copy keyboard command
//        ComponentKeyBinding.install(controller, viewerComponentPanel);
//
////        // add interactive mouse link annotation support via callback
////        controller.getDocumentViewController().setAnnotationCallback(
////                new org.icepdf.ri.common.MyAnnotationCallback(
////                        controller.getDocumentViewController()));
//
//        final SwingNode swingNode = new SwingNode();
//        createAndSetSwingContent(swingNode, viewerComponentPanel);
//
//        // Open a PDF document to view
//        controller.openDocument(filePath);
//        return swingNode;
//    }

    private static void createAndSetSwingContent(final SwingNode swingNode, JPanel viewerComponentPanel) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(viewerComponentPanel);
            }
        });
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.main.lecture.converting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import lecturemanagementdoctor.doctor.Utility.Utility;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.rendering.ImageType;
//import org.apache.pdfbox.rendering.PDFRenderer;
//import org.apache.pdfbox.tools.imageio.ImageIOUtil;
//import org.apache.poi.xslf.usermodel.XMLSlideShow;
//import org.apache.poi.xslf.usermodel.XSLFSlide;

/**
 *
 * @author Amr
 */
public class Convert implements Runnable {

    private ArrayList<String> PathList;
    private Thread thread;
    private final String outPath = "lectures";
    private AfterConvertingListener afl;

    public Convert(ArrayList<String> PathList) {
        this.PathList = PathList;
        thread = new Thread(this);
    }

//    private void PowerPointToImage(String path, int FileNum) throws FileNotFoundException, IOException {
//        ArrayList<File> ImgList = new ArrayList<>();
//        //creating an empty presentation
//        File file = new File(path);
//        XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(file));
//        //getting the dimensions and size of the slide
//        Dimension pgsize = ppt.getPageSize();
//        List<XSLFSlide> slide = ppt.getSlides();
//
//        BufferedImage img = null;
//        FileOutputStream out = null;
//
//        for (int i = 0; i < slide.size(); i++) {
//            img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
//            Graphics2D graphics = img.createGraphics();
//
//            //clear the drawing area
//            graphics.setPaint(Color.white);
//            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
//            //render
//            slide.get(i).draw(graphics);
//            ImgList.add(new File(outPath + "/" + FileNum + "/" + i + ".png")); // add file to list
//
//            out = new FileOutputStream(new File(outPath + "/" + FileNum + "/" + i + ".png"));
//            javax.imageio.ImageIO.write(img, "png", out);
//            ppt.write(out);
//        }
//        ppt.close();
//        out.close();
//
//    }

    @Override
    public void run() {

//        for (int i = 0; i < PathList.size(); i++) {
//            RebuildPath(i + 1);
//            String extension = Utility.getExtension(PathList.get(i));
//            System.out.println(extension);
//            try {
//                if (extension.equals("PPT") || extension.equals("pptx")) {
//                    PowerPointToImage(PathList.get(i), i + 1);
//                } else if (extension.equals("PDF")) {
//                    System.out.println("coverting..");
//                    PDFToImages(PathList.get(i), i + 1);
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(Convert.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        if (afl != null) {
//            afl.AfterHandler();
//        }

    }
//
//    private void PDFToImages(String path, int FileNum) throws IOException {
//        ArrayList<File> ImgList = new ArrayList<>();
//        PDDocument document = PDDocument.load(new File(path));
//        PDFRenderer pdfRenderer = new PDFRenderer(document);
//        for (int page = 0; page < document.getNumberOfPages(); ++page) {
//            BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 150, ImageType.RGB);
//            // suffix in filename will be used as the file format
//            ImageIOUtil.writeImage(bim, outPath + "/" + FileNum + "/" + (page + 1) + ".png", 150);
//            ImgList.add(new File(outPath + "/" + FileNum + "/" + (page + 1) + ".png"));
//            System.gc();
//        }
//        document.close();
//    }
//
//    private void RebuildPath(int FileNum) {
//        File ParentPath = new File(outPath);
//        if (!ParentPath.exists()) {
//            ParentPath.mkdir();
//        }
//        File SubPath = new File(outPath + "/" + FileNum);
//        if (SubPath.exists()) {
//            SubPath.delete();
//        }
//        SubPath.mkdir();
//
//    }
//
//    public void setAfterConvertingListener(AfterConvertingListener afl) {
//        this.afl = afl;
//    }
//
//    public void startCoverting() {
//        thread.start();
//    }
}

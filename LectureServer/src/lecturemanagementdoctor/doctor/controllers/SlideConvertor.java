package lecturemanagementdoctor.doctor.controllers;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package lecturemanagementdoctor.doctor.controllers;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics2D;
//import java.awt.geom.Rectangle2D;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.embed.swing.SwingFXUtils;
//import javafx.scene.image.Image;
//import org.apache.poi.hslf.usermodel.HSLFSlide;
//import org.apache.poi.hslf.usermodel.HSLFSlideShow;
//import org.apache.poi.xslf.usermodel.XMLSlideShow;
//import org.apache.poi.xslf.usermodel.XSLFSlide;
//
///**
// *
// * @author Saad Alenany
// */
//public class SlideConvertor {
//
//    public ArrayList<Image> readImage(String filename) throws IOException {
//        //creating an empty presentation
//        FileInputStream is = new FileInputStream(filename);
//        HSLFSlideShow ppt = new HSLFSlideShow(is);
//        is.close();
//
//        //getting the dimensions and size of the slide 
//        Dimension pgsize = ppt.getPageSize();
//        List<HSLFSlide> slides = ppt.getSlides();
//
//        ArrayList<Image> images = new ArrayList<>();
//
//        for (int i = 0; i < slides.size(); i++) {
//            BufferedImage img = new BufferedImage(
//                    pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
//
//            Graphics2D graphics = img.createGraphics();
//
//            //clear the drawing area
//            graphics.setPaint(Color.white);
//            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
//
//            //render
//            slides.get(i).draw(graphics);
//            Image image = SwingFXUtils.toFXImage(img, null);
//            images.add(image);
//        }
//        return images;
//    }
//}

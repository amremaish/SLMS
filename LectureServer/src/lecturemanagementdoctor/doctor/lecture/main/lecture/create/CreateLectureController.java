/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.lecture.main.lecture.create;

import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.records.LectureRecord;
import lecturemanagementdoctor.doctor.Database.pojos.Lecture;
import lecturemanagementdoctor.doctor.DisplayServer;
import lecturemanagementdoctor.doctor.ref;

/**
 *
 * @author Saad Alenany
 */
public class CreateLectureController implements Initializable {

    @FXML
    private JFXButton startShareButton;

    public static boolean played = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Thread thread = new Thread(() -> {
            try {
                new DisplayServer(80);
            } catch (Exception ex) {
                Logger.getLogger(CreateLectureController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        thread.start();
    }

//    @FXML
//    protected void onUploadFiles() {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Upload New Files");
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
//                new FileChooser.ExtensionFilter("Power Point", "*.ppt", "*.pptx"),
//                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpej")
//        );
//        List<File> files = fileChooser.showOpenMultipleDialog(new Stage());
//        if (files != null) {
//            for (int i = 0; i < files.size(); i++) {
//                choosedFiles.add(files.get(i).getAbsolutePath());
//            }
//            listOfNewFiles.getItems().addAll(files);
//        }
//    }
//
//    @FXML
//    void OnStartLecture(ActionEvent event) {
//        if (choosedFiles.isEmpty()) {
//            return;
//        }
//        newBar.setVisible(true);
//        startButton.setDisable(true);
//        Convert con = new Convert(choosedFiles);
////        con.startCoverting();
////        con.setAfterConvertingListener(() -> {
////            newBar.setVisible(false);
////            startButton.setDisable(false);
////            System.out.println("End......");
////        });
//
//    }
    @FXML
    void OnStartShareScreenLecture(ActionEvent event) {

        if (DisplayServer.running) {
            startShareButton.setText("Resume share screen");
             DisplayServer.running = false ;
        } else {
            startShareButton.setText("Stop share screen");
            DisplayServer.running = true ;
        }
         
    }

}

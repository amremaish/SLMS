/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import static javafx.stage.StageStyle.UNDECORATED;

import lecturemanagementdoctor.doctor.Database.OuterDataBaseManager;
import lecturemanagementdoctor.doctor.Utility.LoadFXML;
import lecturemanagementdoctor.doctor.constant.FXMLPath;
import lecturemanagementdoctor.doctor.Utility.Stage.undecoratedStage.CustomStageStyle;
import lecturemanagementdoctor.doctor.lecture.openning.OpenningController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Saad Alenany
 */
public class launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        
        ref.MAIN_STAGE = stage;
        stage.setWidth(1000);
        stage.setHeight(649);
        ref.databaseManagerOuter = new OuterDataBaseManager();

        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.OPENNING);
        OpenningController controller = (OpenningController) load.getController();
        stage.initStyle(UNDECORATED);
        Scene scene = new Scene(load.getParent(), 900, 600);
        stage.setScene(scene);
        stage.show();
        CustomStageStyle.DragStageOption(stage, controller.getBorder());
        stage.getIcons().add(new Image(launcher.class.getResourceAsStream("/res/lecture.png")));

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import student.Utility.LoadFXML;
import student.Utility.Stage.undecoratedStage.StageStyle;
import student.constant.FXMLPath;
import student.opening.OpenningController;

/**
 *
 * @author dola2
 */
public class LectureClient extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(986.0);
        stage.setHeight(554.0);
        ref.MAIN_STAGE = stage;
        stage.setResizable(false);
        LoadFXML load = new LoadFXML();
        load.LoadFXML(FXMLPath.OPENING);
        OpenningController controller = (OpenningController) load.getController();

        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        Scene scene = new Scene(load.getParent(), 900, 600);
        stage.setScene(scene);
        stage.show();
        StageStyle.undecoratedStageOptions(stage, controller.getBorder());
        stage.getIcons().add(new Image(LectureClient.class.getResourceAsStream("/res/user_big.png")));

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

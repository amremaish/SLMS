/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.Utility;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Amr
 */
public class LoadFXML {

    private Stage stage;
    private Scene scene;
    private Parent parent;
    private Object controller;

    public void LoadFXML(String FXMLPath) {

        FXMLLoader loader = null;
        Parent root = null;
        try {
            loader = new FXMLLoader(getClass().getResource(FXMLPath));
            root = loader.load();
            this.parent = root;
            this.controller = loader.getController();
        } catch (IOException ex) {
            Logger.getLogger(LoadFXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Stage LoadStage(Parent root) {
        FXMLLoader loader = null;
        stage = new Stage();
        stage.setScene(new Scene(new StackPane(root)));
        stage.show();
        this.parent = root;
        this.controller = loader.getController();
        return stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public Parent getParent() {
        return parent;
    }

    public Object getController() {
        return controller;
    }

}

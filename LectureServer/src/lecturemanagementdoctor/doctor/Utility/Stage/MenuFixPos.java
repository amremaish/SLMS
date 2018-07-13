/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.Utility.Stage;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;


/**
 *
 * @author Amr
 */
public class MenuFixPos {

    public MenuFixPos(Scene scene, MenuBar bar) {

        scene.widthProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) -> {
           HBox.setMargin(bar, new Insets(0,(double)newSceneWidth - 40 - bar.getPrefWidth(),0,0));

        });

    }

}

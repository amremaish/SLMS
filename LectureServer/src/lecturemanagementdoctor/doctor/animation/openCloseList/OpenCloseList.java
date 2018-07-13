/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.animation.openCloseList;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lecturemanagementdoctor.doctor.animation.listener.afterAnimationListsner;

/**
 *
 * @author Amr
 */
public class OpenCloseList {
    private Timeline time;
    private int millis_Speed = 5;
    private double speed_value = 1.5;
    private Pane Container;
    private boolean once;
    private boolean isClosed;
    private JFXButton[] buttonList;
    private double OriginalWidth;
    private afterAnimationListsner afl;
    private double graphic_width;

    public OpenCloseList(JFXButton[] buttonList, Pane Container) {
        this.Container = Container;
        this.buttonList = buttonList;
        init();

    }

    private void init() {
        OriginalWidth = Container.getPrefWidth();
        ImageView graphic = (ImageView) buttonList[0].getGraphic();
        if (graphic != null) {
            graphic_width = graphic.getFitWidth();
        }
    }

    public void StartCloseList() {

        time = new Timeline();
        time.setCycleCount(Animation.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(millis_Speed), (ActionEvent event) -> {

            if (graphic_width > Container.getPrefWidth()) {
                if (afl != null) {
                    afl.handler();
                }
                isClosed = true;
                once = false;
                time.stop();
            }

            if (!once && buttonList[0].getGraphic() != null) {
                for (int i = 0; i < buttonList.length; i++) {
                    buttonList[i].setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                }
                once = true;
            }
            Container.setPrefWidth(Container.getPrefWidth() - speed_value);

        });

        time.getKeyFrames().add(keyFrame);
        time.play();
    }

    public void StartOpenList() {
        time = new Timeline();
        time.setCycleCount(Animation.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(millis_Speed), (ActionEvent event) -> {

            if (OriginalWidth <= Container.getPrefWidth()) {
                if (afl != null) {
                    afl.handler();
                }
                isClosed = false;
                once = false;
                time.stop();

                if (buttonList[0].getGraphic() != null) {
                    for (int i = 0; i < buttonList.length; i++) {
                        buttonList[i].setContentDisplay(ContentDisplay.LEFT);
                    }
                }
            }

            Container.setPrefWidth(Container.getPrefWidth() + speed_value);

        });

        time.getKeyFrames().add(keyFrame);
        time.play();
    }

    public void setAfterAnimationListener(afterAnimationListsner afl) {
        this.afl = afl;

    }

    public boolean isClosed() {
        return isClosed;
    }

}

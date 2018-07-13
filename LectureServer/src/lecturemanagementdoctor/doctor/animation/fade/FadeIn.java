/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.animation.fade;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.util.Duration;

/**
 *
 * @author Amr
 */
public class FadeIn extends Fade {

    private final int distance_width = 100, distance_Height = 100;

    public FadeIn(Parent parent, int type) {
        this.parent = parent;
        this.type = type;
        init();
        setAnimation();

    }

    private void init() {

        // shift parent distance 
        if (type == FADE_RIGHT) {
            parent.setTranslateX(parent.getTranslateX() - distance_width);
        } else if (type == FADE_LEFT) {
            parent.setTranslateX(parent.getTranslateX() + distance_width);
        } else if (type == FADE_DOWN) {
            parent.setTranslateY(parent.getTranslateY() - distance_Height);
        } else if (type == FADE_UP) {
            parent.setTranslateY(parent.getTranslateY() + distance_Height);
        }
        curOpacity = 0;
        parent.setOpacity(0);

    }

    private void setAnimation() {
        time = new Timeline();
        time.setCycleCount(Animation.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(millis_Speed), (ActionEvent event) -> {
            if (curOpacity >= 1) {
                if (afl != null) {
                    afl.handler();
                }
                stop();
            }

            if (type == FADE_RIGHT) {
                parent.setTranslateX(parent.getTranslateX() + MovingSpeed);

            } else if (type == FADE_LEFT) {
                parent.setTranslateX(parent.getTranslateX() - MovingSpeed);
            } else if (type == FADE_DOWN) {
                parent.setTranslateY(parent.getTranslateY() + MovingSpeed);
            } else if (type == FADE_UP) {
                parent.setTranslateY(parent.getTranslateY() - MovingSpeed);
            }
            curOpacity += OpacitySpeed;
            parent.setOpacity(curOpacity);
        });

        time.getKeyFrames().add(keyFrame);
       
    }

}

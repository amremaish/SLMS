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
public class FadeOut extends Fade {

    public FadeOut(Parent parent, int type) {
        this.parent = parent;
        this.type = type;
        setAnimation();
    }

    private void setAnimation() {
        time = new Timeline();
        time.setCycleCount(Animation.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(millis_Speed), (ActionEvent event) -> {
            if (curOpacity <= 0) {
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
            curOpacity -= OpacitySpeed;
            parent.setOpacity(curOpacity);
        });

        time.getKeyFrames().add(keyFrame);
    }

}

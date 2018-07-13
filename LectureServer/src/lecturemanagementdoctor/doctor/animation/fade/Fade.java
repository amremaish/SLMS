/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor.animation.fade;

import javafx.animation.Timeline;
import javafx.scene.Parent;
import lecturemanagementdoctor.doctor.animation.listener.afterAnimationListsner;

/**
 *
 * @author Amr
 */
public class Fade {

    public static int FADE_RIGHT = 1, FADE_LEFT = 2, FADE_DOWN = 3, FADE_UP = 4;
    
    protected int millis_Speed = 25 ;
    protected float OpacitySpeed = 0.05f;
    protected float MovingSpeed = 5.0f;
    protected float curOpacity = 1.0f;
    protected afterAnimationListsner afl;
    protected Parent parent;
    protected int type;
    protected Timeline time;
    protected boolean running;

    public void start() {
        if (running) {
            return;
        }
        time.play();
        running = true;
    }

    public void stop() {
        if (!running) {
            return;
        }
        time.stop();
        running = false;
    }

    public void setAfterAnimationListener(afterAnimationListsner afl) {
        this.afl = afl;

    }

    public float getOpacitySpeed() {
        return OpacitySpeed;
    }

    public void setOpacitySpeed(float OpacitySpeed) {
        this.OpacitySpeed = OpacitySpeed;
    }

    public int getType() {
        return type;
    }

    public boolean isRunning() {
        return running;
    }

}

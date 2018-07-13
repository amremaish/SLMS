package lecturemanagementdoctor.doctor.Utility.Stage.undecoratedStage;

import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author Amr
 */
public class CustomStageStyle {

    public static void undecoratedStageOptions(Stage stage, Parent root) {

        DragStage.DragStage(root, stage);
        ResizeHelper.addResizeListener(stage);

    }

    public static void DragStageOption(Stage stage, Parent root) {
        DragStage.DragStage(root, stage);
    }

    public static void ResizeHelperOption(Stage stage) {
          ResizeHelper.addResizeListener(stage);
    }
}

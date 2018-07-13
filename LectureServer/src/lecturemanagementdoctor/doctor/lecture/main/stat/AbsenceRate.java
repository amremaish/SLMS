package lecturemanagementdoctor.doctor.lecture.main.stat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Absence;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Lecture;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.records.LectureRecord;
import lecturemanagementdoctor.doctor.ref;
import org.jooq.Result;

/**
 *
 * @author Saad
 */
public class AbsenceRate extends Tab{

    NumberAxis xAxis;
    NumberAxis yAxis;

    ObservableList<String> lectures_dates;
    ObservableList<Integer> nos, lectures_ids;

    ListView listOfLectures;
    LineChart<Number, Number> lineChart;

    public AbsenceRate() {

        lectures_ids = FXCollections.observableArrayList();
        lectures_dates = FXCollections.observableArrayList();
        nos = FXCollections.observableArrayList();

        Result<LectureRecord> lectureRecords = ref.databaseManagerInner.getAll("Lecture").into(Lecture.LECTURE);
        for (LectureRecord r:lectureRecords) {
            lectures_ids.add(r.getLectureId());
            lectures_dates.add(r.getLectureDate());
        }

        listOfLectures = new ListView();
//        listOfLectures.setStyle("-fx-background-color : #3a8abf ;-fx-text-fill : #fff;-fx-border-color : #494949;");

        xAxis = new NumberAxis();
        yAxis = new NumberAxis();

        lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Lecture Absence timeline");

        yAxis.setLabel("number of Students");

        System.out.print("number of students ==> ");
        for (int x: lectures_ids) {
            nos.add(ref.create_innerDB.selectFrom(Absence.ABSENCE).where(Absence.ABSENCE.LECTURE_ID.eq(x)).fetch().size());
        }

        System.out.println();

        listOfLectures.setItems(lectures_dates);

        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setTickUnit(1);
        xAxis.setUpperBound(2);
//        xAxis.setMinorTickVisible(false);

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setTickUnit(1);
//        yAxis.setMinorTickVisible(false);

        //when pushing Enter button
        listOfLectures.setOnKeyTyped(e -> {
            reloadChart();
        });

        //when moving up & down
        listOfLectures.setOnKeyReleased(e -> {
            reloadChart();
        });

        //when selecting items by Mouse
        listOfLectures.setOnMouseClicked(e -> {
            reloadChart();
        });

        HBox hbox = new HBox();

        hbox.setPadding(new Insets(20, 0, 0, 10));
        hbox.setSpacing(20);

        hbox.getChildren().addAll(lineChart,listOfLectures);
        hbox.setPrefWidth(Double.MAX_VALUE);

//        hbox.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        setText("Absence Rate");
        setContent(hbox);
    }

    private void reloadChart() {
        lineChart.getData().clear();

        XYChart.Series series = new XYChart.Series();
        series.setName("Rate");

        if (listOfLectures.getSelectionModel().getSelectedIndex() < 0){return;}
        //populating the series with data
        String str = listOfLectures.getSelectionModel().getSelectedItem().toString();

        int lecture_index = lectures_dates.indexOf(str);

        series.getData().add(new XYChart.Data(1, nos.get(lecture_index)));

        yAxis.setUpperBound(nos.get(lecture_index)+1);

        lineChart.getData().add(series);

    }

}
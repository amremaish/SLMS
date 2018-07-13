package lecturemanagementdoctor.doctor.lecture.main.stat;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Quiz;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Student;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Studentdegrees;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.records.StudentRecord;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.records.StudentdegreesRecord;
import lecturemanagementdoctor.doctor.ref;
import org.jooq.Result;

/**
 *
 * @author Saad
 */
public class QuizRate extends Tab {

    HBox hbox;

    NumberAxis xAxis;
    NumberAxis yAxis;

    ObservableList<String> studentsnames;
    ObservableList<Integer> studentsmarks, quizesids;

    FilteredList<String> filterdata;
    ListView listOfStudents;
    LineChart<Number, Number> lineChart;

    JFXTextField filterfield;

    int studid = 0;

    String str = "";
    Result<StudentRecord> studentRecords;

    public QuizRate() {

        hbox = new HBox();
        xAxis = new NumberAxis();
        yAxis = new NumberAxis();

        studentRecords = ref.databaseManagerInner.getAll("Student").into(Student.STUDENT);

        studentsnames = FXCollections.observableArrayList();
        for (StudentRecord r: studentRecords) {
            studentsnames.add(r.getStudentName());
        }
        studentsmarks = FXCollections.observableArrayList();
        quizesids = FXCollections.observableArrayList();

        filterfield = new JFXTextField();

        hbox.setAlignment(Pos.CENTER);

        listOfStudents = new ListView();
//        listOfStudents.setStyle("-fx-background-color : #3a8abf ; -fx-text-fill : #fff; -fx-border-color : #494949; ");

        xAxis.setLabel("#Quiz ID");
        yAxis.setLabel("#Quiz Mark");

        lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle(str + " Quiz degree timeline");

        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setTickUnit(1);
//        xAxis.setMinorTickVisible(false);

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setTickUnit(10);
        yAxis.setUpperBound(100);
//        yAxis.setMinorTickVisible(false);

        //when pushing Enter button
        listOfStudents.setOnKeyTyped(e -> {
            reloadChart();
        });

        //when moving up & down
        listOfStudents.setOnKeyReleased(e -> {
            reloadChart();
        });

        //when selecting items by Mouse
        listOfStudents.setOnMouseClicked(e -> {
            reloadChart();
        });

        hbox.setPadding(new Insets(20, 0, 0, 10));
        hbox.setSpacing(20);

        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);

        filterfield.setLabelFloat(true);
        filterfield.setPromptText("Filter Students");

        vbox.getChildren().addAll(filterfield, listOfStudents);

        hbox.getChildren().addAll(lineChart, vbox);

        filterdata = new FilteredList<>(studentsnames, p -> true);

        listOfStudents.setItems(filterdata);

        filterfield.textProperty().addListener(obs -> {
            String filter = filterfield.getText();
            if (filter == null || filter.length() == 0) {
                filterdata.setPredicate(s -> true);
            } else {
                filterdata.setPredicate(s -> s.contains(filter));
            }
        });

        hbox.setPrefWidth(Double.MAX_VALUE);
//        hbox.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        this.setText("Quiz Rate");
        this.setContent(hbox);
    }

    private int getNumberOfAllQuizes() {
        return ref.databaseManagerInner.getAll("Quiz").into(Quiz.QUIZ).size();
    }

    private void reloadChart() {
        lineChart.getData().clear();

        XYChart.Series series = new XYChart.Series();
        series.setName("degree");

        studentsmarks.clear();
        quizesids.clear();

        if (listOfStudents.getSelectionModel().getSelectedIndex() < 0){
            return;
        }
        str = listOfStudents.getSelectionModel().getSelectedItem().toString();

        studid = ref.databaseManagerInner.getStudentIDByName(str);

        Result<StudentdegreesRecord> studentdegreesRecords = ref.create_innerDB.selectFrom(Studentdegrees.STUDENTDEGREES).where(Studentdegrees.STUDENTDEGREES.STUDENT_ID.eq(studid)).fetch().into(Studentdegrees.STUDENTDEGREES);
        for (StudentdegreesRecord r: studentdegreesRecords) {
            quizesids.add(r.getQuizId());
            double x = ((double)r.getDegree())/((double)r.getTotalDegree())*100.0;
            studentsmarks.add((int)x);
        }

        lineChart.setTitle(str + " Quiz degree timeline");

        //populating the series with data
        for (int i = 0; i < quizesids.size(); i++) {
            series.getData().add(new XYChart.Data(quizesids.get(i), studentsmarks.get(i)));
        }

        xAxis.setUpperBound(getNumberOfAllQuizes() + 1);
        lineChart.getData().add(series);

    }

}

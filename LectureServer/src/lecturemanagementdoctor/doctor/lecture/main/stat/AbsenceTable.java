package lecturemanagementdoctor.doctor.lecture.main.stat;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Absence;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Lecture;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.Student;
import lecturemanagementdoctor.doctor.ref;
import org.jooq.Result;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class AbsenceTable extends Tab{

    ObservableList<StudentAbsence> data;

    public AbsenceTable(){
        TableView table = new TableView();
        JFXTextField filterfield = new JFXTextField();

        data = FXCollections.observableArrayList();
        TableColumn username = new TableColumn("Student Name");
        TableColumn dep = new TableColumn("Student Department");
        TableColumn lecture_date = new TableColumn("Lecture Date");

        username.setPrefWidth(200);
        dep.setPrefWidth(200);
        lecture_date.setPrefWidth(200);

        username.setCellValueFactory(new PropertyValueFactory("student_name"));
        dep.setCellValueFactory(new PropertyValueFactory("student_department"));
        lecture_date.setCellValueFactory(new PropertyValueFactory("lecture_date"));

        table.getColumns().addAll(username,dep,lecture_date);

        //saad task
        VBox filterAndtable = new VBox();
        filterAndtable.setAlignment(Pos.CENTER);
        filterAndtable.setSpacing(20);
        filterAndtable.setPadding(new Insets(20,4,5,4));

        filterAndtable.getChildren().addAll(filterfield,table);

        filterfield.setLabelFloat(true);
        filterfield.setPromptText("Filter Absence");

        Map<lecturemanagementdoctor.doctor.Database.pojos.Student,
                List<lecturemanagementdoctor.doctor.Database.pojos.Lecture>> studentAbsences =
                ref.create_innerDB.select(Student.STUDENT.STUDENT_NAME,Student.STUDENT.STUDENT_DEPARTMENT,Lecture.LECTURE.LECTURE_DATE)
                .from(Student.STUDENT).innerJoin(Absence.ABSENCE).on(Student.STUDENT.STUDENT_ID.eq(Absence.ABSENCE.STUDENT_ID))
                .innerJoin(Lecture.LECTURE).on(Absence.ABSENCE.LECTURE_ID.eq(Lecture.LECTURE.LECTURE_ID))
                .orderBy(Lecture.LECTURE.LECTURE_DATE).fetchGroups(
                        r -> r.into(Student.STUDENT).into(lecturemanagementdoctor.doctor.Database.pojos.Student.class),
                        r -> r.into(Lecture.LECTURE).into(lecturemanagementdoctor.doctor.Database.pojos.Lecture.class)
                );

        Set<lecturemanagementdoctor.doctor.Database.pojos.Student> studentSet = studentAbsences.keySet();

        for (lecturemanagementdoctor.doctor.Database.pojos.Student s: studentSet) {
            for (lecturemanagementdoctor.doctor.Database.pojos.Lecture l: studentAbsences.get(s)) {
                data.add(new StudentAbsence(s.getStudent_name(),s.getStudent_department(),l.getLecture_date()));
            }
        }

        FilteredList<StudentAbsence> filterdata = new FilteredList<>(data, p -> true);

        filterfield.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<StudentAbsence>() {
                @Override
                public boolean test(StudentAbsence sa) {
                    // If filter text is empty, display all data.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if(sa.getStudent_name().toLowerCase().contains(lowerCaseFilter)){
                        return true; //filter matches student name
                    } else if(sa.getStudent_department().toLowerCase().contains(lowerCaseFilter)){
                        return true; //filter matches student department
                    } else if (sa.getLecture_date().toLowerCase().contains(lowerCaseFilter)){
                        return true; //filter matches lecture name
                    }
                    return false;
                }
            });
        });

        //bind sorted list comparator to tableview comparator in order of sorting
        SortedList<StudentAbsence> sortedlist = new SortedList<>(filterdata);
        sortedlist.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedlist);

        filterAndtable.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        this.setText("Absence Table");
        this.setContent(filterAndtable);
    }
}

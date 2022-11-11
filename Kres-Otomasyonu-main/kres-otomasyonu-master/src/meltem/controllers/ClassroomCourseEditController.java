package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import meltem.models.Course;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.ClassroomCourseRepository;
import meltem.view_models.CourseViewModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClassroomCourseEditController implements Initializable {
    public static Course courseToEdit = null;
    public static int route = 1;
    @FXML
    public Text txtClassroomCourseId;
    @FXML
    public TextField edtCourseId;
    @FXML
    public TextField edtCourseName;
    @FXML
    public TextField edtTeacherName;
    @FXML
    public TextField edtTeacherLastName;
    @FXML
    public TextField edtTeacherPhone;
    @FXML
    public TextField edtTeacherEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.edtCourseName.setText(courseToEdit.getCourseName());
        this.edtTeacherName.setText(courseToEdit.getTeacherName());
        this.edtTeacherLastName.setText(courseToEdit.getTeacherLastName());
        this.edtTeacherPhone.setText(courseToEdit.getTeacherPhone());
        this.edtTeacherEmail.setText(courseToEdit.getTeacherEmail());

    }

    public void addCourseToClass(ActionEvent event) {
    }

    public void updateCourse(ActionEvent event)  {
        Course newCourse = new Course(courseToEdit.getClassroomCourseId(),
                courseToEdit.getCourseId(),
                edtCourseName.getText(),
                edtTeacherName.getText(),
                edtTeacherLastName.getText(),
                edtTeacherPhone.getText(),
                edtTeacherEmail.getText()
        );
        ClassroomCourseRepository.Instance.UpdateById(newCourse, courseToEdit.getCourseId());
        goBack();
    }

    public void goBack(ActionEvent event) {
        SceneBuilder.Instance.BuildScene("classroom_info_admin");
    }

    public void goBack() {
        SceneBuilder.Instance.BuildScene("classroom_info_admin");
    }
}

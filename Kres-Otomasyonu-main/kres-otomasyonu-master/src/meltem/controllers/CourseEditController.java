package meltem.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import meltem.Main;
import meltem.models.Course;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.models.User;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.CourseViewModel;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CourseEditController implements Initializable {
    //Course course = new CourseViewModel(1, "Sabah Jimnastiği", "Neşe Sönmez", 2).course;
    @FXML
    public Text txtCourseId;
    @FXML
    public TextField txtCourseName;
    @FXML
    public TextField txtCourseTeacher;
    @FXML
    public Text indicatorId;
    @FXML
    public Button btnNew;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Main.userDataService.fetchById(SceneBuilder.routeData.id);
        if(SceneBuilder.routeData != null) {
            Logger.LogDebug(SceneBuilder.routeData.dataName);
            //txtCourseId.setText(String.valueOf(course.courseId));
            //txtCourseName.setText(course.courseName);
           // txtCourseTeacher.setText(course.teacherName);
        } else {
            if(txtCourseId != null) {
                txtCourseId.setDisable(false);
            }
            if(indicatorId != null) {
                indicatorId.setDisable(false);
            }
        }
    }
    public void update() throws IOException {
        SceneBuilder.Instance.BuildScene("course_edit", new RouteData(1, "course"));
    }
    public void delete() throws IOException {
        Logger.LogDebug("DELETE!");
        SceneBuilder.Instance.BuildScene("course_list");
    }
    @FXML
    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("course_list");
    }

    public void addCourseToClass(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("attendance_classroom_admin");
    }
}

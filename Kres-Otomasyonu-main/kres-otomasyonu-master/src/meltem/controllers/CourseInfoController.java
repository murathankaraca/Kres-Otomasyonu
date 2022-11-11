package meltem.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import meltem.models.Course;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.models.User;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.CourseViewModel;
import meltem.view_models.StudentViewModel;
import meltem.view_models.UserViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseInfoController implements Initializable {
   // Course course = new CourseViewModel(1, "Sabah Jimnastiği", "Neşe Sönmez", 2).course;
    @FXML
    public Text txtCourseId;
    @FXML
    public Text txtCourseName;
    @FXML
    public Text txtCourseTeacher;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("course_list");
    }
    public void update() throws IOException {
        SceneBuilder.Instance.BuildScene("course_edit", new RouteData(1, "course"));
    }
    public void delete() throws IOException {
        Logger.LogDebug("DELETE!");
        SceneBuilder.Instance.BuildScene("course_list");
    }
}

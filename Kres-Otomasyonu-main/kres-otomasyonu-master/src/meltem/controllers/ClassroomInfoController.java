package meltem.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import meltem.models.Classroom;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.models.User;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.ClassroomViewModel;
import meltem.view_models.CourseViewModel;
import meltem.view_models.StudentViewModel;
import meltem.view_models.UserViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClassroomInfoController implements Initializable {
    //Classroom classroom = new ClassroomViewModel(1, "A Sınıfı", 25, "Neşe Sönmez").classroom;
    @FXML
    public Text txtClassroomId;
    @FXML
    public Text txtClassroomName;
    @FXML
    public Text txtClassroomCapacity;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(txtClassroomId != null) {
            txtClassroomId.setText(String.valueOf(1));
        }
        txtClassroomName.setText("A Sınıfı");
        //txtClassroomCapacity.setText(String.valueOf(25));

    }

    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_list");
    }
    public void update() throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_edit", new RouteData(1, "user"));
    }
    public void delete() throws IOException {
        Logger.LogDebug("DELETE!");
        SceneBuilder.Instance.BuildScene("classroom_list");
    }

    public void findClassroom(ActionEvent event) {
    }

    public void goBackToClassroomAdmin(ActionEvent event) {
        SceneBuilder.Instance.BuildScene("attendance_classroom_admin");
    }
}

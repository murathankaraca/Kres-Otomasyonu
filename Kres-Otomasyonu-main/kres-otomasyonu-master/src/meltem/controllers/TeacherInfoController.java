package meltem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import meltem.Main;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.models.Teacher;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.StudentViewModel;
import meltem.view_models.TeacherViewModel;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherInfoController implements Initializable {
    public static Teacher teacher;
    public static int route = 1;
    @FXML
    public Text txtFullName;
    @FXML
    public Text txtTeacherId;
    @FXML
    public Text txtTeacherName;
    @FXML
    public Text txtTeacherLastName;
    @FXML
    public Text txtTeacherPhone;
    @FXML
    public Text txtTeacherEmail;
    @FXML
    public Text txtTeacherType;
    @FXML
    public Button btnNew;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (teacher != null) {
            txtFullName.setText("Teacher #" + teacher.getTeacherId());
            txtTeacherId.setText(String.valueOf(teacher.getTeacherId()));
            txtTeacherName.setText(teacher.getTeacherName());
            txtTeacherLastName.setText(teacher.getTeacherLastName());
            txtTeacherEmail.setText(teacher.getTeacherEmail());
            txtTeacherType.setText(teacher.getTeacherType());
        }
    }

    @FXML
    public void goBack() throws IOException {
        switch (route) {
            case 0:
                SceneBuilder.Instance.BuildScene("teacher_list");
                break;
            case 1:
                SceneBuilder.Instance.BuildScene("teacher_list");
                break;
            case 2:
                SceneBuilder.Instance.BuildScene("teacher_list");
                break;
        }
    }
}

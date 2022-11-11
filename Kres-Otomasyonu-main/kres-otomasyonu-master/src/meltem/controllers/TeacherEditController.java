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
import meltem.services.data_access.concrete.TeacherRepository;
import meltem.services.logging.Logger;
import meltem.view_models.StudentViewModel;
import meltem.view_models.TeacherViewModel;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherEditController implements Initializable {

    public static int TeacherId = -1;

    @FXML
    public Text txtTeacherId;
    @FXML
    public TextField txtTeacherName;
    @FXML
    public TextField txtTeacherLastName;
    @FXML
    public TextField txtTeacherPhone;
    @FXML
    public TextField txtTeacherEmail;
    @FXML
    public Text txtTeacherType;

    public Teacher teacher = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(TeacherId != -1) {
            teacher = TeacherRepository.Instance.fetchById(TeacherId);
            txtTeacherId.setText(String.valueOf(teacher.getTeacherId()));
            txtTeacherName.setText(teacher.getTeacherName());
            txtTeacherLastName.setText(teacher.getTeacherLastName());
            txtTeacherEmail.setText(teacher.getTeacherEmail());
            txtTeacherType.setText(teacher.getTeacherType());
        }
    }

    @FXML
    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_list");
    }

    @FXML
    public void goBackToClassroomAdmin() throws IOException {
        SceneBuilder.Instance.BuildScene("attendance_classroom_admin");
    }

    @FXML
    public void update() {
        teacher.setTeacherName(txtTeacherName.getText());
        teacher.setTeacherLastName(txtTeacherLastName.getText());
        teacher.setTeacherPhone(txtTeacherPhone.getText());
        teacher.setTeacherEmail(txtTeacherEmail.getText());

        TeacherRepository.Instance.UpdateById(teacher, TeacherId);

        SceneBuilder.Instance.BuildScene("teacher_list");
    }
}

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
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.StudentRepository;
import meltem.services.logging.Logger;
import meltem.view_models.StudentViewModel;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentSearchController implements Initializable {
    public static Student student;
    public static int route = 1;
    // 1: student_list
    // 2: classroom
    // 3: branch
    @FXML
    public Text txtStudentId;
    @FXML
    public Text txtStudentName;
    @FXML
    public Text txtStudentLastName;
    @FXML
    public Text txtOryantasyonBas;
    @FXML
    public Text txtOryantasyonBit;
    @FXML
    public Text txtParentNumber;
    @FXML
    public Text txtParentName;
    @FXML
    public Text txtParentLastName;
    @FXML
    public Text txtParentEmail;
    @FXML
    public Text txtFullName;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtStudentId.setText(String.valueOf(student.getStudentId()));
        txtStudentName.setText(student.getStudentName());
        txtStudentLastName.setText(student.getStudentLastName());
        txtOryantasyonBas.setText(student.getOrientationStart());
        txtOryantasyonBit.setText(student.getOrientationEnd());
        txtParentNumber.setText(student.getParentNumber());
        txtParentName.setText(student.getParentName());
        txtParentLastName.setText(student.getParentLastName());
        txtParentEmail.setText(student.getParentEmail());
        txtFullName.setText("Öğrenci #" + student.getStudentId());
    }

    @FXML
    public void goBack() throws IOException {
        switch(route) {
            case 1:
                SceneBuilder.Instance.BuildScene("student_list");
                break;
            case 2:
                SceneBuilder.Instance.BuildScene("student_list");
                break;
            case 3:
                SceneBuilder.Instance.BuildScene("student_list");
                break;
        }

    }
}

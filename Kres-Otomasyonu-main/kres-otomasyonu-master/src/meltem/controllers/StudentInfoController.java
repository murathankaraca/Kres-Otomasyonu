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

public class StudentInfoController implements Initializable {
    public static int StudentId = -1;
    Student student = null;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            student = new StudentViewModel(StudentRepository.Instance.fetchById(StudentId)).student;

            if(StudentId != -1) {
                txtStudentId.setText(String.valueOf(StudentId));
                txtStudentName.setText(student.getStudentName());
                txtStudentLastName.setText(student.getStudentLastName());
                txtOryantasyonBas.setText(student.getOrientationStart());
                txtOryantasyonBit.setText(student.getOrientationEnd());
                txtParentNumber.setText(student.getParentNumber());
                txtParentName.setText(student.getParentName());
                txtParentLastName.setText(student.getParentLastName());
                txtParentEmail.setText(student.getParentEmail());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void goBack() throws IOException {
        switch(Main.user.getUserAuth()) {
            case 1:
                SceneBuilder.Instance.BuildScene("student_info");
                break;
            case 2:
                SceneBuilder.Instance.BuildScene("student_info");
                break;
            case 3:
                SceneBuilder.Instance.BuildScene("branch_course_single");
                break;
        }

    }

    public void add(ActionEvent actionEvent)  {
        Student studentToAdd = new Student(
                student.getStudentId(),
                txtStudentName.getText(),
                txtStudentLastName.getText(),
                txtOryantasyonBas.getText(),
                txtOryantasyonBit.getText(),
                txtParentName.getText(),
                txtParentLastName.getText(),
                txtParentNumber.getText(),
                txtParentEmail.getText(),
                500
        );

        try {
            StudentRepository.Instance.Add(studentToAdd);
            Logger.LogDebug(StudentRepository.Instance.fetchById(studentToAdd.getStudentId()).getParentName());
            SceneBuilder.Instance.BuildScene("student_list");
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }
}

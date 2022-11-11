package meltem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import meltem.models.Classroom;
import meltem.models.RouteData;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.ClassroomRepository;
import meltem.services.data_access.concrete.TeacherRepository;
import meltem.services.logging.Logger;
import meltem.view_models.ClassroomViewModel;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClassroomEditController implements Initializable {
    public static Classroom classroom;

    public TextField txtClassroomName;
    public TextField txtTeacherName;
    public TextField txtTeacherLastName;
    public TextField txtClassroomCapacity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(classroom != null) {
            txtClassroomName.setText(classroom.getClassroomName());
            txtTeacherName.setText(classroom.getClassroomTeacherName());
            txtTeacherLastName.setText(classroom.getClassroomTeacherLastName());
            txtClassroomCapacity.setText(String.valueOf(classroom.getClassroomCapacity()));
        } else {
            classroom = new Classroom();
        }
    }
    @FXML
    public void goBack() throws IOException {
        classroom = null;
        SceneBuilder.Instance.BuildScene("attendance_classroom_admin");
    }

    public void addClassroom(ActionEvent event) {
        int teacherIndex = TeacherRepository.Instance.returnLast().getTeacherId() + 1;

        classroom.setClassroomName(txtClassroomName.getText());
        classroom.setClassroomTeacherName(txtTeacherName.getText());
        classroom.setClassroomTeacherLastName(txtTeacherLastName.getText());
        classroom.setClassroomCapacity(Integer.parseInt(txtClassroomCapacity.getText()));

        Logger.LogDebug(String.valueOf(teacherIndex) + " IS INDEX!!!");
        ClassroomRepository.Instance.Add(classroom, teacherIndex);
        classroom = null;
        SceneBuilder.Instance.BuildScene("attendance_classroom_admin");
    }

    public void editClassroom(ActionEvent event) {
        ClassroomRepository.Instance.UpdateById(classroom, classroom.getClassroomTeacherId());
        classroom = null;
        SceneBuilder.Instance.BuildScene("attendance_classroom_admin");
    }
}

package meltem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import meltem.models.Branch;
import meltem.models.Classroom;
import meltem.models.Teacher;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.BranchRepository;
import meltem.services.data_access.concrete.ClassroomRepository;
import meltem.services.data_access.concrete.TeacherRepository;
import meltem.services.logging.Logger;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BranchEditController implements Initializable {
    public static Branch branch;

    public TextField txtBranchName;
    public TextField txtTeacherName;
    public TextField txtTeacherLastName;
    public TextField txtBranchCapacity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(branch != null) {
            txtBranchName.setText(branch.getBranchCourseName());
            txtTeacherName.setText(branch.getBranchTeacherName());
            txtTeacherLastName.setText(branch.getBranchTeacherLastName());
            txtBranchCapacity.setText(String.valueOf(branch.getBranchCapacity()));
        } else {
            branch = new Branch(-1, "", 1, 25);
        }
    }
    @FXML
    public void goBack() throws IOException {
        branch = null;
        SceneBuilder.Instance.BuildScene("attendance_branch");
    }

    public void addBranch(ActionEvent event) {
        Teacher teacherToAdd = new Teacher(-1, txtTeacherName.getText(), txtTeacherLastName.getText(), "", "", 3);
        Logger.LogDebug("-------------------------- BRANCH TEACHER NAME: " + txtTeacherName.getText());

        TeacherRepository.Instance.Add(teacherToAdd, teacherToAdd.getTeacherAuth());
        int teacherIndex = TeacherRepository.Instance.returnLast().getTeacherId();

        branch.setBranchCourseName(txtBranchName.getText());
        branch.setBranchTeacherName(txtTeacherName.getText());
        branch.setBranchTeacherLastName(txtTeacherLastName.getText());
        branch.setBranchCapacity(Integer.parseInt(txtBranchCapacity.getText()));

        Logger.LogDebug(String.valueOf(teacherIndex) + " IS INDEX!!!");
        BranchRepository.Instance.Add(branch, teacherIndex);
        branch = null;
        SceneBuilder.Instance.BuildScene("attendance_branch");
    }

    public void editBranch(ActionEvent event) {
        BranchRepository.Instance.UpdateById(branch, branch.getBranchId());
        branch = null;
        SceneBuilder.Instance.BuildScene("attendance_branch");
    }
}

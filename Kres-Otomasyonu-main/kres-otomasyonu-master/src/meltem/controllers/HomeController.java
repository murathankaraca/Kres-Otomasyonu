package meltem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;

import java.io.IOException;

public class HomeController {
    public Button btnUsers;
    @FXML
    Button btnStudents;
    @FXML
    Button btnClassrooms;
    @FXML
    Button btnBranch;
    @FXML
    Button btnMeetings;
    @FXML
    Button btnEmergency;
    @FXML
    public void initialize() throws Exception {

    }

    @FXML
    private void loadClassroom() throws IOException {
    SceneBuilder.Instance.BuildScene("attendance_classroom");
    }

    @FXML
    private void loadBranch() throws IOException {
        SceneBuilder.Instance.BuildScene("branch_course_single");
    }

    @FXML
    private void loadEmergency() throws IOException {
        SceneBuilder.Instance.BuildScene("emergency_numbers");
    }


    public void quit(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("login");
    }

    public void loadUserInfo(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("user_info_notadmin");
    }
}

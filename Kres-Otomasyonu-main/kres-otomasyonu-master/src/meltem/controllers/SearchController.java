package meltem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import meltem.services.SceneBuilder;

import java.io.IOException;

public class SearchController {
    @FXML
    public void goStudent() throws IOException {
        StudentsController.route = 2;
        SceneBuilder.Instance.BuildScene("student_list");
    }
    @FXML
    public void goClasses() throws IOException {
        SceneBuilder.Instance.BuildScene("attendance_classroom_admin");
    }
    @FXML
    public void goCourses() throws IOException {
        SceneBuilder.Instance.BuildScene("attendance_branch");
    }
    @FXML
    public void goTeachers() throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_list");
    }

    public void goMeetings(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("meeting_list");
    }

    public void goUsers(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("user_list");
    }

    public void quit(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("login");
    }
}

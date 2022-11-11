package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import meltem.Main;
import meltem.models.Teacher;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.TeacherRepository;
import meltem.services.logging.Logger;
import meltem.services.search.TeacherSearch;
import meltem.services.search.UserSearch;
import meltem.view_models.TeacherViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeachersController implements Initializable {

    public static int route = 0;
    private int selectedId = 1;

    @FXML
    public TextField txtTeacherInfo;
    @FXML
    public CheckBox chkId;
    @FXML
    public CheckBox chkName;

    public ObservableList<TeacherViewModel> teachers = FXCollections.observableArrayList(
            fetchAllModelsForTeachers()
    );

    public List<TeacherViewModel> fetchAllModelsForTeachers()  {
        List<TeacherViewModel> teacherVMs = new ArrayList<>();
        try {
            List<Teacher> teachers = TeacherRepository.Instance.fetchAll();
            for (Teacher teacher: teachers) {
                teacherVMs.add(new TeacherViewModel(teacher));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return teacherVMs;
    }

    @FXML
    Button btnUpdateTeacher;

    public TableColumn<TeacherViewModel, SimpleIntegerProperty> colTeacherId;
    public TableColumn<TeacherViewModel, SimpleStringProperty> colTeacherName;
    public TableColumn<TeacherViewModel, SimpleStringProperty> colTeacherLastName;
    public TableColumn<TeacherViewModel, SimpleStringProperty> colTeacherPhone;
    public TableColumn<TeacherViewModel, SimpleStringProperty> colTeacherEmail;
    public TableColumn<TeacherViewModel, SimpleStringProperty> colTeacherAuth;

    @FXML
    private TableView<TeacherViewModel> table = new TableView<TeacherViewModel>();

    @FXML
    public void getCourseViewModel(MouseEvent actionEvent) {
        if(table.getSelectionModel().getSelectedItem() != null) {
            selectedId = table.getSelectionModel().getSelectedItem().teacher.getTeacherId();
            TeacherEditController.TeacherId = selectedId;
            Logger.LogDebug(String.valueOf(TeacherEditController.TeacherId) + " is the selected ID.");
            btnUpdateTeacher.setDisable(false);
        }
    }

    public void goBack(ActionEvent event) throws IOException {
        switch(route) {
            case 1:
                SceneBuilder.Instance.BuildScene("classroom_info_admin");
                break;
            case 2:
                SceneBuilder.Instance.BuildScene("student_list");
                break;
            case 3:
                SceneBuilder.Instance.BuildScene("branch_courses");
                break;
        }
    }

    public void goBackToMenu(ActionEvent event) throws IOException {
        switch(Main.user.getUserAuth()) {
            case 1:
                SceneBuilder.Instance.BuildScene("search_page");
                break;
            case 2:
                SceneBuilder.Instance.BuildScene("home_class");
                break;
            case 3:
                SceneBuilder.Instance.BuildScene("home_branch");
                break;
        }
    }

    public void proceedToEdit(ActionEvent event) throws IOException {
        TeacherEditController.TeacherId = selectedId;
        SceneBuilder.Instance.BuildScene("teacher_edit");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnUpdateTeacher.setDisable(true);
        colTeacherId.setCellValueFactory(
                teacher -> teacher.getValue().teacherId
        );
        colTeacherName.setCellValueFactory(
                teacher -> teacher.getValue().teacherName
        );
        colTeacherLastName.setCellValueFactory(
                teacher -> teacher.getValue().teacherLastName
        );
        colTeacherPhone.setCellValueFactory(
                teacher -> teacher.getValue().teacherPhone
        );
        colTeacherEmail.setCellValueFactory(
                teacher -> teacher.getValue().teacherEmail
        );
        colTeacherAuth.setCellValueFactory(
                teacher -> teacher.getValue().teacherType
        );
        table.setItems(teachers);
        table.setFixedCellSize(75);
    }

    public void searchTeacher() {
        TeacherSearch ts = new TeacherSearch();
        String searchParam = txtTeacherInfo.getText();
        Logger.LogDebug(searchParam + " is the input for the search.");
        if(chkId.isSelected()) {
            ts.searchById(searchParam);
        } else if(chkName.isSelected()) {
            ts.searchByUsername(searchParam);
        }
    }
}

package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import meltem.Main;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.StudentRepository;
import meltem.services.logging.Logger;
import meltem.services.search.StudentSearch;
import meltem.view_models.StudentViewModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

//new GregorianCalendar(2020,0,31)

public class StudentsController implements Initializable {
    public static int route = 0;
    @FXML
    public Button btnUpdateStudent;
    @FXML
    public TableView<StudentViewModel> tableStudents;
    @FXML
    public TableColumn<StudentViewModel, SimpleIntegerProperty> colStudentId;
    @FXML
    public TableColumn<StudentViewModel, SimpleStringProperty> colStudentName;
    @FXML
    public TableColumn<StudentViewModel, SimpleStringProperty> colStudentLastName;
    @FXML
    public TableColumn<StudentViewModel, SimpleStringProperty> colParentPhone;
    @FXML
    public TableColumn<StudentViewModel, SimpleStringProperty> colOrientationStart;
    @FXML
    public TableColumn<StudentViewModel, SimpleStringProperty> colOrientationEnd;
    @FXML
    public Button btnParentInfo;
    @FXML
    public Button btnPaymentInfo;
    @FXML
    public Button btnEdit;
    @FXML
    public TextField txtStudentId;

    public ObservableList<StudentViewModel> studentVMs = FXCollections.observableArrayList();
    @FXML
    public TextField txtStudentInfo;
    @FXML
    public CheckBox chkId;
    @FXML
    public CheckBox chkName;
    @FXML
    public Button btnAddToClass;
    @FXML
    public Button btnAddToBranch;

    private int selectedId;

    public StudentsController() throws SQLException {
    }

    public void findStudent() throws IOException {
        StudentSearch ss = new StudentSearch();
        String searchParam = txtStudentInfo.getText();
        Logger.LogDebug(searchParam + " is the input for the search.");
        if(chkId.isSelected()) {
            ss.searchById(searchParam);
        } else if(chkName.isSelected()) {
            ss.searchByName(searchParam);
        }
    }
    public void findStudentForClass() throws IOException {
        int studentId = Integer.parseInt(txtStudentId.getText());
        if(studentId != 0) {
            SceneBuilder.Instance.BuildScene("student_info_branch", new RouteData(studentId, "student"));
        }
    }

    public final ObservableList<StudentViewModel> data = FXCollections.observableArrayList(
            fetchAllModelsForStudents()
    );

    public List<StudentViewModel> fetchAllModelsForStudents() throws SQLException {
        List<Student> students = StudentRepository.Instance.fetchAll();
        for (Student student: students) {
            studentVMs.add(new StudentViewModel(student));
        }
        return studentVMs;
    }

    private void getCourseViewModel() {
        if(tableStudents.getSelectionModel().getSelectedItem() != null) {
            selectedId = tableStudents.getSelectionModel().getSelectedItem().student.getStudentId();
            StudentEditController.StudentId = selectedId;
            ModalClassroomStudentController.StudentToAdd = tableStudents.getSelectionModel().getSelectedItem().student;
            ModalBranchController.student = tableStudents.getSelectionModel().getSelectedItem().student;
            Logger.LogDebug(String.valueOf(selectedId) + " is the selected ID.");
            btnUpdateStudent.setDisable(false);
            btnAddToBranch.setDisable(false);
            btnAddToClass.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableStudents.setFixedCellSize(50);

        colStudentId.setCellValueFactory(
                student -> student.getValue().studentId
        );

        // First Name
        colStudentName.setCellValueFactory(
                student -> student.getValue().studentName
        );
        // Last Name
        colStudentLastName.setCellValueFactory(
                student -> student.getValue().studentLastName
        );
        // Orientation Start
        colParentPhone.setCellValueFactory(
                student -> student.getValue().parentNumber
        );
        colOrientationStart.setCellValueFactory(
                student -> student.getValue().orientationStart
        );
        colOrientationEnd.setCellValueFactory(
                student -> student.getValue().orientationEnd
        );


        chkId.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                chkName.setSelected(false);
            }
        });

        chkName.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                chkId.setSelected(false);
            }
        });
        tableStudents.setItems(data);

        tableStudents.setOnMouseClicked(v -> getCourseViewModel());

    }

    public void proceedToAdd() throws IOException {
        StudentNewController.route = 2;
        SceneBuilder.Instance.BuildScene("student_new");
    }

    public void proceedToEdit() throws IOException {
        StudentEditController.route = 2;
        SceneBuilder.Instance.BuildScene("student_edit");
    }
    public void proceedToInfo() throws IOException {
        SceneBuilder.Instance.BuildScene("student_info", new RouteData(selectedId, "student"));
    }
    public void proceedToUneditableInfo() throws IOException {
        SceneBuilder.Instance.BuildScene("student_info_branch", new RouteData(selectedId, "student"));
    }

    @FXML
    public void goBackToBranch(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("attendance_branch");
    }

    @FXML
    public void goBackToClassroom(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("attendance_classroom_admin");
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

    public void proceedToAssignBranch(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("student_assign_branch");
    }
    public void proceedToAssignClassroom(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("student_assign_branch");
    }

    public void proceedToAddToClassroom(ActionEvent event) {
        SceneBuilder.Instance.BuildModal("modal_classroom_courses", event);
    }

    public void proceedToAddToBranch(ActionEvent event) {
        SceneBuilder.Instance.BuildModal("modal_branches", event);
    }
}

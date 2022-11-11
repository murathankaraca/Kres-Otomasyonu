package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import meltem.models.*;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.BranchRepository;
import meltem.services.data_access.concrete.StudentRepository;
import meltem.services.logging.Logger;
import meltem.view_models.ClassroomViewModel;
import meltem.view_models.CourseViewModel;
import meltem.view_models.StudentViewModel;
import meltem.view_models.UserViewModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminBranchInfoController implements Initializable {
    public static int BranchId = -1;
    public static String BranchTeacherFullName = "";
    public static Branch branch;
    public Student selectedStudent;
    @FXML
    public Button btnUpdateStudent;
    @FXML
    public TableView<StudentViewModel> tableStudents;
    @FXML
    public Text txtBranchId;
    @FXML
    public Text txtBranchName;
    @FXML
    public Text txtBranchCapacity;
    @FXML
    public Text txtBranchTeacherName;

    @FXML
    public TableColumn<StudentViewModel, SimpleIntegerProperty> colStudentId;
    @FXML
    public TableColumn<StudentViewModel, SimpleStringProperty> colStudentName;
    @FXML
    public TableColumn<StudentViewModel, SimpleStringProperty> colStudentLastName;
    @FXML
    public TableColumn<StudentViewModel, SimpleStringProperty> colParentPhone;

    public ObservableList<StudentViewModel> studentTable;

    public ArrayList<StudentViewModel> studentList = new ArrayList<>();

    private List<Student> studentModels = new ArrayList<>();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            Logger.LogDebug(BranchId + " ID IN BRANCH INFO CONTROLLER!");

            txtBranchId.setText(String.valueOf(branch.getBranchId()));
            txtBranchName.setText(branch.getBranchCourseName());
            txtBranchCapacity.setText(String.valueOf(branch.getBranchCapacity()));
            txtBranchTeacherName.setText(branch.getBranchTeacherName() + " " + branch.getBranchTeacherLastName());


            studentModels = BranchRepository.Instance.fetchAllStudents(BranchId);

            studentTable = FXCollections.observableArrayList(
                    fetchAllModelsForStudents()
            );

            tableStudents.setItems(studentTable);

            tableStudents.setFixedCellSize(50);

            colStudentId.setCellValueFactory(
                    course -> course.getValue().studentId
            );
            colStudentName.setCellValueFactory(
                    course -> course.getValue().studentName
            );
            colStudentLastName.setCellValueFactory(
                    course -> course.getValue().studentLastName
            );
            colParentPhone.setCellValueFactory(
                    course -> course.getValue().parentNumber
            );

            tableStudents.setFixedCellSize(60);

            tableStudents.setOnMouseClicked(v -> getStudentViewModel());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getStudentViewModel() {
        if(tableStudents.getSelectionModel().getSelectedItem() != null) {
            btnUpdateStudent.setDisable(false);
            selectedStudent = tableStudents.getSelectionModel().getSelectedItem().student;
            Logger.LogDebug(selectedStudent.getParentNumber());
        }
    }

    public ArrayList<StudentViewModel> fetchAllModelsForStudents() {
        for (Student student: studentModels) {
            studentList.add(new StudentViewModel(student));
        }
        return studentList;
    }

    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_list");
    }
    public void update() throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_edit", new RouteData(1, "user"));
    }

    public void goBackToBranchAdmin(ActionEvent event) {
        SceneBuilder.Instance.BuildScene("attendance_branch");
    }

    public void goBranchStudentEdit(ActionEvent event) {
        StudentNewController.route = 3;
        StudentNewController.StudentId = selectedStudent.getStudentId();
        SceneBuilder.Instance.BuildScene("branch_student_edit");
    }

    public void goBranchStudentNew(ActionEvent event) {
        StudentNewController.StudentId = -1;
        StudentNewController.route = 3;
        StudentNewController.BranchCourseId = BranchId;
        SceneBuilder.Instance.BuildScene("branch_student_new");
    }

}

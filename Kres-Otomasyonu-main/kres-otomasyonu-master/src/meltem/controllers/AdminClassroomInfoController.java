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
import meltem.services.data_access.concrete.ClassroomAttendanceRepository;
import meltem.services.data_access.concrete.ClassroomCourseRepository;
import meltem.services.data_access.concrete.ClassroomRepository;
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

public class AdminClassroomInfoController implements Initializable {
    public static int CourseId = -1;
    public static int ClassroomId = -1;
    public static String ClassroomTeacherFullName = "";
    public static Classroom classroom;
    public Student selectedStudent;
    @FXML
    public Button btnShowStudents;
    @FXML
    public Button btnShowCourses;
    @FXML
    public Button btnUpdateStudent;
    @FXML
    public Button btnUpdateCourse;
    @FXML
    public StackPane showStack;
    @FXML
    public TableView<StudentViewModel> tableStudents;
    @FXML
    public TableView<CourseViewModel> tableCourses;
    @FXML
    public Text txtClassroomId;
    @FXML
    public Text txtClassroomName;
    @FXML
    public Text txtClassroomCapacity;
    @FXML
    public Text txtClassroomTeacherName;
    @FXML
    public TableColumn<CourseViewModel, SimpleIntegerProperty> colCourseId;
    @FXML
    public TableColumn<CourseViewModel, SimpleStringProperty> colCourseName;
    @FXML
    public TableColumn<CourseViewModel, SimpleStringProperty> colTeacherName;
    @FXML
    public TableColumn<CourseViewModel, SimpleStringProperty> colTeacherLastName;
    @FXML
    public TableColumn<CourseViewModel, SimpleStringProperty> colTeacherPhone;

    public ObservableList<CourseViewModel> courseTable;

    public ArrayList<CourseViewModel> courseList = new ArrayList<>();

    private List<Course> courseModels = new ArrayList<>();

    //

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

            //Classroom classroom = ClassroomRepository.Instance.fetchById(ClassroomId);
            Logger.LogDebug(ClassroomId + " ID IN CLASSROOM INFO CONTROLLER!");
            if(txtClassroomId != null) {
                txtClassroomId.setText(String.valueOf(classroom.getClassroomId()));
            }

            txtClassroomName.setText(classroom.getClassroomName());
            txtClassroomCapacity.setText(String.valueOf(classroom.getClassroomCapacity()));
            txtClassroomTeacherName.setText(classroom.getClassroomTeacherName() + " " + classroom.getClassroomTeacherLastName());

            tableCourses.setOnMouseClicked(v -> getCourseViewModel());

            tableStudents.setOnMouseClicked(v -> getStudentViewModel());

            colCourseId.setCellValueFactory(
                    course -> course.getValue().courseId
            );
            colCourseName.setCellValueFactory(
                    course -> course.getValue().courseName
            );
            colTeacherName.setCellValueFactory(
                    course -> course.getValue().teacherName
            );
            colTeacherLastName.setCellValueFactory(
                    course -> course.getValue().teacherLastName
            );
            colTeacherPhone.setCellValueFactory(
                    course -> course.getValue().teacherPhone
            );

            Logger.LogDebug(ClassroomId + " is the classroom ID. [AdminClassroomInfoController]");
            studentModels = ClassroomAttendanceRepository.Instance.fetchAll(ClassroomId);

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

            courseModels = ClassroomCourseRepository.Instance.fetchAll();

            courseTable = FXCollections.observableArrayList(
                    fetchAllModels()
            );

            tableCourses.setItems(courseTable);
            tableCourses.setFixedCellSize(50);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getCourseViewModel() {
        if(tableCourses.getSelectionModel().getSelectedItem() != null) {
            btnUpdateCourse.setDisable(false);
            AdminClassroomInfoController.ClassroomId = tableCourses.getSelectionModel().getSelectedItem().course.getClassroomCourseId();
            ClassroomCourseEditController.courseToEdit = tableCourses.getSelectionModel().getSelectedItem().course;
            Logger.LogDebug(AdminClassroomInfoController.ClassroomId + " IS THE STATIC ID!");
        }
    }

    private void getStudentViewModel() {
        if(tableStudents.getSelectionModel().getSelectedItem() != null) {
            btnUpdateStudent.setDisable(false);
            selectedStudent = tableStudents.getSelectionModel().getSelectedItem().student;
            Logger.LogDebug(selectedStudent.getParentNumber());
        }
    }

    public ArrayList<CourseViewModel> fetchAllModels() {
        for (Course course: courseModels) {
            courseList.add(new CourseViewModel(course));
        }
        return courseList;
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
    public void delete() throws IOException {
        Logger.LogDebug("DELETE!");
        SceneBuilder.Instance.BuildScene("classroom_list");
    }

    public void goClassroomCourseEdit(ActionEvent event) {
        StudentEditController.route = 1;
        SceneBuilder.Instance.BuildScene("classroom_course_edit");
    }

    public void goClassroomCourseNew(ActionEvent event) {
        SceneBuilder.Instance.BuildScene("classroom_course_new");
    }

    public void goBackToClassroomAdmin(ActionEvent event) {
        SceneBuilder.Instance.BuildScene("attendance_classroom_admin");
    }

    public void changeStackToStudents(ActionEvent event) {
        tableCourses.setVisible(false);
        tableStudents.setVisible(true);
    }

    public void changeStackToCourses(ActionEvent event) {
        tableStudents.setVisible(false);
        tableCourses.setVisible(true);
    }

    public void goClassroomStudentNew(ActionEvent event) {
        StudentNewController.ClassroomId = Integer.parseInt(txtClassroomId.getText());
        StudentNewController.route = 1;
        SceneBuilder.Instance.BuildScene("student_new_classroom");
    }

    public void goClassroomStudentEdit(ActionEvent event) {
        StudentEditController.StudentId = selectedStudent.getStudentId();
        StudentEditController.route = 1;
        SceneBuilder.Instance.BuildScene("student_edit_classroom");
    }
}

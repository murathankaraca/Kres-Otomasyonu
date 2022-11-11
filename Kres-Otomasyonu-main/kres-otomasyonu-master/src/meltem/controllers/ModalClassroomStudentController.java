package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import meltem.models.Classroom;
import meltem.models.Student;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.ClassroomRepository;
import meltem.services.data_access.concrete.StudentRepository;
import meltem.services.logging.Logger;
import meltem.view_models.ClassroomViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ModalClassroomStudentController implements Initializable {
    private int selectedId = -1;
    public static Student StudentToAdd;
    public static int ClassroomId = -1;
    @FXML
    public Button btnShowClassInfo;
    @FXML
    public TableView<ClassroomViewModel> classroomTable;
    @FXML
    public TableColumn<ClassroomViewModel, SimpleIntegerProperty> colClassId;
    @FXML
    public TableColumn<ClassroomViewModel, SimpleStringProperty> colClassName;
    @FXML
    public TableColumn<ClassroomViewModel, SimpleStringProperty> colClassTeacher;
    @FXML
    public TableColumn<ClassroomViewModel, SimpleIntegerProperty> colClassCapacity;

    public ObservableList<ClassroomViewModel> classroomTableList;

    public ArrayList<ClassroomViewModel> classroomList = new ArrayList<>();

    private List<Classroom> classroomModels = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        classroomTable.setOnMouseClicked(v -> getViewModel());

        classroomModels = ClassroomRepository.Instance.fetchAll();

        classroomTableList = FXCollections.observableArrayList(
                fetchAllModels()
        );

        // Classroom ID
        colClassId.setCellValueFactory(
                branch -> branch.getValue().classroomId
        );
        // Classroom Name
        colClassName.setCellValueFactory(
                branch -> branch.getValue().classroomName
        );
        // Classroom Teacher Fullname
        colClassTeacher.setCellValueFactory(
                branch -> branch.getValue().classroomTeacherFullName
        );
        // Classroom Teacher Fullname
        colClassCapacity.setCellValueFactory(
                branch -> branch.getValue().capacity
        );

        classroomTable.setItems(classroomTableList);
        classroomTable.setFixedCellSize(60.0);

    }

    public void getViewModel() {
        if(classroomTable.getSelectionModel().getSelectedItem() != null) {
            ClassroomViewModel vm = classroomTable.getSelectionModel().getSelectedItem();
            // Setting the classroom ID.
            ClassroomId = vm.classroom.getClassroomId();
            Logger.LogDebug("GOTTEN CLASSROOM ID WAS: " + vm.classroom.getClassroomId());
        }
    }

    public ArrayList<ClassroomViewModel> fetchAllModels() {
        for (Classroom classroom: classroomModels) {
            classroomList.add(new ClassroomViewModel(classroom));
        }
        return classroomList;
    }

    public void update() throws Exception {
        StudentRepository.Instance.UpdateClassroomId(StudentToAdd, StudentToAdd.getStudentId(), ClassroomId);
        ClassroomId = -1;
    }


    public void goBack(ActionEvent actionEvent) throws IOException {
    }

    public void proceedToAddClassroom(ActionEvent mouseEvent) {
        SceneBuilder.Instance.BuildScene("classroom_new_admin");
    }

    public void quit(ActionEvent event) {
        SceneBuilder.Instance.BuildScene("login");
    }
}

package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import meltem.models.Classroom;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.ClassroomRepository;
import meltem.services.logging.Logger;
import meltem.view_models.ClassroomViewModel;
import meltem.view_models.StudentViewModel;
import meltem.view_models.UserViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//new GregorianCalendar(2020,0,31)

public class ClassroomsController implements Initializable {

    int selectedId = 0;
    @FXML
    public Button btnEdit;
    @FXML
    public TextField txtClassroomId;
    public void findUser() throws IOException {
        int userId = Integer.parseInt(txtClassroomId.getText());
        if(userId != 0) {
            SceneBuilder.Instance.BuildScene("classroom_info");
        }
    }
    @FXML
    private TableView<ClassroomViewModel> table = new TableView<ClassroomViewModel>();
    public final ObservableList<ClassroomViewModel> data = FXCollections.observableArrayList(
        fetchViewModel()
    );

    ArrayList<ClassroomViewModel> fetchViewModel() {
        ArrayList<ClassroomViewModel> classroomVMs = new ArrayList<>();
        ArrayList<Classroom> classrooms = (ArrayList<Classroom>) ClassroomRepository.Instance.fetchAll();
        for(Classroom c: classrooms) {
            classroomVMs.add(new ClassroomViewModel(c));
        }
        return classroomVMs;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //btnEdit.setDisable(true);
        table.setEditable(true);
        //Logger.LogDebug(data.get(1).classroom.toString());
        // Classroom ID
        TableColumn<ClassroomViewModel, SimpleIntegerProperty> userIdCol = new TableColumn<>("Sınıf Numarası");
        userIdCol.setMinWidth(100);
        userIdCol.setCellValueFactory(
                user -> user.getValue().classroomId
        );
        // Classroom Name
        TableColumn<ClassroomViewModel, SimpleStringProperty> usernameCol = new TableColumn<>("Sınıf Ismi");
        usernameCol.setMinWidth(100);
        usernameCol.setCellValueFactory(
                user -> user.getValue().classroomName
        );
        // Capacity
        TableColumn<ClassroomViewModel, SimpleIntegerProperty> pwCol = new TableColumn<>("Sınıf Kontenjanı");
        pwCol.setMinWidth(250);
        pwCol.setCellValueFactory(
                user -> user.getValue().capacity
        );
        // Orientation Start
        TableColumn<ClassroomViewModel, SimpleStringProperty> teachCol = new TableColumn<>("Sınıf Öğretmeni");
        teachCol.setMinWidth(250);
        teachCol.setCellValueFactory(
                user -> user.getValue().classroomTeacherFullName
        );



        //table.setItems(data);
        //table.getColumns().addAll(userIdCol, usernameCol, pwCol, teachCol);

    }

    public void clickItem(MouseEvent event) {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedId = table.getSelectionModel().getSelectedItem().classroom.getClassroomId();
                if(selectedId != 0) {
                    btnEdit.setDisable(false);
                }
            }
        });
    }

    public void addTeacher() throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_new_admin");
    }

    public void proceedToEdit() throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_edit", new RouteData(selectedId, "classroom"));
    }

    public void proceedToInfo() {
        SceneBuilder.Instance.BuildScene("classroom_info_admin");
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("search_page");
    }
}

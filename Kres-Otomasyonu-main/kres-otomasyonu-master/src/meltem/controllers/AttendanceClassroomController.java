package meltem.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import meltem.services.SceneBuilder;
import meltem.view_models.StudentViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AttendanceClassroomController implements Initializable {
    @FXML
    public TextField txtClassroomId;
    @FXML
    private TableView<StudentViewModel> tableStudents = new TableView<StudentViewModel>();
    public final ObservableList<StudentViewModel> students = FXCollections.observableArrayList(
            new StudentViewModel(1,
                    "Ali",
                    "Oncul",
                    "23/03/2020",
                    "07/04/2020",
                    "0543 555 4433",
                    "Ahmet",
                    "Oncul",
                    "aoncul76@hotmail.com",
                    680
            ),
            new StudentViewModel(1,
                    "Veli",
                    "Turk",
                    "23/03/2020",
                    "07/04/2020",
                    "0543 555 4433",
                    "Huseyin",
                    "Turk",
                    "",
                    680
            ),
            new StudentViewModel(1,
                    "Mehmet",
                    "Kaya",
                    "23/03/2020",
                    "07/04/2020",
                    "0543 666 1122",
                    "Nazan",
                    "Ata",
                    "nazan.ata@gmail.com",
                    680
            ),
            new StudentViewModel(1,
                    "Abdullah",
                    "Gök",
                    "23/03/2020",
                    "07/04/2020",
                    "0543 222 3399",
                    "Davud",
                    "Gök",
                    "",
                    680
            ),
            new StudentViewModel(1,
                    "Atakan",
                    "Irmak",
                    "23/03/2020",
                    "07/04/2020",
                    "0543 545 4433",
                    "Davud",
                    "Oncul",
                    "aoncul76@hotmail.com",
                    680
            )
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // First Name
        TableColumn<StudentViewModel, SimpleStringProperty> firstNameCol = new TableColumn<>("Öğrenci Adı");
        firstNameCol.setCellValueFactory(
                student -> student.getValue().studentName
        );
        // Last Name
        TableColumn<StudentViewModel, SimpleStringProperty> lastNameCol = new TableColumn<>("Öğrenci Soyadı");
        lastNameCol.setCellValueFactory(
                student -> student.getValue().studentLastName
        );
        // Orientation Start
        TableColumn<StudentViewModel, SimpleStringProperty> orientatonStartCol = new TableColumn<>("Oryantasyon Baslangıç Tarihi");
        orientatonStartCol.setCellValueFactory(
                student -> student.getValue().orientationStart
        );
        // Orientation End
        TableColumn<StudentViewModel, SimpleStringProperty> orientatonEndCol = new TableColumn<>("Oryantasyon Bitiş Tarihi");
        orientatonEndCol.setCellValueFactory(
                student -> student.getValue().orientationEnd
        );
        // Parent Phone Number
        TableColumn<StudentViewModel, SimpleStringProperty> parentNameCol = new TableColumn<>("Veli Adı");
        parentNameCol.setCellValueFactory(
                student -> student.getValue().parentName
        );
        // Parent Phone Number
        TableColumn<StudentViewModel, SimpleStringProperty> parentLnameCol = new TableColumn<>("Veli Soyadı");
        parentLnameCol.setCellValueFactory(
                student -> student.getValue().parentLastName
        );
        // Parent Phone Number
        TableColumn<StudentViewModel, SimpleStringProperty> parentNumberCol = new TableColumn<>("Veli Telefon No.");
        parentNumberCol.setCellValueFactory(
                student -> student.getValue().parentNumber
        );
        // Parent Phone Number
        TableColumn<StudentViewModel, SimpleStringProperty> emailCol = new TableColumn<>("Veli Email");
        emailCol.setCellValueFactory(
                student -> student.getValue().parentEmail
        );


        tableStudents.setItems(students);
        tableStudents.setFixedCellSize(60.0);
        tableStudents.getColumns().addAll(
                firstNameCol,
                lastNameCol,
                orientatonStartCol,
                orientatonEndCol,
                parentNameCol,
                parentLnameCol,
                parentNumberCol
        );
    }
}

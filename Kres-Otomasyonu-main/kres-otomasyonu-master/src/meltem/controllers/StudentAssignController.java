package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import meltem.enums.LogType;
import meltem.models.Classroom;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.BranchViewModel;
import meltem.view_models.ClassroomViewModel;
import meltem.view_models.StudentViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentAssignController {
    @FXML
    private TableView<StudentViewModel> studentTable = new TableView<StudentViewModel>();
    @FXML
    private TableView<ClassroomViewModel> classroomTable = new TableView<ClassroomViewModel>();
    @FXML
    private TableView<BranchViewModel> branchTable = new TableView<BranchViewModel>();
    @FXML
    private Button btnEditClass;
    @FXML
    private Button btnEditBranch;

    public final ObservableList<StudentViewModel> data = FXCollections.observableArrayList(
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
}

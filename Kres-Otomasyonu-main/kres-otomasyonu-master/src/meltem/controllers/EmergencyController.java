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
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.EmergencyViewModel;
import meltem.view_models.StudentViewModel;
import meltem.view_models.UserViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//new GregorianCalendar(2020,0,31)

public class EmergencyController implements Initializable {
    int selectedId = 0;
    @FXML
    public Button btnEdit;
    @FXML
    private TableView<EmergencyViewModel> table = new TableView<EmergencyViewModel>();
    public final ObservableList<EmergencyViewModel> data = FXCollections.observableArrayList(
            new EmergencyViewModel(1, "Genel Koordinatör'ün Numarası", "0544 654 0566"),
            new EmergencyViewModel(2, "Temizlik Görevlisi'nin Numarası", "0543 651 3431"),
            new EmergencyViewModel(3, "Kantin Görevlisi'nin Numarası", "0544 555 4433")
    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnEdit.setDisable(true);
        table.setEditable(true);
        Logger.LogDebug(data.get(1).number.toString());
        // Last Name
        TableColumn<EmergencyViewModel, SimpleStringProperty> descriptionCol = new TableColumn<>("Açıklama");
        descriptionCol.setMinWidth(100);
        descriptionCol.setCellValueFactory(
                user -> user.getValue().numberDescription
        );
        // Orientation Start
        TableColumn<EmergencyViewModel, SimpleStringProperty> numCol = new TableColumn<>("Numara");
        numCol.setMinWidth(250);
        numCol.setCellValueFactory(
                user -> user.getValue().number
        );



        table.setItems(data);
        table.getColumns().addAll(descriptionCol, numCol);

    }

    public void clickItem(MouseEvent event) {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedId = table.getSelectionModel().getSelectedItem().emergencyNumber.numberId;
                if(selectedId != 0) {
                    btnEdit.setDisable(false);
                }
            }
        });
    }

    public void addData() throws IOException {
        SceneBuilder.Instance.BuildScene("emergency_number_new");
    }

    public void proceedToEdit() throws IOException {
        SceneBuilder.Instance.BuildScene("emergency_number_edit", new RouteData(selectedId, "emergencyNumber"));
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("home");
    }
}

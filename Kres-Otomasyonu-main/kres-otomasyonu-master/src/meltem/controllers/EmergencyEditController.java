package meltem.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import meltem.Main;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.models.User;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.EmergencyViewModel;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmergencyEditController implements Initializable {
    public final ObservableList<EmergencyViewModel> data = FXCollections.observableArrayList(
            new EmergencyViewModel(1, "Genel Koordinatör'ün Numarası", "0544 654 0566"),
            new EmergencyViewModel(2, "Temizlik Görevlisi'nin Numarası", "0543 651 3431"),
            new EmergencyViewModel(3, "Kantin Görevlisi'nin Numarası", "0544 555 4433")
    );
    @FXML
    public TextField txtDesc;
    @FXML
    public TextField txtNum;
    @FXML
    public Button btnNew;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Main.userDataService.fetchById(SceneBuilder.routeData.id);
        if(SceneBuilder.routeData != null) {
            Logger.LogDebug(SceneBuilder.routeData.dataName);
            txtDesc.setText(String.valueOf(data.get(0).emergencyNumber.numberDescription));
            txtNum.setText(String.valueOf(data.get(0).emergencyNumber.number));
            // Dropdown'a stringler atanir.
        }
    }
    public void update() throws IOException {
        SceneBuilder.Instance.BuildScene("emergency_number_edit", new RouteData(1, "emergency"));
    }
    public void delete() throws IOException {
        Logger.LogDebug("DELETE!");
        SceneBuilder.Instance.BuildScene("emergency_numbers");
    }
    @FXML
    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("emergency_numbers");
    }

}

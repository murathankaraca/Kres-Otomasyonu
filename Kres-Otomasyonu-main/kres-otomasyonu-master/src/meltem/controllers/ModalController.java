package meltem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.text.Text;
import meltem.services.SceneBuilder;

import java.net.URL;
import java.util.ResourceBundle;


public class ModalController implements Initializable {
    @FXML
    public Text txtWarning;
    @FXML
    public void close(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            if(txtWarning != null) {
                txtWarning.setText(SceneBuilder.modalWarning);
            }

    }
}

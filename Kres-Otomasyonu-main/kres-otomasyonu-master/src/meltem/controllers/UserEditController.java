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
import meltem.services.data_access.concrete.UserRepository;
import meltem.services.logging.Logger;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserEditController implements Initializable {
    public static int route = 1;
    public static int UserId = -1;
    public static User User;
    public User user = null;
    @FXML
    public Text indicatorId;
    @FXML
    public Text txtUserId;
    @FXML
    public TextField txtUserName;
    @FXML
    public TextField txtPw;
    @FXML
    public Button btnNew;
    @FXML
    public ChoiceBox<String> userAuth;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userAuth.setItems(FXCollections.observableArrayList(
                "Yonetici", "Sinif Ogretmeni", "Brans Ders Ogretmeni"
        ));

        if(User != null) {
            txtUserName.setText(User.getUserName());
            txtPw.setText(User.getPassword());
            userAuth.setValue(User.getTrueAuth());
        } else {
            User = new User(-1, "", "", 0);
        }

        changeAuthSelection();
        ArrayList<String> choiceBoxList = new ArrayList<>();
        choiceBoxList.add("Yönetici");
        choiceBoxList.add("Sınıf Öğretmeni");
        choiceBoxList.add("Branş Ders Öğretmeni");
        userAuth.setItems(FXCollections.observableList(choiceBoxList));
    }
    public String setChoiceBoxValue() {
        switch(user.getUserAuth()) {
            case 1:
                return "Yonetici";
            case 2:
                return "Sinif Ogretmeni";
            case 3:
                return "Brans Ogretmeni";
            default:
                return "Yetkisiz";
        }
    }
    @FXML
    public void goBack()  {
        try {
            switch(route) {
                case 1:
                    SceneBuilder.Instance.BuildScene("user_list");
                    break;
                case 2:
                    SceneBuilder.Instance.BuildScene("home_classroom");
                    break;
                default:
                    SceneBuilder.Instance.BuildScene("home_branch");
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    @FXML
    public void addUser() {
        User.setUserName(txtUserName.getText());
        User.setPassword(txtPw.getText());
        User.setUserAuth(userAuth.getSelectionModel().getSelectedIndex() + 1);
        UserRepository.Instance.Add(User);
        User = null;
        goBack();
    }
    @FXML
    public void updateUser() {
        User.setUserName(txtUserName.getText());
        User.setPassword(txtPw.getText());
        User.setUserAuth(userAuth.getSelectionModel().getSelectedIndex() + 1);
        UserRepository.Instance.UpdateById(User, User.getUserId());
        User = null;
        goBack();
    }
    public void changeAuthSelection() {
        userAuth.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        User.setUserAuth((int) newValue + 1);
                        Logger.LogDebug(String.valueOf(User.getUserAuth()));
                    }
                }
        );
    }
}

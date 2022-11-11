package meltem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import meltem.Main;
import meltem.enums.LogType;
import meltem.models.User;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.UserRepository;
import meltem.services.logging.Logger;

import java.io.IOException;
import java.util.List;

public class LoginController {
    @FXML
    public TextField txtUsername;
    @FXML
    public TextField txtPassword;

    private Boolean userIsValid() {
        Logger.LogDebug(txtUsername.getText());

        Logger.LogDebug(txtPassword.getText());
        for(User user: UserRepository.Instance.fetchAll()) {
            if(txtUsername.getText().equals(user.getUserName()) && txtPassword.getText().equals(user.getPassword())) {
                Main.user = user;
                return true;
            }
        }
        return false;
    }

    @FXML
    public void login() throws IOException {
        if(userIsValid()) {
            switch(Main.user.getUserAuth()) {
                case 1:
                    SceneBuilder.Instance.BuildScene("search_page");
                    break;
                case 2:
                    SceneBuilder.Instance.BuildScene("attendance_classroom_teacher");
                    break;
                case 3:
                    SceneBuilder.Instance.BuildScene("attendance_branch_teacher");
                    break;
            }
        } else {
            Logger.Log(LogType.Warning, "Yanlis hesap bilgileri girdiniz!");
        }
    }

    public void goForgotPassword(ActionEvent event) {
        SceneBuilder.Instance.BuildScene("forgot_password");
    }
}

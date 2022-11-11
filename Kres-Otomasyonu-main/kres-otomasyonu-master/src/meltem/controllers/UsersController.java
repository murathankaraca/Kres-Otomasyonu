package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.models.User;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.UserRepository;
import meltem.services.logging.Logger;
import meltem.services.search.UserSearch;
import meltem.view_models.StudentViewModel;
import meltem.view_models.UserViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//new GregorianCalendar(2020,0,31)

public class UsersController implements Initializable {

    int selectedId = 0;
    @FXML
    public TextField txtUserInfo;
    @FXML
    public CheckBox chkId;
    @FXML
    public CheckBox chkName;
    @FXML
    TableColumn<UserViewModel, SimpleIntegerProperty> userIdCol;
    @FXML
    TableColumn<UserViewModel, SimpleStringProperty> userNameCol;
    @FXML
    TableColumn<UserViewModel, SimpleStringProperty> trueAuthCol;

    @FXML
    public Button btnUpdateUser;
    @FXML
    public void findUser() {
        if(tableUsers.getSelectionModel().getSelectedItem() != null) {
            btnUpdateUser.setDisable(false);
            UserEditController.User = tableUsers.getSelectionModel().getSelectedItem().user;
            Logger.LogDebug(UserEditController.User.getUserName());
        }
    }
    @FXML
    private TableView<UserViewModel> tableUsers = new TableView<UserViewModel>();
    public final ObservableList<UserViewModel> data = FXCollections.observableArrayList(
            fetchUserList()
    );

    private List<UserViewModel> fetchUserList() {
        ArrayList<User> userList = (ArrayList<User>) UserRepository.Instance.fetchAll();
        ArrayList<UserViewModel> userViewModels = new ArrayList<>();
        for(User user: userList) {
            userViewModels.add(new UserViewModel(user.getUserId(), user.getUserName(), user.getPassword(), user.getUserAuth()));
        }
        return userViewModels;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        chkId.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                chkName.setSelected(false);
            }
        });

        chkName.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                chkId.setSelected(false);
            }
        });


        tableUsers.setEditable(true);
        // First Name
        tableUsers.setFixedCellSize(50);
        userIdCol.setCellValueFactory(
                user -> user.getValue().userId
        );
        // Last Name
        userNameCol.setCellValueFactory(
                user -> user.getValue().userName
        );
        trueAuthCol.setCellValueFactory(
                user -> user.getValue().userTrueAuth
        );
        tableUsers.setItems(data);
        tableUsers.setFixedCellSize(60.0);
        tableUsers.setOnMouseClicked(v -> findUser());
    }

    public void proceedToEdit() throws IOException {
        Logger.LogDebug(UserEditController.User.getUserName() + "???????????");
        SceneBuilder.Instance.BuildScene("user_edit");
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("search_page");
    }

    public void proceedToNew(ActionEvent event) {
        SceneBuilder.Instance.BuildScene("user_new");
    }

    public void searchUser() {
        UserSearch us = new UserSearch();
        String searchParam = txtUserInfo.getText();
        Logger.LogDebug(searchParam + " is the input for the search.");
        if(chkId.isSelected()) {
            us.searchById(searchParam);
        } else if(chkName.isSelected()) {
            us.searchByUsername(searchParam);
        }
    }
}

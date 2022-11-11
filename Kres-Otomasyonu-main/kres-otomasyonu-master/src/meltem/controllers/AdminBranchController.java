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
import meltem.models.Branch;
import meltem.models.RouteData;
import meltem.models.Teacher;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.BranchRepository;
import meltem.services.logging.Logger;
import meltem.view_models.BranchViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminBranchController implements Initializable {

    private int selectedId = -1;
    public Branch selectedBranch;

    @FXML
    public Button btnShowBranchInfo;
    @FXML
    public TableView<BranchViewModel> branchTable;
    @FXML
    public TableColumn<BranchViewModel, SimpleIntegerProperty> colBranchId;
    @FXML
    public TableColumn<BranchViewModel, SimpleStringProperty> colBranchName;
    @FXML
    public TableColumn<BranchViewModel, SimpleStringProperty> colBranchTeacherName;
    @FXML
    public TableColumn<BranchViewModel, SimpleStringProperty> colBranchTeacherLastName;
    @FXML
    public TableColumn<BranchViewModel, SimpleStringProperty> colBranchTeacherPhone;
    @FXML
    public TableColumn<BranchViewModel, SimpleIntegerProperty> colBranchCapacity;

    public ObservableList<BranchViewModel> branchTableList;

    public ArrayList<BranchViewModel> branchList = new ArrayList<>();

    private List<Branch> branchModels = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        branchTable.setOnMouseClicked(v -> getViewModel());

        branchModels = BranchRepository.Instance.fetchAllTeachers();

        branchTableList = FXCollections.observableArrayList(
            fetchAllModels()
        );

        // Classroom ID
        colBranchId.setCellValueFactory(
                branch -> branch.getValue().branchId
        );
        // Classroom Name
        colBranchName.setCellValueFactory(
                branch -> branch.getValue().branchName
        );
        // Classroom Teacher Fullname
        colBranchTeacherName.setCellValueFactory(
                branch -> branch.getValue().branchTeacherName
        );
        // Classroom Teacher Fullname
        colBranchTeacherLastName.setCellValueFactory(
                branch -> branch.getValue().branchTeacherLastName
        );
        // Classroom Teacher Fullname
        colBranchTeacherPhone.setCellValueFactory(
                branch -> branch.getValue().branchTeacherPhone
        );
        // Classroom Capacity
        colBranchCapacity.setCellValueFactory(
                branch -> branch.getValue().branchCapacity
        );

        branchTable.setItems(branchTableList);

    }

    public void getViewModel() {
        if(branchTable.getSelectionModel().getSelectedItem() != null) {
            btnShowBranchInfo.setDisable(false);
            BranchViewModel vm = branchTable.getSelectionModel().getSelectedItem();

            selectedBranch = branchTable.getSelectionModel().getSelectedItem().branch;

            Logger.LogDebug("GOTTEN BRANCH ID WAS: " + vm.branch.getBranchId());
            AdminBranchInfoController.branch = vm.branch;
            AdminBranchInfoController.BranchId = vm.branch.getBranchId();
            AdminBranchInfoController.BranchTeacherFullName = vm.branch.getBranchTeacherName() + " " + vm.branch.getBranchTeacherLastName();
            Logger.LogDebug(AdminBranchInfoController.BranchId + " IS The IDDDDDDDDDD!");
        }
    }

    public ArrayList<BranchViewModel> fetchAllModels() {
        for (Branch branch: branchModels) {
            branchList.add(new BranchViewModel(branch));
        }
        return branchList;
    }

    public void update() throws IOException {
        SceneBuilder.Instance.BuildScene("branch_edit", new RouteData(1, "user"));
    }

    public void proceedToEdit() throws IOException {
        SceneBuilder.Instance.BuildScene("branch_edit", new RouteData(1, "classroom"));
    }

    public void proceedToInfo() {
        SceneBuilder.Instance.BuildScene("branch_info_admin");
    }

    public void proceedToAddBranch(ActionEvent mouseEvent) {
        BranchEditController.branch = selectedBranch;
        SceneBuilder.Instance.BuildScene("branch_new_admin");
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("search_page");
    }

    public void quit(ActionEvent event) {
        SceneBuilder.Instance.BuildScene("login");
    }

}

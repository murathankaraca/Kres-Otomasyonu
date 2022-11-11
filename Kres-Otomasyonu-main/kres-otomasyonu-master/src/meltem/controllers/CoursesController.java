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
import meltem.Main;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.CourseViewModel;
import meltem.view_models.StudentViewModel;
import meltem.view_models.UserViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//new GregorianCalendar(2020,0,31)

public class CoursesController implements Initializable {
    int selectedId = 0;
    @FXML
    public Button btnEdit;
    @FXML
    public TextField txtCourseId;
    public void findCourse() throws IOException {
        int userId = Integer.parseInt(txtCourseId.getText());
        if(userId != 0) {
            SceneBuilder.Instance.BuildScene("course_info");
        }
    }
    @FXML
    private TableView<CourseViewModel> table = new TableView<CourseViewModel>();
//    public final ObservableList<CourseViewModel> data = FXCollections.observableArrayList(
//            new CourseViewModel(1, "Sabah Jimnastiği", "Neşe Sönmez", 2),
//            new CourseViewModel(1, "Türkçe", "Sema Yirun", 1),
//            new CourseViewModel(1, "Çizim", "Büşra Özel",2),
//            new CourseViewModel(1, "Serbest Oyun", "Hülya Özdin",2),
//            new CourseViewModel(1, "İlgili Köşelerinde Oyun", "Seher Sedef Kurubaş",2)
//    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(btnEdit != null) {
            btnEdit.setDisable(true);
        }
        table.setEditable(true);
        //Logger.LogDebug(data.get(1).course.courseName.toString());
        // First Name
        TableColumn<CourseViewModel, SimpleIntegerProperty> courseIdCol = new TableColumn<>("Ders Numarası");
        courseIdCol.setMinWidth(100);
        courseIdCol.setCellValueFactory(
                course -> course.getValue().courseId
        );
        // Last Name
        TableColumn<CourseViewModel, SimpleStringProperty> courseNameCol = new TableColumn<>("Ders Adı");
        courseNameCol.setMinWidth(100);
        courseNameCol.setCellValueFactory(
                course -> course.getValue().courseName
        );



        //table.setItems(data);
        //table.getColumns().addAll(courseIdCol, courseNameCol, courseTeacher, userAuthCol);

    }

    public void clickItem(MouseEvent event) {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedId = table.getSelectionModel().getSelectedItem().course.getCourseId();
                if(selectedId != 0) {
                    btnEdit.setDisable(false);
                }
            }
        });
    }

    public void addData() throws IOException {
        SceneBuilder.Instance.BuildScene("course_new");
    }

    public void proceedToEdit() throws IOException {
        SceneBuilder.Instance.BuildScene("course_edit", new RouteData(selectedId, "course"));
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        switch(Main.user.getUserAuth()) {
            case 1:
                SceneBuilder.Instance.BuildScene("search_page");
                break;
            case 2:
                SceneBuilder.Instance.BuildScene("home_class");
                break;
            case 3:
                SceneBuilder.Instance.BuildScene("home_branch");
                break;
        }
    }
    public void goBackProcess(ActionEvent event) throws Exception {
        SceneBuilder.Instance.BuildScene("home");
    }
}

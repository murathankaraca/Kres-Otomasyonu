package meltem;

import javafx.application.Application;
import javafx.stage.Stage;
import meltem.enums.LogType;
import meltem.models.User;
import meltem.services.data_access.concrete.*;
import meltem.services.logging.Logger;
import meltem.services.SceneBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main extends Application {
    public static UserRepository ur = new UserRepository();
    public ClassroomRepository cr = new ClassroomRepository();
    public ClassroomCourseRepository ccr = new ClassroomCourseRepository();
    public ClassroomAttendanceRepository car = new ClassroomAttendanceRepository();
    public TeacherRepository tr = new TeacherRepository();
    public StudentRepository sr = new StudentRepository();
    public MeetingRepository mr = new MeetingRepository();
    public BranchRepository br = new BranchRepository();
    public static User user = new User(1, "sema_yirun", "123456", (short)1);
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneBuilder sb = new SceneBuilder(primaryStage);
        Logger.Log(LogType.Debug, this.getClass().getSimpleName());
        sb.BuildScene("login");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

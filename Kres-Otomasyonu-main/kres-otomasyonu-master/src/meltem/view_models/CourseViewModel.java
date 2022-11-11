package meltem.view_models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import meltem.models.Course;
import meltem.models.User;
import meltem.services.logging.Logger;

public class CourseViewModel{
    public Course course;

    public ObservableValue<SimpleIntegerProperty> classroomCourseId;
    public ObservableValue<SimpleIntegerProperty> courseId;
    public ObservableValue<SimpleStringProperty> courseName;
    public ObservableValue<SimpleStringProperty> teacherName;
    public ObservableValue<SimpleStringProperty> teacherLastName;
    public ObservableValue<SimpleStringProperty> teacherPhone;
    public ObservableValue<SimpleStringProperty> teacherEmail;

    public CourseViewModel (
            Course course
    ) {
        try {
            this.course = course;
            this.classroomCourseId = (ObservableValue) new SimpleIntegerProperty(course.getClassroomCourseId());
            this.courseId = (ObservableValue) new SimpleIntegerProperty(course.getCourseId());
            this.courseName = (ObservableValue) new SimpleStringProperty(course.getCourseName());
            this.teacherName = (ObservableValue) new SimpleStringProperty(course.getTeacherName());
            this.teacherLastName = (ObservableValue) new SimpleStringProperty(course.getTeacherLastName());
            this.teacherPhone = (ObservableValue) new SimpleStringProperty(course.getTeacherPhone());
            this.teacherEmail = (ObservableValue) new SimpleStringProperty(course.getTeacherEmail());
            //Logger.LogDebug(this.course.courseName + " is generated!");
        }
        catch(NullPointerException ex) {
            Logger.LogError(ex.toString());
        }
    }

}

package meltem.view_models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import meltem.controllers.ClassroomInfoController;
import meltem.models.Classroom;
import meltem.models.Course;
import meltem.models.Student;
import meltem.models.Teacher;
import meltem.services.data_access.concrete.ClassroomRepository;
import meltem.services.data_access.concrete.TeacherRepository;
import meltem.services.logging.Logger;

import java.util.ArrayList;
import java.util.List;

public class ClassroomViewModel {
    public Classroom classroom;
    public Teacher teacher;
    public ObservableValue<SimpleIntegerProperty> classroomId;
    public ObservableValue<SimpleStringProperty> classroomName;
    public ObservableValue<SimpleIntegerProperty> capacity;
    public ObservableValue<SimpleStringProperty> classroomTeacherFullName;

    public ClassroomViewModel (
            int id,
            int teacherId
    ) {
        try {
            classroom = ClassroomRepository.Instance.fetchById(id);
            teacher = TeacherRepository.Instance.fetchById(teacherId);
            this.classroomId = (ObservableValue) new SimpleIntegerProperty(classroom.getClassroomId());
            this.classroomName = (ObservableValue) new SimpleStringProperty(classroom.getClassroomName());
            this.capacity = (ObservableValue) new SimpleIntegerProperty(classroom.getClassroomCapacity());
            this.classroomTeacherFullName = (ObservableValue) new SimpleStringProperty(teacher.getTeacherName() + " " + teacher.getTeacherLastName());
        }
        catch(NullPointerException ex) {
            Logger.LogError(ex.toString());
        }
    }
    public ClassroomViewModel(Classroom classroom) {
        this.classroom = classroom;
        this.classroomId = (ObservableValue) new SimpleIntegerProperty(classroom.getClassroomId());
        this.classroomName = (ObservableValue) new SimpleStringProperty(classroom.getClassroomName());
        this.capacity = (ObservableValue) new SimpleIntegerProperty(classroom.getClassroomCapacity());
        this.classroomTeacherFullName = (ObservableValue) new SimpleStringProperty(classroom.getClassroomTeacherName() + " " + classroom.getClassroomTeacherLastName());
    }

}

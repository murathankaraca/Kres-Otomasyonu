package meltem.view_models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import meltem.models.Branch;
import meltem.models.Classroom;
import meltem.models.Teacher;
import meltem.models.User;
import meltem.services.data_access.concrete.BranchRepository;
import meltem.services.data_access.concrete.ClassroomRepository;
import meltem.services.data_access.concrete.TeacherRepository;
import meltem.services.logging.Logger;

import java.util.ArrayList;
import java.util.List;

public class BranchViewModel{
    public Branch branch;
    public Teacher teacher;

    public ObservableValue<SimpleIntegerProperty> branchId;
    public ObservableValue<SimpleIntegerProperty> branchTeacherId;
    public ObservableValue<SimpleStringProperty> branchName;
    public ObservableValue<SimpleIntegerProperty> branchCapacity;
    public ObservableValue<SimpleStringProperty> branchTeacherName;
    public ObservableValue<SimpleStringProperty> branchTeacherLastName;
    public ObservableValue<SimpleStringProperty> branchTeacherPhone;

    public BranchViewModel (
            int id,
            int teacherId
    ) {
        try {
            branch = BranchRepository.Instance.fetchById(id);
            teacher = TeacherRepository.Instance.fetchById(teacherId);
            this.branchId = (ObservableValue) new SimpleIntegerProperty(branch.getBranchId());
            this.branchTeacherId = (ObservableValue) new SimpleIntegerProperty(teacher.getTeacherId());
            this.branchName = (ObservableValue) new SimpleStringProperty(branch.getBranchCourseName());
            this.branchCapacity = (ObservableValue) new SimpleIntegerProperty(branch.getBranchCapacity());
            this.branchTeacherName = (ObservableValue) new SimpleStringProperty(teacher.getTeacherName());
            this.branchTeacherLastName = (ObservableValue) new SimpleStringProperty(teacher.getTeacherLastName());
        }
        catch(NullPointerException ex) {
            Logger.LogError(ex.toString());
        }
    }
    public BranchViewModel(Branch branch) {
        this.branch = branch;
        this.branchId = (ObservableValue) new SimpleIntegerProperty(branch.getBranchId());
        this.branchName = (ObservableValue) new SimpleStringProperty(branch.getBranchCourseName());
        this.branchCapacity = (ObservableValue) new SimpleIntegerProperty(branch.getBranchCapacity());
        this.branchTeacherName = (ObservableValue) new SimpleStringProperty(branch.getBranchTeacherName());
        this.branchTeacherLastName = (ObservableValue) new SimpleStringProperty(branch.getBranchTeacherLastName());
        this.branchTeacherPhone = (ObservableValue) new SimpleStringProperty(branch.getBranchTeacherPhone());
    }

}

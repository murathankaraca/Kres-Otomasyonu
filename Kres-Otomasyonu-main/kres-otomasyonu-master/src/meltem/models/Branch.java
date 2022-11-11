package meltem.models;

public class Branch {
    private int branchId;
    private String branchCourseName;
    private int branchTeacher;
    private String branchTeacherName;
    private String branchTeacherLastName;
    private int branchCapacity;
    private String branchTeacherPhone;

    public void setBranchTeacherPhone(String branchTeacherPhone) {
        this.branchTeacherPhone = branchTeacherPhone;
    }


    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchCourseName() {
        return branchCourseName;
    }

    public void setBranchCourseName(String branchCourseName) {
        this.branchCourseName = branchCourseName;
    }

    public int getBranchTeacher() {
        return branchTeacher;
    }

    public void setBranchTeacher(int branchTeacher) {
        this.branchTeacher = branchTeacher;
    }

    public int getBranchCapacity() {
        return branchCapacity;
    }

    public void setBranchCapacity(int branchCapacity) {
        this.branchCapacity = branchCapacity;
    }


    public String getBranchTeacherLastName() {
        return branchTeacherLastName;
    }

    public void setBranchTeacherLastName(String branchTeacherLastName) {
        this.branchTeacherLastName = branchTeacherLastName;
    }

    public String getBranchTeacherName() {
        return branchTeacherName;
    }

    public void setBranchTeacherName(String branchTeacherName) {
        this.branchTeacherName = branchTeacherName;
    }

    public Branch(int id, String courseName, int teacher, int capacity) {
        this.branchId = id;
        this.branchCourseName = courseName;
        this.branchTeacher = teacher;
        this.branchCapacity = capacity;
    }

    public Branch(int id, String courseName, String teacherName, String teacherLastName, int capacity) {
        this.branchId = id;
        this.branchCourseName = courseName;
        this.branchTeacherName = teacherName;
        this.branchTeacherLastName = teacherLastName;
        this.branchCapacity = capacity;
    }

    public String getBranchTeacherPhone() {
        return this.branchTeacherPhone;
    }
}

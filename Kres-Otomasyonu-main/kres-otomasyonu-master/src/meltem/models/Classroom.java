package meltem.models;

public class Classroom {
    private int classroomId;
    private String classroomName;
    private int classroomTeacherId;
    private String classroomTeacherName;
    private String classroomTeacherLastName;
    private int classroomCapacity;

    public Classroom() {
        this.classroomId = -1;
        this.classroomName = "";
        this.classroomTeacherId = -1;
        this.classroomTeacherName = "";
        this.classroomTeacherLastName = "";
        this.classroomCapacity = 25;
    }

    public Classroom(int classroom_id, String classroom_name, String teacher_name, String teacher_lastname, int classroom_capacity) {
        this.classroomId = classroom_id;
        this.classroomName = classroom_name;
        this.classroomCapacity = classroom_capacity;
        this.classroomTeacherName = teacher_name;
        this.classroomTeacherLastName = teacher_lastname;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }
    public int getClassroomId() {
        return this.classroomId;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public int getClassroomCapacity() {
        return classroomCapacity;
    }

    public void setClassroomCapacity(int classroomCapacity) {
        this.classroomCapacity = classroomCapacity;
    }

    public int getClassroomTeacherId() {
        return classroomTeacherId;
    }

    public void setClassroomTeacherId(int classroomTeacherId) {
        this.classroomTeacherId = classroomTeacherId;
    }

    public Classroom(int id, String name, int teacherId, int capacity) {
        this.classroomId = id;
        this.classroomName = name;
        this.classroomTeacherId = teacherId;
        this.classroomCapacity = capacity;
    }

    public String getClassroomTeacherName() {
        return classroomTeacherName;
    }

    public void setClassroomTeacherName(String classroomTeacherName) {
        this.classroomTeacherName = classroomTeacherName;
    }

    public String getClassroomTeacherLastName() {
        return classroomTeacherLastName;
    }

    public void setClassroomTeacherLastName(String classroomTeacherLastName) {
        this.classroomTeacherLastName = classroomTeacherLastName;
    }
}

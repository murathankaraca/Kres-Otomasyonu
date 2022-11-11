package meltem.models;

public class Course {

    private int classroomCourseId;
    private int courseId;
    private String courseName;
    private String teacherName;
    private String teacherLastName;
    private String teacherPhone;

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    private String teacherEmail;

    public int getClassroomCourseId() {
        return classroomCourseId;
    }

    public void setClassroomCourseId(int classroomCourseId) {
        this.classroomCourseId = classroomCourseId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public Course(int classroomCourseId,
                  int courseId,
                  String courseName,
                  String teacherName,
                  String teacherLastName,
                  String teacherPhone,
                  String teacherEmail) {
        this.classroomCourseId = classroomCourseId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.teacherLastName = teacherLastName;
        this.teacherPhone = teacherPhone;
        this.teacherEmail = teacherEmail;
    }

}

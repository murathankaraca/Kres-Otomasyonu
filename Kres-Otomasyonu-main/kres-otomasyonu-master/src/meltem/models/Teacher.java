package meltem.models;

public class Teacher {
    private int teacherId;
    private String teacherName;
    private String teacherLastName;
    private String teacherPhone;
    private String teacherEmail;
    private int teacherAuth;

    public Teacher(int id,
                   String name,
                   String lastName,
                   String phone,
                   String email,
                   int auth) {
        this.teacherId = id;
        this.teacherName = name;
        this.teacherLastName = lastName;
        this.teacherPhone = phone;
        this.teacherEmail = email;
        this.teacherAuth = auth;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public int getTeacherAuth() {
        return teacherAuth;
    }

    public void setTeacherAuth(int teacherAuth) {
        this.teacherAuth = teacherAuth;
    }

    public String getTeacherType() {
        switch(teacherAuth) {
            case 1:
                return "Yönetici";
            case 2:
                return "Sınıf Öğretmeni";
            case 3:
                return "Branş Öğretmeni";
            default:
                return "Yetkisiz";
        }
    }
}

package meltem.services.data_access.concrete;

import meltem.controllers.AdminClassroomController;
import meltem.controllers.AdminClassroomInfoController;
import meltem.enums.LogType;
import meltem.models.Course;
import meltem.models.Student;
import meltem.models.Teacher;
import meltem.services.data_access.PersistentDataService;
import meltem.services.logging.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassroomCourseRepository extends PersistentDataService<Course> {
    public static ClassroomCourseRepository Instance;
    public ClassroomCourseRepository() {
        if(Instance == null) {
            Instance = this;
        }
    }
    @Override
    public Course fetchById(int id) throws SQLException {
        Course[] courseList = new Course[1];
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("select * from (\n" +
                    "\tcourses\n" +
                    "\tINNER JOIN teachers ON courses.course_teacher_id = teachers.teacher_id\n" +
                    ") WHERE courses.course_classroom_id = %d;", id);
            Logger.LogDebug(query);
            int classroomCourseId = 0;
            int courseId = 0;
            String courseName = "";
            String teacherName = "";
            String teacherLastName = "";
            String teacherPhone = "";
            String teacherEmail = "";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                classroomCourseId = rs.getInt("classroom_course_id");
                courseId = rs.getInt("course_id");
                courseName = rs.getString("course_name");
                teacherName = rs.getString("teacher_name");
                teacherLastName = rs.getString("teacher_lastname");
                teacherPhone = rs.getString("teacher_phone");
                teacherEmail = rs.getString("teacher_email");
            }
            Course course = new Course(classroomCourseId, courseId, courseName, teacherName, teacherLastName, teacherPhone, teacherEmail);
            courseList[0] = course;
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        // Returning the found user, or null if not found any.
        return courseList[0];
    }

    @Override
    public List<Course> fetchAll() throws SQLException {
        List<Course> courseList = new ArrayList<Course>();
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "select courses.course_classroom_id, courses.course_id, courses.course_name, teachers.teacher_name, teachers.teacher_lastname, teachers.teacher_phone, teachers.teacher_email from (\n" +
                    "\tcourses\n" +
                    "\tINNER JOIN teachers ON courses.course_teacher_id = teachers.teacher_id\n" +
                    ");";
            Logger.LogDebug(query);
            int classroomCourseId = 0;
            int courseId = 0;
            String courseName = "";
            String teacherName = "";
            String teacherLastName = "";
            String teacherPhone = "";
            String teacherEmail = "";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                classroomCourseId = rs.getInt("course_classroom_id");
                courseId = rs.getInt("course_id");
                courseName = rs.getString("course_name");
                teacherName = rs.getString("teacher_name");
                teacherLastName = rs.getString("teacher_lastname");
                teacherPhone = rs.getString("teacher_phone");
                teacherEmail = rs.getString("teacher_email");
                courseList.add(new Course(classroomCourseId, courseId, courseName, teacherName, teacherLastName, teacherPhone, teacherEmail));
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        return courseList;
    }

    @Override
    public void Add(Course entity)  {
        try {
            Teacher teacherToAdd = new Teacher(99, entity.getTeacherName(), entity.getTeacherLastName(), entity.getTeacherPhone(), entity.getTeacherEmail(), 2);
            TeacherRepository.Instance.Add(teacherToAdd);
            this.connect();

            String sql = "INSERT INTO courses values (?, ?, ?, ?);";

            PreparedStatement pst = this.connection.prepareStatement(sql);

            Logger.LogDebug(TeacherRepository.Instance
                    .fetchByName(teacherToAdd.getTeacherName()).getTeacherName() + " ALLASDFDSF");

            pst.setInt(1, entity.getClassroomCourseId());
            pst.setString(2, entity.getCourseName());
            pst.setInt(3, TeacherRepository.Instance
                    .fetchByName(teacherToAdd.getTeacherName()).getTeacherId()
            );
            pst.setString(4, entity.getTeacherName());

            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            this.close();
        }
        catch(Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    public void Add(Course entity, String teacherName) {
        try {
            this.connect();

            String sqlTeacherAdd = "INSERT INTO teachers(teacher_name, teacher_lastname, teacher_phone, teacher_email, teacher_auth) VALUES ('" + entity.getTeacherName() + "','" + entity.getTeacherLastName() + "','"+ entity.getTeacherPhone() + "','" + entity.getTeacherEmail() + "', 2);";
            String sqlCourseAdd = "INSERT INTO courses values (?, ?, ?, ?);";

            Statement pstTeacher = this.connection.createStatement();

            int i = pstTeacher.executeUpdate(sqlTeacherAdd);

            PreparedStatement pstCourse = this.connection.prepareStatement(sqlCourseAdd);

            pstCourse.setInt(1, AdminClassroomInfoController.ClassroomId);
            pstCourse.setString(2, entity.getCourseName());
            //pstCourse.setInt(3, teacherName);
            pstCourse.setString(4, entity.getTeacherName());

            int i2 = pstCourse.executeUpdate();
            Logger.LogDebug(String.valueOf(i2));
            this.close();
        }
        catch(Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    public void Add(Course entity, int teacherId) {
        try {
            this.connect();

            Logger.LogDebug("START OF LOG");
            Logger.LogDebug(entity.getTeacherName());
            Logger.LogDebug(entity.getTeacherLastName());
            Logger.LogDebug(entity.getTeacherPhone());
            Logger.LogDebug(entity.getTeacherEmail());
            Logger.LogDebug("END OF LOG");

            Teacher teacherToAdd = new Teacher(99, entity.getTeacherName(), entity.getTeacherLastName(), entity.getTeacherPhone(), entity.getTeacherEmail(), 2);
            TeacherRepository.Instance.Add(teacherToAdd);

            String sqlCourseAdd = "INSERT INTO courses values (?, ?, ?, ?);";

            PreparedStatement pstCourse = this.connection.prepareStatement(sqlCourseAdd);

            pstCourse.setInt(1, AdminClassroomInfoController.ClassroomId);
            pstCourse.setString(2, entity.getCourseName());
            pstCourse.setInt(3, teacherId);
            pstCourse.setString(4, entity.getTeacherName());

            int i2 = pstCourse.executeUpdate();
            Logger.LogDebug(String.valueOf(i2));
            this.close();
        }
        catch(Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    @Override
    public void UpdateById(Course entity, int id)  {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "update courses set course_name = ? where course_id = ?;";

            String sql2 = "update teachers set teacher_name = ?, teacher_lastname = ?, teacher_phone = ?, teacher_email = ? WHERE teacher_id = ?;";

            PreparedStatement pst1 = this.connection.prepareStatement(sql);
            pst1.setString(1, entity.getCourseName());
            pst1.setInt(2, entity.getCourseId());

            int i = pst1.executeUpdate();
            Logger.LogDebug(String.valueOf(i));


            PreparedStatement pst2 = this.connection.prepareStatement(sql2);
            pst2.setString(1, entity.getTeacherName());
            pst2.setString(2, entity.getTeacherLastName());
            pst2.setString(3, entity.getTeacherPhone());
            pst2.setString(4, entity.getTeacherEmail());
            pst2.setInt(5, id);

            int i2 = pst2.executeUpdate();
            Logger.LogDebug(String.valueOf(i2));
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    @Override
    public void Delete(int id) {

    }
}

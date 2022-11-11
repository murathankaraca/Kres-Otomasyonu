package meltem.services.data_access.concrete;

import meltem.enums.LogType;
import meltem.models.Classroom;
import meltem.models.Student;
import meltem.models.Teacher;
import meltem.services.data_access.PersistentDataService;
import meltem.services.logging.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository extends PersistentDataService<Teacher> {

    public static TeacherRepository Instance;

    public TeacherRepository() {
        if(Instance == null) {
            Instance = this;
        }
    }

    @Override
    public Teacher fetchById(int id) {
        Teacher[] teachers = new Teacher[1];
        Teacher teacher = new Teacher(99, "", "", "", "", 99);
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT TOP 1 * FROM teachers WHERE teacher_id = %d", id);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                // teacher.setId blah blah
                teacher.setTeacherId(rs.getInt(1));
                teacher.setTeacherName(rs.getString(2));
                teacher.setTeacherLastName(rs.getString(3));
                teacher.setTeacherPhone(rs.getString(4));
                teacher.setTeacherEmail(rs.getString(5));
                teacher.setTeacherAuth(rs.getInt(6));
            }
            teachers[0] = teacher;
            // Bitis
            this.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return teachers[0];
    }

    public Teacher fetchByName(String name) {
        Logger.LogDebug(name + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
        Teacher[] teachers = new Teacher[1];
        Teacher teacher = new Teacher(-1, "", "", "", "", -1);
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT TOP 1 * FROM teachers WHERE teacher_name = '%s'", name);

            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                teacher.setTeacherId(rs.getInt(1));
                teacher.setTeacherName(rs.getString(2));
                teacher.setTeacherLastName(rs.getString(3));
                teacher.setTeacherPhone(rs.getString(4));
                teacher.setTeacherEmail(rs.getString(5));
                teacher.setTeacherAuth(rs.getInt(6));
            }
            if(teacher.getTeacherId() != -1) {
                teachers[0] = teacher;
            }
            // Bitis
            this.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return teachers[0];
    }

    @Override
    public List<Teacher> fetchAll() throws SQLException {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM teachers";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getInt("teacher_id"),
                        rs.getString("teacher_name"),
                        rs.getString("teacher_lastname"),
                        rs.getString("teacher_phone"),
                        rs.getString("teacher_email"),
                        rs.getInt("teacher_auth")
                );
                teacherList.add(teacher);
                Logger.Log(LogType.Debug, String.valueOf(teacher.getTeacherPhone()));
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        return teacherList;
    }

    @Override
    public void Add(Teacher entity)  {
        try {
            this.connect();
            String sql = "INSERT INTO teachers(teacher_name, teacher_lastname, teacher_phone, teacher_email, teacher_auth) VALUES (?,?,?,?,?);";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getTeacherName());
            pst.setString(2, entity.getTeacherLastName());
            pst.setString(3, entity.getTeacherPhone());
            pst.setString(4, entity.getTeacherEmail());
            pst.setInt(5, 2);
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            this.close();
        }
        catch(Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    public void Add(Teacher entity, int auth)  {
        try {
            Logger.LogDebug(entity.getTeacherName());
            this.connect();
            String sql = "INSERT INTO teachers(teacher_name, teacher_lastname, teacher_phone, teacher_email, teacher_auth) VALUES (?,?,?,?,?);";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getTeacherName());
            pst.setString(2, entity.getTeacherLastName());
            pst.setString(3, entity.getTeacherPhone());
            pst.setString(4, entity.getTeacherEmail());
            pst.setInt(5, auth);
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            this.close();
        }
        catch(Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    @Override
    public void UpdateById(Teacher entity, int teacherId) {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "UPDATE teachers SET teacher_name = ?, teacher_lastname = ?, teacher_phone = ?, teacher_email = ?, teacher_auth = ? WHERE teacher_id = ?";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getTeacherName());
            pst.setString(2, entity.getTeacherLastName());
            pst.setString(3, entity.getTeacherPhone());
            pst.setString(4, entity.getTeacherEmail());
            pst.setInt(5, entity.getTeacherAuth());
            pst.setInt(6, teacherId);
            int i = pst.executeUpdate();
            Logger.LogDebug("RESULTS OF THIS TEACHER UPDATE IS: " + i);
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

    public Teacher returnLast() {
        Teacher[] teachers = new Teacher[1];
        try {
            Teacher teacher = new Teacher(-1, "", "", "", "", 2);
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "SELECT TOP 1 * FROM teachers ORDER BY teachers.teacher_id DESC;";

            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                teacher.setTeacherId(rs.getInt("teacher_id"));
                teacher.setTeacherName(rs.getString("teacher_name"));
                teacher.setTeacherLastName(rs.getString("teacher_lastname"));
                teacher.setTeacherPhone(rs.getString("teacher_phone"));
                teacher.setTeacherEmail(rs.getString("teacher_email"));
                teacher.setTeacherAuth(rs.getInt("teacher_auth"));
            }
            Logger.LogDebug(String.valueOf(teacher.getTeacherName()));
            teachers[0] = teacher;
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return teachers[0];
    }

}

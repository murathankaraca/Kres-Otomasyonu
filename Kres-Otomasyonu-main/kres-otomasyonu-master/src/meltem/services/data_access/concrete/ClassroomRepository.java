package meltem.services.data_access.concrete;

import meltem.enums.LogType;
import meltem.models.Classroom;
import meltem.models.User;
import meltem.services.data_access.PersistentDataService;
import meltem.services.logging.Logger;
import meltem.view_models.ClassroomViewModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassroomRepository extends PersistentDataService<Classroom> {
    public static ClassroomRepository Instance;

    public ClassroomRepository() {
        if(Instance == null) {
            Instance = this;
        }
    }
    @Override
    public Classroom fetchById(int id) {
        Classroom[] classroomList = new Classroom[1];
        try {
            Classroom classroom = new Classroom(-1, "", "", "", 25);
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT classrooms.classroom_id, classrooms.classroom_name, teachers.teacher_name, teachers.teacher_lastname, classrooms.classroom_capacity FROM classrooms\n" +
            "INNER JOIN teachers ON classroom_teacher_id = teachers.teacher_id WHERE teachers.teacher_id = %d;", id);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                classroom.setClassroomId(rs.getInt("classroom_id"));
                classroom.setClassroomName(rs.getString("classroom_name"));
                classroom.setClassroomTeacherName(rs.getString("teacher_name"));
                classroom.setClassroomTeacherLastName(rs.getString("teacher_lastname"));
                classroom.setClassroomCapacity(rs.getInt("classroom_capacity"));
            }
            classroomList[0] = classroom;
            // Bitis
            this.close();
        }
        catch (Exception ex) {
             ex.printStackTrace();
        }
        return classroomList[0];
    }


    public int fetchTeacherId(Classroom entity) {
        Classroom[] classroomList = new Classroom[1];
        try {
            Classroom classroom = new Classroom(-1, "", "", "", 25);
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT classrooms.classroom_id, classrooms.classroom_name, teachers.teacher_name, teachers.teacher_lastname, classrooms.classroom_capacity FROM classrooms\n" +
                    "INNER JOIN teachers ON classroom_teacher_id = teachers.teacher_id WHERE teachers.teacher_name = %s, teachers.teacher_lastname = %s;",
                    entity.getClassroomTeacherName(),
                    entity.getClassroomTeacherLastName()
            );
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                classroom.setClassroomId(rs.getInt("classroom_id"));
                classroom.setClassroomName(rs.getString("classroom_name"));
                classroom.setClassroomTeacherName(rs.getString("teacher_name"));
                classroom.setClassroomTeacherLastName(rs.getString("teacher_lastname"));
                classroom.setClassroomCapacity(rs.getInt("classroom_capacity"));
            }
            classroomList[0] = classroom;
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return classroomList[0].getClassroomTeacherId();
    }

    @Override
    public List<Classroom> fetchAll() {
        List<Classroom> classroomList = new ArrayList<Classroom>();
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "SELECT classrooms.classroom_id, classrooms.classroom_name, teachers.teacher_name, teachers.teacher_lastname, classrooms.classroom_capacity FROM classrooms\n" +
                    "INNER JOIN teachers ON classroom_teacher_id = teachers.teacher_id;";
            int classroomId = -1;
            String classroomName = "";
            String teacherName = "";
            String teacherLastName = "";
            int classroomCapacity = -1;
            Classroom classroom = null;
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                classroomId = rs.getInt("classroom_id");
                classroomName = rs.getString("classroom_name");
                teacherName = rs.getString("teacher_name");
                teacherLastName = rs.getString("teacher_lastname");
                classroomCapacity = rs.getInt("classroom_capacity");
                classroomList.add(new Classroom(classroomId, classroomName, teacherName, teacherLastName, classroomCapacity));
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return classroomList;
    }

    @Override
    public void Add(Classroom entity) {
        try {
            this.connect();
            String sql = "INSERT INTO teachers VALUES(?, ?, ?, ?, 2)";

            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getClassroomName());
            pst.setString(2, entity.getClassroomTeacherName());
            pst.setString(3, entity.getClassroomTeacherLastName());
            pst.setInt(4, entity.getClassroomCapacity());

            int resultPst1 = pst.executeUpdate();

            String sql2 = "INSERT INTO classrooms VALUES(?, ?, ?)";

            PreparedStatement pst2 = this.connection.prepareStatement(sql2);
            pst2.setInt(1, Instance.returnLast().getClassroomTeacherId());
            pst2.setString(2, entity.getClassroomName());
            pst2.setInt(3, entity.getClassroomCapacity());

            int resultPst2 = pst2.executeUpdate();

            Logger.LogDebug(String.valueOf(resultPst1) + " " + String.valueOf(resultPst2));

            this.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Add(Classroom entity, int classroomTeacherId) {
        try {
            this.connect();
            String sql = "INSERT INTO TEACHERS(teacher_name, teacher_lastname, teacher_auth) VALUES(?,?,2);";

            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getClassroomTeacherName());
            pst.setString(2, entity.getClassroomTeacherLastName());

            int resultPst1 = pst.executeUpdate();

            String sql2 = "INSERT INTO classrooms VALUES(?, ?, ?)";

            PreparedStatement pst2 = this.connection.prepareStatement(sql2);
            pst2.setInt(1, classroomTeacherId);
            pst2.setString(2, entity.getClassroomName());
            pst2.setInt(3, entity.getClassroomCapacity());

            int resultPst2 = pst2.executeUpdate();

            Logger.LogDebug(String.valueOf(resultPst1) + " " + String.valueOf(resultPst2));

            this.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void UpdateById(Classroom entity, int teacherId) {
        try {
            this.connect();
            String sql = "UPDATE teachers SET teacher_name = ?, teacher_lastname = ?, teacher_phone = ?, teacher_email = ?, teacher_auth = 2;";

            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getClassroomName());
            pst.setString(2, entity.getClassroomTeacherName());
            pst.setString(3, entity.getClassroomTeacherLastName());
            pst.setInt(4, entity.getClassroomCapacity());

            int resultPst1 = pst.executeUpdate();

            String sql2 = "UPDATE classroooms SET classroom_teacher_id = ?, classroom_name = ?, classroom_capacity = ?;";

            PreparedStatement pst2 = this.connection.prepareStatement(sql2);
            pst2.setInt(1, teacherId);
            pst2.setString(2, entity.getClassroomName());
            pst2.setInt(3, entity.getClassroomCapacity());

            int resultPst2 = pst2.executeUpdate();

            Logger.LogDebug(String.valueOf(resultPst1) + " " + String.valueOf(resultPst2));

            this.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void Delete(int id) {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "DELETE FROM classrooms WHERE classroom_id = ?";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setInt(1, id);
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    public Classroom returnLast() {
        Classroom[] classroomList = new Classroom[1];
        try {
            Classroom classroom = new Classroom(-1, "", "", "", 25);
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "SELECT TOP 1 classrooms.classroom_id, classrooms.classroom_name, teachers.teacher_name, teachers.teacher_lastname, classrooms.classroom_capacity FROM classrooms \n" +
                    "INNER JOIN teachers ON classroom_teacher_id = teachers.teacher_id ORDER BY classrooms.classroom_id DESC;";

            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                classroom.setClassroomId(rs.getInt("classroom_id"));
                classroom.setClassroomName(rs.getString("classroom_name"));
                classroom.setClassroomTeacherName(rs.getString("teacher_name"));
                classroom.setClassroomTeacherLastName(rs.getString("teacher_lastname"));
                classroom.setClassroomCapacity(rs.getInt("classroom_capacity"));
            }
            Logger.LogDebug(String.valueOf(classroom.getClassroomId()));
            classroomList[0] = classroom;
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return classroomList[0];
    }
}

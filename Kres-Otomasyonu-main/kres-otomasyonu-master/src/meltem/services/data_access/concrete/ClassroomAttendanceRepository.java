package meltem.services.data_access.concrete;

import meltem.controllers.StudentEditController;
import meltem.controllers.StudentNewController;
import meltem.enums.LogType;
import meltem.models.ClassroomAttendance;
import meltem.models.Student;
import meltem.services.data_access.PersistentDataService;
import meltem.services.logging.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassroomAttendanceRepository extends PersistentDataService<Student> {
    public static ClassroomAttendanceRepository Instance;

    public ClassroomAttendanceRepository() {
        if(Instance == null) {
            Instance = this;
        }
    }


    @Override
    public Student fetchById(int classroomId) throws SQLException {
        Student[] studentList = new Student[1];
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM students WHERE student_classroom_id = %d;", classroomId);
            Logger.LogDebug(query);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("student_lastname"),
                        rs.getString("orientation_start"),
                        rs.getString("orientation_end"),
                        rs.getString("parent_name"),
                        rs.getString("parent_lastname"),
                        rs.getString("parent_phone"),
                        rs.getString("parent_email"),
                        rs.getInt("payment_monthly")
                );
                StringBuilder sb = new StringBuilder();
                sb.append("Student by the id of ");
                sb.append(student.getStudentId());
                sb.append(" was found. ");
                sb.append("Orientation start and end dates are: ");
                sb.append(student.getOrientationStart());
                sb.append(", ");
                sb.append(student.getOrientationEnd());
                Logger.LogDebug(sb.toString());
                studentList[0] = student;
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        // Returning the found user, or null if not found any.
        return studentList[0];
    }

    @Override
    public List<Student> fetchAll() throws SQLException {
        return null;
    }

    public List<Student> fetchAll(int classroomId)  {
        List<Student> studentList = new ArrayList<Student>();
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "SELECT student_id, student_name, student_lastname, orientation_start, orientation_end, parent_name, parent_lastname, parent_phone, parent_email, payment_monthly FROM students WHERE student_classroom_id = " + classroomId + ";";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("student_lastname"),
                        rs.getString("orientation_start"),
                        rs.getString("orientation_end"),
                        rs.getString("parent_name"),
                        rs.getString("parent_lastname"),
                        rs.getString("parent_phone"),
                        rs.getString("parent_email"),
                        rs.getInt("payment_monthly")
                );
                studentList.add(student);
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return studentList;
    }

    @Override
    public void Add(Student entity) {
        try {
            this.connect();
            Logger.LogDebug("BEFORE OPERATION, CLASSROOM ID IS: " + StudentNewController.ClassroomId);
            String sql = "INSERT INTO students(students.student_name, students.student_lastname, students.orientation_start, students.orientation_end, students.parent_name, students.parent_lastname, students.parent_phone, students.parent_email, students.payment_monthly, students.student_classroom_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getStudentName());
            pst.setString(2, entity.getStudentLastName());
            pst.setString(3, entity.getOrientationStart());
            pst.setString(4, entity.getOrientationEnd());
            pst.setString(5, entity.getParentName());
            pst.setString(6, entity.getParentLastName());
            pst.setString(7, entity.getParentNumber());
            pst.setString(8, entity.getParentEmail());
            pst.setInt(9, entity.getPaymentMonthly());
            pst.setInt(10, StudentNewController.ClassroomId);
            int i = pst.executeUpdate();
            Logger.LogDebug("BEFORE RESULT");
            Logger.LogDebug(String.valueOf(i) + " IS THE RESULT OF ADD");
            Logger.LogDebug("END OF RESULT");
            this.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Add(Student entity, int classroomId) {
        try {
            this.connect();
            Logger.LogDebug("BEFORE OPERATION, CLASSROOM ID IS: " + StudentNewController.ClassroomId);
            String sql = "INSERT INTO students(student_name, student_lastname, orientation_start, orientation_end, parent_name, parent_lastname, parent_phone, parent_email, payment_monthly, student_classroom_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getStudentName());
            pst.setString(2, entity.getStudentLastName());
            pst.setString(3, entity.getOrientationStart());
            pst.setString(4, entity.getOrientationEnd());
            pst.setString(5, entity.getParentName());
            pst.setString(6, entity.getParentLastName());
            pst.setString(7, entity.getParentNumber());
            pst.setString(8, entity.getParentEmail());
            pst.setInt(9, entity.getPaymentMonthly());
            pst.setInt(10, classroomId);
            int i = pst.executeUpdate();
            Logger.LogDebug("BEFORE RESULT" + "ID: " + classroomId);
            Logger.LogDebug(String.valueOf(i));
            Logger.LogDebug("END OF RESULT");
            this.close();
        }
        catch(Exception ex) {
            Logger.Log(LogType.Error, ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void UpdateById(Student entity, int id) throws SQLException {

    }

    @Override
    public void Delete(int id) throws SQLException {

    }
}

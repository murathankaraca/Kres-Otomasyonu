package meltem.services.data_access.concrete;

import meltem.controllers.StudentNewController;
import meltem.enums.LogType;
import meltem.models.Classroom;
import meltem.models.Student;
import meltem.models.Teacher;
import meltem.models.User;
import meltem.services.data_access.PersistentDataService;
import meltem.services.logging.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository extends PersistentDataService<Student> {

    public static StudentRepository Instance;

    public StudentRepository() {
        if(Instance == null) {
            Instance = this;
        }
    }

    public Student returnLast() {
        Student[] studentList = new Student[1];
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "SELECT TOP 1 * FROM students ORDER BY students.student_id DESC;";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
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
                // Bitis
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        this.close();
        return studentList[0];
    }


    @Override
    public Student fetchById(int id) {
        Student[] studentList = new Student[1];
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM students WHERE student_id = %d;", id);
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

    public Student fetchByName(String studentName) {
        Student[] studentList = new Student[1];
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM students WHERE student_name = '%s';", studentName);
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
        List<Student> studentList = new ArrayList<Student>();
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM students";
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
                Logger.Log(LogType.Debug, String.valueOf(student.getParentEmail()));
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        return studentList;
    }

    public List<Student> fetchAllBranch(int id) throws SQLException {
        List<Student> studentList = new ArrayList<Student>();
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM students";
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
                Logger.Log(LogType.Debug, String.valueOf(student.getParentEmail()));
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        return studentList;
    }

    @Override
    public void Add(Student entity) throws SQLException {
        try {
            this.connect();
            String sql = "INSERT INTO students VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setInt(1, entity.getStudentId());
            pst.setString(2, entity.getStudentName());
            pst.setString(3, entity.getStudentLastName());
            pst.setString(4, entity.getOrientationStart());
            pst.setString(5, entity.getOrientationEnd());
            pst.setString(6, entity.getParentName());
            pst.setString(7, entity.getParentLastName());
            pst.setString(8, entity.getParentNumber());
            pst.setString(9, entity.getParentEmail());
            pst.setInt(10, entity.getPaymentMonthly());
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
        }
        catch(Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        this.close();
    }

    public void AddToBranch(Student entity) {
        try {
            this.connect();
            Logger.LogDebug("BEFORE OPERATION, Branch ID IS: " + StudentNewController.BranchCourseId);
            String sql = "INSERT INTO students(students.student_name, students.student_lastname, students.orientation_start, students.orientation_end, students.parent_name, students.parent_lastname, students.parent_phone, students.parent_email, students.payment_monthly, students.student_branch_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            pst.setInt(10, StudentNewController.BranchCourseId);
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


    @Override
    public void UpdateById(Student entity, int id) throws SQLException {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "UPDATE students SET student_name = ?, student_lastname = ?, orientation_start = ?, orientation_end = ?, parent_name = ?, parent_lastname = ?, parent_phone = ?, parent_email = ?, payment_monthly = ? WHERE student_id = ?";
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
            pst.setInt(10, id);
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    public void UpdateClassroomId(Student entity, int studentId, int classroomId) throws SQLException {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "UPDATE students SET student_name = ?, student_lastname = ?, orientation_start = ?, orientation_end = ?, parent_name = ?, parent_lastname = ?, parent_phone = ?, parent_email = ?, payment_monthly = ?, student_classroom_id = ? WHERE student_id = ?";
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
            pst.setInt(11, studentId);
            int i = pst.executeUpdate();
            Logger.LogDebug("[UpdateClassroomId()] Result of executed update is: " + i);
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    public void UpdateBranchId(Student entity, int studentId, int branchId) throws SQLException {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "UPDATE students SET student_name = ?, student_lastname = ?, orientation_start = ?, orientation_end = ?, parent_name = ?, parent_lastname = ?, parent_phone = ?, parent_email = ?, payment_monthly = ?, student_branch_id = ? WHERE student_id = ?";
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
            pst.setInt(10, branchId);
            pst.setInt(11, studentId);
            int i = pst.executeUpdate();
            Logger.LogDebug("[UpdateBranchId()] Result of executed update is: " + i);
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "DELETE FROM students WHERE student_id = ?";
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
}

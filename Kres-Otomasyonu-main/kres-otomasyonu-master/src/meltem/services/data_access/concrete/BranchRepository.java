package meltem.services.data_access.concrete;

import meltem.controllers.BranchEditController;
import meltem.controllers.StudentEditController;
import meltem.enums.LogType;
import meltem.models.*;
import meltem.services.data_access.PersistentDataService;
import meltem.services.logging.Logger;
import meltem.view_models.BranchViewModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BranchRepository extends PersistentDataService<Branch> {
    public static BranchRepository Instance;

    public BranchRepository() {
        if(Instance == null) {
            Instance = this;
        }
    }

    @Override
    public Branch fetchById(int id) {
        Branch[] branchList = new Branch[1];
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM branch_courses WHERE branch_id = %d", id);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                Branch branch = new Branch(
                        rs.getInt("branch_id"),
                        rs.getString("branch_name"),
                        rs.getInt("branch_teacher_id"),
                        rs.getInt("branch_capacity")
                );
                branchList[0] = branch;
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        return branchList[0];
    }

    @Override
    public List<Branch> fetchAll() {
        List<Branch> branchList = new ArrayList<Branch>();
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM branch_courses";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                Branch branch = new Branch (
                        rs.getInt("branch_id"),
                        rs.getString("branch_name"),
                        rs.getInt("branch_teacher_id"),
                        rs.getInt("branch_capacity")
                );
                branchList.add(branch);
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        return branchList;
    }

    @Override
    public void Add(Branch entity) {
        try {
            this.connect();
            String sql = "INSERT INTO teachers VALUES(?, ?, ?, ?, 3)";

            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getBranchCourseName());
            pst.setString(2, entity.getBranchTeacherName());
            pst.setString(3, entity.getBranchTeacherLastName());
            pst.setInt(4, entity.getBranchCapacity());

            int resultPst1 = pst.executeUpdate();

            String sql2 = "INSERT INTO branch_courses VALUES(?, ?, ?)";

            PreparedStatement pst2 = this.connection.prepareStatement(sql2);

            pst2.setString(1, entity.getBranchCourseName());
            pst2.setInt(2, Instance.returnLast().getTeacherId());
            pst2.setInt(3, entity.getBranchCapacity());

            int resultPst2 = pst2.executeUpdate();

            Logger.LogDebug(String.valueOf(resultPst1) + " " + String.valueOf(resultPst2));

            this.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void AddStudentToBranchCourse(int branchId, Student entity) {

        try {
            entity.setBranchId(branchId);
            StudentRepository.Instance.AddToBranch(entity);
            Student addedStudent = StudentRepository.Instance.returnLast();
            Logger.LogDebug(addedStudent.getParentNumber() + " IS THE PARENT NUMBER OF LAST INDEX");
            Logger.LogDebug(addedStudent.getStudentId() + " x");


            this.connect();

            String sql2 = "INSERT INTO branch_attendance VALUES (?, ?);";

            PreparedStatement pst = this.connection.prepareStatement(sql2);
            pst.setInt(1, branchId);
            pst.setInt(2, addedStudent.getStudentId());

            int i = pst.executeUpdate();
            Logger.LogDebug("RESULT OF UPDATING BRANCH ATTENDANCE IS: " + i);




        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    public List<Student> fetchAllStudents(int branchId) {
        List<Student> studentList = new ArrayList<Student>();
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("select students.student_id, students.student_name, students.student_lastname, students.orientation_start, students.orientation_end, students.parent_name, students.parent_lastname, students.parent_phone, students.parent_email FROM (\n" +
                    "\tbranch_attendance\n" +
                    "\tINNER JOIN students ON branch_attendance.student_id = students.student_id\n" +
                    ") WHERE branch_attendance.branch_id = %d;", branchId);
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
                        850
                );
                studentList.add(student);
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
    public void UpdateById(Branch branch, int teacherId) {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "UPDATE branch_courses SET branch_name = ?, branch_teacher_id = ?, branch_capacity = ? WHERE branch_id = ?";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, branch.getBranchCourseName());
            pst.setInt(2, branch.getBranchTeacher());
            pst.setInt(3, branch.getBranchCapacity());
            pst.setInt(4, branch.getBranchId());
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
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
            String sql = "DELETE FROM branch_courses WHERE branch_id = ?";
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

    public void Add(Branch entity, int teacherIndex) {
        try {
            this.connect();
            String sql = "INSERT INTO branch_courses(branch_name, branch_teacher_id, branch_capacity) VALUES (?,?,?);";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getBranchCourseName());
            pst.setInt(2, teacherIndex);
            pst.setInt(3, entity.getBranchCapacity());
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            this.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Branch> fetchAllTeachers() {
        List<Branch> branches = new ArrayList<Branch>();
        try {

            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "select branch_id, branch_name, teacher_name, teacher_lastname, teacher_phone, branch_capacity from (\n" +
                    "\tbranch_courses\n" +
                    "\tINNER JOIN teachers ON branch_courses.branch_teacher_id = teachers.teacher_id\n" +
                    ");";

            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                Branch branch = new Branch(-1, "", "", "", 2);
                Logger.LogDebug("INDEX OF BRANCH IS: " + rs.getInt("branch_id"));
                branch.setBranchId(rs.getInt("branch_id"));
                branch.setBranchCourseName(rs.getString("branch_name"));
                branch.setBranchTeacherName(rs.getString("teacher_name"));
                branch.setBranchTeacherLastName(rs.getString("teacher_lastname"));
                branch.setBranchTeacherPhone(rs.getString("teacher_phone"));
                branch.setBranchCapacity(rs.getInt("branch_capacity"));

                branches.add(branch);
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return branches;
    }
    public Teacher fetchTeacherByBranch(int branchId) {
        Teacher[] teachers = new Teacher[1];
        Teacher teacher = new Teacher(-1, "", "", "", "", -1);
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM teachers WHERE teacher_name = %d", branchId);

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
}

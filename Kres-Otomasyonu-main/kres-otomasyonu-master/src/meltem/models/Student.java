package meltem.models;

import java.sql.Date;

public class Student {
    private int studentId;
    private String studentName;
    private String studentLastName;
    private String studentGender;
    private String orientationStart;
    private String orientationEnd;
    private String parentName;
    private String parentLastName;
    private String parentNumber;
    private String parentEmail;
    private int paymentMonthly;

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    private int branchId;

    public int getStudentId() {
        return this.studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getOrientationStart() {
        return orientationStart;
    }

    public void setOrientationStart(String orientationStart) {
        this.orientationStart = orientationStart;
    }

    public String getOrientationEnd() {
        return orientationEnd;
    }

    public void setOrientationEnd(String orientationEnd) {
        this.orientationEnd = orientationEnd;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentLastName() {
        return parentLastName;
    }

    public void setParentLastName(String parentLastName) {
        this.parentLastName = parentLastName;
    }

    public String getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(String parentNumber) {
        this.parentNumber = parentNumber;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public int getPaymentMonthly() {
        return paymentMonthly;
    }

    public void setPaymentMonthly(int paymentMonthly) {
        this.paymentMonthly = paymentMonthly;
    }

    public Student(int id,
                   String name,
                   String lastName,
                   String orientationStart,
                   String orientationEnd,
                   String parentName,
                   String parentLastName,
                   String parentNumber,
                   String parentEmail,
                   int paymentMonthly
    ) {
        this.studentId = id;
        this.studentName = name;
        this.studentLastName = lastName;
        this.orientationStart = orientationStart;
        this.orientationEnd = orientationEnd;
        this.parentName = parentName;
        this.parentLastName = parentLastName;
        this.parentNumber = parentNumber;
        this.parentEmail = parentEmail;
        this.paymentMonthly = paymentMonthly;
    }
}

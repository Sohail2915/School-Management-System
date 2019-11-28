/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subject;

/**
 *
 * @author kamran qadeer
 */
public class subjectlist {
    private String SubjectName;
    
    private String SubjectCode;
    
    private String TeacherName;
    
    private String TeacherID;

    public subjectlist(String SubjectName, String SubjectCode, String TeacherName, String TeacherID) {
        this.SubjectName = SubjectName;
        this.SubjectCode = SubjectCode;
        this.TeacherName = TeacherName;
        this.TeacherID = TeacherID;
    }

    /**
     * @return the SubjectName
     */
    public String getSubjectName() {
        return SubjectName;
    }

    /**
     * @param SubjectName the SubjectName to set
     */
    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }

    /**
     * @return the SubjectCode
     */
    public String getSubjectCode() {
        return SubjectCode;
    }

    /**
     * @param SubjectCode the SubjectCode to set
     */
    public void setSubjectCode(String SubjectCode) {
        this.SubjectCode = SubjectCode;
    }

    /**
     * @return the TeacherName
     */
    public String getTeacherName() {
        return TeacherName;
    }

    /**
     * @param TeacherName the TeacherName to set
     */
    public void setTeacherName(String TeacherName) {
        this.TeacherName = TeacherName;
    }

    /**
     * @return the TeacherID
     */
    public String getTeacherID() {
        return TeacherID;
    }

    /**
     * @param TeacherID the TeacherID to set
     */
    public void setTeacherID(String TeacherID) {
        this.TeacherID = TeacherID;
    }
}

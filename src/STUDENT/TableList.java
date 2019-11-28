/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STUDENT;

/**
 *
 * @author kamran qadeer
 */

public class TableList {
    private String name;
    private String fathname;
    private String gender;
    private String id;
    private String clas;
    private String email;
    private String Pcont;
    private String dbd;
    

    public TableList(String name, String fathname,String gender, String id, String clas, String email, String Pcont, String dbd) {
        this.name = name;
        this.fathname = fathname;
        this.id = id;
        this.clas = clas;
        this.email = email;
        this.Pcont = Pcont;
        this.dbd = dbd;
        this.gender = gender;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the fathname
     */
    public String getFathname() {
        return fathname;
    }

    /**
     * @param fathname the fathname to set
     */
    public void setFathname(String fathname) {
        this.fathname = fathname;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the clas
     */
    public String getClas() {
        return clas;
    }

    /**
     * @param clas the clas to set
     */
    public void setClas(String clas) {
        this.clas = clas;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the Pcont
     */
    public String getPcont() {
        return Pcont;
    }

    /**
     * @param Pcont the Pcont to set
     */
    public void setPcont(String Pcont) {
        this.Pcont = Pcont;
    }

    /**
     * @return the dbd
     */
    public String getDbd() {
        return dbd;
    }

    /**
     * @param dbd the dbd to set
     */
    public void setDbd(String dbd) {
        this.dbd = dbd;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

}
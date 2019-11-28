/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TEACHER;

/**
 *
 * @author kamran qadeer
 */
public class TableList {
    private String name;
    private String fname;
    private String id;
    private String totalclass;
    private String email;
    private String cont;
    private String dbd;
    private String subjAss;
    private String edu;
    private String exp;    

    public TableList(String name, String fname, String id, String totalclass, String email, String cont, String dbd, String subjAss, String edu, String exp) {
        this.name = name;
        this.fname = fname;
        this.id = id;
        this.totalclass = totalclass;
        this.email = email;
        this.cont = cont;
        this.dbd = dbd;
        this.subjAss = subjAss;
        this.edu = edu;
        this.exp = exp;
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
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
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
     * @return the totalclass
     */
    public String getTotalclass() {
        return totalclass;
    }

    /**
     * @param totalclass the totalclass to set
     */
    public void setTotalclass(String totalclass) {
        this.totalclass = totalclass;
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
     * @return the cont
     */
    public String getCont() {
        return cont;
    }

    /**
     * @param cont the cont to set
     */
    public void setCont(String cont) {
        this.cont = cont;
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
     * @return the subjAss
     */
    public String getSubjAss() {
        return subjAss;
    }

    /**
     * @param subjAss the subjAss to set
     */
    public void setSubjAss(String subjAss) {
        this.subjAss = subjAss;
    }

    /**
     * @return the edu
     */
    public String getEdu() {
        return edu;
    }

    /**
     * @param edu the edu to set
     */
    public void setEdu(String edu) {
        this.edu = edu;
    }

    /**
     * @return the exp
     */
    public String getExp() {
        return exp;
    }

    /**
     * @param exp the exp to set
     */
    public void setExp(String exp) {
        this.exp = exp;
    }
}

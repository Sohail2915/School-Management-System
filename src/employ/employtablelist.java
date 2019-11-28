/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employ;

/**
 *
 * @author kamran qadeer
 */

public class employtablelist {
    private String name;
    private String fname;
    private String id;
    private String add;
    private String cont;
    private String edu;
    private String dbd;
    private String exp;

    public employtablelist(String name, String fname, String id, String address, String cont, String edu, String exp, String dbd) {
        this.name = name;
        this.fname = fname;
        this.id = id;
        this.add = address;
        this.cont = cont;
        this.edu = edu;
        this.dbd = dbd;
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
     * @return the add
     */
    public String getAdd() {
        return add;
    }

    /**
     * @param add the add to set
     */
    public void setAdd(String add) {
        this.add = add;
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
 
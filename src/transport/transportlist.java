/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transport;

/**
 *
 * @author kamran qadeer
 */
public class transportlist {
     private String Vname;
    
    private String Vid;
    
    private String EmployName;
    
    private String EmployID;
    
    private String EmployCont;

    public transportlist(String Vname, String Vid, String EmployName, String EmployID, String EmployCont) {
        this.Vname = Vname;
        this.Vid = Vid;
        this.EmployName = EmployName;
        this.EmployID = EmployID;
        this.EmployCont = EmployCont;
    }

    /**
     * @return the Vname
     */
    public String getVname() {
        return Vname;
    }

    /**
     * @param Vname the Vname to set
     */
    public void setVname(String Vname) {
        this.Vname = Vname;
    }

    /**
     * @return the Vid
     */
    public String getVid() {
        return Vid;
    }

    /**
     * @param Vid the Vid to set
     */
    public void setVid(String Vid) {
        this.Vid = Vid;
    }

    /**
     * @return the EmployName
     */
    public String getEmployName() {
        return EmployName;
    }

    /**
     * @param EmployName the EmployName to set
     */
    public void setEmployName(String EmployName) {
        this.EmployName = EmployName;
    }

    /**
     * @return the EmployID
     */
    public String getEmployID() {
        return EmployID;
    }

    /**
     * @param EmployID the EmployID to set
     */
    public void setEmployID(String EmployID) {
        this.EmployID = EmployID;
    }

    /**
     * @return the EmployCont
     */
    public String getEmployCont() {
        return EmployCont;
    }

    /**
     * @param EmployCont the EmployCont to set
     */
    public void setEmployCont(String EmployCont) {
        this.EmployCont = EmployCont;
    }

    }
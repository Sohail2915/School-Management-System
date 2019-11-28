/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hostal;

/**
 *
 * @author kamran qadeer
 */
public class hostallist {
     private String name;
    
    private String id;
    
    private String studentclass;
    
    private String roomnumber;
    
    private String monthlydues;

    public hostallist(String name, String id, String studentclass, String roomnumber, String monthlydues) {
        this.name = name;
        this.id = id;
        this.studentclass = studentclass;
        this.roomnumber = roomnumber;
        this.monthlydues = monthlydues;
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
     * @return the studentclass
     */
    public String getStudentclass() {
        return studentclass;
    }

    /**
     * @param studentclass the studentclass to set
     */
    public void setStudentclass(String studentclass) {
        this.studentclass = studentclass;
    }

    /**
     * @return the roomnumber
     */
    public String getRoomnumber() {
        return roomnumber;
    }

    /**
     * @param roomnumber the roomnumber to set
     */
    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    /**
     * @return the monthlydues
     */
    public String getMonthlydues() {
        return monthlydues;
    }

    /**
     * @param monthlydues the monthlydues to set
     */
    public void setMonthlydues(String monthlydues) {
        this.monthlydues = monthlydues;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book;

/**
 *
 * @author kamran qadeer
 */
public class table {
    private String BookName;
    private String AuthorName;
    private String BookID;
    private String PersonName;
    private String PersonID;
    private String IssuDays;
    private String IssuDate;
    private String ReturnDate;

    public table(String BookName, String AuthorName, String BookID, String PersonName, String PersonID, String IssuDays, String IssuDate, String ReturnDate) {
        this.BookName = BookName;
        this.AuthorName = AuthorName;
        this.BookID = BookID;
        this.PersonName = PersonName;
        this.PersonID = PersonID;
        this.IssuDays = IssuDays;
        this.IssuDate = IssuDate;
        this.ReturnDate = ReturnDate;
    }

    /**
     * @return the BookName
     */
    public String getBookName() {
        return BookName;
    }

    /**
     * @param BookName the BookName to set
     */
    public void setBookName(String BookName) {
        this.BookName = BookName;
    }

    /**
     * @return the AuthorName
     */
    public String getAuthorName() {
        return AuthorName;
    }

    /**
     * @param AuthorName the AuthorName to set
     */
    public void setAuthorName(String AuthorName) {
        this.AuthorName = AuthorName;
    }

    /**
     * @return the BookID
     */
    public String getBookID() {
        return BookID;
    }

    /**
     * @param BookID the BookID to set
     */
    public void setBookID(String BookID) {
        this.BookID = BookID;
    }

    /**
     * @return the PersonName
     */
    public String getPersonName() {
        return PersonName;
    }

    /**
     * @param PersonName the PersonName to set
     */
    public void setPersonName(String PersonName) {
        this.PersonName = PersonName;
    }

    /**
     * @return the PersonID
     */
    public String getPersonID() {
        return PersonID;
    }

    /**
     * @param PersonID the PersonID to set
     */
    public void setPersonID(String PersonID) {
        this.PersonID = PersonID;
    }

    /**
     * @return the IssuDays
     */
    public String getIssuDays() {
        return IssuDays;
    }

    /**
     * @param IssuDays the IssuDays to set
     */
    public void setIssuDays(String IssuDays) {
        this.IssuDays = IssuDays;
    }

    /**
     * @return the IssuDate
     */
    public String getIssuDate() {
        return IssuDate;
    }

    /**
     * @param IssuDate the IssuDate to set
     */
    public void setIssuDate(String IssuDate) {
        this.IssuDate = IssuDate;
    }

    /**
     * @return the ReturnDate
     */
    public String getReturnDate() {
        return ReturnDate;
    }

    /**
     * @param ReturnDate the ReturnDate to set
     */
    public void setReturnDate(String ReturnDate) {
        this.ReturnDate = ReturnDate;
    }

    
    }
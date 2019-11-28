/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book;

import ass.SqlConection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
/**
 * FXML Controller class
 *
 * @author kamran qadeer
 */
public class BookController implements Initializable {
     Connection con=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    private ObservableList<table>data;
    @FXML
    private JFXTextField SearchFiled;
    @FXML
    private JFXButton BookClear;
    @FXML
    private JFXButton BookAdd;
    @FXML
    private JFXButton BookUpdate;
    @FXML
    private JFXTextField BookName;
    @FXML
    private JFXTextField AuthorName;
    @FXML
    private JFXTextField BookID;
    @FXML
    private JFXTextField PersonName;
    @FXML
    private JFXTextField PersonID;
    @FXML
    private JFXTextField IssuDays;
    @FXML
    private DatePicker IssuDate;
    @FXML
    private DatePicker IssuReturn;
    @FXML
    private JFXButton IssuClearButt;
    @FXML
    private JFXButton IssuButt;
    @FXML
    private JFXButton IssuExtendButt;
    @FXML
    private MenuItem ReturnProcess;
    @FXML
    private MenuItem BookDelet;
    @FXML
    private TableColumn<table, String> Cbookname;
    @FXML
    private TableColumn<table, String> Cauthorname;
    @FXML
    private TableColumn<table, String> CbookID;
    @FXML
    private TableColumn<table, String> Cpersonname;
    @FXML
    private TableColumn<table, String> CissuID;
    @FXML
    private TableColumn<table, String> Cdays;
    @FXML
    private TableColumn<table, String> CissuDate;
    @FXML
    private TableColumn<table, String> Creturndate;
    @FXML
    private TableView<table> booktable;
    @FXML
    private JFXButton Print;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           con=SqlConection.ConnectDB();
           data=FXCollections.observableArrayList();
          setTable();
          loadData();  
          SelectCell();
          clearTextfiled();
          search();

        // TODO
    }  
     private void SelectCell()
    {
          booktable.setOnMouseClicked(new EventHandler<MouseEvent>() {
           public void handle(MouseEvent event) {
               table t=booktable.getItems().get(booktable.getSelectionModel().getSelectedIndex());
               BookName.setText(t.getBookName());
               AuthorName.setText(t.getAuthorName());
               BookID.setText(t.getBookID());
               PersonName.setText(t.getPersonName());
               PersonID.setText(t.getPersonID());
               IssuDays.setText(t.getIssuDays());
           }
       });
    }
      private void setTable() {

        Cbookname.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        Cauthorname.setCellValueFactory(new PropertyValueFactory<>("AuthorName"));
        CbookID.setCellValueFactory(new PropertyValueFactory<>("BookID"));
        Cpersonname.setCellValueFactory(new PropertyValueFactory<>("PersonName"));
        CissuID.setCellValueFactory(new PropertyValueFactory<>("PersonID"));
        Cdays.setCellValueFactory(new PropertyValueFactory<>("IssuDays"));
        CissuDate.setCellValueFactory(new PropertyValueFactory<>("IssuDate"));
        Creturndate.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));        

        }
       private void loadData() {
          data.clear();
        try {
             pst=con.prepareStatement("Select * from book ");
             rs=pst.executeQuery();
            while(rs.next())
            {
                  data.add(new table(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));          
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
           booktable.setItems(data);
     }
        private void clearTextfiled()
    {
        BookName.clear();
        AuthorName.clear();
        BookID.clear();
        PersonName.clear();
        PersonID.clear();
        IssuDays.clear();
        IssuDate.getEditor().clear(); 
        IssuReturn.getEditor().clear(); 

    }
     private void search()
    {
        SearchFiled.setOnKeyReleased(e->{
         if(SearchFiled.getText().equals(""))
         {
             loadData();
         }
         else{
             data.clear();
             String sql="Select * from book where BookID like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from book where BookName like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from book where AuthorName like '%" + SearchFiled.getText() + "%'";
         try {
                 
                 pst=con.prepareStatement(sql);
                 rs=pst.executeQuery();
                 while(rs.next())
                 {
                  data.add(new table(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
                 }
                 booktable.setItems(data);
                 
             } catch (SQLException ex) {
                 Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
        });
    }  

    @FXML
    private void BookClearAcc(ActionEvent event) {
        BookName.clear();
        AuthorName.clear();
        BookID.clear();
    }

    @FXML
    private void BookAddAcc(ActionEvent event) {
        try{
             con= SqlConection.ConnectDB();
            if (BookName.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  book name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (AuthorName.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  book author name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (BookID.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter id","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
           
            
       Statement stmt;
       stmt= con.createStatement();
       String sql1="Select BookID from book where BookID= '" + BookID.getText() + "'";
      rs=stmt.executeQuery(sql1);
      if(rs.next()){
        JOptionPane.showMessageDialog( null, "BOOK id already exists","Error", JOptionPane.ERROR_MESSAGE);
        BookID.setText("");
       return;
      }
            String sql= "insert into book(BookName,AuthorName,BookID)values('"+ BookName.getText()+ "','"+ AuthorName.getText()+ "','"+ BookID.getText()+"')";
            pst=con.prepareStatement(sql);
            pst.execute();
            pst.close();            
            JOptionPane.showMessageDialog(null,"Successfully add","NEW BOOK",JOptionPane.INFORMATION_MESSAGE);
            setTable();
            loadData();
        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }

    @FXML
    private void BookUpdateAcc(ActionEvent event) {
        try {
            if (BookName.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  book name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (AuthorName.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  book author name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (BookID.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter id","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
           con=SqlConection.ConnectDB();
            String sql= "update book set BookName='"+ BookName.getText() + "',AuthorName='"+ AuthorName.getText() + "' where BookID='"+ BookID.getText()+"'";
            pst=con.prepareStatement(sql);
            int i=0;
            pst.executeUpdate();
            loadData();
            clearTextfiled();
            JOptionPane.showMessageDialog(null,"Successfully updated","Record",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(null,ex);
        }  
    }

    @FXML
    private void IssuClearButtAcc(ActionEvent event) {
        PersonName.clear();
        PersonID.clear();
        IssuDays.clear();
        IssuDate.getEditor().clear(); 
        IssuReturn.getEditor().clear();
    }
     private void issclear()
     {
          PersonName.clear();
        PersonID.clear();
        IssuDays.clear();
        IssuDate.getEditor().clear(); 
        IssuReturn.getEditor().clear();
     }
    @FXML
    private void IssuButtAcc(ActionEvent event) {
       
try {
            if (PersonName.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please SOME ENTERY IN MISSING","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (PersonID.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  book author name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (IssuDays.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter DAYS","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (IssuReturn.getEditor().getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter ISSU DATE","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (IssuReturn.getEditor().getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter RETURN DATE","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            else {
           con=SqlConection.ConnectDB();
            String sql= "update book set PersonName='"+ PersonName.getText() + "',PersonID='"+ PersonID.getText()+ "',IssuDays='"+ IssuDays.getText()+ "',IssuDate='"+ IssuDate.getEditor().getText()+ "',ReturnDate='"+ IssuReturn.getEditor().getText() + "' where BookID='"+ BookID.getText()+"'";
            pst=con.prepareStatement(sql);
            int i=0;
            pst.executeUpdate();
            loadData();
            clearTextfiled();
            JOptionPane.showMessageDialog(null,"Successfully ISSU BOOK","Record",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(null,ex);
        } 
    }

    @FXML
    private void IssuExtendButtAcc(ActionEvent event) { 
       try {
            if (PersonName.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please SOME ENTERY IN MISSING","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (PersonID.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  book author name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (IssuDays.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter DAYS","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (IssuReturn.getEditor().getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter ISSU DATE","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (IssuReturn.getEditor().getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter RETURN DATE","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            else {
           con=SqlConection.ConnectDB();
            String sql= "update book set PersonName='"+ PersonName.getText() + "',PersonID='"+ PersonID.getText()+ "',IssuDays='"+ IssuDays.getText()+ "',IssuDate='"+ IssuDate.getEditor().getText()+ "',ReturnDate='"+ IssuReturn.getEditor().getText() + "' where BookID='"+ BookID.getText()+"'";
            pst=con.prepareStatement(sql);
            int i=0;
            pst.executeUpdate();
            loadData();
            clearTextfiled();
            JOptionPane.showMessageDialog(null,"Successfully EXTEND BOOK","Record update",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(null,ex);
        }  
    }

    @FXML
    private void ButAddAcc(KeyEvent event) {
    }
    @FXML
    private void ReturnProcessAcc(ActionEvent event) {
         String a="";
        try {
             
           
           con=SqlConection.ConnectDB();
            String sql= "update book set PersonName='"+ a + "',IssuDays='"+ a +"',PersonID='"+ a +"',IssuDate='"+ a +"',ReturnDate='"+ a + "' where PersonID='"+ PersonID.getText()+"'";
            pst=con.prepareStatement(sql);
            int i=0;
            pst.executeUpdate();
            loadData();
            clearTextfiled();
            JOptionPane.showMessageDialog(null,"Successfully BOOK RETURN","RETURN BOOK AND RECORD IS UPDATE",JOptionPane.INFORMATION_MESSAGE);
            
        }
        catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(null,ex);
        }  
        
    }

    @FXML
    private void BookDeletAcc(ActionEvent event) {
        book.table selectedForDeletion = booktable.getSelectionModel().getSelectedItem();
                String sq= "delete from book where BookID = ?";
         try {
             if (BookID.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please SELECT THE ROW","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
             else
             {
            pst=con.prepareStatement(sq);
            pst.setString(1, BookID.getText());
            int i=pst.executeUpdate();
            if(i==1)
            {
                JOptionPane.showMessageDialog(null,"Successfully DELETE","BOOK ALL INFORMATION",JOptionPane.INFORMATION_MESSAGE);
                loadData();
                clearTextfiled();
            }
             }
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void PrintAcc(ActionEvent event) {
         try{
          String report="C:\\Users\\kamran qadeer\\Documents\\NetBeansProjects\\unit1\\ASS\\src\\report\\library.jrxml";
          JasperReport js=JasperCompileManager.compileReport(report);
          JasperPrint jp=JasperFillManager.fillReport(js,null,con);
          JasperViewer.viewReport(jp);
          }
                  
               catch(JRException e)
                {
                  JOptionPane.showMessageDialog( null,e);
                }
    }

    
    
}

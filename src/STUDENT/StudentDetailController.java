/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STUDENT;

import ass.ASS;
import ass.SqlConection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
public class StudentDetailController implements Initializable {
    JFXTextField id=null;
    Connection con=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    private ObservableList<TableList> data;
    @FXML
    private JFXButton Print;
    @FXML
    private TableView<TableList> studenttable;
    @FXML
    private TableColumn<TableList, String> C1Nmae;
    @FXML
    private TableColumn<TableList, String> C2FathName;
    @FXML
    private TableColumn<TableList, String> CGender;
    @FXML
    private TableColumn<TableList, String> C3ID;
    @FXML
    private TableColumn<TableList, String> C7Class;
    @FXML
    private TableColumn<TableList, String> C4Email;
    @FXML
    private TableColumn<TableList, String> C5ParConNum;
    @FXML
    private TableColumn<TableList, String> C6DateOfBirth;
    @FXML
    private MaterialDesignIconView close;
    @FXML
    private JFXTextField Name;
    @FXML
    private JFXTextField Fname;
    @FXML
    private JFXTextField Gender;
    @FXML
    private JFXTextField ID;
    @FXML
    private JFXTextField Class;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXTextField Pcon;
    @FXML
    private JFXTextField DOB;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con=SqlConection.ConnectDB();
        data=FXCollections.observableArrayList();
        setTable();
        loadData();
        loadfiled();
//       loginstage.initStyle(StageStyle.TRANSPARENT);
        
    }    
     
    private void setTable() {

        C1Nmae.setCellValueFactory(new PropertyValueFactory<>("name"));
        C2FathName.setCellValueFactory(new PropertyValueFactory<>("fathname"));
        CGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        C3ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        C4Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        C5ParConNum.setCellValueFactory(new PropertyValueFactory<>("Pcont"));
        C6DateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dbd"));
        C7Class.setCellValueFactory(new PropertyValueFactory<>("clas"));
        } 
      private void loadData() {
          
        try {
             pst=con.prepareStatement("Select * from studentdetails");
             rs=pst.executeQuery();
            while(rs.next())
            {       
                  data.add(new TableList(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
       studenttable.setItems(data);
     }
    @FXML
    private void PrintAcc(ActionEvent event) throws SQLException {
      try{
          String report="C:\\Users\\kamran qadeer\\Documents\\NetBeansProjects\\unit1\\ASS\\src\\report\\Sdetails.jrxml";
          JasperReport js=JasperCompileManager.compileReport(report);
          JasperPrint jp=JasperFillManager.fillReport(js,null,con);
          JasperViewer.viewReport(jp);
          }
                  
               catch(JRException e)
                {
                  JOptionPane.showMessageDialog( null,e);
                }
    
    }

   
    private void loadfiled()
    {  
        String name = null,fathN=null,id=null,clas=null,email=null,Pcont=null,dob=null,Gend=null;
        String DATA =STUDENT.StudentController.data();
        System.out.println(DATA);
        try{
         String sq= "select * from student where id=DATA"; 
         pst=con.prepareStatement(sq);
         rs=pst.executeQuery(sq);
         while(rs.next())
         {
                name = rs.getString("name");
                fathN = rs.getString("fathname");
                id = rs.getString("id");
                clas = rs.getString("clas");
                email = rs.getString("email");
                Pcont = rs.getString("Pcont");
                dob = rs.getString("dbd");
                Gend = rs.getString("Gender");
         }
          } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(name);
               Name.setText(name);
               Fname.setText(fathN);
               Gender.setText(Gend);
               ID.setText(id);
               Email.setText(email);
               Pcon.setText(Pcont);
               DOB.setText(dob);
    }
    @FXML
    private void close(MouseEvent event) throws SQLException {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
          
    }
    @FXML
    private void closeStage(MouseEvent event) throws SQLException {
          Stage stage = (Stage) close.getScene().getWindow();
          stage.close();
          
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subject;

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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class SubjectController implements Initializable {
    Connection con=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    private ObservableList<subjectlist>data;

    @FXML
    private JFXTextField ClassName1;
    @FXML
    private JFXTextField SubjectName;
    @FXML
    private JFXTextField SubjectCode;
    @FXML
    private JFXTextField TeacherName;
    @FXML
    private JFXTextField TeacherID;
    @FXML
    private JFXButton AddButt;
    @FXML
    private JFXButton ClearButt;
    @FXML
    private JFXButton UpdateButt;
    @FXML
    private TableView<subjectlist> subjecttable;
    @FXML
    private MenuItem DeteteSubject;
    @FXML
    private TableColumn<subjectlist, String> CSubject;
    @FXML
    private TableColumn<subjectlist, String> CsubjectID;
    @FXML
    private TableColumn<subjectlist, String> CTeacherName;
    @FXML
    private TableColumn<subjectlist, String> CTeacherID;
    @FXML
    private JFXButton Print;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con=SqlConection.ConnectDB();
          data=FXCollections.observableArrayList();
         
          setTable();
          loadData();
          SelectCell();
          clearTextfiled();
         // search();
        // TODO
    }    
    private void setTable() {

        CSubject.setCellValueFactory(new PropertyValueFactory<>("SubjectName"));
        CsubjectID.setCellValueFactory(new PropertyValueFactory<>("SubjectCode"));
        CTeacherName.setCellValueFactory(new PropertyValueFactory<>("TeacherName"));
        CTeacherID.setCellValueFactory(new PropertyValueFactory<>("TeacherID"));        
        } 
      private void loadData() {
          String a="";
          data.clear();
        try {
             pst=con.prepareStatement("Select  * from subject");
             rs=pst.executeQuery();
            while(rs.next())
            {
//                String teachername = rs.getString("name");
//                String teacherid = rs.getString("id");
//                data.add(new subjectlist(a,a,teachername,teacherid));
             
                    data.add(new subjectlist(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
       subjecttable.setItems(data);
     }
       private void SelectCell()
    {
       subjecttable.setOnMouseClicked(new EventHandler<MouseEvent>() {
           public void handle(MouseEvent event) {
               subjectlist t=subjecttable.getItems().get(subjecttable.getSelectionModel().getSelectedIndex());
               SubjectName.setText(t.getSubjectName());
                SubjectCode.setText(t.getSubjectCode());
               TeacherName.setText(t.getTeacherName());
               TeacherID.setText(t.getTeacherID());
               }
       });
    }
        private void clearTextfiled()
    {
       SubjectName.clear();
       SubjectCode.clear();
       TeacherID.clear();
        TeacherName.clear();
    }
    @FXML
    private void AddButtAcc(ActionEvent event) {
         try{
            if (SubjectCode.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  Subject  Code","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
        Statement st;
        st=con.createStatement();
        String sq2="Select * from teacher where id= '" + TeacherID.getText() + "'";
        rs=st.executeQuery(sq2);        
        if((rs.next())||(TeacherID.getText().equals("")))
        {  
            String sql1="Select subjectcode from subject where subjectcode= '" + SubjectCode.getText() + "'";
        rs=st.executeQuery(sql1);
        if(rs.next()){
        JOptionPane.showMessageDialog( null, "SUBJECT  already exists","Error", JOptionPane.ERROR_MESSAGE);
        SubjectCode.setText("");
        SubjectName.setText("");
       return;
      }
            
            String sq1="Insert into subject(subjectname,subjectcode,teachername,teacherid)values('"+ SubjectName.getText() +"','"+ SubjectCode.getText()+"','"+ TeacherName.getText()+"','"+ TeacherID.getText()+"')";
            pst=con.prepareStatement(sq1);
            pst.execute();
            pst.close();
            setTable();
            loadData();
            JOptionPane.showMessageDialog(null,"Successfully ADD ROOM or any information","hostal",JOptionPane.INFORMATION_MESSAGE);
                 
         
        }
        else
        {
          JOptionPane.showMessageDialog( null, "UNKNOWN teacher","Error", JOptionPane.ERROR_MESSAGE);
        TeacherID.setText("");  
            
        }       
    
         }
         catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }

    @FXML
    private void ClearButtAcc(ActionEvent event) {
    }

    @FXML
    private void UpdateButtAcc(ActionEvent event) {
         try{
             con= SqlConection.ConnectDB();
            if (SubjectCode.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter Subject code","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (SubjectName.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  Subject Name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (TeacherID.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  Teacher ID","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (TeacherName.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  TeacherName","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
      else{
             Statement st;
        st=con.createStatement();
        String sq2="Select * from teacher where id= '" + TeacherID.getText() + "'";
        rs=st.executeQuery(sq2);        
        if(rs.next())
        {
           con=SqlConection.ConnectDB();
            String sql= "update subject set subjectname='"+ SubjectName.getText()+ "',subjectcode='"+ SubjectCode.getText() + "'WHERE teacherid='"+ TeacherID.getText()+"'";
            pst=con.prepareStatement(sql);
            int i=pst.executeUpdate();
            loadData();
            clearTextfiled();
            if(i==1){
                con=SqlConection.ConnectDB();
                String sq="update teacher set subjass='"+SubjectName.getText()+ "'WHERE id='"+ TeacherID.getText()+"'";
                pst=con.prepareStatement(sq);
                pst.executeUpdate();
            }
            JOptionPane.showMessageDialog(null,"Successfully updated","Record",JOptionPane.INFORMATION_MESSAGE);   
        }
         
          else
        {
          JOptionPane.showMessageDialog( null, "UNKNOWN Teacher","Error", JOptionPane.ERROR_MESSAGE);
        TeacherID.setText("");  
            
        }
         }
         }
        catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(null,ex);
        }  
    }

    @FXML
    private void StudentDelete(ActionEvent event) {
    }

    @FXML
    private void PrintAcc(ActionEvent event) {
         try{
          String report="C:\\Users\\kamran qadeer\\Documents\\NetBeansProjects\\unit1\\ASS\\src\\report\\subject.jrxml";
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TEACHER;

import ass.FXMLloginController;
import ass.SqlConection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.io.IOException;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
public class TEACHERController implements Initializable {
    Connection con=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    private ObservableList<TableList>data;

    @FXML
    private JFXTextField Name;
    @FXML
    private JFXTextField FathNam;
    @FXML
    private JFXTextField ID;
    @FXML
    private JFXTextField Email;
    @FXML
    private DatePicker DatePick;
    @FXML
    private JFXButton ButtClear;
    @FXML
    private JFXButton ButAdd;
    @FXML
    private JFXButton ButtUpdate;
    @FXML
    private JFXTextField SearchFiled;
    
    @FXML
    private TableColumn<TableList,String> C3ID;
    @FXML
    private TableColumn<TableList,String> C1Nmae;
    @FXML
    private TableColumn<TableList,String> C2FathName;
    @FXML
    private TableColumn<TableList,String> C6DateOfBirth;
    @FXML
    private TableColumn<TableList,String> C4Email;
    @FXML
    private JFXTextField EduFile;
    @FXML
    private JFXTextField ExperFiled;
    @FXML
    private JFXTextField ConFiled;
    @FXML
    private JFXTextField SubAssFiled;
    @FXML
    private JFXTextField WeekLec;
    @FXML
    private TableColumn<TableList,String> C8edu;
    @FXML
    private TableColumn<TableList,String> C9expe;
    @FXML
    private TableColumn<TableList,String> C10subAss;
    @FXML
    private TableColumn<TableList,String> C7CWeekLec;
    @FXML
    private TableColumn<TableList,String> C5ConNum;
    @FXML
    private TableView<TableList> teachertable;
    @FXML
    private JFXButton PRINT;
    @FXML
    private Pane PrintPan;

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
          
        
    } 
     private void SelectCell()
    {
       teachertable.setOnMouseClicked((MouseEvent event) -> {
           TEACHER.TableList t=teachertable.getItems().get(teachertable.getSelectionModel().getSelectedIndex());
           Name.setText(t.getName());
           FathNam.setText(t.getFname());
           ID.setText(t.getId());
           ExperFiled.setText(t.getExp());
           Email.setText(t.getEmail());
           ConFiled.setText(t.getCont());
           EduFile.setText(t.getEdu());
           WeekLec.setText(t.getTotalclass());
           SubAssFiled.setText(t.getSubjAss());
       });
    }
     private void setTable() {

        C1Nmae.setCellValueFactory(new PropertyValueFactory<>("name"));
        C2FathName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        C3ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        C7CWeekLec.setCellValueFactory(new PropertyValueFactory<>("totalclass"));
        C4Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        C5ConNum.setCellValueFactory(new PropertyValueFactory<>("cont"));
        C6DateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dbd"));
        C10subAss.setCellValueFactory(new PropertyValueFactory<>("subjAss"));        
        C8edu.setCellValueFactory(new PropertyValueFactory<>("edu"));
        C9expe.setCellValueFactory(new PropertyValueFactory<>("exp"));
        } 
      private void loadData() {
          data.clear();
        try {
             pst=con.prepareStatement("Select * from teacher");
             rs=pst.executeQuery();
            while(rs.next())
            {
                String name = rs.getString("name");
                String fname = rs.getString("fname");
                String id = rs.getString("id");
                String dbd = rs.getString("dbd");
                String totalclass = rs.getString("totalclass");
                String cont = rs.getString("cont");
                String email = rs.getString("email");
                String subjass = rs.getString("subjass");
                String edu = rs.getString("edu");
                String exp = rs.getString("exp");
                data.add(new TableList(name,fname,id,totalclass,email,cont,dbd,subjass,edu,exp));
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TEACHERController.class.getName()).log(Level.SEVERE, null, ex);
        }
         teachertable.setItems(data);
     }

    @FXML
    private void ButtClearAcc(ActionEvent event) {
        Name.clear();
        FathNam.clear();
        ID.clear();
        Email.clear();
        EduFile.clear();
        ConFiled.clear();
        SubAssFiled.clear();
        WeekLec.clear();
        ExperFiled.clear();
        ConFiled.clear();
        SearchFiled.clear();
        DatePick.getEditor().clear();
    }
    private void clearTextfiled()
    {
        ExperFiled.clear();
         Name.clear();
        FathNam.clear();
        ID.clear();
        Email.clear();
        EduFile.clear();
        ConFiled.clear();
        SubAssFiled.clear();
        WeekLec.clear();
        ConFiled.clear();
        SearchFiled.clear();
        DatePick.getEditor().clear(); 
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
             String sql="Select * from teacher where id like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from teacher where name like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from teacher where fname like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from teacher where totalclass like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from teacher where email like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from teacher where cont like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from teacher where subjass like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from teacher where exp like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from teacher where edu like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from teacher where dbd like '%" + SearchFiled.getText() + "%'";
         try {
                 
                 pst=con.prepareStatement(sql);
                 rs=pst.executeQuery();
                 while(rs.next())
                 {
                String name = rs.getString("name");
                String fname = rs.getString("fname");
                String id = rs.getString("id");
                String dbd = rs.getString("dbd");
                String totalclass = rs.getString("totalclass");
                String cont = rs.getString("cont");
                String email = rs.getString("email");
                String subjass = rs.getString("subjass");
                String edu = rs.getString("edu");
                String exp = rs.getString("exp");
                data.add(new TableList(name,fname,id,totalclass,email,cont,dbd,subjass,edu,exp));
                 }
                 teachertable.setItems(data);
                 
             } catch (SQLException ex) {
                 Logger.getLogger(TEACHERController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
        });
    }  
    @FXML
    private void ButAddAcc(ActionEvent event) {
         String d=DatePick.getEditor().getText();

         try{
             con= SqlConection.ConnectDB();
            if (Name.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (ID.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  id","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (EduFile.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter EDUCATION","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
                if (ExperFiled.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter EXPERIANCE  number","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (ConFiled.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter contect number","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
           
            
       Statement stmt;
       stmt= con.createStatement();
       String sql1="Select id from teacher where id= '" + ID.getText() + "'";
      rs=stmt.executeQuery(sql1);
      if(rs.next()){
        JOptionPane.showMessageDialog( null, "TEACHER id already exists","Error", JOptionPane.ERROR_MESSAGE);
        ID.setText("");
       return;
      }
            String sql= "insert into teacher(name,fname,id,totalclass,email,cont,dbd,subjass,edu,exp)values('"+ Name.getText() + "','"+ FathNam.getText() + "','"+ ID.getText() + "','"+ WeekLec.getText() + "','"+ Email.getText() + "','"+ ConFiled.getText() + "','"+ d+ "','"+ SubAssFiled.getText()+ "','"+ EduFile.getText()+ "','"+ ExperFiled.getText()+"')";

            pst=con.prepareStatement(sql);
            pst.execute();
            pst.close();
            
            JOptionPane.showMessageDialog(null,"Successfully add","STUDENT",JOptionPane.INFORMATION_MESSAGE);
            setTable();
            loadData();
        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }

    @FXML
    private void ButtUpdateAcc(ActionEvent event) {
        String d=DatePick.getEditor().getText();

        try {
            if (Name.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (ID.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  id","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (EduFile.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter EDUCATION","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
                if (ExperFiled.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter EXPERIANCE  number","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (ConFiled.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter contect number","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
           con=SqlConection.ConnectDB();
            String sql= "update teacher set name='"+ Name.getText() + "',fname='"+ FathNam.getText() + "',dbd='"+ DatePick.getEditor().getText() + "',totalclass='"+ WeekLec.getText() + "',email='"+ Email.getText() + "',cont='"+ ConFiled.getText()+ "',edu='"+ EduFile.getText() + "',exp='"+ ExperFiled.getText() + "',subjass='"+ SubAssFiled.getText()  +  "' where id='"+ ID.getText()+"'";
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
    private void StudInformation(ActionEvent event) {
         try {
            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/STUDENT/StudentDetail.fxml"));
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
//            ASS.loginstage.close();
            mainStage.show();
            mainStage.setResizable(true);
            mainStage.setTitle("STUDENT DETAIL");
                    } 
            catch (IOException ex) {
            Logger.getLogger(FXMLloginController.class.getName()).log(Level.SEVERE, null, ex);
                     }
    }

    @FXML
    private void StudentDelete(ActionEvent event) {
         TEACHER.TableList selectedForDeletion = teachertable.getSelectionModel().getSelectedItem();
                String sq= "delete  from teacher where id = ?";
         try {
             if (ID.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please SELECT THE ROW","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
             else
             {
            pst=con.prepareStatement(sq);
            pst.setString(1, ID.getText());
            int i=pst.executeUpdate();
            if(i==1)
            {
                JOptionPane.showMessageDialog(null,"Successfully DELETE","TEACHER",JOptionPane.INFORMATION_MESSAGE);
                loadData();
                clearTextfiled();
            }
             }
        } catch (SQLException ex) {
            Logger.getLogger(TEACHERController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void printAcc(ActionEvent event) {
        
        try{
          String report="C:\\Users\\kamran qadeer\\Documents\\NetBeansProjects\\unit1\\ASS\\src\\report\\Teacher.jrxml";
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
   
   



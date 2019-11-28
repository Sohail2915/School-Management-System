/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STUDENT;

import ass.ASS;
import static ass.ASS.loginstage;
import ass.FXMLloginController;
import ass.SqlConection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.HeadlessException;
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
import javafx.event.EventHandler;
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
public class StudentController implements Initializable {
    Connection con=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    String test=null;
    private ObservableList<TableList> data;
    private ObservableList<check> data2;
    private static String StuData;
    @FXML
    private TableView<TableList> studenttable;
    @FXML
    private JFXTextField Name;
    @FXML
    private JFXTextField FathNam;
    @FXML
    private JFXTextField ID;
    @FXML
    private JFXTextField PareConNumb;
    @FXML
    private JFXTextField Email;
    @FXML
    private DatePicker DatePick;
    @FXML
    private JFXButton ButtClear;
    @FXML
    private JFXButton ButAdd;
    @FXML
    private TableColumn<TableList, String> C1Nmae;
    @FXML
    private TableColumn<TableList, String> C2FathName;
    @FXML
    private TableColumn<TableList, String> C3ID;
    @FXML
    private TableColumn<TableList, String> C4Email;
    @FXML
    private TableColumn<TableList, String> C5ParConNum;
    @FXML
    private TableColumn<TableList, String> C6DateOfBirth;
    @FXML
    private TableColumn<TableList, String> C7Class;
    @FXML
    private JFXButton ButtUpdate;
    @FXML
    private JFXTextField clas;
    @FXML
    private JFXTextField SearchFiled;
    @FXML
    private JFXButton Print;
    @FXML
    private JFXComboBox<String> Gender;
    @FXML
    private TableColumn<TableList, String> CGender;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Gender.setItems(List);
          con=SqlConection.ConnectDB();
          data=FXCollections.observableArrayList();
          setTable();
          loadData();  
          SelectCell();
          clearTextfiled();
          search();
        // TODO
    }   
    ObservableList<String> List=FXCollections.observableArrayList("MALE","FEMALE-","N-O-N");
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
          data.clear();
        try {
             pst=con.prepareStatement("Select * from student");
             rs=pst.executeQuery();
            while(rs.next())
            {       
                  data.add(new TableList(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
//                String name = rs.getString("name");
//                String fathN = rs.getString("fathname");
//                String id = rs.getString("id");
//                String clas = rs.getString("clas");
//                String email = rs.getString("email");
//                String Pcont = rs.getString("Pcont");
//                String dbd = rs.getString("dbd");
//                String Gender = rs.getString("Gender");
//                data.add(new TableList(name,fathN,Gender, id,email, Pcont,dbd,clas));
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       studenttable.setItems(data);
     }
    @FXML
    private void ButtClearAcc(ActionEvent event) {
        Name.clear();
        FathNam.clear();
        ID.clear();
        Email.clear();
        clas.clear();
        PareConNumb.clear();
        DatePick.getEditor().clear();
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
            if (clas.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter class","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (PareConNumb.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter parrent contect number","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
           
            
       Statement stmt;
       stmt= con.createStatement();
       String sql1="Select id from student where id= '" + ID.getText() + "'";
      rs=stmt.executeQuery(sql1);
      if(rs.next()){
        JOptionPane.showMessageDialog( null, "STUDENT id already exists","Error", JOptionPane.ERROR_MESSAGE);
        ID.setText("");
       return;
      }
            String sq= "insert into student(name,fathname,Gender,id,clas,email,Pcont,dbd)values('"+ Name.getText() + "','"+ FathNam.getText() +"','"+ Gender.getValue() + "','"+ ID.getText() + "','"+ clas.getText() + "','"+Email.getText()+ "','"+ PareConNumb.getText() + "','"+d+"')";
            pst=con.prepareStatement(sq);
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null,"Successfully add","STUDENT",JOptionPane.INFORMATION_MESSAGE);
            setTable();
            loadData();
        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }


    private void SelectCell()
    {
       studenttable.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               TableList t=studenttable.getItems().get(studenttable.getSelectionModel().getSelectedIndex());
               Name.setText(t.getName());
               FathNam.setText(t.getFathname());
               ID.setText(t.getId());
               clas.setText(t.getClas());
               Email.setText(t.getEmail());
               Gender.getEditor().setText(t.getGender());
               PareConNumb.setText(t.getPcont());
               StuData=ID.getText();
           }
       });
    }

    @FXML
    private void ButtUpdateAcc(ActionEvent event) throws SQLException {
        String d=DatePick.getEditor().getText();

        try {
            if (Name.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            else  if (ID.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  id","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            else if (clas.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter class","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else if (PareConNumb.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter parrent contect number","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
           con=SqlConection.ConnectDB();
            String sql= "update student set name='"+ Name.getText() + "',fathname='"+ FathNam.getText()+ "',Gender='"+ Gender.getValue() + "',dbd='"+ DatePick.getEditor().getText() + "',clas='"+ clas.getText() + "',email='"+ Email.getText() + "',Pcont='"+ PareConNumb.getText() +  "' where id='"+ ID.getText()+"'";
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

    private void clearTextfiled()
    {
        Name.clear();
        FathNam.clear();
        ID.clear();
        Email.clear();
        clas.clear();
        PareConNumb.clear();
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
             String sql1="Select * from student where id like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from student where name like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from student where fathname like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from student where clas like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from student where email like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from student where Pcont like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from student where Gender like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from student where dbd like '%" + SearchFiled.getText() + "%'";
         try {
                 
                 pst=con.prepareStatement(sql1);
                 rs=pst.executeQuery();
                 while(rs.next())
                 {
                    
                   data.add(new TableList(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));

//                String name = rs.getString("name");
//                String fathN = rs.getString("fathname");
//                String id = rs.getString("id");
//                String clas = rs.getString("clas");
//                String email = rs.getString("email");
//                String Pcont = rs.getString("Pcont");
//                String dbd = rs.getString("dbd");
//                String Gender = rs.getString("Gender");
//                data.add(new TableList(name,fathN,Gender, id,email, Pcont,dbd,clas));
                 }
                 studenttable.setItems(data);
                 
             } catch (SQLException ex) {
                 Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
        });
    }        
    @FXML
    private void StudInformation(ActionEvent event) throws SQLException {
           String d=DatePick.getEditor().getText();
          try {
            String sq= "insert into studentdetails(name,fathname,Gender,id,clas,email,Pcont,dbd)values('"+ Name.getText() + "','"+ FathNam.getText() +"','"+ Gender.getValue() + "','"+ ID.getText() + "','"+ clas.getText() + "','"+Email.getText()+ "','"+ PareConNumb.getText() + "','"+d+"')";
            pst=con.prepareStatement(sq);
            pst.execute();
            pst.close();
            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/STUDENT/StudentDetail.fxml"));
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
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
                TableList selectedForDeletion = studenttable.getSelectionModel().getSelectedItem();
                String sq= "delete  from student where id = ?";
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
                JOptionPane.showMessageDialog(null,"Successfully DELETE","STUDENT",JOptionPane.INFORMATION_MESSAGE);
                loadData();
                clearTextfiled();
            }
             }
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
   }   

    @FXML
    private void PrintAcc(ActionEvent event) {
          try{
          String report="C:\\Users\\kamran qadeer\\Documents\\NetBeansProjects\\unit1\\ASS\\src\\report\\Student2.jrxml";
          JasperReport js=JasperCompileManager.compileReport(report);
          JasperPrint jp=JasperFillManager.fillReport(js,null,con);
          JasperViewer.viewReport(jp);
          }
                  
               catch(JRException e)
                {
                  JOptionPane.showMessageDialog( null,e);
                }
    }
    static String data()
    {
        return StuData;
    }
}


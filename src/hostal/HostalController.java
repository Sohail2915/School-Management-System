/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hostal;

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
public class HostalController implements Initializable {
    Connection con=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    private ObservableList<hostallist>data;

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField studentclass;
    @FXML
    private JFXTextField roomnumber;
    @FXML
    private JFXTextField monthlydues;
    @FXML
    private JFXButton ButtClear;
    @FXML
    private JFXButton ButAdd;
    @FXML
    private JFXButton ButtUpdate;
    @FXML
    private JFXTextField SearchFiled;
    @FXML
    private TableColumn<hostallist, String> CStudentID;
    @FXML
    private TableColumn<hostallist, String> CStudentName;
    @FXML
    private TableColumn<hostallist, String> CStudentClass;
    @FXML
    private TableColumn<hostallist, String> CRoomNumber;
    @FXML
    private TableColumn<hostallist, String> CMonthlyDues;
    
    @FXML
    private TableView<hostallist> renttable;
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
          search();
        // TODO
    }    
     private void setTable() {

        CStudentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        CStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        CStudentClass.setCellValueFactory(new PropertyValueFactory<>("studentclass"));
        CRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomnumber"));        
        CMonthlyDues.setCellValueFactory(new PropertyValueFactory<>("monthlydues"));
        } 
      private void loadData() {
          data.clear();
        try {
             pst=con.prepareStatement("Select * from rent");
             rs=pst.executeQuery();
            while(rs.next())
            {
             data.add(new hostallist(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HostalController.class.getName()).log(Level.SEVERE, null, ex);
        }
       renttable.setItems(data);
     }
       private void SelectCell()
    {
       renttable.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               hostallist t=renttable.getItems().get(renttable.getSelectionModel().getSelectedIndex());
               id.setText(t.getId());
               name.setText(t.getName());
               studentclass.setText(t.getStudentclass());
               roomnumber.setText(t.getRoomnumber());
               monthlydues.setText(t.getMonthlydues());
               }
       });
    }
        private void clearTextfiled()
    {
       name.clear();
       id.clear();
       studentclass.clear();
        roomnumber.clear();
        monthlydues.clear();
    }
    @FXML
    private void ButtClearAcc(ActionEvent event) {
         name.clear();
       id.clear();
       studentclass.clear();
        roomnumber.clear();
        monthlydues.clear();
    }

    @FXML
    private void ButAddAcc(ActionEvent event) {
        try{
            if (roomnumber.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter   STUDENT name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
        Statement st;
        st=con.createStatement();
        String sq2="Select * from student where id= '" + id.getText() + "'";
        rs=st.executeQuery(sq2);        
        if((rs.next())||(id.getText().equals("")))
        {  
             String sq3="Select * from rent where id= '" + id.getText() + "'";
        rs=st.executeQuery(sq3);
        if(rs.next())
        {
            JOptionPane.showMessageDialog( null, "THIS STUDENT ALSO ASSIGN A ROOM","Error", JOptionPane.ERROR_MESSAGE);
        id.setText("");  
        }
        else{
            String sql1="Select roomnumber from rent where roomnumber= '" + roomnumber.getText() + "'";
        rs=st.executeQuery(sql1);
        if(rs.next()){
        JOptionPane.showMessageDialog( null, "ROOM  already exists","Error", JOptionPane.ERROR_MESSAGE);
        roomnumber.setText("");
       return;
      }
            
            String sq1="Insert into rent(name,id,studentclass,roomnumber,monthlydues)values('"+ name.getText() +"','"+ id.getText()+"','"+ studentclass.getText()+"','"+ roomnumber.getText()+"','"+ monthlydues.getText()+"')";
            pst=con.prepareStatement(sq1);
            pst.execute();
            pst.close();
            setTable();
            loadData();
            JOptionPane.showMessageDialog(null,"Successfully ADD ROOM or any information","hostal",JOptionPane.INFORMATION_MESSAGE);
        }         
         
        }
        else
        {
          JOptionPane.showMessageDialog( null, "UNKNOWN student","Error", JOptionPane.ERROR_MESSAGE);
        id.setText("");  
            
        }       
    
         }
         catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
         
    }

    @FXML
    private void ButtUpdateAcc(ActionEvent event) {
         try{
             con= SqlConection.ConnectDB();
            if (name.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter STUDENT name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (id.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  STUDENT id","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (studentclass.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  STUDENT class","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (roomnumber.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  ROOM number","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (monthlydues.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  MONTLY dues","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
      else{
             Statement st;
        st=con.createStatement();
        String sq2="Select * from student where id= '" + id.getText() + "'";
        rs=st.executeQuery(sq2);        
        if(rs.next())
        {
           con=SqlConection.ConnectDB();
            String sql= "update rent set name='"+ name.getText() + "',studentclass='"+ studentclass.getText() + "',id='"+ id.getText() + "',monthlydues='"+ monthlydues.getText() + "'WHERE roomnumber='"+ roomnumber.getText()+"'";
            pst=con.prepareStatement(sql);
            pst.executeUpdate();
            loadData();
            clearTextfiled();
            JOptionPane.showMessageDialog(null,"Successfully updated","Record",JOptionPane.INFORMATION_MESSAGE);   
        }
         
          else
        {
          JOptionPane.showMessageDialog( null, "UNKNOWN STUDENT","Error", JOptionPane.ERROR_MESSAGE);
        id.setText("");  
            
        }
         }
         }
        catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(null,ex);
        }  
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
             String sql1="Select * from rent where id like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from rent where name like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from rent where studentclass like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from rent where roomnumber like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from rent where monthlydues like '%" + SearchFiled.getText() + "%'";
                    
         try {
                 
                 pst=con.prepareStatement(sql1);
                 rs=pst.executeQuery();
                 while(rs.next())
                 {
                     data.add(new hostallist(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                 }
                 renttable.setItems(data);
                 
             } catch (SQLException ex) {
                 Logger.getLogger(HostalController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
        });
    } 

    @FXML
    private void StudInformation(ActionEvent event) {
    }
    @FXML
    private void DeleteAss(ActionEvent event) {
         String a="";
        try{
             con= SqlConection.ConnectDB();
            
      
             Statement st;
        st=con.createStatement();
        String sq2="Select * from student where id= '" + id.getText() + "'";
        rs=st.executeQuery(sq2);        
       
           con=SqlConection.ConnectDB();
            String sql= "update rent set name='"+ a + "',id='"+ a + "',monthlydues='"+a+ "',studentclass='"+a+ "'WHERE roomnumber='"+ roomnumber.getText()+"'";
            pst=con.prepareStatement(sql);
            pst.executeUpdate();
            loadData();
            clearTextfiled();
            JOptionPane.showMessageDialog(null,"Successfully DELETE ASSIGNING TRANSPORT ","Record",JOptionPane.INFORMATION_MESSAGE);   
       
         }
         
        catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(null,ex);
        }  
    }

    @FXML
    private void DeleteRoom(ActionEvent event) {
         hostallist selectedForDeletion = renttable.getSelectionModel().getSelectedItem();
                String sq= "delete  from rent where roomnumber = ?";
         try {
             if (roomnumber.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please SELECT THE ROW","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
             else
             {
            pst=con.prepareStatement(sq);
            pst.setString(1, roomnumber.getText());
            int i=pst.executeUpdate();
            if(i==1)
            {
                JOptionPane.showMessageDialog(null,"Successfully DELETE ROOM ALL INFMORMATION","RECORD",JOptionPane.INFORMATION_MESSAGE);
                loadData();
                clearTextfiled();
            }
             }
        } catch (SQLException ex) {
            Logger.getLogger(HostalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void PrintAcc(ActionEvent event) {
         try{
          String report="C:\\Users\\kamran qadeer\\Documents\\NetBeansProjects\\unit1\\ASS\\src\\report\\hostel.jrxml";
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

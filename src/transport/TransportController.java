/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transport;

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
public class TransportController implements Initializable {
      Connection con=null;
      ResultSet rs=null;
      PreparedStatement pst=null;
       private ObservableList<transportlist>data;

    @FXML
    private JFXTextField Vname;
    @FXML
    private JFXTextField Vid;
    @FXML
    private JFXTextField EmployName;
    @FXML
    private JFXTextField EmployID;
    @FXML
    private JFXButton ButtClear;
    @FXML
    private JFXButton ButAdd;
    @FXML
    private JFXButton ButtUpdate;
    @FXML
    private JFXTextField SearchFiled;
    @FXML
    private TableView<transportlist> employtable;
    @FXML
    private TableColumn<transportlist, String> CVehicleID;
    @FXML
    private TableColumn<transportlist, String> CVehicleName;
    @FXML
    private TableColumn<transportlist, String> CEmployName;
    @FXML
    private TableColumn<transportlist, String> CEmployCont;
    @FXML
    private TableColumn<transportlist, String> CEmployID;
    @FXML
    private JFXTextField EmployContactNo;
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

        CVehicleID.setCellValueFactory(new PropertyValueFactory<>("Vname"));
        CVehicleName.setCellValueFactory(new PropertyValueFactory<>("Vid"));
        CEmployName.setCellValueFactory(new PropertyValueFactory<>("EmployName"));
        CEmployCont.setCellValueFactory(new PropertyValueFactory<>("EmployCont"));        
        CEmployID.setCellValueFactory(new PropertyValueFactory<>("EmployID"));
        } 
      private void loadData() {
          data.clear();
        try {
             pst=con.prepareStatement("Select * from transport");
             rs=pst.executeQuery();
            while(rs.next())
            {
             data.add(new transportlist(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TransportController.class.getName()).log(Level.SEVERE, null, ex);
        }
       employtable.setItems(data);
     }
       private void SelectCell()
    {
       employtable.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               transportlist t=employtable.getItems().get(employtable.getSelectionModel().getSelectedIndex());
               Vname.setText(t.getVname());
               Vid.setText(t.getVid());
               EmployName.setText(t.getEmployName());
               EmployID.setText(t.getEmployID());
               EmployContactNo.setText(t.getEmployCont());
               }
       });
    }
        private void clearTextfiled()
    {
        EmployName.clear();
        EmployID.clear();
        EmployContactNo.clear();
        Vname.clear();
        Vid.clear();
    }
    @FXML
    private void ButtClearAcc(ActionEvent event) {
        EmployName.clear();
        EmployID.clear();
        EmployContactNo.clear();
        Vname.clear();
        Vid.clear();
    }

    @FXML
    private void ButAddAcc(ActionEvent event) throws SQLException {
         try{
            if (Vname.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter   vehicle name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (Vid.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  Vehicle id","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
           
        Statement st;
        st=con.createStatement();
        String sq2="Select * from employ where id= '" + EmployID.getText() + "'";
        rs=st.executeQuery(sq2);        
        if((rs.next())||(EmployID.getText().equals("")))
        {
            String sql1="Select Vid from transport where Vid= '" + Vid.getText() + "'";
        rs=st.executeQuery(sql1);
        if(rs.next()){
        JOptionPane.showMessageDialog( null, "TRANSPORT id already exists","Error", JOptionPane.ERROR_MESSAGE);
        Vid.setText("");
       return;
      }
            
            String sq1="Insert into transport(Vname,Vid,EmployName,EmployID,Employphone)values('"+ Vname.getText() +"','"+ Vid.getText()+"','"+ EmployName.getText()+"','"+ EmployID.getText()+"','"+ EmployContactNo.getText()+"')";
            pst=con.prepareStatement(sq1);
            pst.execute();
            pst.close();
            setTable();
            loadData();
            JOptionPane.showMessageDialog(null,"Successfully assign ","transport",JOptionPane.INFORMATION_MESSAGE);
                      
         
        }
        else
        {
          JOptionPane.showMessageDialog( null, "UNKNOWN EMPLOY","Error", JOptionPane.ERROR_MESSAGE);
        EmployID.setText("");  
            
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
            if (Vname.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter   vehicle name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (Vid.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  Vehicle id","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (EmployName.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  EMPLOY NAME","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (EmployID.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  EMPLOY ID","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (EmployContactNo.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  EMPLOY CONTACT NO","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
      else{
             Statement st;
        st=con.createStatement();
        String sq2="Select * from employ where id= '" + EmployID.getText() + "'";
        rs=st.executeQuery(sq2);        
        if(rs.next())
        {
           con=SqlConection.ConnectDB();
            String sql= "update transport set Vname='"+ Vname.getText() + "',Employphone='"+ EmployContactNo.getText() + "',EmployName='"+ EmployName.getText() + "',EmployID='"+ EmployID.getText() + "'WHERE Vid='"+ Vid.getText()+"'";
            pst=con.prepareStatement(sql);
            pst.executeUpdate();
            loadData();
            clearTextfiled();
            JOptionPane.showMessageDialog(null,"Successfully updated","Record",JOptionPane.INFORMATION_MESSAGE);   
        }
         
          else
        {
          JOptionPane.showMessageDialog( null, "UNKNOWN EMPLOY","Error", JOptionPane.ERROR_MESSAGE);
        EmployID.setText("");  
            
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
             String sql1="Select * from transport where Vid like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from transport where Vname like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from transport where EmployID like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from transport where EmployName like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from transport where Employphone like '%" + SearchFiled.getText() + "%'";
                    
         try {
                 
                 pst=con.prepareStatement(sql1);
                 rs=pst.executeQuery();
                 while(rs.next())
                 {
                     data.add(new transportlist(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                 }
                 employtable.setItems(data);
                 
             } catch (SQLException ex) {
                 Logger.getLogger(TransportController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
        });
    }        

    @FXML
    private void EmployInfromation(ActionEvent event) {
    }

    @FXML
    private void DeletBuss(ActionEvent event) {
        transportlist selectedForDeletion = employtable.getSelectionModel().getSelectedItem();
                String sq= "delete  from transport where Vid = ?";
         try {
             if (Vid.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please SELECT THE ROW","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
             else
             {
            pst=con.prepareStatement(sq);
            pst.setString(1, Vid.getText());
            int i=pst.executeUpdate();
            if(i==1)
            {
                JOptionPane.showMessageDialog(null,"Successfully DELETE","BUS ALL RECORD",JOptionPane.INFORMATION_MESSAGE);
                loadData();
                clearTextfiled();
            }
             }
        } catch (SQLException ex) {
            Logger.getLogger(TransportController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }    
   

    @FXML
    private void DeleteAss(ActionEvent event) {
        String a="";
        try{
             con= SqlConection.ConnectDB();
            
      
             Statement st;
        st=con.createStatement();
        String sq2="Select * from transport where EmployID= '" + EmployID.getText() + "'";
        rs=st.executeQuery(sq2);        
       
           con=SqlConection.ConnectDB();
            String sql= "update transport set Employphone='"+ a + "',EmployName='"+ a + "',EmployID='"+a+ "'WHERE Vid='"+ Vid.getText()+"'";
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
    private void PrintAcc(ActionEvent event) {
          try{
          String report="C:\\Users\\kamran qadeer\\Documents\\NetBeansProjects\\unit1\\ASS\\src\\report\\transport.jrxml";
          JasperReport js=JasperCompileManager.compileReport(report);
          JasperPrint jp=JasperFillManager.fillReport(js,null,con);
          JasperViewer.viewReport(jp);
          }
                  
               catch(JRException e)
                {
                  JOptionPane.showMessageDialog( null,e);
                }
    }

    @FXML
    private void ButAddAcc(KeyEvent event) {
    }

   
}

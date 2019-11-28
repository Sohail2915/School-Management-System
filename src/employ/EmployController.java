/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employ;

import STUDENT.StudentController;
import ass.FXMLloginController;
import ass.SqlConection;
import com.jfoenix.controls.JFXButton;
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
public class EmployController implements Initializable {
      Connection con=null;
      ResultSet rs=null;
      PreparedStatement pst=null;
    private ObservableList<employtablelist>data;
    @FXML
    private JFXTextField SearchFiled;
    @FXML
    private JFXTextField Name;
    @FXML
    private JFXTextField FathNam;
    @FXML
    private JFXTextField ID;
    @FXML
    private JFXTextField EduFile;
    @FXML
    private JFXTextField ExperFiled;
    @FXML
    private JFXTextField ConFiled;
    @FXML
    private JFXTextField addFiled;
    @FXML
    private DatePicker DatePick;
    @FXML
    private JFXButton ButtClear;
    @FXML
    private JFXButton ButAdd;
    @FXML
    private JFXButton ButtUpdate;
    @FXML
    private TableColumn<employtablelist,String> C3ID;
    @FXML
    private TableColumn<employtablelist,String> C1Nmae;
    @FXML
    private TableColumn<employtablelist,String> C2FathName;
    @FXML
    private TableColumn<employtablelist,String> C8edu;
    @FXML
    private TableColumn<employtablelist,String> C9expe;
    @FXML
    private TableColumn<employtablelist,String> C5ConNum;
    @FXML
    private TableColumn<employtablelist,String> C6DateOfBirth;
    @FXML
    private TableColumn<employtablelist,String> C10add;
    @FXML
    private TableView<employtablelist> employtable;
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
    private void setTable() {

        C1Nmae.setCellValueFactory(new PropertyValueFactory<>("name"));
        C2FathName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        C3ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        C10add.setCellValueFactory(new PropertyValueFactory<>("add"));        
        C5ConNum.setCellValueFactory(new PropertyValueFactory<>("cont"));
        C8edu.setCellValueFactory(new PropertyValueFactory<>("edu"));
        C9expe.setCellValueFactory(new PropertyValueFactory<>("exp"));
        C6DateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dbd"));

        } 
      private void loadData() {
          data.clear();
        try {
             pst=con.prepareStatement("Select * from employ");
             rs=pst.executeQuery();
            while(rs.next())
            {
                data.add(new employtablelist(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       employtable.setItems(data);
     }
       private void SelectCell()
    {
       employtable.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               employtablelist t=employtable.getItems().get(employtable.getSelectionModel().getSelectedIndex());
               Name.setText(t.getName());
               FathNam.setText(t.getFname());
               ID.setText(t.getId());
               addFiled.setText(t.getAdd());
               ExperFiled.setText(t.getExp());
               ConFiled.setText(t.getCont());
               EduFile.setText(t.getEdu());           }
       });
    }
    @FXML
    private void ButtClearAcc(ActionEvent event) {
        Name.clear();
        FathNam.clear();
        ID.clear();
        EduFile.clear();
        addFiled.clear();
        ExperFiled.clear();
        ConFiled.clear();
        DatePick.getEditor().clear();
    }
     private void clearTextfiled()
    {
        Name.clear();
        FathNam.clear();
        ID.clear();
        EduFile.clear();
        addFiled.clear();
        ExperFiled.clear();
        ConFiled.clear();
        SearchFiled.clear();
        DatePick.getEditor().clear();  
    }

    @FXML
    private void ButAddAcc(ActionEvent event) {
         String d=DatePick.getEditor().getText();
         try{
            if (Name.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  name","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (ID.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter  id","Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (addFiled.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter address","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (ConFiled.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter employ contect number","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
           
            
        Statement st;
        st=con.createStatement();
        String sql1="Select id from employ where id= '" + ID.getText() + "'";
        rs=st.executeQuery(sql1);
        if(rs.next()){
        JOptionPane.showMessageDialog( null, "EMPLOY id already exists","Error", JOptionPane.ERROR_MESSAGE);
        ID.setText("");
       return;
      }
            
            String sq1="Insert into employ(name,fname,id,address,cont,edu,exp,dbd)values('"+ Name.getText() +"','"+ FathNam.getText()+"','"+ ID.getText() +"','"+ addFiled.getText()+"','"+ConFiled.getText()+"','"+ EduFile.getText()+"','"+ ExperFiled.getText()+"','"+d+"')";
            pst=con.prepareStatement(sq1);
            pst.executeUpdate();
            pst.close();
            setTable();
            loadData();
            JOptionPane.showMessageDialog(null,"Successfully add","STUDENT",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }

    @FXML
    private void ButtUpdateAcc(ActionEvent event) {
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
            if (addFiled.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter address","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (ConFiled.getText().equals("")) {
                JOptionPane.showMessageDialog( null, "Please enter employ contect number","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
           con=SqlConection.ConnectDB();
            String sql= "update employ set name='"+ Name.getText() + "',fname='"+ FathNam.getText() + "',dbd='"+ DatePick.getEditor().getText() + "',edu='"+ EduFile.getText() + "',exp='"+ ExperFiled.getText() + "',cont='"+ ConFiled.getText()+ "',address='"+ addFiled.getText()+ "' where id='"+ ID.getText()+"'";
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
     private void search()
    {
        SearchFiled.setOnKeyReleased(e->{
         if(SearchFiled.getText().equals(""))
         {
             loadData();
         }
         else{
             data.clear();
             String sql1="Select * from employ where id like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from employ where name like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from employ where fname like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from employ where address like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from employ where edu like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from employ where exp like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from employ where cont like '%" + SearchFiled.getText() + "%'"
                     +"UNION Select * from employ where dbd like '%" + SearchFiled.getText() + "%'";
         try {
                 
                 pst=con.prepareStatement(sql1);
                 rs=pst.executeQuery();
                 while(rs.next())
                 {
                     data.add(new employtablelist(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
                 }
                 employtable.setItems(data);
                 
             } catch (SQLException ex) {
                 Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
        });
    }        
   
    @FXML
    private void StudInformation(ActionEvent event) {
          try {
            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/STUDENT/StudentDetail.fxml"));
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
            mainStage.setResizable(true);
            mainStage.setTitle("EMPLOY DETAIL");
                    } 
            catch (IOException ex) {
            Logger.getLogger(FXMLloginController.class.getName()).log(Level.SEVERE, null, ex);
                     }
    }

    @FXML
    private void StudentDelete(ActionEvent event) {
             employtablelist selectedForDeletion = employtable.getSelectionModel().getSelectedItem();
                String sq= "delete  from employ where id = ?";
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
                JOptionPane.showMessageDialog(null,"Successfully DELETE","EMPLOY",JOptionPane.INFORMATION_MESSAGE);
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
          String report="C:\\Users\\kamran qadeer\\Documents\\NetBeansProjects\\unit1\\ASS\\src\\report\\employ.jrxml";
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;
import java.sql.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author kamran qadeer
 */
public class FXMLloginController implements Initializable {
    Connection conn=SqlConection.ConnectDB();
    Statement st=null;
    ResultSet res=null;
    String username="root";
    String pass="";


    @FXML
    private JFXTextField AdminUserNameField;
    @FXML
    private JFXButton AdminUserNameButt;
    @FXML
    private JFXButton AdminCancel;
    @FXML
    private JFXPasswordField AdminUserPassField;
    @FXML
    private AnchorPane mainpan;
    @FXML
    private MaterialDesignIconView close;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            mainpan.setVisible(true);
            if(!ASS.isSplashLoaded) 
            Splash();
              
    }    



    @FXML
    private void AdminUserNameButtAcc(ActionEvent event) {
        String Name=AdminUserNameField.getText();
        String Pswd=AdminUserPassField.getText();
                try
        {
            System.out.print("kk");
            st=conn.createStatement();
            res=st.executeQuery("select * from admine");
            while (res.next())
            {
                username=res.getString(1);
                pass=res.getString(2);
                if(username.equals(AdminUserNameField.getText()) && pass.equals(AdminUserPassField.getText()))
                { 
                    try {
            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/home1/home1.fxml"));
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            ASS.loginstage.close();
            mainStage.show();
            mainStage.setResizable(false);
            mainStage.setTitle("HOME PAGE");
                    } 
            catch (IOException ex) {
            Logger.getLogger(FXMLloginController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }
                else
                { JOptionPane.showMessageDialog(null,"Incorrect Username or Password","Error Message",JOptionPane.ERROR_MESSAGE);
               
                
                }
            }
        }
        catch(HeadlessException | SQLException e)
        {
            System.out.println("hello "+e);
        }
         
    }

    @FXML
    private void AdminCancelAcc(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void AdminUserPassFieldAcc(ActionEvent event) {
        String Name=AdminUserNameField.getText();
        String Pswd=AdminUserPassField.getText();
                try
        {
            System.out.print("kk");
            st=conn.createStatement();
            res=st.executeQuery("select * from admine");
            while (res.next())
            {
                username=res.getString(1);
                pass=res.getString(2);
                if(username.equals(AdminUserNameField.getText()) && pass.equals(AdminUserPassField.getText()))
                { 
                    try {
            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/home1/home1.fxml"));
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            ASS.loginstage.close();
            mainStage.show();
            mainStage.setResizable(false);
            mainStage.setTitle("HOME PAGE");  
                    } 
            catch (IOException ex) {
            Logger.getLogger(FXMLloginController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }
                else
                { JOptionPane.showMessageDialog(null,"Incorrect Username or Password","Error Message",JOptionPane.ERROR_MESSAGE);
                
                
                }
            }
        }
        catch(HeadlessException | SQLException e)
        {
            System.out.println("hello "+e);
        }
    }   

    private void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void closeStage(MouseEvent event) {
         System.exit(0);
    }
    private void Splash(){
     try {
         ASS.isSplashLoaded=true;
    AnchorPane splash =FXMLLoader.load(getClass().getResource("logo.fxml"));
    mainpan.getChildren().setAll(splash);
    FadeTransition fadeIn=new FadeTransition(Duration.millis(3000),splash);
    fadeIn.setFromValue(0);
    fadeIn.setToValue(1);
    fadeIn.setCycleCount(1);
    fadeIn.play();
    FadeTransition fadeOut=new FadeTransition(Duration.millis(3000),splash);
    fadeOut.setFromValue(1);
    fadeOut.setToValue(0);
    fadeOut.setCycleCount(1);
    fadeIn.setOnFinished(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) {
            fadeOut.play();
        }
    });
    fadeOut.setOnFinished(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) {
            try {
                AnchorPane mainfome =FXMLLoader.load(getClass().getResource("FXMLlogin.fxml"));  
                mainpan.getChildren().setAll(mainfome);
            } catch (IOException ex) {
                Logger.getLogger(FXMLloginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
     }
     
       catch (IOException ex) {
            Logger.getLogger(FXMLloginController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author kamran qadeer
 */
public class AttendanceController implements Initializable {

    @FXML
    private AnchorPane holderpan;
    @FXML
    private JFXButton student;
    @FXML
    private JFXButton Teacher;
    @FXML
    private JFXButton Employ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try{
        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/studentattendance/studentattendance.fxml")));
                }
        catch(IOException g)
        {
            
        }
        // TODO
    }    

    @FXML
    private void studentAcc(ActionEvent event) {
                try{
                            holderpan.getChildren().clear();

        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/studentattendance/studentattendance.fxml")));
                }
        catch(IOException g)
        {
            
        }
    }

    @FXML
    private void TeacherButt(ActionEvent event) {
                try{
                            holderpan.getChildren().clear();

        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/teacherattendance/teacherattendance.fxml")));
                }
        catch(IOException g)
        {
            
        }
    }

    @FXML
    private void EmployButt(ActionEvent event) {
                try{
                            holderpan.getChildren().clear();

        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/employattendance/employattendance.fxml")));
                }
        catch(IOException g)
        {
            
        }
    }
    
}

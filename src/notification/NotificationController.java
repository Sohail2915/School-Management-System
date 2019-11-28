/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notification;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author kamran qadeer
 */
public class NotificationController implements Initializable {

    @FXML
    private JFXTextField notificationname;
    @FXML
    private JFXTextField day;
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
    private TableColumn<?, ?> CNotificationName;
    @FXML
    private TableColumn<?, ?> Cday;
    @FXML
    private TableColumn<?, ?> CDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ButtClearAcc(ActionEvent event) {
    }

    @FXML
    private void ButAddAcc(ActionEvent event) {
    }

    @FXML
    private void ButtUpdateAcc(ActionEvent event) {
    }

    @FXML
    private void ButAddAcc(KeyEvent event) {
    }

    @FXML
    private void StudInformation(ActionEvent event) {
    }

    @FXML
    private void StudentDelete(ActionEvent event) {
    }
    
}

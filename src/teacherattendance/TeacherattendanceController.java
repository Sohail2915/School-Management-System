/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherattendance;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author kamran qadeer
 */
public class TeacherattendanceController implements Initializable {

    @FXML
    private JFXButton ButtClear;
    @FXML
    private DatePicker DatePick;
    @FXML
    private JFXTextField SearchFiled;
    @FXML
    private TableView<?> studenttable;
    @FXML
    private TableColumn<?, ?> CID;
    @FXML
    private TableColumn<?, ?> CName;
    @FXML
    private TableColumn<?, ?> Cfathername;
    @FXML
    private TableColumn<?, ?> Cdate;
    @FXML
    private TableColumn<?, ?> Cattendance;

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
    private void StudInformation(ActionEvent event) {
    }

    @FXML
    private void StudentDelete(ActionEvent event) {
    }
    
}

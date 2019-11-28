/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentClass;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author kamran qadeer
 */
public class StudentClassController implements Initializable {

    @FXML
    private JFXTextField ClassName1;
    @FXML
    private JFXTextField ClassName;
    @FXML
    private JFXTextField FirstSub;
    @FXML
    private JFXTextField SecondSub;
    @FXML
    private JFXTextField ThirdSub;
    @FXML
    private JFXTextField ForthSub;
    @FXML
    private JFXTextField FivthSub;
    @FXML
    private JFXTextField SixSub;
    @FXML
    private JFXTextField SeventSub;
    @FXML
    private JFXTextField EightSub;
    @FXML
    private JFXTextField NinthSub;
    @FXML
    private JFXTextField TenthSub;
    @FXML
    private JFXButton AddButt;
    @FXML
    private JFXButton ClearButt;
    @FXML
    private JFXButton UpdateButt;
    @FXML
    private TableColumn<?, ?> Cclassname;
    @FXML
    private TableColumn<?, ?> C1subject;
    @FXML
    private TableColumn<?, ?> C4subject;
    @FXML
    private TableColumn<?, ?> C3subject;
    @FXML
    private TableColumn<?, ?> C5subject;
    @FXML
    private TableColumn<?, ?> C6subject;
    @FXML
    private TableColumn<?, ?> C7subject;
    @FXML
    private TableColumn<?, ?> C8subject;
    @FXML
    private TableColumn<?, ?> C9subject;
    @FXML
    private TableView<?> classtable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddButtAcc(ActionEvent event) {
    }

    @FXML
    private void ClearButtAcc(ActionEvent event) {
    }

    @FXML
    private void UpdateButtAcc(ActionEvent event) {
    }

    @FXML
    private void StudInformation(ActionEvent event) {
    }

    @FXML
    private void StudentDelete(ActionEvent event) {
    }
    
}

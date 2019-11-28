/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contacts;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author kamran qadeer
 */
public class ContactsController implements Initializable {

    @FXML
    private JFXButton SearchButt;
    @FXML
    private JFXButton AddNewContectButt;
    @FXML
    private JFXButton PersonDeletButt;
    @FXML
    private JFXTextField PersonIdField2;
    @FXML
    private JFXTextField PersonNameField2;
    @FXML
    private JFXButton PersonSearchButt2;
    @FXML
    private JFXTextField NewPersonIdField;
    @FXML
    private JFXTextField NewPersonNameFelid;
    @FXML
    private JFXButton AddButt;
    @FXML
    private JFXTextField NewContectNumberField;
    @FXML
    private AnchorPane MainPan;
    @FXML
    private AnchorPane MainPan2;
    @FXML
    private AnchorPane ContectPan;
    @FXML
    private AnchorPane SearchPan;
    @FXML
    private AnchorPane AddPan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainPan2.setVisible(true);
        SearchPan.setVisible(true);
        AddPan.setVisible(false);
        // TODO
    }    

    @FXML
    private void SearchButtAcc(ActionEvent event) {
         SearchPan.setVisible(true);
         AddPan.setVisible(false);
    }

    @FXML
    private void AddNewContectButtAcc(ActionEvent event) {
         SearchPan.setVisible(false);
        AddPan.setVisible(true);
    }

    @FXML
    private void PersonDeletButtAcc(ActionEvent event) {
    }

    @FXML
    private void PersonIdField2Acc(ActionEvent event) {
    }

    @FXML
    private void PersonSearchButt2Acc(ActionEvent event) {
    }

    @FXML
    private void AddButtAcc(ActionEvent event) {
    }

    @FXML
    private void NewContectNumberFieldAcc(ActionEvent event) {
    }
    
}

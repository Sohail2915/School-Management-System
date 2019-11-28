/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home1;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author kamran qadeer
 */
public class Home1Controller implements Initializable {

    @FXML
    private JFXButton ButtStud;
    @FXML
    private JFXButton ButtTeach;
    @FXML
    private JFXButton ButtEmpl;
    @FXML
    private JFXButton ButtLibr;
    @FXML
    private JFXButton ButtSubj;
    @FXML
    private JFXButton ButtTrans;
    @FXML
    private JFXButton ButtHostel;
    @FXML
    private JFXButton ButtHome;
    @FXML
    private AnchorPane holderpan;
 
    @FXML
    private AnchorPane ClockPan;
    AnchorPane student,teacher,employ,library,transport,subject,hostel,home2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       // clock();
       
        try{
        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/home2/home_1.fxml")));
                }
        catch(IOException g)
        {
            
        }   
        // TODO
    }    
    private void setNode(Node node) {
        holderpan.getChildren().clear();
        holderpan.getChildren().add((Node) node);

    }
    @FXML
    private void ButtStudAcc(ActionEvent event) {
                try{
        holderpan.getChildren().clear();
        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/STUDENT/student.fxml")));
                }
        catch(IOException g)
        {
            
        }
         
    }

    @FXML
    private void ButtTeachAcc(ActionEvent event) {
            try{
        holderpan.getChildren().clear();
        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/TEACHER/TEACHER.fxml")));
                }
        catch(IOException g)
        {
            
        }
                     

    }

    @FXML
    private void ButtEmplAcc(ActionEvent event) {
                    try{
        holderpan.getChildren().clear();
        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/employ/employ.fxml")));
                }
        catch(IOException g)
        {
            
        }
                  

    }

    @FXML
    private void ButtLibrAcc(ActionEvent event) {
                           try{
        holderpan.getChildren().clear();
        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/book/book.fxml")));
                }
        catch(IOException g)
        {
            
        }
        

    }



    @FXML
    private void ButtSubjAcc(ActionEvent event) {
             try{
        holderpan.getChildren().clear();
        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/subject/subject.fxml")));
                }
        catch(IOException g)
        {
            
        } 
    }

    private void ButtClaRoutAcc(ActionEvent event) {
          try{
        holderpan.getChildren().clear();
        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/routain/routain.fxml")));
                }
        catch(IOException g)
        {
            
        } 
    }

    private void ButtAttenAcc(ActionEvent event) {
          try{
        holderpan.getChildren().clear();
        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/attendance/attendance.fxml")));
                }
        catch(IOException g)
        {
            
        } 
    }


    @FXML
    private void ButtTransAcc(ActionEvent event) {
          try{
        holderpan.getChildren().clear();
        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/transport/transport.fxml")));
                }
        catch(IOException g)
        {
            
        }
         

    }

    @FXML
    private void ButtHostelAcc(ActionEvent event) {
         try{
        holderpan.getChildren().clear();
        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/hostal/hostal.fxml")));
                }
        catch(IOException g)
        {
            
        }
         

    }

    private void ButtNoticAcc(ActionEvent event) {
         try{
        holderpan.getChildren().clear();
        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/notification/notification.fxml")));
                }
        catch(IOException g)
        {
            
        }
    }


    @FXML
    private void ButtHomeAcc(ActionEvent event) {
                try{
        holderpan.getChildren().clear();
        holderpan.getChildren().add(FXMLLoader.load(getClass().getResource("/home2/home_1.fxml")));
                }
        catch(IOException g)
        {
            
        }
          

    }
     private void Node(Node node) {
        holderpan.getChildren().clear();
        holderpan.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(4000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
  private void clock()
  {
//      Calendar cal=new GregorianCalendar();
//      int second =cal.get(Calendar.SECOND);
//      int minute =cal.get(Calendar.MINUTE);
//      int hour =cal.get(Calendar.HOUR);
//      LabClock.setText("TIME :: "+hour+" : "+minute+" : "+second);     
//      Thread Clock=new Thread()
//      {
//          public void run()
//          {
//              for(;;)
//              {
//              System.out.print("p");
//              try{
//                  sleep(1000);
//              }
//              catch(Exception e){
//                  JOptionPane.showMessageDialog(null, e);
//              }
//          }}
//          
//      };
  }
}

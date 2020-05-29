/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Enfants;
import Entities.Events;
import Service.ServiceEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OsamaML97
 */
public class AjoutEventController implements Initializable {

    @FXML
    private TextArea EvDesc;
    @FXML
    private TextField EvPurp;
    @FXML
    private TextField EvEnd;
    @FXML
    private TextField EvStart;
    @FXML
    private TextField EvMonth;
    @FXML
    private TextField EvName;
    
    ServiceEvent se = new ServiceEvent();

            String f ="f";
        String m ="m";
        String datez ="20";
        String t ="-";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   
    }    
                 @FXML
    private void ajouter1(ActionEvent event) throws IOException {
     if ((EvDesc.getText().length()==0)||(EvPurp.getText().length()==0)||EvEnd.getText().isEmpty()||EvStart.getText().isEmpty()||EvMonth.getText().isEmpty()
                       ||EvName.getText().isEmpty() )
             {
                             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ajout event");
            alert.setHeaderText("information");
            alert.setContentText("please fill all the blanks ");
            alert.showAndWait();

             }
     
                  else if ((EvStart.getText().length()!=10)||!EvStart.getText().startsWith(datez)||!EvStart.getText().regionMatches(true,4, t, 0, 1)||!EvStart.getText().regionMatches(true,7, t, 0, 1)
                          ||(EvEnd.getText().length()!=10)||!EvEnd.getText().startsWith(datez)||!EvEnd.getText().regionMatches(true,4, t, 0, 1)||!EvEnd.getText().regionMatches(true,7, t, 0, 1))
             {
                          {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ajout event");
            alert.setHeaderText("information");
            alert.setContentText("date format y-m-d ");
            alert.showAndWait();}
             }
   

     else {
    
           
         Events e =new Events(EvName.getText(),EvMonth.getText(),EvStart.getText(),EvEnd.getText(),EvPurp.getText(),EvDesc.getText());
        try {
            se.ajouter1(e);
            
        } catch (SQLException ex) {
            }
        
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/interfaceEvent.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }}
    @FXML
        private void Cancel(ActionEvent event) throws IOException {
    

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/interfaceEvent.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}

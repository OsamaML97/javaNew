/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataBaseConnection.DataBaseConnection;
import Entities.Enfants;
import Entities.Events;
import Service.ServiceEnfant;
import Service.ServiceEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OsamaML97
 */
public class UpdateEventController implements Initializable {

    @FXML
    private TextField EventF;
    @FXML
    private Button Bfind;
    @FXML
    private TextField EvMonthF;
    @FXML
    private TextField EvStartF;
    @FXML
    private TextField EvEndF;
    @FXML
    private Button Bupdate;
    @FXML
    private Button cancel;
    @FXML
    private TextField PurF;
    @FXML
    private TextArea DescF;
    
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
    private void find(ActionEvent event) throws SQLException {
        String nomF = EventF.getText();
        
        Connection con = DataBaseConnection.getConnection2();
        Events EnR = ServiceEvent.getEvent(nomF);
        EvMonthF.setText(String.valueOf(EnR.getEvMonth()));
        EvStartF.setText(String.valueOf(EnR.getEvStart()));
        EvEndF.setText(String.valueOf(EnR.getEvEnd()));
        PurF.setText(String.valueOf(EnR.getEvPur()));
        DescF.setText(String.valueOf(EnR.getEvDesc()));
    }

    @FXML
    private void Bupdate(ActionEvent event) throws SQLException {
        
        System.out.println("Z");
        String nomF = EventF.getText();
        Connection con = DataBaseConnection.getConnection2();
        String EvMonthS = EvMonthF.getText();
        String EvStartS = EvStartF.getText();
        String EvEndS = EvEndF.getText();
        String EvPurS = PurF.getText();
        String EvDescS = DescF.getText();

        Events std = new Events();
        std.setEvName(nomF);
        std.setEvMonth(EvMonthS);
        std.setEvStart(EvStartS);
        std.setEvEnd(EvEndS);
        std.setEvPur(EvPurS);
        std.setEvDesc(EvDescS);
        
             if ((EventF.getText().length()==0)||(EvMonthF.getText().length()==0)||EvStartF.getText().isEmpty()||EvEndF.getText().isEmpty()||PurF.getText().isEmpty()
                       ||DescF.getText().isEmpty() )
             {
                             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("update event");
            alert.setHeaderText("information");
            alert.setContentText("please fill all the blanks ");
            alert.showAndWait();

             }
     
                  else if ((EvEndF.getText().length()!=10)||!EvEndF.getText().startsWith(datez)||!EvEndF.getText().regionMatches(true,4, t, 0, 1)||!EvEndF.getText().regionMatches(true,7, t, 0, 1)
                          ||(EvStartF.getText().length()!=10)||!EvStartF.getText().startsWith(datez)||!EvStartF.getText().regionMatches(true,4, t, 0, 1)||!EvStartF.getText().regionMatches(true,7, t, 0, 1))
             {
                          {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("update event");
            alert.setHeaderText("information");
            alert.setContentText("date format y-m-d ");
            alert.showAndWait();}
             }
   

     else {
        
        int status = ServiceEvent.update2(std);
        if (status>0)
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("update event");
            alert.setHeaderText("information");
            alert.setContentText("event bien changer ");
            alert.showAndWait();
        }
        else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("update event");
            alert.setHeaderText("information");
            alert.setContentText("error ");
            alert.showAndWait();
        
        }
    }}

    @FXML
    private void Bcancel(ActionEvent event) throws IOException {
                System.out.println("X");
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/interfaceEvent.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
        @FXML
    private void SearchUpdate(ActionEvent event) throws IOException {
        System.out.println("X");
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/UpdateEnfant.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataBaseConnection.DataBaseConnection;
import Entities.Enfants;
import Entities.Events;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OsamaML97
 */
public class InterfaceEventController implements Initializable {

    @FXML
    private TableView<Events> table;
    @FXML
    private TableColumn<Events, String> EvName;
    @FXML
    private TableColumn<Events, String> EvMonth;
    @FXML
    private TableColumn<Events, String> EvStart;
    @FXML
    private TableColumn<Events, String> EvEnd;
    @FXML
    private TableColumn<Events, String> EvPur;
    @FXML
    private TableColumn<Events, String> EvDesc;
    
    ResultSet rs;
    PreparedStatement pst;
    
    
    ObservableList<Events> oblist = FXCollections.observableArrayList();
    @FXML
    private Button retour;
    @FXML
    private Button AddEvent;
    @FXML
    private Button ButtonRemove;
    @FXML
    private Button SearchUpdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
                try {
            // TODO
            
           
            Connection con = DataBaseConnection.getConnection2();
            ResultSet rs = con.createStatement().executeQuery("select * from event");
            while (rs.next()) {
                oblist.add(new Events(rs.getString("EvName"), rs.getString("EvMonth"), rs.getString("EvStart"), rs.getString("EvEnd"), rs.getString("EvPur"),rs.getString("EvDesc")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(InterfaceEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
            
        
        
        
            EvName.setCellValueFactory(new PropertyValueFactory<>("EvName"));
            EvMonth.setCellValueFactory(new PropertyValueFactory<>("EvMonth")  );
            EvStart.setCellValueFactory(new PropertyValueFactory<>("EvStart"));
            EvEnd.setCellValueFactory(new PropertyValueFactory<>("EvEnd"));
            EvPur.setCellValueFactory(new PropertyValueFactory<>("EvPur"));
            EvDesc.setCellValueFactory(new PropertyValueFactory<>("EvDesc"));
            
            
            
            
        table.setItems(oblist);
            
            
        
}
    
    @FXML
    private void retour(ActionEvent event) throws IOException {
        System.out.println("X");
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/ShowHome.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    
    }
    
     @FXML
    private void AddEvent(ActionEvent event) throws IOException {
        System.out.println("X");
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/AjoutEvent.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    
    }
    
            @FXML
    public void ButtonRemove(ActionEvent event) throws IOException, SQLException {

       System.out.print("x");
       Connection con = DataBaseConnection.getConnection2();
       int selectedIndex = table.getSelectionModel().getSelectedIndex();
       Events e = (Events) table.getSelectionModel().getSelectedItem();
       String nomE = e.getEvName();
       
       if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + e.getEvName() + " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
      

      if (alert.getResult() == ButtonType.YES) {
                String sql = "DELETE FROM event WHERE evName = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, nomE);
                pst.execute();
                table.getItems().remove(selectedIndex);
                

            }
       }
    }

    @FXML
    private void SearchUpdate(ActionEvent event) throws IOException {
        System.out.println("X");
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/UpdateEvent.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    
    }
    
    }    
    


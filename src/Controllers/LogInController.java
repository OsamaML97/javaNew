/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataBaseConnection.DataBaseConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OsamaML97
 */
public class LogInController implements Initializable {

    @FXML
    private TextField usernameZ;
    @FXML
    private TextField passwordZ;
    @FXML
    private Button LoginZ;
    
    private Connection con;
     private DataBaseConnection conn;
     PreparedStatement pst;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conn=DataBaseConnection.getInstance();
    }    

    @FXML
    private void LoginZ(ActionEvent event) throws IOException, SQLException {
      conn=DataBaseConnection.getInstance();
      String sql ="select * from FOS_user where username =? and password =? ";
     try{
      pst=conn.getCnx().prepareStatement(sql);
      pst.setString(1, usernameZ.getText());
      pst.setString(2, passwordZ.getText());
      
      
      ResultSet rs= pst.executeQuery();
      if(rs != null )
      {
          if(!rs.next())
          {
          Alert alert =new Alert(Alert.AlertType.ERROR);
              alert.setContentText("Username or mot de passe is incorrect");
                  alert.showAndWait();
                      
          } else{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/ShowHome.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
          }  
      
      
      
      
      
      
      } }catch(SQLException ex){
                  System.out.println(ex.getMessage());
                  
                  }
     
                         
      }
    

}

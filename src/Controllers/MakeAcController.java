/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataBaseConnection.DataBaseConnection;
import Entities.Enfants;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author OsamaML97
 */
public class MakeAcController implements Initializable {

    @FXML
    private TextField usernameZ;
    @FXML
    private Button Back;
    @FXML
    private Button Create;
    @FXML
    private TextField EmailZ;
    @FXML
    private PasswordField passwordZ;

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
    private void Back(ActionEvent event) {
    }


    
    @FXML
    private void Create(ActionEvent event) throws IOException, SQLException {
        conn=DataBaseConnection.getInstance();
        String sql ="INSERT INTO FOS_user (username,password,email) values (?,?,?)";
       // Integer.valueOf(idC_tf.getText());
        String name = usernameZ.getText();
        String pass=passwordZ.getText();
        String us=EmailZ.getText();
        
        try {
            pst = conn.getCnx().prepareStatement(sql);
           
            pst.setString(1, name);
            pst.setString(2, pass);
            pst.setString(3, us);
            
       
            pst.executeQuery();
                        
            
            pst.close();
             } catch (SQLException ex) {
            
        }
    }
}

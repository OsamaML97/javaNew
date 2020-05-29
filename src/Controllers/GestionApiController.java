/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.teknikindustries.bulksms.SMS;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author OsamaML97
 */
public class GestionApiController implements Initializable {

    @FXML
    private CheckBox CheckB;
    @FXML
    private Button Bok;
    @FXML
    private TextField NumP;
    @FXML
    private TextArea Continue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ok(ActionEvent event) throws MalformedURLException, UnsupportedEncodingException, IOException {
        if(CheckB.isSelected())
        {
        System.out.println("XX");
        SMS send = new SMS();
        send.SendSMS("oussama97", "Esprit2019", " "+Continue.getText()+"","+216"+NumP.getText(), "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
        JOptionPane.showMessageDialog(null,"message sent");
        }
        
           
        if (CheckB.isSelected()==false)
        {JOptionPane.showMessageDialog(null,"cas non critique");;}
    }
  
    @FXML
    private void cancel(ActionEvent event) throws MalformedURLException, UnsupportedEncodingException, IOException {
        System.out.println("X");
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/InterfaceEnfant.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Enfants;
import Service.ServiceEnfant;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author OsamaML97
 */
public class AjoutEnfantController implements Initializable {

    @FXML
    private AnchorPane zz;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField sexe;
    @FXML
    private TextField lieuN;
    @FXML
    private TextField medecin;
    @FXML
    private TextField MedNum;
    @FXML
    private TextField dateN;

    
    ServiceEnfant se = new ServiceEnfant();
    
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
    
    System.out.println("nom:"+nom.getText());
    System.out.println("prenom:"+prenom.getText());
    System.out.println("age:"+sexe.getText());
    System.out.println("lieu:"+lieuN.getText());
    System.out.println("date:"+dateN.getText());
    System.out.println("medecin:"+medecin.getText());
    System.out.println("med num:"+MedNum.getText());
   
             if ((nom.getText().length()==0)||(prenom.getText().length()==0)||MedNum.getText().isEmpty()||sexe.getText().isEmpty()||lieuN.getText().isEmpty()
                       ||medecin.getText().isEmpty()||(dateN.getText().length()==0) )
             {
                             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("update enfant");
            alert.setHeaderText("information");
            alert.setContentText("please fill all the blanks ");
            alert.showAndWait();

             }
             else if((!sexe.getText().equals("f")&&!sexe.getText().equals(m)))
             {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("update enfant");
            alert.setHeaderText("information");
            alert.setContentText("f or m for gender ");
            alert.showAndWait();}
             
             else if ((dateN.getText().length()!=10)||!dateN.getText().startsWith(datez)||!dateN.getText().regionMatches(true,4, t, 0, 1)||!dateN.getText().regionMatches(true,7, t, 0, 1))
             {
                          {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("update enfant");
            alert.setHeaderText("information");
            alert.setContentText("date format y-m-d ");
            alert.showAndWait();}
             }
             else {
    
           
         Enfants e =new Enfants(nom.getText(),prenom.getText(),sexe.getText(),lieuN.getText(),dateN.getText(),medecin.getText(),Integer.parseInt((MedNum.getText())));
        try {
            
            se.ajouter1(e);

            
        } catch (SQLException ex) {

                 }
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/InterfaceEnfant.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
        

    }}
    @FXML
        private void Cancel(ActionEvent event) throws IOException {
    

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/InterfaceEnfant.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}

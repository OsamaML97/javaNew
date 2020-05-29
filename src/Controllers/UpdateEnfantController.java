/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataBaseConnection.DataBaseConnection;
import Entities.Enfants;
import Service.ServiceEnfant;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OsamaML97
 */
public class UpdateEnfantController implements Initializable {

    @FXML
    private TextField nomU;
    @FXML
    private TextField prenomU;
    @FXML
    private Button Bfind;
    @FXML
    private TextField Sexe;
    @FXML
    private TextField LieuN;
    @FXML
    private TextField DateN;
    @FXML
    private Button Bupdate;
    @FXML
    private Button cancel;
    @FXML
    private TextField medicin;
    @FXML
    private TextField MedNum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void find(ActionEvent event) throws SQLException {
        String nomF = nomU.getText();
        String prenomF = prenomU.getText();
        Connection con = DataBaseConnection.getConnection2();
        Enfants EnR = ServiceEnfant.getEnfant(nomF, prenomF);
        Sexe.setText(String.valueOf(EnR.getSexe()));
        LieuN.setText(String.valueOf(EnR.getLieuN()));
        DateN.setText(String.valueOf(EnR.getDateN()));
        medicin.setText(String.valueOf(EnR.getMedicin()));
        MedNum.setText(String.valueOf(EnR.getMedNum()));
    }

    @FXML
    private void Bupdate(ActionEvent event) throws SQLException, IOException {
          System.out.println("Z");
        String nomF = nomU.getText();
        String prenomF = prenomU.getText();
        Connection con = DataBaseConnection.getConnection2();
        String SexeF = Sexe.getText();
        String LieuNF = LieuN.getText();
        String DateNF = DateN.getText();
        String medicinF = medicin.getText();
        String MedNumF = MedNum.getText();

        Enfants std = new Enfants();
        std.setNom(nomF);
        std.setPrenom(prenomF);
        std.setSexe(SexeF);
        std.setLieuN(LieuNF);
        std.setDateN(DateNF);
        std.setMedicin(medicinF);
        std.setMedNum((Integer.parseInt(MedNumF)));
        String f ="f";
        String m ="m";
        String datez ="20";
        String t ="-";
        
        
         if ((nomU.getText().length()==0)||(prenomU.getText().length()==0)||MedNum.getText().isEmpty()||Sexe.getText().isEmpty()||LieuN.getText().isEmpty()
                       ||medicin.getText().isEmpty()||(DateN.getText().length()==0) )
             {
                             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("update enfant");
            alert.setHeaderText("information");
            alert.setContentText("please fill all the blanks ");
            alert.showAndWait();

             }
             else if((!Sexe.getText().equals("f")&&!Sexe.getText().equals(m)))
             {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("update enfant");
            alert.setHeaderText("information");
            alert.setContentText("f or m for gender ");
            alert.showAndWait();}
             
             else if ((DateN.getText().length()!=10)||!DateN.getText().startsWith(datez)||!DateN.getText().regionMatches(true,4, t, 0, 1)||!DateN.getText().regionMatches(true,7, t, 0, 1))
             {
                          {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("update enfant");
            alert.setHeaderText("information");
            alert.setContentText("date format y-m-d ");
            alert.showAndWait();}
             }
             else {
        
        int status = ServiceEnfant.update2(std);
        if (status>0)
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("update enfant");
            alert.setHeaderText("information");
            alert.setContentText("enfant bien changer ");
            alert.showAndWait();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/InterfaceEnfant.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        }
        else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("update enfant");
            alert.setHeaderText("information");
            alert.setContentText("error ");
            alert.showAndWait();
        
        }
    }}

    @FXML
    private void Bcancel(ActionEvent event) throws IOException {
        System.out.println("X");
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/InterfaceEnfant.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Entities.Enfants;
import DataBaseConnection.DataBaseConnection;
import Service.ServiceEnfant;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OsamaML97
 */
public class InterfaceEnfantController implements Initializable {

    @FXML
    private AnchorPane Anc;
    @FXML
    private TableView<Enfants> Table;
    @FXML
    private TableColumn<Enfants, String> nom;
    @FXML
    private TableColumn<Enfants, String> prenom;
    @FXML
    private TableColumn<Enfants, String> sexe;
    @FXML
    private TableColumn<Enfants, String> lieuN;
    @FXML
    private TableColumn<Enfants, String> DateN;
    @FXML
    private TableColumn<Enfants, String> NumMed;
    @FXML
    private TableColumn<Enfants, String> medicin;
    @FXML
    private TableView<Enfants> Table_Enfants;
    
    ResultSet rs;
    PreparedStatement pst;
    
    
    ObservableList<Enfants> oblist = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // TODO
            
           
            Connection con = DataBaseConnection.getConnection2();
            ResultSet rs = con.createStatement().executeQuery("select * from enfants");
            while (rs.next()) {
                oblist.add(new Enfants(rs.getInt("id"),rs.getString("nom"), rs.getString("prenom"), rs.getString("sexe"), rs.getString("lieuNaissance"), rs.getString("dateNaissance"),rs.getString("medicin"),rs.getInt("medecinNumero")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(InterfaceEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
            
        
        
        
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom")  );
            sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
            lieuN.setCellValueFactory(new PropertyValueFactory<>("lieuN"));
            DateN.setCellValueFactory(new PropertyValueFactory<>("DateN"));
            NumMed.setCellValueFactory(new PropertyValueFactory<>("MedNum"));
            medicin.setCellValueFactory(new PropertyValueFactory<>("medicin"));
            
            
            
        Table.setItems(oblist);
            
            
        
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
    private void ajoutEnf(ActionEvent event) throws IOException {
        System.out.println("X");
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/AjoutEnfant.fxml"));
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
       int selectedIndex = Table.getSelectionModel().getSelectedIndex();
       Enfants e = (Enfants) Table.getSelectionModel().getSelectedItem();
       String nomE = e.getNom();
       String prenomE = e.getPrenom();
       if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + e.getNom() + " " + e.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
      

      if (alert.getResult() == ButtonType.YES) {
                String sql = "DELETE FROM enfants WHERE nom = ? and prenom = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, nomE);
                pst.setString(2 ,prenomE);
                pst.execute();
                Table.getItems().remove(selectedIndex);
                

            }
       }
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
    
                @FXML
    private void BSMS(ActionEvent event) throws IOException {
        System.out.println("X");
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/GestionApi.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    
    }
            
            
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Enfants;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import DataBaseConnection.DataBaseConnection;
import Entities.Enfants;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Iservice.IserviceEnfant;

/**
 *
 * @author House
 */
public class ServiceEnfant implements IserviceEnfant<Enfants> {

    private Connection con;
    private Statement ste;

    public ServiceEnfant() {
        con = DataBaseConnection.getInstance().getCnx();

    }

    @Override
    public void ajouter(Enfants t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `jardins`.`enfants` ( `id`,`nom`, `prenom`,`sexe`,`lieuNaissance`,`dateNaissance`,`medicin`,`medecinNumero`) VALUES (" + t.getId() + "" + t.getNom() + "" + t.getPrenom() + "', '" + t.getSexe() +"', '"+t.getLieuN()+"', '"+t.getDateN()+""+t.getMedicin()+""+t.getMedNum()+"');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Enfants e) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `jardins`.`enfants` ( `nom`, `prenom`,`sexe`,`lieuNaissance`,`dateNaissance`,`medicin`,`medecinNumero`) VALUES ( ?,?,?,?,?,?,?);");
    
    pre.setString(1, e.getNom());
    pre.setString(2, e.getPrenom());
    pre.setString(3, e.getSexe());
    pre.setString(4, e.getLieuN());
    pre.setString(5, e.getDateN());
    pre.setString(6, e.getMedicin());
    pre.setInt(7, e.getMedNum());
 
    pre.executeUpdate();
    }
            

    public boolean delete(Enfants e) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean update(Enfants e) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ObservableList<Enfants> readAll() throws SQLException {
    ObservableList<Enfants> arr= FXCollections.observableArrayList();
    Statement ste;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from enfants");
     while (rs.next()) {                
               int id=rs.getInt("id");
               String nom=rs.getString("nom");
               String prenom=rs.getString("prenom");
               String sexe=rs.getString("sexe");
               String lieuN=rs.getString("lieuNaissance");
               String dateN=rs.getString("dateNaissance");
               String Medicin=rs.getString("Medicin");
               int MedNum=rs.getInt("medecinNumero");
             
               Enfants p=new Enfants(id ,nom, prenom, sexe,lieuN ,dateN,Medicin,MedNum);
     arr.add(p);
     }
    return arr;
    }
    
    public static Enfants getEnfant(String nom, String prenom) throws SQLException{
    Enfants en = new Enfants();
    try{
    String sql = "SELECT nom,prenom,sexe,lieuNaissance,dateNaissance,medicin,medecinNumero FROM enfants WHERE nom = ? and prenom = ?";
    Connection con = DataBaseConnection.getConnection2();
    PreparedStatement stat;
    stat = con.prepareStatement(sql);
    stat.setString(1, nom);
    stat.setString(2,prenom);
    ResultSet rst = stat.executeQuery();
    if (rst.next())
    {
    
    en.setNom(rst.getString(1));
    en.setPrenom(rst.getString(2));
    en.setSexe(rst.getString(3));
    en.setLieuN(rst.getString(4));
    en.setDateN(rst.getString(5));  
    en.setMedicin(rst.getString(6));
    en.setMedNum(rst.getInt(7));;
    }
    con.close();
    }catch(Exception e){
        e.printStackTrace();
    }
    return en;
    
    }   
    
    public static int update2(Enfants std) throws SQLException{
    int st =0;
    try{
    String sql = "UPDATE enfants SET sexe=?, lieuNaissance=?, dateNaissance=? ,medicin =? ,medecinNumero =? WHERE nom = ? and prenom = ?";
    Connection con = DataBaseConnection.getConnection2();
    PreparedStatement stat;
    stat = con.prepareStatement(sql);
    stat.setString(1, std.getSexe());
    stat.setString(2, std.getLieuN());
    stat.setString(3, std.getDateN());
    stat.setString(4, std.getMedicin());
    stat.setString(5,Integer.toString(std.getMedNum()));
    stat.setString(6, std.getNom());
    stat.setString(7, std.getPrenom());
    
    st = stat.executeUpdate();
    
    con.close();
    }catch(Exception e){
        e.printStackTrace();
    }
    return st;
    
    }   
    


    
}

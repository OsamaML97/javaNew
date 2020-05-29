/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataBaseConnection.DataBaseConnection;
import Entities.Events;
import Iservice.IserviceEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;

/**
 *
 * @author OsamaML97
 */

    public class ServiceEvent implements IserviceEvent<Events> {

    private Connection con;
    private Statement ste;

    public ServiceEvent() {
        con = DataBaseConnection.getInstance().getCnx();

    }

    @Override
    public void ajouter(Events t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `jardins`.`event` ( `EvName`, `EvMonth`,`EvStart`,`EvEnd`,`EvPur`,`EvDesc`) VALUES (" + t.getEvName() + "" + t.getEvMonth() + "" + t.getEvStart() + "', '" + t.getEvEnd() +"', '"+t.getEvPur()+"', '"+t.getEvDesc()+"');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Events e) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `jardins`.`event` ( `evName`, `evMonth`,`evStart`,`evEnd`,`evPur`,`evDesc`) VALUES ( ?,?,?,?,?,?);");
    pre.setString(1, e.getEvName());
    pre.setString(2, e.getEvMonth());
    pre.setString(3, e.getEvStart());
    pre.setString(4, e.getEvEnd());
    pre.setString(5, e.getEvPur());
    pre.setString(6, e.getEvDesc());
    
 
    pre.executeUpdate();
    }
            

    public boolean delete(Events e) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean update(Events e) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ObservableList<Events> readAll() throws SQLException {
    ObservableList<Events> arr= FXCollections.observableArrayList();
    Statement ste;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from event");
     while (rs.next()) {                
               
               String EvName=rs.getString("EvName");
               String EvMonth=rs.getString("EvMonth");
               String EvStart=rs.getString("EvStart");
               String EvEnd=rs.getString("EvEnd");
               String EvPur=rs.getString("EvPur");
               String EvDesc=rs.getString("EvDesc");
               
             
               Events p=new Events(EvName, EvMonth, EvStart,EvEnd ,EvPur,EvDesc);
     arr.add(p);
     }
    return arr;
    }
    
    public static Events getEvent(String evName) throws SQLException{
    Events en = new Events();
    try{
    String sql = "SELECT evName,evMonth,evStart,evEnd,evPur,evDesc FROM event WHERE evName = ?";
    Connection con = DataBaseConnection.getConnection2();
    PreparedStatement stat;
    stat = con.prepareStatement(sql);
    stat.setString(1, evName);
    
    ResultSet rst = stat.executeQuery();
    if (rst.next())
    {
    
    en.setEvName(rst.getString(1));
    en.setEvMonth(rst.getString(2));
    en.setEvStart(rst.getString(3));
    en.setEvEnd(rst.getString(4));
    en.setEvPur(rst.getString(5));  
    en.setEvDesc(rst.getString(6));
    

    }
    con.close();
    }catch(Exception e){
        e.printStackTrace();
    }
    return en;
    
    }   
    
    public static int update2(Events std) throws SQLException{
    int st =0;
    try{
    String sql = "UPDATE event SET evMonth=?, evStart=?, evEnd=? ,evPur=? ,evDesc =?  WHERE evName = ? ";
    Connection con = DataBaseConnection.getConnection2();
    PreparedStatement stat;
    stat = con.prepareStatement(sql);
    stat.setString(1, std.getEvMonth());
    stat.setString(2, std.getEvStart());
    stat.setString(3, std.getEvEnd());
    stat.setString(4, std.getEvPur());
    stat.setString(5, std.getEvDesc());
    stat.setString(6, std.getEvName());
    
    
    
    
    st = stat.executeUpdate();
    
    con.close();
    }catch(Exception e){
        e.printStackTrace();
    }
    return st;
    
    }   
    
    }


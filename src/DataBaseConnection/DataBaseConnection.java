/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author OsamaML97
 */
public class DataBaseConnection {
    private static DataBaseConnection instance;
    
    private Connection cnx;
    
    final String url="jdbc:mysql://127.0.0.1/jardins";
    final String login ="root";
    final String password="";
    
    
    private DataBaseConnection()
    {
        try{
            cnx=DriverManager.getConnection(url,login,password);
            System.out.println("Connexion établie ");
            
        }catch(SQLException ex14)
        {
            System.out.println(ex14.getMessage());
        }
    }
    
    public Connection getCnx()
    {
        return cnx;
    }
    public static DataBaseConnection getInstance ()
    {
        if(instance==null)
        
            instance=new DataBaseConnection();
        return instance;
    }
    
    public static Connection getConnection2() throws SQLException{
    
    Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/jardins","root","");
    
    
    return connection ;
    }
    

    }

package com.rdstew.engines;

import java.sql.*;
import java.util.Properties;

public class DatabaseEngine{

    private static DatabaseEngine engine = null;
    private Connection conn = null;

    private DatabaseEngine(){
        
    }

    public static DatabaseEngine start(){
        if (engine == null){
            engine = new DatabaseEngine();
        }
        return engine;
    }

    public static DatabaseEngine getInstance(){
        return engine;
    }


    public void connect(String url, String user, String pass){
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pass);
        props.setProperty("ssl", "false");

        try{
            conn = DriverManager.getConnection(url, props);
        }
        catch(java.sql.SQLException e){
            System.out.println(e);
            System.exit(1);
        }
    }

    public ResultSet query(String q){
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(q);
            return rs;
        } 
        catch (java.sql.SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public void upsert(String q){
        try{
            PreparedStatement st = conn.prepareStatement(q);
            st.executeUpdate();
            st.close();
        }
        catch (java.sql.SQLException e){
            System.out.println(e);
           
        }
      
    }
    
}
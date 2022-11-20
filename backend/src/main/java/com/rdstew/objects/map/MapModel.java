package com.rdstew.objects.map;

import com.rdstew.objects.Object;
import com.rdstew.engines.DatabaseEngine;
import com.rdstew.objects.map.CoordinatePoint;
import java.util.HashMap;
import java.sql.*;

public class MapModel{
    private int width;
    private int height;
    private HashMap<Integer, Object> chunks;
    
    public MapModel(){
        this.width = 0;
        this.height = 0;
        this.chunks = new HashMap<Integer, Object>();
    }

    public void loadChunks(CoordinatePoint p1, CoordinatePoint p2){
        DatabaseEngine db = DatabaseEngine.getInstance();
        String qstr = String.format( "SELECT * FROM chunks "     
                                    +"WHERE (x BETWEEN %f AND %f)"
                                    +"AND (x BETWEEN %f AND %f);",
                                    p1.x, p2.x, p1.y, p2.y);
        System.out.println(qstr);
        ResultSet chunks = db.query(qstr);
        try{
            while(chunks.next()){
                System.out.println(chunks.getString("environment"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
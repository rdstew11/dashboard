package com.rdstew.engines;

import com.rdstew.engines.DatabaseEngine;
import com.rdstew.objects.map.MapModel;
import com.rdstew.objects.map.CoordinatePoint;

public class CoreEngine{
    private static CoreEngine engine = null;
    private DatabaseEngine db = null;

    private CoreEngine(){
        init();
    }

    private void init(){
        db = DatabaseEngine.start();
        db.connect("jdbc:postgresql:webapp", "webapp", "password");
        //db.upsert("INSERT INTO chunks(c_id, environment, x, y) VALUES (1,'OCEAN',0,0);");
        MapModel map = new MapModel();
        CoordinatePoint p1 = new CoordinatePoint(-1,-1);
        CoordinatePoint p2 = new CoordinatePoint(1, 1);
        System.out.println("Loading chunks");
        map.loadChunks(p1,p2);
    }

    public static CoreEngine start(){
        if (engine == null){
            engine = new CoreEngine();
        }
        return engine;
    }
}
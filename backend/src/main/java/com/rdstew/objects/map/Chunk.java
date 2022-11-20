package com.rdstew.objects.map;

import com.rdstew.objects.Object;
import com.rdstew.objects.map.Environment;
import com.rdstew.objects.map.CoordinatePoint;

import java.util.ArrayList;


public class Chunk{
    public int cuid = 0;
    private int width = 100;
    private int length = 100;
    private Environment env = null;
    private CoordinatePoint base_point = null;
    private ArrayList<Object> objects = null;

    public Chunk(int cuid, Environment env, CoordinatePoint base_point){
        this.cuid = cuid;
        this.env = env;
        this.base_point = base_point;
        this.objects = new ArrayList<Object>();
    }

    public void addObject(Object obj){
        objects.add(obj);
    }
}
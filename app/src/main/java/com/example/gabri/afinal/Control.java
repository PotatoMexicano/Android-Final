package com.example.gabri.afinal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class Control {

    SQLiteDatabase db;
    Constructor constructor;

    public Control(Context ctx) {
        constructor = new Constructor(ctx);
    }

    public long insertUser(String name, String login, String password){
        db = constructor.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME",name);
        cv.put("LOGIN",login);
        cv.put("PASSWORD",password);

        long retorno = db.insert("USER", null, cv);
        return retorno;
    } //Signup user
    public boolean selectUser(String login, String password){
        db = constructor.getReadableDatabase();
        String sql = "SELECT * FROM USER WHERE LOGIN = '"+login+"' AND PASSWORD ='"+password+"';";
        Cursor resp = db.rawQuery(sql,null);
        if(resp.moveToNext()){
            return true;
        }else{
            return  false;
        }
    } //Login method

    public long insertEvent(String type, String date, String time, String description){
        db = constructor.getWritableDatabase();
        ContentValues cv = new ContentValues();

        description = description.trim();

        cv.put("TYPE",type);
        cv.put("DATE",date);
        cv.put("TIME",time);
        cv.put("DESCRIPTION", description);
        long retorno = db.insert("EVENT",null,cv);
        return retorno;
    } //add new event

    public ArrayList<Events> selectEvents(String type){
        db = constructor.getReadableDatabase();
        String sql = "SELECT * FROM EVENT WHERE TYPE = '"+type+"';";
        Cursor resp = db.rawQuery(sql,null);
        ArrayList<Events> ArrayEvents = new ArrayList<Events>();

        while(resp.moveToNext()){
            Events events = new Events();
            events.ID = resp.getInt(0);
            events.Type = resp.getString(1);
            events.Date = resp.getString(2);
            events.Time = resp.getString(3);
            events.Comment = resp.getString(4);
            ArrayEvents.add(events);

        }
        return ArrayEvents;
    } //Load events
}

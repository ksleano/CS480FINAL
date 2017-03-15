/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs480.pkgclass.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Log to hold entries
 */
public class Log implements Comparable <Log>{
    
    private String month;
    private String date;
    private String src;
    private String dst;
    private String id;
    private String spt;
    private String dpt;
    private String time;
    
    private String mdt;
    
    
    // counter 
    private int ddosCounter;
    private static int count = 0;
    private static int toStringIndexCounter = 0;
            
    public Log(String month, String date, String time, String src,
               String dst, String id, String spt, String dpt, int count){
        this.month = month;
        this.date = date;
        this.time = time;
        this.src = src;
        this.dst = dst;
        this.id = id;
        this.spt = spt;
        this.dpt = dpt;
        this.ddosCounter = count;
        
        
        //complete month date time format
        this.mdt = month + " " + date + " " + time;
       
    }
    
    public String print() {
        return (month + " " + date + " " + time + " " + src + " " + dst 
                + " " + id + " " + spt + " " + dpt );
    }
    
    // getters
    public String getIpSRC(){
        return this.src;
    }
   
    public String getTime(){
        return this.mdt;
    }
    
    public int getDDOScounter(){
        return this.ddosCounter;
    }
    
    public void updateDDOScounter(){
        this.ddosCounter += 1;
    }
    
    // keep in mind that this thing is in millisecondss
    public long getTimeDifference(String t1, String t2) throws ParseException{
        
        // Dec 16 00:00:00 format
        SimpleDateFormat format = new SimpleDateFormat("MMM dd HH:mm:ss");
        Date date1 = format.parse(t1);
        Date date2 = format.parse(t2);
        // we want seconds for this instance
        long difference = Math.abs((date2.getTime() - date1.getTime())/1000); 
        
        //System.out.println(date1 +  " " + date2 +  " "  + difference);
        
        return difference;
    }

    @Override
    public int compareTo(Log otherLog) {
        if (otherLog == null){
            throw new NullPointerException("Comparing " + this + "to null");
        } else if (!this.getClass().equals(otherLog.getClass())){
            throw new ClassCastException("Possible ClassLoader issue");
        } else if (this.ddosCounter > otherLog.getDDOScounter()){
            return -1;
        } else if (this.ddosCounter < otherLog.getDDOScounter()){
            return 1;
        }
        return 0;
    }
   
    
    
    
    
}
    


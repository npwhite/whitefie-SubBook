package com.example.subbook;

import java.util.Date;
import java.io.Serializable;


/**
 * Created by Nick on 2018-01-28.
 */

public class Subscription implements Serializable{
    private String name;            // up to 20 chars
    private Date dateStarted;       // presented in yyyy-mm-dd
    private int monthlyCharge;      // non-negative currency value, in Canadian dollars
    private String comment;         // Optional entry: up to 30 chars

    public Subscription(String name, Date dateStarted, int monthlyCharge) {
        // Constructor
        this.name = name;
        this.dateStarted = dateStarted;
        this.monthlyCharge = monthlyCharge;
        //this.comment = comment;

    }

    public Subscription(String name, Date dateStarted, int monthlyCharge, String comment) {
        this.name = name;
        this.dateStarted = dateStarted;
        this.monthlyCharge = monthlyCharge;
        this.comment = comment;
    }

    public String getName() {
        return this.name;
    }

    public Date getDateStarted() {
        return this.dateStarted;
    }

    public int getMonthlyCharge() {
        return this.monthlyCharge;
    }

    public String comment() {
        return this.comment;
    }




}

package com.example.subbook;

import java.util.Date;
import java.io.Serializable;


/**
 * Created by Nick on 2018-01-28.
 */

public class Subscription implements Serializable{
    private String name;            // up to 20 chars
    private Date dateStarted;       // presented in yyyy-mm-dd
    private double monthlyCharge;      // non-negative currency value, in Canadian dollars
    private String comment;         // Optional entry: up to 30 chars

    private int listViewPosition;
    /*
    1 or 0 --> flag which indicated if Subscription was edited when
    returning from the view subscription activity
    */
    private int wasEdited;


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

    public String toString() {
        return this.name + " " + (this.dateStarted).toString();

    }

    /* getters */
    public String getName() {
        return this.name;
    }

    public Date getDateStarted() {
        return this.dateStarted;
    }

    public double getMonthlyCharge() {
        return this.monthlyCharge;
    }

    public String getComment() {
        return this.comment;
    }

    public int getListViewPosition() {
        return listViewPosition;
    }

    public int getWasEdited() {
        return wasEdited;
    }

    /* setters */
    public void changeName(String newName) {
        this.name = newName;
    }

    public void changeDate(Date newDate) {
        this.dateStarted = newDate;
    }

    public void setMonthlyCharge(double newMonthlyCharge) {
        this.monthlyCharge = newMonthlyCharge;
    }

    public void setComment(String newComment) {
        this.comment = newComment;
    }

    public void setListViewPosition(int position) {
        this.listViewPosition = position;
    }

    public void setWasEdited(int wasEdited) {
        this.wasEdited = wasEdited;
    }
}

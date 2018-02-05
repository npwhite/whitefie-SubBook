package com.example.subbook;

import java.util.Date;
import java.io.Serializable;


/**
 * Created by Nick on 2018-01-28.
 */


/**
 * represents a subscription object
 * @see MainActivity
 * @see ViewSubscription
 * @see AddSubscription
 * @see SubscriptionRowAdapter
 */
public class Subscription implements Serializable{
    private String name;            // up to 20 chars
    private Date dateStarted;       // presented in yyyy-mm-dd
    private double monthlyCharge;   // non-negative currency value, in Canadian dollars
    private String comment;         // Optional entry: up to 30 chars

    private int listViewPosition;   /* subscriptions position in the list view */
    /*
    1 or 0 -->  flag which indicated if Subscription was edited when
                returning from the view subscription activity
    */
    private int wasEdited;


//    public Subscription(String name, Date dateStarted, Double monthlyCharge) {
//        // Constructor
//        this.name = name;
//        this.dateStarted = dateStarted;
//        this.monthlyCharge = monthlyCharge;
//        //this.comment = comment;
//
//    }

    /**
     * subscription constructor
     * @param name name of subscription
     * @param dateStarted dateStarted
     * @param monthlyCharge monthlyCharge
     * @param comment comment
     */
    public Subscription(String name, Date dateStarted, Double monthlyCharge, String comment) {
        this.name = name;
        this.dateStarted = dateStarted;
        this.monthlyCharge = monthlyCharge;
        this.comment = comment;
    }

    /**
    simple to string method
     */
    @Override
    public String toString() {
        return this.name + " " + (this.dateStarted).toString();

    }

    /* ==================================== getters ==================================== */

    /**
     * @return subscription
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the date started
     */
    public Date getDateStarted() {
        return this.dateStarted;
    }

    /**
     * @return the monthly charge
     */
    public double getMonthlyCharge() {
        return this.monthlyCharge;
    }

    /**
     * @return the optional comment
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * @return the position of the subscription in the list
     */
    public int getListViewPosition() {
        return listViewPosition;
    }

    /**
     * used in a return from the view subscription activity
     * @return 1 or 0 flag indicating if subscription was edited
     */
    public int getWasEdited() {
        return wasEdited;
    }

    /* ==================================== setters ==================================== */

    /**
     * change the subscription name
     * @param newName the new name text
     */
    public void changeName(String newName) {
        this.name = newName;
    }

    /**
     * change the subscription date
     * @param newDate the new date
     */
    public void changeDate(Date newDate) {
        this.dateStarted = newDate;
    }

    /**
     * set a new monthly charge
     * @param newMonthlyCharge the new monthly charge
     */
    public void setMonthlyCharge(double newMonthlyCharge) {
        this.monthlyCharge = newMonthlyCharge;
    }

    /**
     * set a new comment
     * @param newComment the new comment text
     */
    public void setComment(String newComment) {
        this.comment = newComment;
    }

    /**
     * update int position which keeps track of the subscription in the list
     * @param position new position
     */
    public void setListViewPosition(int position) {
        this.listViewPosition = position;
    }

    /**
     * update the was edited flag based on whether the subscription was edited or not
     * @param wasEdited 1 or 0 flag, 1 indicated subscription WAS edited
     */
    public void setWasEdited(int wasEdited) {
        this.wasEdited = wasEdited;
    }
}

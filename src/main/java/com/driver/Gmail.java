package com.driver;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    //<Message,
    Map<String,Pair> inbox;
    Map<String,Pair> trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbox = new HashMap<>();
        this.trash = new HashMap<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        if(inbox.size() < inboxCapacity) {
            inbox.put(message,new Pair(sender,date));
        }
        else {
            String oldestMessage = findOldestMessage();
            trash.put(oldestMessage, inbox.get(oldestMessage));
            inbox.remove(oldestMessage);

            inbox.put(message,new Pair(sender,date));
        }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        if(this.inbox.containsKey(message)) {
            this.trash.put(message,this.inbox.get(message));
            this.inbox.remove(message);
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

        if(inbox.isEmpty())
            return null;

        String message = "";

        for(String str : inbox.keySet()) {
            message = str;
            break;
        }
        for(String msg : inbox.keySet()) {
            if(inbox.get(message).date.compareTo(inbox.get(msg).date) < 0) {
                message = msg;
            }
        }
        return message;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.isEmpty())
            return null;

        String message = "";

        for(String str : inbox.keySet()) {
            message = str;
            break;
        }
        for(String msg : inbox.keySet()) {
            if(inbox.get(message).date.compareTo(inbox.get(msg).date) < 0) {
                message = msg;
            }
        }
        return message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

        int count = 0;
        for(String message : inbox.keySet()) {
            if(inbox.get(message).date.compareTo(start) >= 0 && inbox.get(message).date.compareTo(end) <= 0) {
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return this.inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return this.trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        this.trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}

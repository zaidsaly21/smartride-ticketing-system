package com.SmartRideSystem;

import java.util.LinkedList;
import java.util.Queue;

public class TicketQueue {
    private Queue<Ticket> queue;

    public TicketQueue() {
        queue = new LinkedList<>();
    }

    public void bookTicket(String passengerName, String gettingInPoint, String gettingOffPoint, int travelTime) {
        Ticket ticket = new Ticket(passengerName, gettingInPoint, gettingOffPoint, travelTime);
        queue.add(ticket);
    }

    public Ticket cancelTicket() {
        if (!queue.isEmpty()) {
            return ((LinkedList<Ticket>) queue).removeLast();
        }
        return null;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

}
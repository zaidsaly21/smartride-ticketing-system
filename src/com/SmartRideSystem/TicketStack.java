package com.SmartRideSystem;

import java.util.Stack;

public class TicketStack {
    private Stack<Ticket> stack;
    private static final int MAX_SIZE = 6;

    public TicketStack() {
        stack = new Stack<>();
    }

    public boolean addTicket(String passengerName, String gettingInPoint, String gettingOffPoint, int travelTime) {
        if (stack.size() >= MAX_SIZE) {
            System.out.println("Ticket stack is full. Cannot add more tickets.");
            return false;
        }
        stack.push(new Ticket(passengerName, gettingInPoint, gettingOffPoint, travelTime));
        return true;
    }

    public Ticket cancelTicket() {
        if (!stack.isEmpty()) {
            // Remove and return the most recently added ticket (top of stack)
            return stack.pop();
        }
        System.out.println("Stack is empty - no tickets to cancel");
        return null;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public Ticket searchPassenger(String passengerName) {
        for (Ticket ticket : stack) {
            if (ticket.getPassengerName().equalsIgnoreCase(passengerName)) {
                return ticket;
            }
        }
        return null;
    }

    public Stack<Ticket> getAllTickets() {
        return stack;
    }
}
package com.SmartRideSystem;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class SmartRideSystem {
    private static final String[] busStops = {"Dehiwala", "Karagampitiya", "Kalubowila", "Kohuwala", "Nugegoda"};
    private static final int[][] travelTimes = {
        {0, 10, 25, 31, 35}, // Dehiwala to others
        {10, 0, 15, 21, 25}, // Karagampitiya to others
        {25, 15, 0, 6, 10},  // Kalubowila to others
        {31, 21, 6, 0, 4},   // Kohuwala to others
        {35, 25, 10, 4, 0}   // Nugegoda to others
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketStack ticketStack = new TicketStack();
        TicketQueue ticketQueue = new TicketQueue();

        while (true) {
            System.out.println("\n1. Book Ticket");
            System.out.println("2. Search Passenger");
            System.out.println("3. Cancel Last Ticket");
            System.out.println("4. Show Sorted Passengers (By Name)");
            System.out.println("5. Show Sorted Passengers (By Travel Time)");
            System.out.println("6. Exit");

            int choice = 0;
            while (choice < 1 || choice > 6) {
                System.out.print("Enter choice (1 to 6): ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    if (choice < 1 || choice > 6) {
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 6.");
                    scanner.nextLine(); // clear invalid input
                }
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter passenger name: ");
                    String passengerName = scanner.nextLine();

                    int gettingInPoint = getUserInput(scanner, busStops.length, "Select your getting in point:");
                    int gettingOffPoint = getUserInput(scanner, busStops.length, "Select your getting off point:");

                    int travelTime = calculateTravelTime(gettingInPoint - 1, gettingOffPoint - 1);

                    if (!ticketStack.addTicket(passengerName, busStops[gettingInPoint - 1], busStops[gettingOffPoint - 1], travelTime)) {
                        System.out.println("Ticket could not be booked due to stack being full.");
                    } else {
                        ticketQueue.bookTicket(passengerName, busStops[gettingInPoint - 1], busStops[gettingOffPoint - 1], travelTime);
                        System.out.println("Ticket booked successfully.");
                    }
                    break;
                    
                case 2:
                    System.out.print("Enter passenger name to search: ");
                    String searchName = scanner.nextLine();
                    Ticket stackResult = ticketStack.searchPassenger(searchName);
                   
                    if (stackResult != null) {
                        System.out.println("Found in stack: " + stackResult);
                    }
                    break;

                case 3:
                    Ticket cancelledTicketFromStack = ticketStack.cancelTicket();
                    Ticket cancelledTicketFromQueue = ticketQueue.cancelTicket();

                    if (cancelledTicketFromStack != null && cancelledTicketFromQueue != null) {
                        System.out.println("Cancelled Last Ticket from Stack: " + cancelledTicketFromStack);
                        //System.out.println("Cancelled Last Ticket from Queue: " + cancelledTicketFromQueue);
                    } else {
                        System.out.println("No tickets to cancel.");
                    }
                    break;

                case 4:
                    System.out.println("Sorted Passengers (By Name): ");
                    List<Ticket> sortedByName = SortingAlgorithms.bubbleSortByName(new ArrayList<>(ticketStack.getAllTickets()));
                    for (Ticket ticket : sortedByName) {
                        System.out.println(ticket);
                    }
                    break;

                case 5:
                    System.out.println("Sorted Passengers (By Travel Time): ");
                    List<Ticket> sortedByTime = SortingAlgorithms.mergeSortByTravelTime(new ArrayList<>(ticketStack.getAllTickets()));
                    for (Ticket ticket : sortedByTime) {
                        System.out.println(ticket);
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
            }
        }
    }

    private static int getUserInput(Scanner scanner, int max, String message) {
        System.out.println(message);
        for (int i = 0; i < max; i++) {
            System.out.println((i + 1) + ". " + busStops[i]);
        }
        return getValidChoice(scanner, 1, max);
    }

    private static int getValidChoice(Scanner scanner, int min, int max) {
        int choice;
        while (true) {
            System.out.print("Enter choice (" + min + " to " + max + "): ");
            try {
                choice = scanner.nextInt();
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }
    }

    private static int calculateTravelTime(int gettingInIndex, int gettingOffIndex) {
        return travelTimes[gettingInIndex][gettingOffIndex];
    }
}
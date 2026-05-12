package com.SmartRideSystem;
import java.util.Scanner;

public class BusScheduleSystem {

    // Define the bus stops and their corresponding travel times between them
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
        
        System.out.println("Select your getting in point from the following bus stops:");
        for (int i = 0; i < busStops.length; i++) {
            System.out.println((i + 1) + ". " + busStops[i]);
        }

        // Get user input for getting in point
        int gettingInPoint = getUserInput(scanner, busStops.length);

        System.out.println("\nSelect your getting off point from the following bus stops:");
        for (int i = 0; i < busStops.length; i++) {
            System.out.println((i + 1) + ". " + busStops[i]);
        }

        // Get user input for getting off point
        int gettingOffPoint = getUserInput(scanner, busStops.length);

        // Calculate the travel time between the selected points
        int travelTime = calculateTravelTime(gettingInPoint - 1, gettingOffPoint - 1);

        // Display the result
        if (gettingInPoint == gettingOffPoint) {
            System.out.println("You are already at your destination.");
        } else {
            System.out.println("\nThe travel time from " + busStops[gettingInPoint - 1] + " to " +
                    busStops[gettingOffPoint - 1] + " is " + travelTime + " minutes.");
        }

        scanner.close();
    }

    // Method to get the user's input for bus stop selection
    private static int getUserInput(Scanner scanner, int max) {
        int choice;
        while (true) {
            System.out.print("Enter your choice (1 to " + max + "): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + max + ".");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); // Clear the buffer
            }
        }
    }

    // Method to calculate travel time between two bus stops
    private static int calculateTravelTime(int start, int end) {
        // Return the travel time between start and end bus stops
        return travelTimes[start][end];
    }
}

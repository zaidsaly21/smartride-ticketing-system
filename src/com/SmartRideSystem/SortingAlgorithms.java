package com.SmartRideSystem;

import java.util.List;
import java.util.ArrayList;

public class SortingAlgorithms {

    // Bubble Sort by Passenger Name
    public static List<Ticket> bubbleSortByName(List<Ticket> tickets) {
        int n = tickets.size();
        Ticket temp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (tickets.get(j).getPassengerName().compareTo(tickets.get(j + 1).getPassengerName()) > 0) {
                    temp = tickets.get(j);
                    tickets.set(j, tickets.get(j + 1));
                    tickets.set(j + 1, temp);
                }
            }
        }
        return tickets;
    }

    // Merge Sort by Travel Time
    public static List<Ticket> mergeSortByTravelTime(List<Ticket> tickets) {
        if (tickets.size() <= 1) {
            return tickets;
        }
        
        int mid = tickets.size() / 2;
        List<Ticket> left = new ArrayList<>(tickets.subList(0, mid));
        List<Ticket> right = new ArrayList<>(tickets.subList(mid, tickets.size()));
        
        left = mergeSortByTravelTime(left);
        right = mergeSortByTravelTime(right);
        
        return merge(left, right);
    }

    private static List<Ticket> merge(List<Ticket> left, List<Ticket> right) {
        List<Ticket> merged = new ArrayList<>();
        int leftIndex = 0, rightIndex = 0;
        
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).getTravelTime() <= right.get(rightIndex).getTravelTime()) {
                merged.add(left.get(leftIndex++));
            } else {
                merged.add(right.get(rightIndex++));
            }
        }
        
        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex++));
        }
        
        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex++));
        }
        
        return merged;
    }
}
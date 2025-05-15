package project2.service;

import project2.entity.Booking;
import project2.entity.Room;
import project2.entity.enums.Role;
import project2.entity.enums.RoomStatus;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import static project2.db.Datasource.*;
import static project2.tools.Util.*;

public class UserService {

    public void service() {

        while (true) {
            System.out.println("""
                    0 exit
                    1 show rooms
                    2 search room
                    3 booking room
                    4 show bookings
                    5 cancel booking
                    6 balance
                    """);

            switch (scanner.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    showRoom();
                }
                case 2 -> {
                    searchRoom();
                }
                case 3 -> {
                    bookingRoom();
                }
                case 4 -> {
                    showBookings();
                }
                case 5 -> {
                    cancelBooking();
                }
                case 6 -> {
                    balanceManagement();
                }
            }
        }


    }

    private void balanceManagement() {

        while (true) {
            System.out.println("""
                    0 exit
                    1 show balance
                    2 fill balance
                    """);
            switch (scanner.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    System.out.println("balance: " + currentUser.getBalance());
                }
                case 2 -> {
                    System.out.println("enter amount");
                    double amount = scanner.nextDouble();
                    if (amount <= 0) {
                        System.out.println("invalid amount");
                        break;
                    }
                    currentUser.setBalance(currentUser.getBalance() + amount);
                }
            }
        }
    }

    private void cancelBooking() {

        showBookings();
        System.out.println("enter booking id");
        String id = strScanner.nextLine();
        Booking booking = getBooking(id);
        if (booking == null) {
            System.out.println("booking not found");
            return;
        }

        double refundAmount = booking.getTotalPrice() * 0.7;
        currentUser.setBalance(currentUser.getBalance() + refundAmount);

        BALANCE_HOTEL -= refundAmount;

        bookings.remove(booking);
        System.out.println("cancelled successfully. 70% refunded to you, 30% remains with hotel.");

    }

    private Booking getBooking(String id) {
        for (Booking booking : bookings) {
            if (booking.getUserId().equals(currentUser.getId()) && booking.getId().equals(id)) {
                return booking;
            }
        }
        return null;
    }

    private void showBookings() {

        for (Booking booking : bookings) {
            if (booking.getUserId().equals(currentUser.getId())) {
                Room room = getRoom(booking.getRoomId());
                if (room != null) {
                    if (room.getRoomStatus().equals(RoomStatus.ACTIVE)) {
                        System.out.println(booking);
                    }
                }
            }
        }
    }


    private void bookingRoom() {
        showRoom();
        System.out.println("enter room id");
        String id = strScanner.nextLine();
        Room room = getRoom(id);
        if (room == null) {
            System.out.println("room not found");
            return;
        }
        System.out.println("enter start date (yyyy-mm-dd)");
        String startStr = strScanner.nextLine();
        System.out.println("enter end date (yyyy-mm-dd)");
        String endStr = strScanner.nextLine();

        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(startStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            endDate = LocalDate.parse(endStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return;
        }

        LocalDate today = LocalDate.now();

        if (startDate.isBefore(today)) {
            System.out.println("Start date cannot be in the past.");
            return;
        }

        if (endDate.isBefore(startDate)) {
            System.out.println("end date cannot be before start date.");
            return;
        }

        for (Booking booking : bookings) {
            if (booking.getRoomId().equals(room.getId())) {
                LocalDate existingStart = booking.getStartDayTime();
                LocalDate existingEnd = booking.getEndDayTime();

                if (!(endDate.isBefore(existingStart) || startDate.isAfter(existingEnd))) {
                    System.out.println("This room is already booked during these dates.");
                    return;
                }
            }
        }
        long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        double totalPrice = days * room.getPrice();

        if (currentUser.getBalance() < totalPrice) {
            System.out.println("you do not have enough balance to book this room.");
            return;
        }
        currentUser.setBalance(currentUser.getBalance() - totalPrice);
        BALANCE_HOTEL += totalPrice;

        Booking booking = new Booking(currentUser.getId(), room.getId(), startDate, endDate, totalPrice);
        bookings.add(booking);
        System.out.println("booking successfully!");
    }

    private Room getRoom(String id) {
        for (Room room : rooms) {
            if (room.getRoomStatus().equals(RoomStatus.ACTIVE) && room.getId().equals(id)) {
                return room;
            }
        }
        return null;
    }

    private void searchRoom() {

        System.out.println("enter capacity (max = 8)");
        int capacity = scanner.nextInt();
        if (capacity <= 0 || capacity > MAX_CAPACITY) {
            System.out.println("invalid capacity");
            return;
        }
        for (Room room : rooms) {
            if (room.getRoomStatus().equals(RoomStatus.ACTIVE) && room.getCapacity() >= capacity) {
                System.out.println(room);
            }
        }
    }

    private void showRoom() {

        for (Room room : rooms) {
            if (room.getRoomStatus().equals(RoomStatus.ACTIVE)) {
                System.out.println(room);
            }
        }
    }
}

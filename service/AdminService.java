package project2.service;

import project2.db.Datasource;
import project2.entity.Booking;
import project2.entity.Room;
import project2.entity.User;
import project2.entity.enums.RoomStatus;


import java.time.LocalDate;
import java.util.Iterator;

import static project2.db.Datasource.*;
import static project2.tools.Util.scanner;
import static project2.tools.Util.strScanner;

public class AdminService {

    public void service() {

        while (true) {

            System.out.println("""
                    0 exit
                    1 show rooms
                    2 add room
                    3 edit room
                    4 bookings
                    5 cancel bookings
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
                    addRoom();
                }
                case 3 -> {
                    editRoom();
                }
                case 4 -> {
                    bookings();
                }
                case 5 -> {
                    cancelBooking();
                }
                case 6 -> {
                    overallBalance();
                }
            }
        }
    }

    private void overallBalance() {

        System.out.println("hotel balance:" + BALANCE_HOTEL);
    }

    private void cancelBooking() {

        bookings();
        System.out.println("enter booking id");
        String id = strScanner.nextLine();
        Booking booking = getBooking(id);
        if (booking == null) {
            System.out.println("booking not found");
            return;
        }
        for (Booking booking1 : bookings) {
            if (booking1.getId().equals(booking.getId())) {
                User user = getUser(booking1.getUserId());
                if (user != null) {
                    user.setBalance(user.getBalance() + booking1.getTotalPrice());
                }
                BALANCE_HOTEL -= booking1.getTotalPrice();
            }
        }
        bookings.remove(booking);
        System.out.println("cancel booking successfully");
    }

    private Booking getBooking(String id) {
        for (Booking booking : bookings) {
            if (booking.getId().equals(id)) {
                Room room = getRoom(booking.getRoomId());
                if (room.getRoomStatus().equals(RoomStatus.ACTIVE)) {
                    return booking;
                }

            }
        }
        return null;
    }

    private void bookings() {

        for (Booking booking : bookings) {
            Room room = getRoom(booking.getRoomId());
            if (room != null) {
                if (room.getRoomStatus().equals(RoomStatus.ACTIVE)) {
                    System.out.println(booking);
                }
            }
        }
    }

    private void editRoom() {

        showRoom();
        System.out.println("enter room id");
        String id = strScanner.nextLine();
        Room room = getRoom(id);
        if (room == null) {
            System.out.println("room not found");
            return;
        }

        while (true) {
            System.out.println("""
                    0 exit
                    1 edit room status
                    2 edit room price
                    3 edit room capacity
                    """);
            switch (scanner.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    System.out.println("enter status");
                    String status = strScanner.nextLine();

                    if (status.equals("INACTIVE")) {
                        for (Booking booking : bookings) {
                            if (booking.getRoomId().equals(room.getId())) {
                                User user = getUser(booking.getUserId());
                                if (user != null) {
                                    user.setBalance(user.getBalance() + booking.getTotalPrice());
                                    BALANCE_HOTEL -= booking.getTotalPrice();
                                }
                            }
                        }

                        for (int i = 0; i < bookings.size(); i++) {
                            Booking booking = bookings.get(i);
                            if (booking.getRoomId().equals(room.getId())) {
                                bookings.remove(booking);
                            }
                        }

                        room.setRoomStatus(RoomStatus.valueOf(status));
                    } else {
                        room.setRoomStatus(RoomStatus.valueOf(status));
                    }
                }
                case 2 -> {
                    System.out.println("enter new room price max("+MAX_PRICE+")");
                    int price = scanner.nextInt();
                    if (price <= 0 || price > MAX_PRICE) {
                        System.out.println("invalid price");
                        break;
                    }
                    room.setPrice(price);
                }
                case 3 -> {

                    System.out.println("enter new room capacity max("+MAX_CAPACITY+")");
                    int capacity = scanner.nextInt();
                    if (capacity <= 0 || capacity > MAX_CAPACITY) {
                        System.out.println("invalid capacity");
                        break;
                    }
                    room.setCapacity(capacity);

                }
            }
        }


    }

    private User getUser(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    private Room getRoom(String id) {

        for (Room room : rooms) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        return null;
    }

    private void addRoom() {

        System.out.println("enter room number max("+MAX_ROOM_NUMBER+")");
        int roomNumber = scanner.nextInt();
        if (roomNumber < 0 || roomNumber > MAX_ROOM_NUMBER) {
            System.out.println(" invalid room number");
            return;
        }
        System.out.println("enter floor max("+MAX_FLOOR+")");
        int floor = scanner.nextInt();
        if (floor <= 0 || floor > MAX_FLOOR) {
            System.out.println("invalid floor");
            return;
        }
        System.out.println("enter room capacity max("+MAX_CAPACITY+")");
        int capacity = scanner.nextInt();
        if (capacity <= 0 || capacity > MAX_CAPACITY) {
            System.out.println("invalid capacity");
            return;
        }
        System.out.println("enter room price max("+MAX_PRICE+")");
        double price = scanner.nextDouble();
        if (price <= 0 || price > MAX_PRICE) {
            System.out.println("invalid price");
            return;
        }
        Room room = new Room(roomNumber, floor, capacity, price);
        rooms.add(room);
        System.out.println("add room successfully");

    }

    private void showRoom() {

        for (Room room : rooms) {
            System.out.println(room);
        }
    }
}

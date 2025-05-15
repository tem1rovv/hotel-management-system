package project2.db;

import project2.entity.Booking;
import project2.entity.Room;
import project2.entity.User;
import project2.entity.enums.Role;
import project2.entity.enums.RoomStatus;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    public static User currentUser;
    public static List<User> users = new ArrayList<>();
    public static List<Room> rooms = new ArrayList<>();
    public static List<Booking> bookings = new ArrayList<>();
    public static final int MAX_FLOOR = 15;
    public static final int MAX_CAPACITY = 8;
    public static final int MAX_ROOM_NUMBER = 500;
    public static double MAX_PRICE = 1000000;
    public static double BALANCE_HOTEL = 0;

    static {

        User user = new User("admin","admin","admin","admin", Role.ADMIN);
        User user1 = new User("a","a","a","a", Role.USER);
        users.add(user);
        users.add(user1);
        Room room = new Room(2,3,2,150000.0);
        Room room1 = new Room(4,5,5,180000.0);
        Room room2 = new Room(2,3,4,200000.0);
        rooms.add(room);
        rooms.add(room1);
        rooms.add(room2);


    }

}

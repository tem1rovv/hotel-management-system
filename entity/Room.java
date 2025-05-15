package project2.entity;

import project2.entity.enums.RoomStatus;

import java.time.LocalDate;

import java.util.UUID;

public class Room {
    private final String id = UUID.randomUUID().toString();
    private int roomNumber;
    private int floor;
    private int capacity;
    private double price;

    private RoomStatus roomStatus = RoomStatus.ACTIVE;

    public Room(int roomNumber, int floor, int capacity, double price) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.capacity = capacity;
        this.price = price;

    }

    public String getId() {
        return id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", roomNumber=" + roomNumber +
                ", floor=" + floor +
                ", capacity=" + capacity +
                ", price=" + price +
                ", roomStatus=" + roomStatus +
                '}';
    }
}

package project2.entity;

import project2.entity.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.UUID;

public class Booking {
    private final String id = UUID.randomUUID().toString();
    private String userId;
    private String roomId;
    private LocalDate startDayTime;
    private LocalDate endDayTime;
    private double totalPrice;

    public Booking(String userId, String roomId, LocalDate startDayTime, LocalDate endDayTime, double totalPrice) {
        this.userId = userId;
        this.roomId = roomId;
        this.startDayTime = startDayTime;
        this.endDayTime = endDayTime;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public LocalDate getStartDayTime() {
        return startDayTime;
    }

    public void setStartDayTime(LocalDate startDayTime) {
        this.startDayTime = startDayTime;
    }

    public LocalDate getEndDayTime() {
        return endDayTime;
    }

    public void setEndDayTime(LocalDate endDayTime) {
        this.endDayTime = endDayTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", roomId='" + roomId + '\'' +
                ", startDayTime=" + startDayTime +
                ", endDayTime=" + endDayTime +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

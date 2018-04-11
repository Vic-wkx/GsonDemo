package com.example.gsonstudy;

public class Classroom {
    private String number;
    private String house;
    private Message message;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "number = "+number+
                "\nhouse = "+house+
                "\nmessage = "+message.toString();
    }
}

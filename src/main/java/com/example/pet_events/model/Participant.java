package com.example.pet_events.model;

public class Participant {
    private String id;
    private String name;
    private String petName;
    private String petType;
    private int petAge;

    public Participant(String id, String name, String petName, String petType, int petAge) {
        this.id = id;
        this.name = name;
        this.petName = petName;
        this.petType = petType;
        this.petAge = petAge;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public int getPetAge() {
        return petAge;
    }

    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }
}

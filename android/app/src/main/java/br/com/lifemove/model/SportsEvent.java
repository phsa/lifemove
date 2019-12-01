package br.com.lifemove.model;

import br.com.lifemove.model.enums.SportCategory;

public class SportsEvent {

    private String title;
    private String description;
    private long startTime;
    private long endTime;
    private double locationLongitude;
    private double locationLatitude;
    private SportCategory category;

    public SportsEvent(String title, String description, long startTime, long endTime, double locationLongitude, double locationLatitude, SportCategory category) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.locationLongitude = locationLongitude;
        this.locationLatitude = locationLatitude;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(int locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(int locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public SportCategory getCategory() {
        return category;
    }

    public void setCategory(SportCategory category) {
        this.category = category;
    }
}

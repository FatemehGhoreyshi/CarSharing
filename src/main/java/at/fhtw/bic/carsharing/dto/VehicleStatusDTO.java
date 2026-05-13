package at.fhtw.bic.carsharing.dto;

public class VehicleStatusDTO {

    private double latitude;
    private double longitude;

    private long timestamp;

    private boolean occupied;

    private String occupiedByDriver;

    private double distanceTravelled;

    private long durationSeconds;

    public VehicleStatusDTO() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public String getOccupiedByDriver() {
        return occupiedByDriver;
    }

    public void setOccupiedByDriver(String occupiedByDriver) {
        this.occupiedByDriver = occupiedByDriver;
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public long getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(long durationSeconds) {
        this.durationSeconds = durationSeconds;
    }
}
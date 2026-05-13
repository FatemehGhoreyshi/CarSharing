package at.fhtw.bic.carsharing.dto;

public class EmergencyDTO {

    private String occupiedByDriver;

    private String priority;

    private String description;

    public EmergencyDTO() {
    }

    public String getOccupiedByDriver() {
        return occupiedByDriver;
    }

    public void setOccupiedByDriver(String occupiedByDriver) {
        this.occupiedByDriver = occupiedByDriver;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
package LibraryManagementSystem.entity;

public class Rack {
    private final String rackId;
    private final String locationNumber;
    private final String aisle;

    public Rack(String rackId, String locationNumber, String aisle) {
        this.rackId = rackId;
        this.locationNumber = locationNumber;
        this.aisle = aisle;
    }

    public String getRackId() {
        return rackId;
    }

    public String getLocationNumber() {
        return locationNumber;
    }

    public String getAisle() {
        return aisle;
    }
}

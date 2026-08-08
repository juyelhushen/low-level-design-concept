package hotet_managment_system.design.builder;

import hotet_managment_system.entity.DateRange;
import hotet_managment_system.enums.AmenityType;
import hotet_managment_system.enums.RoomType;

import java.util.Collections;
import java.util.List;

public class SearchFilter {
    // required — must be present to make any meaningful search
    private final String location;
    private final DateRange dateRange;
    private final int guests;

    // optional — absent means "no constraint"
    private final RoomType roomType;
    private final Double maxPricePerNight;
    private final List<AmenityType> requiredAmenities;

    public SearchFilter(Builder builder) {
        this.location = builder.location;
        this.dateRange = builder.dateRange;
        this.guests = builder.guests;
        this.roomType = builder.roomType;
        this.maxPricePerNight = builder.maxPricePerNight;
        this.requiredAmenities = Collections.unmodifiableList(builder.requiredAmenities);
    }

    public String getLocation() {
        return location;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public int getGuests() {
        return guests;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public Double getMaxPricePerNight() {
        return maxPricePerNight;
    }

    public List<AmenityType> getRequiredAmenities() {
        return requiredAmenities;
    }


    public static class Builder {
        private final String location;
        private final DateRange dateRange;
        private final int guests;

        private RoomType roomType;
        private Double maxPricePerNight;
        private List<AmenityType> requiredAmenities;

        public Builder(String location, DateRange dateRange, int guests) {
            this.location = location;
            this.dateRange = dateRange;
            this.guests = guests;
        }

        public Builder roomType(RoomType type) {
            this.roomType = roomType;
            return this;
        }

        public Builder maxPricePerNight(double amount) {
            this.maxPricePerNight = amount;
            return this;
        }

        public Builder amenity(AmenityType type) {
            this.requiredAmenities.add(type);
            return this;
        }

        public SearchFilter build() {
            return new SearchFilter(this);
        }
    }
}

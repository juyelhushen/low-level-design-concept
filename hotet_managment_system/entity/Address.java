package hotet_managment_system.entity;


// Record: immutable, auto-generates equals/hashCode/toString.
// A hotel's address is pure data — no behaviour, no identity beyond its fields.
public record Address(
        String street, String city, String state,
        String country, String pincode) {
}

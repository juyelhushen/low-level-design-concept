package online_shopping_cart.entity;

public record Address(
        String street,
        String area,
        String state,
        String country,
        String pincode
) {
}

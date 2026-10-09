package order.storage.model;

import lombok.Getter;

@Getter
public abstract class Product {
    private final String id;
    private final String name;
    private final double price;

    protected Product(String id, String name, double price) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Product ID cannot be empty");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        if (!Double.isFinite(price) || price < 0) {
            throw new IllegalArgumentException("Invalid product price");
        }

        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "%s (ID: %s, price: %.2f)",
                name, id, price
        );
    }
}

package order.storage.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Clothing extends Product {
    private final String size;
    private final String material;

    @Builder
    public Clothing(
            String id,
            String name,
            double price,
            String size,
            String material
    ) {
        super(id, name, price);

        if (size == null || size.isBlank()) {
            throw new IllegalArgumentException("Size cannot be empty");
        }

        if (material == null || material.isBlank()) {
            throw new IllegalArgumentException("Material cannot be empty");
        }

        this.size = size;
        this.material = material;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", size: " + size
                + ", material: " + material;
    }
}

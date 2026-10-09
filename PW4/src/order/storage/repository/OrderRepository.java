package order.storage.repository;

import order.storage.model.Product;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class OrderRepository<T extends Product> {
    private final List<T> products = new CopyOnWriteArrayList<>();

    public void save(T product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        products.add(product);
    }

    public List<T> findAll() {
        return List.copyOf(products);
    }

    public int size() {
        return products.size();
    }
}
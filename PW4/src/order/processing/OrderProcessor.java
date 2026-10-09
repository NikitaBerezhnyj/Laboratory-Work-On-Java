package order.processing;

import order.storage.model.Product;

import java.util.function.Consumer;

public class OrderProcessor<T extends Product> {
    private final Consumer<T> processingAction;

    public OrderProcessor(Consumer<T> processingAction) {
        if (processingAction == null) {
            throw new IllegalArgumentException(
                    "Processing action cannot be null"
            );
        }

        this.processingAction = processingAction;
    }

    public void processOrder(T product)
            throws OrderProcessingException {
        if (product == null) {
            throw new OrderProcessingException(
                    "Cannot process a null product"
            );
        }

        try {
            processingAction.accept(product);
        } catch (RuntimeException exception) {
            throw new OrderProcessingException(
                    "Failed to process product: " + product.getName(),
                    exception
            );
        }
    }
}
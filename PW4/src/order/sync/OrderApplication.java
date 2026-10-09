package order.sync;

import order.processing.OrderProcessor;
import order.processing.OrderProcessingException;
import order.storage.model.Clothing;
import order.storage.model.Electronics;
import order.storage.model.Product;
import order.storage.repository.OrderRepository;
import com.github.javafaker.Faker;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderApplication {

    public static void main(String[] args) {
        Faker faker = new Faker();

        OrderRepository<Electronics> electronicsRepository =
                new OrderRepository<>();

        OrderRepository<Clothing> clothingRepository =
                new OrderRepository<>();

        for (int i = 1; i <= 5; i++) {
            Electronics electronics = Electronics.builder()
                    .id("E" + i)
                    .name(faker.commerce().productName())
                    .price(faker.number().numberBetween(500, 50000))
                    .brand(faker.company().name())
                    .warrantyMonths(
                            faker.number().numberBetween(6, 36)
                    )
                    .build();

            Clothing clothing = Clothing.builder()
                    .id("C" + i)
                    .name(faker.commerce().productName())
                    .price(faker.number().numberBetween(200, 5000))
                    .size(faker.options().option(
                            "S", "M", "L", "XL"
                    ))
                    .material(faker.options().option(
                            "Cotton", "Polyester", "Wool"
                    ))
                    .build();

            electronicsRepository.save(electronics);
            clothingRepository.save(clothing);
        }

        List<Product> orders = Stream.concat(
                electronicsRepository.findAll().stream(),
                clothingRepository.findAll().stream()
        ).collect(Collectors.toList());

        List<Electronics> electronicsOrders = orders.stream()
                .filter(Electronics.class::isInstance)
                .map(Electronics.class::cast)
                .collect(Collectors.toList());

        List<Clothing> clothingOrders = orders.stream()
                .filter(Clothing.class::isInstance)
                .map(Clothing.class::cast)
                .collect(Collectors.toList());

        System.out.println("=== Generated orders ===");
        orders.forEach(System.out::println);

        System.out.println("\nElectronics orders: "
                + electronicsOrders.size());
        System.out.println("Clothing orders: "
                + clothingOrders.size());

        OrderProcessor<Product> processor = new OrderProcessor<>(
                product -> System.out.println(
                        "Processing " + product.getName()
                                + " in thread "
                                + Thread.currentThread().getName()
                )
        );

        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            for (Product product : orders) {
                executor.submit(() -> {
                    try {
                        processor.processOrder(product);
                    } catch (OrderProcessingException exception) {
                        System.err.println(
                                exception.getMessage()
                        );
                    }
                });
            }
        } finally {
            executor.shutdown();

            try {
                if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException exception) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();

                System.err.println(
                        "Processing interrupted: "
                                + exception.getMessage()
                );
            }
        }

        System.out.println("\nAll order tasks have been submitted.");
    }
}
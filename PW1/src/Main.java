import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном…", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        List<Product> products = new ArrayList<>();

        products.add(product1);
        products.add(product2);
        products.add(product3);

        Cart cart = new Cart();

        List<Order> orders = new ArrayList<>();

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Пошук товару");
            System.out.println("3 - Додати товар до кошика");
            System.out.println("4 - Переглянути кошик");
            System.out.println("5 - Видалити товар з кошика");
            System.out.println("6 - Зробити замовлення");
            System.out.println("7 - Переглянути історію замовлень");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(product1);
                    System.out.println(product2);
                    System.out.println(product3);
                    break;

                case 2:
                    System.out.println("Введіть назву або категорію товару:");
                    String search = scanner.next().toLowerCase();

                    if (product1.getName().toLowerCase().contains(search) ||
                            product1.getCategory().getName().toLowerCase().contains(search)) {
                        System.out.println(product1);
                    }

                    if (product2.getName().toLowerCase().contains(search) ||
                            product2.getCategory().getName().toLowerCase().contains(search)) {
                        System.out.println(product2);
                    }

                    if (product3.getName().toLowerCase().contains(search) ||
                            product3.getCategory().getName().toLowerCase().contains(search)) {
                        System.out.println(product3);
                    }

                    System.out.println("Товар не знайдено");
                    break;

                case 3:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int id = scanner.nextInt();

                    if (id == 1) cart.addProduct(product1);
                    else if (id == 2) cart.addProduct(product2);
                    else if (id == 3) cart.addProduct(product3);
                    else System.out.println("Товар з таким ID не знайдено");
                    break;

                case 4:
                    System.out.println(cart);
                    break;

                case 5:
                    System.out.println("Введіть ID товару для видалення з кошика:");
                    int removeId  = scanner.nextInt();

                    if (removeId == 1) cart.removeProduct(product1);
                    else if (removeId == 2) cart.removeProduct(product2);
                    else if (removeId == 3) cart.removeProduct(product3);
                    else System.out.println("Товар з таким ID не знайдено");
                    break;

                case 6:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orders.add(order);
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;

                case 7:
                    for (Order order : orders) {
                        System.out.println(order);
                    }
                    break;

                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;

                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}
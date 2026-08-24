package inventory_update_service;

import inventory_update_service.entity.Product;
import inventory_update_service.entity.User;
import inventory_update_service.enums.EventType;
import inventory_update_service.enums.NotificationChannel;
import inventory_update_service.observer.NotificationDispatcher;
import inventory_update_service.repository.*;
import inventory_update_service.service.InventoryService;
import inventory_update_service.service.NotificationService;
import inventory_update_service.service.SubscriptionService;

import java.util.EnumSet;

public class Demo {

    public static void main(String[] args) {
        // --- Wire up ---
        ProductRepository productRepo      = new InMemoryProductRepository();
        UserRepository         userRepo         = new InMemoryUserRepository();
        SubscriptionRepository subscriptionRepo = new InMemorySubscriptionRepository();
        NotificationLogRepository logRepo       = new InMemoryNotificationLogRepository();

        NotificationService notificationService = new NotificationService(logRepo);
        NotificationDispatcher dispatcher       = new NotificationDispatcher(
                subscriptionRepo, userRepo, notificationService);

        InventoryService inventoryService    = new InventoryService(productRepo);
        SubscriptionService subscriptionService = new SubscriptionService(subscriptionRepo);

        inventoryService.registerObserver(dispatcher);

        // --- Users with different channel preferences ---
        User alice = new User("Alice",
                "alice@email.com", "+91-9990001111", "device-token-alice",
                EnumSet.of(NotificationChannel.EMAIL, NotificationChannel.PUSH));

        User bob = new User("Bob",
                "bob@email.com", "+91-9990002222", "device-token-bob",
                EnumSet.of(NotificationChannel.SMS, NotificationChannel.IN_APP));

        userRepo.save(alice);
        userRepo.save(bob);

        // --- Products ---
        Product iphone = new Product("iPhone 15 Pro", "Electronics", 134900.0, 0); // starts OUT OF STOCK
        Product airpods = new Product(
                "AirPods Pro", "Electronics", 26900.0, 50); // in stock

        inventoryService.addProduct(iphone);
        inventoryService.addProduct(airpods);

        // --- Subscriptions ---
        // Alice: wants BACK_IN_STOCK and PRICE_DROP for iPhone via her preferred channels
        subscriptionService.subscribe(alice.getUserId(), iphone.getProductId(),
                EnumSet.of(EventType.BACK_IN_STOCK, EventType.PRICE_DROP));

        // Bob: wants only BACK_IN_STOCK for iPhone, but wants SMS + IN_APP specifically
        subscriptionService.subscribe(bob.getUserId(), iphone.getProductId(),
                EnumSet.of(EventType.BACK_IN_STOCK),
                EnumSet.of(NotificationChannel.SMS, NotificationChannel.IN_APP));

        // Alice also watches AirPods for LOW_STOCK (she's buying as a gift)
        subscriptionService.subscribe(alice.getUserId(), airpods.getProductId(),
                EnumSet.of(EventType.LOW_STOCK));

        // === Scenario 1: iPhone comes back in stock ===
        System.out.println("\n======= iPhone stock updated: 0 → 25 =======");
        inventoryService.updateStock(iphone.getProductId(), 25);
        // Expected: Alice gets EMAIL + PUSH, Bob gets SMS + IN_APP

        // === Scenario 2: iPhone price drops ===
        System.out.println("\n======= iPhone price drops: 134900 → 119900 =======");
        inventoryService.updatePrice(iphone.getProductId(), 119900.0);
        // Expected: Alice gets EMAIL + PUSH (subscribed to PRICE_DROP), Bob gets nothing (not subscribed)

        // === Scenario 3: AirPods stock drops to low threshold ===
        System.out.println("\n======= AirPods stock updated: 50 → 3 =======");
        inventoryService.updateStock(airpods.getProductId(), 3);
        // Expected: Alice gets LOW_STOCK notification via EMAIL + PUSH

        // === Scenario 4: AirPods go out of stock (50→0 in one step) ===
        System.out.println("\n======= AirPods stock updated: 3 → 0 =======");
        inventoryService.updateStock(airpods.getProductId(), 0);
        // Expected: nobody subscribed to OUT_OF_STOCK for AirPods — no notifications

        // === Scenario 5: Neutral stock change — no event fired ===
        System.out.println("\n======= iPhone stock updated: 25 → 10 (no threshold crossed) =======");
        inventoryService.updateStock(iphone.getProductId(), 10);
        // Expected: no event — 25→10 doesn't cross any transition boundary

        // === Scenario 6: iPhone goes out of stock ===
        System.out.println("\n======= iPhone stock updated: 10 → 0 =======");
        inventoryService.updateStock(iphone.getProductId(), 0);
        // Expected: nobody subscribed to OUT_OF_STOCK for iPhone — no notifications
        // (good demo point: subscriptions are event-type specific)
    }
}

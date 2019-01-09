package com.alpha;

import com.alpha.decorations.*;

import com.alpha.delivery.*;
import com.alpha.enums.ShowFilter;
import com.alpha.enums.WrapperType;
import com.alpha.payment.PaymentMethod;
import com.alpha.payment.PaymentMethodFactory;
import com.alpha.plants.*;
import com.alpha.random.StoreRandomizer;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class StoreForUser {
    Scanner scanner = new Scanner(System.in);
    private Store store;

    public StoreForUser(Store store) {
        this.store = store;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store store = new StoreRandomizer(47).nextStore(10, 10, 10);/*new Store(plants,flowerDecorations,"Ukraine");*/
        Customer customer = new Customer("Don", "Vova", 1,
                new ArrayList<PaymentMethod>() {{
                    add(PaymentMethodFactory.getPaymentMethod("Debit", 2000, "Don", "Vova", "213", "21/2027"));
                }}
        );
        store.getCustomers().add(customer);
        StoreForUser storeForUser = new StoreForUser(store);

        while (true) {
            storeForUser.help();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {

                    case 1:
                        storeForUser.buyPlant(Flower.class, customer);
                        break;
                    case 2:
                        storeForUser.buyPlant(Tree.class, customer);
                        break;
                    case 3:
                        storeForUser.buyReadyFlowerComposition(customer);
                        break;
                    case 4:
                        storeForUser.createAndBuyFlowerComposition(customer);
                        break;
                    case 5:
                        System.out.println(storeForUser.getStore().getFlowers(ShowFilter.ALL));
                        break;
                    case 6:
                        System.out.println(storeForUser.getStore().getTrees(ShowFilter.ALL));
                        break;
                    case 7:
                        System.out.println(storeForUser.getStore().getReadyFlowerCompositions());
                        break;
                    case 8:
                        System.out.println(customer.getPaymentMethods());
                        break;
                    case 9:
                        System.out.println(customer.getBoughtProducts());
                        break;
                    default:
                        System.out.println("Wrong command");
                        break;
                }
                System.out.println("Do you want to continue 1 - yes, 2 - no?");
                if (storeForUser.readNumberInBounds(1, 2) == 2) {
                    return;
                }
                storeForUser.scanner.nextLine();
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }

    public Store getStore() {
        return store;
    }

    public void help() {
        System.out.println("What do you want to buy ?" +
                "\n1  - Buy flower" +
                "\n2  - Buy tree" +
                "\n3  - Buy ready flower composition" +
                "\n4  - Create and buy bouquet" +
                "\n5  - Show all available flowers" +
                "\n6  - Show all available trees" +
                "\n7  - Show all available flower bouquets" +
                "\n8 - Show my balance" +
                "\n9 - Show bought products"
        );
    }


    private int readNumberInBounds(int from, int to) {
        int choice;
        do {
            choice = scanner.nextInt();
            if (!(choice < from || choice > to)) {
                return choice;
            }
            System.out.println("Enter correct answer");

        } while (true);
    }

    private Delivery chooseDeliveryMethod() {
        int i = 1;
        for (String deliveryMethod : DeliveryFactory.getDeliveryMethods()) {
            System.out.println(i++ + ". " + DeliveryFactory.getDelivery(deliveryMethod));
        }
        return DeliveryFactory.getDelivery(DeliveryFactory.getDeliveryMethods()[readNumberInBounds(1, DeliveryFactory.getDeliveryMethods().length) - 1]);
    }

    private PaymentMethod choosePaymentMethod(Customer customer) {
        int i = 1;
        for (PaymentMethod paymentMethod : customer.getPaymentMethods()) {
            System.out.println(i++ + ". " + paymentMethod);
        }
        return customer.getPaymentMethods().get(readNumberInBounds(1, customer.getPaymentMethods().size()) - 1);
    }

    private WrapperType chooseWrapperType() {
        int i = 1;
        for (WrapperType wrapperType : WrapperType.values()) {
            System.out.println(i++ + ". " + wrapperType);
        }
        return WrapperType.values()[readNumberInBounds(1, WrapperType.values().length) - 1];
    }

    public void buyPlant(Class<? extends Plant> plantClass, Customer customer) {
        if (store.getPlants(ShowFilter.ALL).isEmpty()) {
            System.out.println("There\'s no  available plants, try again later");
            return;
        }
        System.out.println("What type of " + plantClass.getSimpleName().toLowerCase() + " do you want: \n\t1 - All\n\t2 - Native\n\t3 - Oversea");
        ShowFilter choiceEn = ShowFilter.values()[readNumberInBounds(1, 3) - 1];
        List<Plant> plantsOfChosentType = new ArrayList<>();
        if (plantClass.equals(Flower.class)) {
            plantsOfChosentType = store.getFlowers(choiceEn).stream().map((a) -> (Plant) a).collect(Collectors.toList());

        }
        if (plantClass.equals(Tree.class)) {
            plantsOfChosentType = store.getTrees(choiceEn).stream().map((a) -> (Plant) a).collect(Collectors.toList());
        }
        if (plantsOfChosentType.isEmpty()) {
            System.out.println("There\'s no available such " + plantClass.getSimpleName().toLowerCase());
            return;
        }
        System.out.println("Choose" + plantClass.getSimpleName().toLowerCase());

        int i = 1;
        for (Plant plant : plantsOfChosentType) {
            System.out.println(i++ + ". " + plant);
        }
        store.buy(plantsOfChosentType.get(readNumberInBounds(1, i - 1) - 1), customer, chooseDeliveryMethod(), choosePaymentMethod(customer));

    }

    public void buyReadyFlowerComposition(Customer customer) {
        if (store.getReadyFlowerCompositions().isEmpty()) {
            System.out.println("There\'s no  available decorations, try again later");
            return;
        }
        List<FlowerCompositionDecorator> flowerDecorationsOfChosenType = store.getReadyFlowerCompositions();

        int i = 1;
        for (FlowerCompositionDecorator flowerDecoration : flowerDecorationsOfChosenType) {
            System.out.println(i++ + ". " + flowerDecoration);
        }

        store.buy(flowerDecorationsOfChosenType.get(readNumberInBounds(1, i - 1) - 1), customer, chooseDeliveryMethod(), choosePaymentMethod(customer));

    }


    FlowerCompositionDecorator createFlowerComposition() {
        List<Flower> availableFlowers = store.getFlowers(ShowFilter.ALL);
        List<Flower> chosenFlowers = new ArrayList<>();
        if (availableFlowers.isEmpty()) {
            System.out.println("There\'s no  available plants, try again later");
            return null;
        }
        int i = 1;
        for (Flower flower : availableFlowers) {
            System.out.println(i++ + ". " + flower);
        }
        Pattern pattern = Pattern.compile("(?<index>\\d+)");
        Matcher matcher = pattern.matcher(scanner.nextLine());
        while (matcher.find()) {
            Flower flower = availableFlowers.get(Integer.parseInt(matcher.group("index")) - 1);
            chosenFlowers.add(flower);
        }
        System.out.println("Do you want to  put flowers into 1 - wrapper or 2 - pot" + Pot.potPrice + "?");
        FlowerCompositionDecorator priceable;
        if (readNumberInBounds(1, 2) == 1) {
            priceable = new Wrapper(new FlowerComposition(chosenFlowers), chooseWrapperType());
            System.out.println("Do you want to add tape " + Tape.tapePrice + "?  1 - yes, 2 - no");
            if (readNumberInBounds(1, 2) == 1) {
                priceable = new Tape(priceable);
            }
        } else {
            priceable = new Pot(new FlowerComposition(chosenFlowers));
        }
        return priceable;
    }

    void createAndBuyFlowerComposition(Customer customer) {
        try {
            FlowerCompositionDecorator flowerBouquet = createFlowerComposition();
            if (flowerBouquet == null) {
                return;
            }
            store.buyFlowerComposition(flowerBouquet, customer, chooseDeliveryMethod(), choosePaymentMethod(customer));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You entered some wrong indexes, try again!");
        }
    }


}

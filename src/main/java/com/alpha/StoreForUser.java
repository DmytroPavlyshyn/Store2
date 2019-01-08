package com.alpha;

import com.alpha.decorations.*;

import com.alpha.delivery.*;
import com.alpha.enums.ShowFilter;
import com.alpha.enums.WrapperType;
import com.alpha.payment.DebitCard;
import com.alpha.payment.PaymentMethod;
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

        Store store = new StoreRandomizer(47).nextStore(5, 5, 5);/*new Store(plants,flowerDecorations,"Ukraine");*/
        Customer customer = new Customer("Don", "Vova", 1000, 1,
                new ArrayList<PaymentMethod>(){{
                    add(new DebitCard(124, "Don", "Vova", "213", "21/2027"));
                }}
        );
        store.getCustomers().add(customer);
        StoreForUser storeForUser = new StoreForUser(store);

        while (true) {
            storeForUser.help();
            try {
                switch (new Scanner(System.in).nextInt()) {
                    case 1:
                        storeForUser.buyPlant(Flower.class, customer);
                        break;
                    case 2:
                        storeForUser.buyPlant(Tree.class, customer);
                        break;
                    case 3:
                        storeForUser.buyReadyFlowerDecoration(customer);
                        break;
                    case 4:
                        storeForUser.buyReadyFlowerDecoration(customer);
                        break;
                    case 5:
                        storeForUser.createAndBuyBouquet(customer);
                        break;
                    case 6:

                        break;
                    case 7:
                        System.out.println(storeForUser.getStore().getFlowers(ShowFilter.ALL));
                        break;
                    case 8:
                        System.out.println(storeForUser.getStore().getTrees(ShowFilter.ALL));
                        break;
                    case 9:
                        System.out.println(storeForUser.getStore().getReadyFlowerDecorations());
                        break;
                    case 10:

                        break;
                    case 11:
                        System.out.println(customer.getPaymentMethods());
                        break;
                    case 12:
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
                "\n3  - Buy ready flower bouquet" +
                "\n4  - Buy ready flower pot" +
                "\n5  - Create and buy bouquet" +
                "\n6  - Create and buy pot" +
                "\n7  - Show all available flowers" +
                "\n8  - Show all available trees" +
                "\n9  - Show all available flower bouquets" +
                "\n10 - Show all available flower pots" +
                "\n11 - Show my balance" +
                "\n12 - Show bought products"
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
            System.out.println(i++ + ". " + deliveryMethod);
        }
        return DeliveryFactory.getDelivery(DeliveryFactory.getDeliveryMethods()[readNumberInBounds(1, DeliveryFactory.getDeliveryMethods().length) - 1]);
    }

    private PaymentMethod chooseBuyMethod(Customer customer) {
        int i = 1;
        for (PaymentMethod paymentMethod : customer.getPaymentMethods()) {
            System.out.println(i++ + ". " + paymentMethod);
        }
        return customer.getPaymentMethods().get(readNumberInBounds(1, customer.getPaymentMethods().size()) - 1);
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


//        store.buyPlant(plantsOfChosentType.get(readNumberInBounds(1, i - 1) - 1), customer, chooseDeliveryMethod(), chooseBuyMethod());
        store.buy(plantsOfChosentType.get(readNumberInBounds(1, i - 1) - 1), customer, chooseDeliveryMethod(), chooseBuyMethod(customer));

    }

    public void buyReadyFlowerDecoration(Customer customer) {
        if (store.getReadyFlowerDecorations().isEmpty()) {
            System.out.println("There\'s no  available decorations, try again later");
            return;
        }

        List<FlowerCompositionDecorator> flowerDecorationsOfChosenType = store.getReadyFlowerDecorations();


        int i = 1;
        for (FlowerCompositionDecorator flowerDecoration : flowerDecorationsOfChosenType) {
            System.out.println(i++ + ". " + flowerDecoration);
        }

        store.buy(flowerDecorationsOfChosenType.get(readNumberInBounds(1, i - 1) - 1), customer, chooseDeliveryMethod(), chooseBuyMethod(customer));

    }

    FlowerCompositionDecorator createBouquet() {
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
        i = 1;
        for (WrapperType wrapperType : WrapperType.values()) {
            System.out.println(i++ + ". " + wrapperType);
        }

        WrapperType chosenWrapperType = WrapperType.values()[readNumberInBounds(1, WrapperType.values().length) - 1];
        FlowerCompositionDecorator priceable = new Wrapper(new FlowerComposition(chosenFlowers), chosenWrapperType);
        System.out.println("Do you want tape 1- yes, 0 - no");
        if (readNumberInBounds(0, 1) == 1) {
            priceable = new Tape(priceable);
        }

        return priceable;
    }

    void createAndBuyBouquet(Customer customer) {
        try {
            Priceable flowerBouquet = createBouquet();
            if (flowerBouquet == null) {
                return;
            }
            store.buyFlowerBouquet(((FlowerCompositionDecorator) flowerBouquet).getFlowerComposition().getFlowers(), customer, chooseDeliveryMethod(), chooseBuyMethod(customer));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You entered some wrong indexes, try again!");
        }
    }


}

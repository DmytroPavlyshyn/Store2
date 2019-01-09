package com.alpha;


import com.alpha.decorations.FlowerComposition;
import com.alpha.decorations.FlowerCompositionDecorator;

import com.alpha.decorations.Priceable;
import com.alpha.delivery.Delivery;
import com.alpha.discount.SimpleDiscount;
import com.alpha.enums.ShowFilter;
import com.alpha.payment.PaymentMethod;
import com.alpha.plants.Flower;
import com.alpha.plants.Plant;
import com.alpha.plants.Tree;

import java.util.*;


public class Store {

    private List<Plant> inventoryOfPlants;
    private List<FlowerCompositionDecorator> readyFlowerCompositions;
    private List<Order> soldProducts = new ArrayList<>();
    private Set<Customer> customers = new LinkedHashSet<>();
    private Integer balance;

    public Store(List<Plant> inventoryOfPlants, List<FlowerCompositionDecorator> readyFlowerCompositions) {
        this.inventoryOfPlants = inventoryOfPlants;
        this.readyFlowerCompositions = readyFlowerCompositions;
        this.balance = 0;
    }


    public List<Plant> getPlants() {
        return inventoryOfPlants;
    }

    public List<FlowerCompositionDecorator> getReadyFlowerCompositions() {
        return readyFlowerCompositions;
    }

    public List<Order> getSoldProducts() {
        return soldProducts;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public List<Plant> getPlants(ShowFilter showFilter) {
        List<Plant> plants = new ArrayList<>();
        for (Plant plant : inventoryOfPlants) {
            if (showFilter.equals(ShowFilter.NATIVE)) {
                if (!plant.isNative()) {
                    continue;
                }
            }
            if (showFilter.equals(ShowFilter.OVERSEA)) {
                if (plant.isNative()) {
                    continue;
                }
            }
            plants.add(plant);
        }
        return plants;
    }

    public List<Flower> getFlowers(ShowFilter showFilter) {
        List<Flower> flowers = new ArrayList<>();
        List<Plant> plants = getPlants(showFilter);
        for (Plant plant : plants) {
            if (plant instanceof Flower) {
                flowers.add((Flower) plant);
            }
        }
        return flowers;
    }

    public List<Tree> getTrees(ShowFilter showFilter) {
        List<Tree> trees = new ArrayList<>();
        List<Plant> plants = getPlants(showFilter);
        for (Plant plant : plants) {
            if (plant instanceof Tree) {
                trees.add((Tree) plant);
            }
        }
        return trees;
    }

    LinkedHashSet<String> getAvailableFlowerTypes() {
        LinkedHashSet<String> flowerTypes = new LinkedHashSet<>();
        for (Plant plant : getPlants()) {
            if (plant instanceof Flower) {
                flowerTypes.add(((Flower) plant).getFlowerType());
            }
        }
        return flowerTypes;
    }

    public List<Flower> getFlowersByType(String flowerType) {
        List<Flower> flowers = new ArrayList<>();
        List<Plant> plants = getPlants();
        for (Plant plant : plants) {
            if (plant instanceof Flower) {
                if (((Flower) plant).getFlowerType().equals(flowerType)) {
                    flowers.add((Flower) plant);
                }
            }
        }
        return flowers;
    }


    public Integer getBalance() {
        return balance;
    }

    public Customer getCustomer(int index) {
        if (index < 0) {
            return null;
        }
        Iterator<Customer> customerIterator = customers.iterator();
        for (int i = 0; i < index; i++) {
            customerIterator.next();
        }
        return customerIterator.next();
    }

    FlowerComposition createFlowerComposition(List<Flower> flowers) {
        List<Flower> flowers1 = new ArrayList<>(flowers);
        for (Plant plant : inventoryOfPlants) {
            if (!(plant instanceof Flower)) {
                continue;
            }
            if (flowers1.contains(plant)) {
                flowers1.remove(plant);
            }
        }
        if (flowers1.size() == 0) {
            return new FlowerComposition(flowers);
        }
        throw new RuntimeException("There\'s no such flowers");
    }

    void buyFlowerComposition(FlowerCompositionDecorator flowerCompositionDecorator, Customer customer, Delivery deliveryMethod, PaymentMethod paymentMethod) {
        FlowerComposition flowerBouquet = createFlowerComposition(flowerCompositionDecorator.getFlowerComposition().getFlowers());
        Order order = new Order(flowerCompositionDecorator, deliveryMethod, paymentMethod);
        transaction(order);
        for (Flower flower : flowerBouquet.getFlowers()) {
            inventoryOfPlants.remove(flower);
        }
        soldProducts.add(order);
        customer.getBoughtProducts().add(order);
    }


    void transaction(Order order) {
        int price = calculatePrice(order);
        this.balance += price;
        order.getPaymentMethod().pay(price);
    }

    int calculatePrice(Order order) {
        int discount = new SimpleDiscount().calculateDiscount(order);
        return order.calculatePrice() - discount;
    }

    void buy(Priceable priceable, Customer customer, Delivery deliveryMethod, PaymentMethod paymentMethod) {
        if (priceable instanceof FlowerCompositionDecorator) {
            if (!readyFlowerCompositions.contains(priceable)) {
                throw new RuntimeException("There\'s no such flower composition");
            }
        } else if (priceable instanceof Plant) {
            if (!inventoryOfPlants.contains(priceable)) {
                throw new RuntimeException("There\'s no such plant");
            }
        } else {
            throw new RuntimeException("The there is no such suitable purchase method here");
        }
        Order order = new Order(priceable, deliveryMethod, paymentMethod);
        transaction(order);

        if (priceable instanceof FlowerCompositionDecorator) {
            readyFlowerCompositions.remove(priceable);
        }
        if (priceable instanceof Plant) {
            inventoryOfPlants.remove(priceable);
        }
        soldProducts.add(order);
        customer.getBoughtProducts().add(order);
    }


}


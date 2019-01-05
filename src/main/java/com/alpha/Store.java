package com.alpha;


import com.alpha.decorations.FlowerComposition;
import com.alpha.decorations.FlowerCompositionDecorator;

import com.alpha.enums.BuyMethod;
import com.alpha.enums.DeliveryMethod;
import com.alpha.enums.ShowFilter;
import com.alpha.plants.Flower;
import com.alpha.plants.Plant;
import com.alpha.plants.Tree;

import java.util.*;


public class Store {

    private List<Plant> inventoryOfPlants;
    private List<FlowerComposition> readyFlowerDecorations;
    private List<Order> soldProducts = new ArrayList<>();
    private String country;
    private Set<Customer> customers = new LinkedHashSet<>();
    private Integer balance;

    public Store(List<Plant> inventoryOfPlants, List<FlowerComposition> readyFlowerDecorations, String country) {
        this.inventoryOfPlants = inventoryOfPlants;
        this.readyFlowerDecorations = readyFlowerDecorations;
        this.country = country;
        this.balance = 0;
    }


    //getters
    public List<Plant> getPlants() {
        return inventoryOfPlants;
    }

    public List<FlowerComposition> getReadyFlowerDecorations() {
        return readyFlowerDecorations;
    }

    public List<Order> getSoldProducts() {
        return soldProducts;
    }

    public String getCountry() {
        return country;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public List<Plant> getPlants(ShowFilter showFilter) {
        List<Plant> plants = new ArrayList<>();
        for (Plant plant : inventoryOfPlants) {
            if (showFilter.equals(ShowFilter.NATIVE)) {
                if (!isNative(plant)) {
                    continue;
                }
            }
            if (showFilter.equals(ShowFilter.OVERSEA)) {
                if (isNative(plant)) {
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




    void buy(Priceable priceable, Customer customer, DeliveryMethod deliveryMethod, BuyMethod buyMethod) {
        if (priceable instanceof FlowerCompositionDecorator) {
            if (!readyFlowerDecorations.contains(priceable)) {
                throw new RuntimeException("There\'s no such flower decoration");
            }
        }
        else if (priceable instanceof Plant) {
            if (!inventoryOfPlants.contains(priceable)) {
                throw new RuntimeException("There\'s no such plant");
            }
        }
        else {
            throw new RuntimeException("The there is no such suitable purchase method here");
        }
        Order order = new Order(priceable, deliveryMethod, buyMethod);
        if (order.calculatePrice() > customer.getBalance()) {
            throw new RuntimeException("You don\'t have enough money");
        }
        this.balance += order.calculatePrice();
        customer.setBalance(customer.getBalance() - order.calculatePrice());

        if (priceable instanceof FlowerCompositionDecorator) {
            readyFlowerDecorations.remove(priceable);
        }
        if (priceable instanceof Plant) {
            inventoryOfPlants.remove(priceable);
        }
        soldProducts.add(order);
        customer.getBoughtProducts().add(order);
    }

    private boolean isNative(Plant plant) {
        return plant.isNative();
    }

}


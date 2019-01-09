package com.alpha;

import com.alpha.decorations.*;
import com.alpha.delivery.*;
import com.alpha.enums.*;
import com.alpha.payment.Cash;
import com.alpha.payment.PaymentMethod;
import com.alpha.plants.*;
import com.alpha.random.StoreRandomizer;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class StoreTest {
    Store store;
    List<Plant> plants;
    List<FlowerCompositionDecorator> flowerDecorations;
    Customer customer;

    @Before
    public void setUp() {

        plants = new ArrayList<>();
        flowerDecorations = new ArrayList<>();

        store = new StoreRandomizer(47).nextStore(10, 10, 10);//new Store(new ArrayList<Plant>(plants), new ArrayList<FlowerDecoration>(flowerDecorations), "Ukraine");
        customer = new Customer("first", "last", 1,
                new ArrayList<PaymentMethod>() {{
                    add(new Cash(3000));
                }});
        store.getCustomers().add(customer);
        plants = new ArrayList<>(store.getPlants());
        flowerDecorations = new ArrayList<>(store.getReadyFlowerCompositions());
    }

    @Test
    public void testBuyAllPlants() {
        for (Plant plant : plants) {
            store.buy(plant, customer, new UkrPoshta(), customer.getPaymentMethods().get(0));
        }
        assertTrue(store.getPlants().isEmpty());
    }

    @Test
    public void testBuyAllFlowerDecorations() {

        for (FlowerCompositionDecorator flowerDecoration : flowerDecorations) {
            store.buy(flowerDecoration, customer, new UkrPoshta(), customer.getPaymentMethods().get(0));
        }
        assertTrue(store.getReadyFlowerCompositions().isEmpty());
    }

    @Test
    public void testBuyNotSuitableType() {
        boolean wasThrown = false;
        try {
            store.buy(new Priceable() {
                @Override
                public int calculatePrice() {
                    return 0;
                }
            }, customer, new UkrPoshta(), customer.getPaymentMethods().get(0));
        } catch (RuntimeException e) {
            wasThrown = true;
        }
        assertTrue(wasThrown);
    }

    @Test
    public void testCreateFlowerBouquet() {
        List<Flower> flowers = store.getFlowers(ShowFilter.NATIVE);
        WrapperType wrapperType = WrapperType.PAPER;
        FlowerComposition flowerBouquet = store.createFlowerComposition(flowers);
        assertEquals(flowerBouquet.getFlowers(), flowers);
    }

    @Test
    public void testCreateAndBuyFlowerBouquet() {
        List<Flower> flowers = store.getFlowers(ShowFilter.NATIVE);
        WrapperType wrapperType = WrapperType.PAPER;
        FlowerComposition flowerComposition = store.createFlowerComposition(flowers);
        FlowerCompositionDecorator flowerCompositionDecorator = new Wrapper(flowerComposition, WrapperType.CARDBOARD);
        store.buyFlowerComposition(flowerCompositionDecorator, customer, new UkrPoshta(), customer.getPaymentMethods().get(0));
        assertEquals(store.getSoldProducts().get(0).getPriceable(), flowerCompositionDecorator);
    }

}
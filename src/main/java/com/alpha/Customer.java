package com.alpha;


import com.alpha.payment.Cash;
import com.alpha.payment.PaymentMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private String firstName;
    private String lastName;
    private List<PaymentMethod> paymentMethods;
    private List<Order> boughtProducts = new ArrayList<>();
    private final int id;
    public Customer(String firstName, String lastName, int id, List<PaymentMethod> paymentMethods) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.paymentMethods = new ArrayList<>();
        this.paymentMethods.add(new Cash(0));
        addAll(paymentMethods);

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }



    public int getId() {
        return id;
    }

    public List<Order> getBoughtProducts() {
        return boughtProducts;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }
    void addAll(List<PaymentMethod> paymentMethods){
        for(PaymentMethod paymentMethod:paymentMethods){
            if(paymentMethod instanceof Cash){
                this.findCash().replenishBalance(paymentMethod.getBalance());
            }
            else {
                this.paymentMethods.add(paymentMethod);
            }
        }
    }
    PaymentMethod findCash(){
        for(PaymentMethod paymentMethod:paymentMethods){
            if(paymentMethod instanceof Cash){
                return paymentMethod;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", paymentMethods=" + paymentMethods +
                ", boughtProducts=" + boughtProducts +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getId() == customer.getId() &&
                Objects.equals(getFirstName(), customer.getFirstName()) &&
                Objects.equals(getLastName(), customer.getLastName()) &&
                Objects.equals(getPaymentMethods(), customer.getPaymentMethods()) &&
                Objects.equals(getBoughtProducts(), customer.getBoughtProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getPaymentMethods(), getBoughtProducts(), getId());
    }
}

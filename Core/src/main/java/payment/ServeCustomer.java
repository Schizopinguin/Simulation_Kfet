package main.java.payment;

import classes.Customer;

import main.java.Event;
import main.java.WaitingList;
import main.java.control.Scheduler;


public class ServeCustomer extends Event {

    private Customer customer;

    public ServeCustomer(Customer customer, int startingTime){
        super(startingTime);
        this.customer = customer;
    }

    public void run(){
        System.out.println("ServeCustomer");

        int nbOrder = customer.getOrder().getNbPizza() +
                customer.getOrder().getPicard() +
                customer.getOrder().getChocolate() +
                customer.getOrder().getCoffee() +
                customer.getOrder().getRamen();
        if( nbOrder == 0){
            customer.setDepartureTime(Scheduler.getInstance().getCurrentTime());
            WaitingList.getInstance().getPostOrder().remove(customer);
        }

    }
}
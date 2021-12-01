package core.pizza;

import core.*;
import core.control.scheduler;
import core.payment.serveCustomer;

public class servePizza extends Event {

    private Device oven;
    private Customer customer;

    public servePizza(Customer customer, Device oven, int startingTime){
        super(startingTime);
        this.oven = oven;
        this.customer = customer;
    }

    public void run(){
        oven.setFree(true);
        WaitingList.getInstance().searchPizza(customer);
        scheduler.getInstance().addEvent(new serveCustomer(customer, getStartingTime()));
    }

}

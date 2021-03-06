package main.java.ramen;

import classes.Customer;

import main.java.Device;
import main.java.Event;
import main.java.Kfetier;
import main.java.WaitingList;
import main.java.control.ControllerDevices;
import main.java.control.ControllerHR;
import main.java.control.Scheduler;
import main.java.payment.PreparationOrder;


public class StartBoilingWater extends Event {

    private static Boolean isCold = true;
    private final Customer customer;
    private static int lastBoiling = 0;

    public StartBoilingWater(Customer customer, int startingTime) {
        super(startingTime);
        this.customer = customer;
    }

    public static void setIsCold(boolean cold) {
        isCold = cold;
    }

    public static void setLastBoiling(int lastBoiling) {
        StartBoilingWater.lastBoiling = lastBoiling;
    }

    @Override
    public void run() {

        //L'eau repasse froide toute les 10 minutes
        if (Scheduler.getInstance().getCurrentTime() > lastBoiling + 900) {
            isCold = true;
        }

        if (isCold) {
            if (ControllerDevices.getInstance().getFreeDevices().get("Kettle") > 0) {
                int position = ControllerDevices.getInstance().whichKettle();
                Device kettle = ControllerDevices.getInstance().getKettle().get(position);
                Scheduler.getInstance().addEvent(new EndBoilingWater(customer, kettle, getStartingTime() + 180));
                customer.getOrder().setRamen(customer.getOrder().getRamen() - 1);
                WaitingList.getInstance().searchGlobal(customer);
            } else {       //Pas de bouilloire libre
                Scheduler.getInstance().addEvent(new PreparationOrder(customer, Scheduler.getInstance().getCurrentTime() + 60));
            }
        } else {            //L'eau a déjà chauffé donc on peut servir directement
            Scheduler.getInstance().addEvent(new ServeRamen(customer, getStartingTime() + 1));
            customer.getOrder().setRamen(customer.getOrder().getRamen() - 1);
        }

    }
}

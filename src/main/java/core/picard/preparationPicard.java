package core.picard;

import core.Customer;
import core.Device;
import core.Event;
import core.Kfetier;
import core.control.ControllerDevices;
import core.control.ControllerHR;
import core.control.scheduler;

public class preparationPicard extends Event {
    private Customer customer;

    public preparationPicard(Customer customer, int startingTime){
        super(startingTime);
        this.customer = customer;
    }

    @Override
    public void run() {
        int time = 30; //le temps que met cet event à se réaliser

        //On récupere le Cuisinier et le four qui vont mettre la pizza à cuire
        int position = ControllerHR.getInstance().whichKfetier();
        Kfetier kfetier = ControllerHR.getInstance().getKfetiers().get(position);
        position = ControllerDevices.getInstance().whichMicrowave();
        Device microwave = ControllerDevices.getInstance().getMicrowave().get(position);

        //On change le nb de picard dans la commande
        customer.getOrder().setPicard(customer.getOrder().getPicard() - 1);

        //On set le temps à attendre
        time += scheduler.getInstance().getCurrentTime();

        //On ajoute au scheduler
        scheduler.getInstance().addEvent(new cookingPicard(customer, kfetier, microwave, time));

    }
}


package generator;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Customer implements Serializable {

    private  double probaKfetier = 0.06;
    private static int cptPayement = 3, fastLydiaPayement = 7, cashPayement = 15, slowLydiaPayement = 20;
    private static ArrayList<Integer> payement = new ArrayList<Integer>(Stream.of(cptPayement, fastLydiaPayement, cashPayement,slowLydiaPayement).collect(Collectors.toList()));

    private Boolean kfetier;
    private Order order;
    private int paymentDuration; //Cpt = 3s, Lydia rapide = 7s, Liquide = 15s, Lydia lent = 20s
    private int arrivalTime;

    public Customer(double proba,int fast,int cash, int slow, int cpt), int ArrivalTime{
        probaKfetier=proba;
        fastLydiaPayement=fast;
        cashPayement=cash;
        slowLydiaPayement=slow;
        cptPayement=cpt;
        Random r = new Random();

        if(r.nextDouble() > probaKfetier){
            kfetier = false;
        } else kfetier = true;

        if (kfetier){
            paymentDuration = cptPayement;
        } else {
            paymentDuration = payement.get(r.nextInt(payement.size()));
        }
        order = new Order();

        //TODO: Initialiser arrivalTime
    }

    public Boolean getKfetier() {
        return kfetier;
    }

    public Order getOrder() {
        return order;
    }
}

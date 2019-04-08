package ristorante;

import java.util.ArrayList;
import java.util.List;

public class Ordine implements Comparable{

    private String identificatore;

    private Cliente cliente;
    private List<Piatto> piatti;

    public Ordine(Cliente cliente, String identificatore){
        this.cliente = cliente;
        this.identificatore = identificatore;
        piatti = new ArrayList<>();
    }

    public Ordine(Cliente cliente, List<Piatto> piatti){
        this.cliente = cliente;
        this.piatti = piatti;
    }

    public void addPiatto(Piatto p){
        piatti.add(p);
    }

    public double getPrezzo(){
        return piatti.stream().map(p -> p.getPrezzo()).reduce(0.0, (a, b) -> a + b);
    }

    public void chiudi(){
        cliente.paga();
    }

    @Override
    public int compareTo(Object o) {
        if(o == this) {
            return 0;
        }
        if(!(o instanceof Ordine)){
            return 1;
        }
        if(this.identificatore == ((Ordine) o).identificatore){
            return 0;
        }
        return 1;
    }
}

package concessionario;

public class AutomobileNuova extends Automobile {

    private String modello;
    private double prezzo;

    public AutomobileNuova(String modello, double prezzo){
        this.modello = modello;
        this.prezzo = prezzo;
    }

    public double getPrezzo(){
        return prezzo;
    }

    @Override
    public String toString() {
        return "Modello: " + modello + " ";
    }
}

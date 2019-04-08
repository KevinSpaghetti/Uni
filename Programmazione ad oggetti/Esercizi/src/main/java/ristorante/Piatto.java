package ristorante;

public class Piatto {

    private String nome;
    private double prezzo;

    public Piatto(String nome, double prezzo){
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public double getPrezzo(){
        return this.prezzo;
    }


}

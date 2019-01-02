package agenzia;

public class Domanda {

    protected Contattabile richiedente;
    protected int prezzoOfferto;

    public Domanda(Contattabile richiedente, int offerta){
        this.richiedente = richiedente;
        this.prezzoOfferto = offerta;
    }


}

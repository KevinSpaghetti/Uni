package ristorante;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LibroPrenotazioni {

    private List<Prenotazione> prenotazioni;
    private List<Tavolo> tavoli;

    public LibroPrenotazioni(List<Tavolo> tavoli){
        prenotazioni = new ArrayList<>();
        this.tavoli = tavoli;
    }

    public void addPrenotazione(Prenotazione p){
        prenotazioni.add(p);
    }

    public List<Tavolo> tavoliLiberi(LocalDateTime da, LocalDateTime a){
        List<Tavolo> tavoliLiberi = new ArrayList<>();
        //Per ogni tavolo vedo se il tavolo è in una prenotazione
        //e se i tempi della prenotazione e quelli forniti si intersecano
        return tavoliLiberi;
    }

}

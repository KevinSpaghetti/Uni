package ristorante;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){
        Piatto piatto = new Piatto("Patate", 5.0);
        Tavolo tavolo = new Tavolo("1", 4);
        Cliente marco = new Cliente("AAAKSJ74AB2");

        LocalDateTime da = LocalDateTime.now();
        LocalDateTime a = da.plusHours(1);
        Prenotazione prenotazione = new Prenotazione(marco, tavolo, da, a);

        List<Tavolo> tavoli = new ArrayList<>();
        tavoli.add(tavolo);

        LibroPrenotazioni libro = new LibroPrenotazioni(tavoli);
        libro.addPrenotazione(prenotazione);

        LocalDateTime tempoInizioRicerca = LocalDateTime.now().plusDays(1);
        LocalDateTime tempoFineRicerca = tempoInizioRicerca.plusHours(1);

        List<Tavolo> tavoliLiberi = libro.tavoliLiberi(tempoInizioRicerca, tempoFineRicerca);

        Ordine o = new Ordine(marco, "AAA");
        o.addPiatto(piatto);
        o.chiudi();

    }

}

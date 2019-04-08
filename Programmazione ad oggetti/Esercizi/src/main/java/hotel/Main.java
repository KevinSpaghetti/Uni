package hotel;

import java.time.LocalDate;

public class Main {

    public static void Main(String[] args){

        Prenotabile sala = new SalaRistorante("Sala Ristorante 1", 20);
        Prenotabile ristorante = new SalaConferenza("Sala Ristorante 2", 30);

        Cliente azienda = new Azienda("99938273645");

        LocalDate dataIniziale = LocalDate.now();
        LocalDate dataFinale = dataIniziale.plusDays(1);

        Prenotazione p = new PrenotazionePeriodo(sala, azienda, dataIniziale, dataFinale);

    }

}

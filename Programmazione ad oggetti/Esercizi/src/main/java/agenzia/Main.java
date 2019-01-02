package agenzia;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args){

        Ufficio ufficio = new Ufficio();

        Contattabile offerente = new Offerente("Peter", "Smith", "3829174825");
        Abitazione abitazione = new Appartamento.AppartamentoBuilder("Via Dante 44", "Roma", 15)
                .conPiani(2)
                .conDataCostruzione(new Date())
                .conDataUltimaRistrutturazione(new Date())
                .build();

        Offerta offerta = new Offerta(offerente, abitazione, new BigDecimal(170000));
        ufficio.addOfferta(offerta);

        System.out.println(offerta);
        Predicate<Abitazione> duePiano = (a) -> a.piani == 2;
        //Filtro per richiedere che l'abitazione sia un appartamento e che soddisfi altri filtri
        Predicate<Abitazione> isAppartamento = (a) -> {
            if(a instanceof Appartamento){
                Appartamento app = (Appartamento) a;
                if(app.piani == 2 && app.nAppartamento == 15){
                    return true;
                }
            }
            return false;
        };

        try {
            System.out.println("Prezzo Medio (2 Piani): " + ufficio.prezzoMedioPerFiltro(duePiano));
            System.out.println("Prezzo Medio (Appartamento numero 15 con 2 Piani): " + ufficio.prezzoMedioPerFiltro(isAppartamento));
        }catch(NoResultsException e){
            System.out.println(e.userErrorMessage());
            System.err.println(e.developerErrorMessage());
        }

        try {
            System.out.println("Abitazioni per filtro: \n" + ufficio.abitazioniPerFiltro(duePiano));
        }catch(NoResultsException e){
            System.out.println(e.userErrorMessage());
            System.err.println(e.developerErrorMessage());
        }

        Contattabile agenteImmobiliare = new AgenteImmobiliare("Jhon", "Wong", "0284561264");
        Contattabile richiedente = new Richiedente("Mario", "Rossi", "92816320");

        LocalDate data = LocalDate.now();
        //Aggiungo la visita all'offerta
        if(offerta.isFreeForVisita(data)){
            System.out.println(data + " è libera per una visita");
        }

        if(offerta.isFreeForVisita(data)){
            offerta.prenotaVisita(agenteImmobiliare, richiedente, data);
        }

        if(!offerta.isFreeForVisita(data)){
            System.out.println(data + " non è libera per una visita");
        }

        System.out.println(offerta);
    }

}

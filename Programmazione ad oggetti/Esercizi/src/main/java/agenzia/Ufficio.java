package agenzia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Ufficio {

    protected List<Offerta> offerte;

    public Ufficio(){
        offerte = new ArrayList<>();
    }

    public void addOfferta(Offerta offerta){
        offerte.add(offerta);
    }

    private List<Offerta> offertePerFiltro(Predicate<Abitazione> filtro){
        List<Offerta> risultati = offerte.stream().filter(
                    offerta -> filtro.test(offerta.abitazione)
        ).collect(Collectors.toList());
        return risultati;
    }

    public BigDecimal prezzoMedioPerFiltro(Predicate<Abitazione> filtro) throws NoResultsException {
        List<Offerta> risultati = offertePerFiltro(filtro);
        if(risultati.size() == 0) {
            throw new NoResultsException("Nessun risultato trovato usando i filtri");
        }
        BigDecimal prezzoMedio = new BigDecimal(0);
        prezzoMedio = risultati.stream().map(o -> o.prezzo).reduce(new BigDecimal(0), (a, b) -> a.add(b));
        prezzoMedio.divide(new BigDecimal(risultati.size()));
        return prezzoMedio;
    }

    public List<Abitazione> abitazioniPerFiltro(Predicate<Abitazione> filtro) throws NoResultsException{
        List<Abitazione> risultati = offertePerFiltro(filtro).stream().map(o -> o.abitazione).collect(Collectors.toList());
        if(risultati.size() == 0) {
            throw new NoResultsException("Nessun risultato trovato usando i filtri");
        }
        return risultati;
    }


}

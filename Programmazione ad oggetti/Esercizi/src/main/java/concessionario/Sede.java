package concessionario;

import agenzia.NoResultsException;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Sede {

    protected List<Automobile> inVendita;
    protected List<Automobile> ordinate;

    protected List<TestDrive> testDrive;

    public void prenotaTestDrive(Automobile auto, LocalDate data){
        testDrive.add(new TestDrive(auto, data));
    }

    public void prenotaAcquisto(Automobile auto, double sconto){
        double prezzo = auto.getPrezzo() * (sconto/100);
        ordinate.add(auto);
    }

    //Il filtro per automobili usate in un range
    public List<Automobile> usatePerFiltro(Predicate<Automobile> filtro) throws NoResultsException {
        List<Automobile> risultati = inVendita.stream().filter(a -> a instanceof AutomobileUsata).filter(a -> filtro.test(a)).collect(Collectors.toList());
        if(risultati.size() == 0){
            throw new NoResultsException("Nessun Risultato");
        }
        return risultati;
    }

    public static void main(String[] args){
        Automobile a = new AutomobileConBatteria(new AutomobileNuova("S", 90000), AutomobileConBatteria.Batteria.V75);
        System.out.println(a);
        System.out.println(a.getPrezzo());
    }

}

package ambulatorio;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Intervento {

    protected List<Dottore> dottori;

    protected LocalDateTime dataInizio;
    protected LocalDateTime dataFine;

    public Intervento(LocalDateTime dataInizio, LocalDateTime dataFine){
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public abstract double getPrezzo();

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("INIZIO: " + dataInizio + " - ");
        result.append("FINE: " + dataFine);
        return result.toString();
    }
}

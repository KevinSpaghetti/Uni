package ambulatorio;

import java.util.ArrayList;
import java.util.List;

public class PianoTerapeutico {

    Persona paziente;
    List<Intervento> interventi;

    public PianoTerapeutico(Persona paziente){
        this.paziente = paziente;
        this.interventi = new ArrayList<>();
    }

    public PianoTerapeutico aggiungiIntervento(Intervento i){
        interventi.add(i);
        return this;
    }

    public double costo(){
        return interventi.stream().map( a -> a.getPrezzo() ).reduce(0.0, (a, b) -> a + b);

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("INTERVENTI: ");
        for (Intervento i: interventi) {
            builder.append("\n " + i);
        }
        return builder.toString();
    }

}

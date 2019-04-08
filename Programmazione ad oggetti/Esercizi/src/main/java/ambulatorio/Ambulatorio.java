package ambulatorio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ambulatorio implements Contattabile{

    private String nTelefonico;
    private List<Sala> sale;
    private List<PianoTerapeutico> pianiTerapeutici;

    public Ambulatorio(String nTelefonico){
        this.nTelefonico = nTelefonico;
        sale = new ArrayList<>();
        pianiTerapeutici = new ArrayList<>();
    }

    public void addPianoTerapeutico(PianoTerapeutico p){
        pianiTerapeutici.add(p);
    }

    public Ambulatorio addSala(Sala s){
        sale.add(s);
        return this;
    }
    
    public boolean checkSala(Sala s, LocalDateTime dataInizio, LocalDateTime dataFine){

    }

    @Override
    public String contatto() {
        return nTelefonico;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Numero Telefonico: " + nTelefonico + "\n");
        result.append("Sale: ");
        for (Sala s: sale) {
            result.append("\n\t" + s.getIdentificatore());
        }
        return result.toString();
    }
}

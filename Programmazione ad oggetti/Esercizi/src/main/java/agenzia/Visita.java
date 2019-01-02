package agenzia;

import java.time.LocalDate;
import java.util.Date;

public class Visita {

    protected Contattabile agenteImmobiliare;
    protected Contattabile visitatore;
    protected LocalDate dataVisita;

    public Visita(Contattabile agenteImmobiliare, Contattabile visitatore, LocalDate data){
        this.agenteImmobiliare = agenteImmobiliare;
        this.visitatore = visitatore;
        this.dataVisita = data;
    }

    public LocalDate getDataVisita(){
        return dataVisita;
    }

    @Override
    public String toString() {
        StringBuilder ris = new StringBuilder();
        ris.append("Agente: " + agenteImmobiliare.getNome() + " " + agenteImmobiliare.getCognome() + " : ");
        ris.append("Visitatore: " + visitatore.getNome() + " " + visitatore.getCognome() + " : ");
        ris.append("Data Visita: " + dataVisita.toString() + "");
        return ris.toString();
    }
}

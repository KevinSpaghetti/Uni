package agenzia;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Offerta {

    protected Abitazione abitazione;
    protected Contattabile propietario;
    protected BigDecimal prezzo;
    protected ArrayList<Visita> visite;
    protected ArrayList<Domanda> domande;

    public Offerta(Contattabile propietario, Abitazione abitazione, BigDecimal prezzo){
        this.abitazione = abitazione;
        this.propietario = propietario;
        this.prezzo = prezzo;
        visite = new ArrayList<>();
        domande = new ArrayList<>();
    }

    public void addDomanda(Domanda domanda){
        domande.add(domanda);
    }

    public boolean isFreeForVisita(LocalDate giorno){
        for (Visita element : visite){
            if(element.getDataVisita().getYear() == giorno.getYear() &&
                    element.getDataVisita().getMonth() == giorno.getMonth() &&
                    element.getDataVisita().getDayOfYear() == giorno.getDayOfYear()){
                return false;
            }
        }
        return true;
    }

    public void prenotaVisita(Contattabile agenteImmobiliare, Contattabile visitatore, LocalDate giorno){
        if(isFreeForVisita(giorno)){
            Visita visita = new Visita(agenteImmobiliare, visitatore, giorno);
            visite.add(visita);
        }
    }

    @Override
    public String toString() {
        StringBuilder descrizioneOfferta = new StringBuilder();
        descrizioneOfferta.append("Propietario: " + propietario.getNome() + " " + propietario.getCognome() + "\n");
        descrizioneOfferta.append("Abitazione: \n" + abitazione.toString() + "\n");
        descrizioneOfferta.append("Prezzo: " + prezzo + "€ \n");
        descrizioneOfferta.append("Visite Prenotate:" + visite);
        return descrizioneOfferta.toString();
    }
}

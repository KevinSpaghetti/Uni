package hotel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PrenotazioneEvento implements Prenotazione{

    private Prenotabile prenotazione;
    private Cliente cliente;
    private Evento evento;

    public PrenotazioneEvento(Prenotabile prenotazione, Cliente cliente, Evento evento){
        this.prenotazione = prenotazione;
        this.cliente = cliente;
        this.evento = evento;
    }


    @Override
    public LocalDateTime inizio() {
        return evento.inizio();
    }

    @Override
    public LocalDateTime fine() {
        return evento.fine();
    }
}

package hotel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PrenotazionePeriodo implements Prenotazione {

    private Prenotabile prenotazione;
    private Cliente cliente;
    private LocalDate dataIniziale;
    private LocalDate dataFinale;

    public PrenotazionePeriodo(Prenotabile prenotazione, Cliente cliente,LocalDate dataIniziale, LocalDate dataFinale){
        this.prenotazione = prenotazione;
        this.cliente = cliente;
        this.dataIniziale = dataIniziale;
        this.dataFinale = dataFinale;
    }

    @Override
    public LocalDateTime inizio() {
        return dataIniziale.atStartOfDay();
    }

    @Override
    public LocalDateTime fine() {
        return dataFinale.plusDays(1).atStartOfDay();
    }
}

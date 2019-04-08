package ristorante;

import java.time.LocalDateTime;

public class Prenotazione {

    private Cliente cliente;
    private Tavolo tavolo;
    private LocalDateTime da;
    private LocalDateTime a;

    public Prenotazione(Cliente cliente, Tavolo tavolo, LocalDateTime da, LocalDateTime a){
        this.cliente = cliente;
        this.tavolo = tavolo;
        this.da = da;
        this.a = a;
    }



}

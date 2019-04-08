package concessionario;

import java.time.LocalDateTime;

public class AutomobileUsata extends Automobile {

    private LocalDateTime dataImmatricolazione;
    private int kmPercorsi;

    public double getPrezzo(){
        return prezzo;
    }
}
